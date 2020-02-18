package batch.face;

import com.alibaba.fastjson.JSON;
import com.xinchao.common.constant.DateConstant;
import com.xinchao.common.util.DateUtils;
import com.xinchao.flink.constant.ApplicationPropertiesContstant;
import com.xinchao.flink.constant.WaringMessageConstant;
import com.xinchao.flink.model.FaceData;
import com.xinchao.flink.model.dto.FaceDataDeviceDateDTO;
import com.xinchao.flink.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.io.jdbc.JDBCOutputFormat;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FaceDataDeviceDateJob
 *
 * 设备日期维度统计人数、人次
 *
 * @author dxy
 * @date 2019/11/22 15:48
 */
public class FaceDataDeviceDateJob {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(FaceDataDeviceDateJob.class);

	public static void main(String[] args) {
		try {
			// ExecutionEnvironment
			final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
			BatchTableEnvironment batchTableEnvironment = BatchTableEnvironment.create(env);
			// 文件路径
			String filePath = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MONITOR_FILE_PATH);
			if (StringUtils.isBlank(filePath)) {
				logger.error(WaringMessageConstant.FILE_PATH_IS_NULL);
				return;
			}
			// 读取文件，获得DataSource
			DataSource<String> dataSource = env.readTextFile(filePath);
			// 转化数据
			MapOperator<String, FaceData> faceDataMap = dataSource.map(faceDataStr -> JSON.parseObject(faceDataStr, FaceData.class));
			// 注册人脸数据
			batchTableEnvironment.registerDataSet("FaceData", faceDataMap);

			// 人次Table
			Table peopleTimeTable = getPeopleTimeTable(batchTableEnvironment);
			// 人数Table
			Table peopleNumberTable = getPeopleNumberTable(batchTableEnvironment);

			// 数据库连接信息
			String url = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MYSQL_URL);
			String username = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MYSQL_USERNAME);
			String password = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MYSQL_PASSWORD);
			String driverClassName = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MYSQL_DRIVER_CLASS_NAME);

			// 住宅类型Table
			Table residenceTypeTable = getResidenceTypeTable(env, batchTableEnvironment, url, username, password, driverClassName);

			// 人数Table、人次Table、住宅类型Table关联
			Table joinTable = peopleTimeTable.leftOuterJoin(peopleNumberTable).where("deviceCode = tempDeviceCode")
					.leftOuterJoin(residenceTypeTable).where("deviceCode = deviceNumber")
					.select("deviceCode, residenceType, peopleNumber, peopleTime");

			// 昨天的日期
			String yearMonthDay = DateUtils.getPastDay(1, DateConstant.DATE_YEAR_MONTH_DAY);
			// 插入SQL
			String insertSql = "INSERT INTO frp_face_data_device(date, device_code, residence_type, people_num, people_time, add_time) VALUES (?,?,?,?,?,?)";

			// 转为为Row
			MapOperator<FaceDataDeviceDateDTO, Row> resultMap = batchTableEnvironment.toDataSet(joinTable, FaceDataDeviceDateDTO.class)
					.map(dto -> {
						Row row = new Row(6);
						row.setField(0, yearMonthDay);
						row.setField(1, dto.getDeviceCode());
						row.setField(2, dto.getResidenceType());
						row.setField(3, dto.getPeopleNumber());
						row.setField(4, dto.getPeopleTime());
						long currentTime = DateUtils.getCurrentTimestamp().getTime();
						row.setField(5, currentTime);
						return row;
					});

			// 存入数据库
			resultMap.output(JDBCOutputFormat.buildJDBCOutputFormat()
					.setDBUrl(url)
					.setUsername(username)
					.setPassword(password)
					.setDrivername(driverClassName)
					.setQuery(insertSql)
					.setBatchInterval(5000)
					.finish());

			// 执行任务
			env.execute("设备性别维度统计人数、人次");
		} catch (Exception e) {
			logger.error("设备性别维度统计人数、人次", e);
		}
	}

	/**
	 * 获取人次Table
	 * @param batchTableEnvironment BatchTableEnvironment
	 * @return Table
	 */
	private static Table getPeopleTimeTable(BatchTableEnvironment batchTableEnvironment) {
		// 人次SQL
		String peopleTimeSql = "SELECT deviceCode, COUNT(id) as peopleTime FROM FaceData GROUP BY deviceCode";
		// 人次Table
		return batchTableEnvironment.sqlQuery(peopleTimeSql);
	}

	/**
	 * 获取人数Table
	 * @param batchTableEnvironment BatchTableEnvironment
	 * @return Table
	 */
	private static Table getPeopleNumberTable(BatchTableEnvironment batchTableEnvironment) {
		// 人数SQL
		String peopleNumberSql = "SELECT " +
				"temp.deviceCode AS tempDeviceCode," +
				"COUNT( temp.visitorId ) AS peopleNumber " +
				"FROM " +
				"( SELECT deviceCode, visitorId FROM FaceData GROUP BY deviceCode, visitorId ) temp " +
				"GROUP BY " +
				"temp.deviceCode";
		// 人数Table
		return batchTableEnvironment.sqlQuery(peopleNumberSql);
	}

	/**
	 * 获取住宅类型Table
	 *
	 * @param env                   ExecutionEnvironment
	 * @param batchTableEnvironment BatchTableEnvironment
	 * @param username              用户名
	 * @param password              面膜
	 * @param driverClassName       驱动类名
	 * @return Table
	 */
	private static Table getResidenceTypeTable(ExecutionEnvironment env, BatchTableEnvironment batchTableEnvironment, String url,
                                               String username, String password, String driverClassName) {
		// 从数据库中查询住宅类型SQL
		String residenceTypeSql = "SELECT device_number, residence_type FROM frp_device_info";
		// 从数据库中获取设备的住宅类型
		MapOperator<Row, Tuple2<Object, Object>> residenceTypeMap = env.createInput(JDBCInputFormat.buildJDBCInputFormat()
				.setDBUrl(url)
				.setUsername(username)
				.setPassword(password)
				.setDrivername(driverClassName)
				.setQuery(residenceTypeSql)
				.finish())
				.filter(row -> row != null && row.getField(0) != null)
				.map(row -> {
					Tuple2<Object, Object> tuple2 = new Tuple2<>();
					tuple2.f0 = row.getField(0);
					tuple2.f1 = row.getField(1);
					return tuple2;
				});

		// 注册住宅类型数据源
		batchTableEnvironment.registerDataSet("ResidenceType", residenceTypeMap, "deviceNumber, residenceType");
		// 查询住宅类型
		String typeSql = "SELECT deviceNumber, residenceType FROM ResidenceType";
		// 住宅类型Table
		return batchTableEnvironment.sqlQuery(typeSql);
	}
}
