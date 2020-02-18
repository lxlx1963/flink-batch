package batch.face;

import com.alibaba.fastjson.JSON;
import com.xinchao.common.constant.DateConstant;
import com.xinchao.common.util.DateUtils;
import com.xinchao.flink.constant.ApplicationPropertiesContstant;
import com.xinchao.flink.constant.WaringMessageConstant;
import com.xinchao.flink.model.FaceData;
import com.xinchao.flink.model.dto.FaceDataDateDTO;
import com.xinchao.flink.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCOutputFormat;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * FaceDataDateJob
 *
 * 日期维度统计人数、人次
 *
 * @author dxy
 * @date 2019/11/22 15:48
 */
public class FaceDataDateJob {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(FaceDataDateJob.class);

	public static void main(String[] args) {
		try {
			// ExecutionEnvironment
			final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
			BatchTableEnvironment batchTableEnvironment = BatchTableEnvironment.create(env);

			// 文件路径
			String filePath = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_FACE_FILE_PATH);
			if (StringUtils.isBlank(filePath)) {
				logger.error(WaringMessageConstant.FILE_PATH_IS_NULL);
				return;
			}
			// 读取文件，获得DataSource
			DataSource<String> dataSource = env.readTextFile(filePath);
			// 转化数据
			MapOperator<String, FaceData> faceDataMap = dataSource.map(faceDataStr -> JSON.parseObject(faceDataStr, FaceData.class));
			// 注册人数数据
			batchTableEnvironment.registerDataSet("FaceData", faceDataMap);

			// 计算人次
			String peopleTimeSql = "SELECT COUNT(id) as peopleTime FROM FaceData";
			// 人次Table
			Table peopleTimeTable = batchTableEnvironment.sqlQuery(peopleTimeSql);

			// 计算人数
			String sql = "SELECT COUNT(temp.visitorId) as peopleNumber " +
					"FROM (SELECT visitorId FROM FaceData GROUP BY visitorId) temp ";
			// 人数Table
			Table peopleNumberTable = batchTableEnvironment.sqlQuery(sql);

			Table peopleNumber = peopleNumberTable.select("peopleNumber");
			DataSet<Long> longDataSet = batchTableEnvironment.toDataSet(peopleNumber, Long.class);
			List<Long> peopleNumberList = longDataSet.collect();
			Long number = peopleNumberList.get(0);


			DataSet<Long> peopleTimeSet = batchTableEnvironment.toDataSet(peopleTimeTable.select("peopleTime"), Long.class);
			Long time = peopleTimeSet.collect().get(0);

			// 昨天的日期
			String yearMonthDay = DateUtils.getPastDay(1, DateConstant.DATE_YEAR_MONTH_DAY);

			// 插入SQL
			String insertSql = "INSERT INTO frp_face_data_date(date, people_num, people_time, add_time) VALUES (?,?,?,?)";

			// 转为为Row
			MapOperator<FaceDataDateDTO, Row> resultMap = batchTableEnvironment.toDataSet(peopleNumberTable, FaceDataDateDTO.class)
					.map(dto -> {
						Row row = new Row(4);
						row.setField(0, yearMonthDay);
						row.setField(1, number);
						row.setField(2, dto.getPeopleTime());
						long currentTime = DateUtils.getCurrentTimestamp().getTime();
						row.setField(3, currentTime);
						return row;
					});

			// 数据库连接信息
			String url = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MYSQL_URL);
			String username = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MYSQL_USERNAME);
			String password = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MYSQL_PASSWORD);
			String driverClassName = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MYSQL_DRIVER_CLASS_NAME);

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
			env.execute("日期维度统计人数、人次");
		} catch (Exception e) {
			logger.error("日期维度统计人数、人次", e);
		}
	}
}
