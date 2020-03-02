package batch.face;

import com.alibaba.fastjson.JSON;
import constant.ApplicationPropertiesContstant;
import constant.DateConstant;
import constant.WaringMessageConstant;
import model.FaceData;
import model.dto.FaceDataTimeDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCOutputFormat;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DateUtils;
import util.PropertiesUtils;

/**
 * FaceDataSexJob
 *
 * 小时维度统计人数、人次
 *
 * @author dxy
 * @date 2019/11/22 15:48
 */
public class FaceDataTimeJob {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(FaceDataTimeJob.class);

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

			batchTableEnvironment.registerDataSet("FaceData", faceDataMap);

			// 计算人次
			String peopleTimeSql = "SELECT gender,COUNT(id) as peopleTime FROM FaceData GROUP BY dateTime";
			// 人次Table
			Table peopleTimeTable = batchTableEnvironment.sqlQuery(peopleTimeSql);

			// 计算人数
			String sql = "SELECT temp.dateTime as tempDateTime,COUNT(temp.visitorId) as peopleNumber " +
					"FROM (SELECT dateTime,visitorId as visitorId FROM FaceData GROUP BY dateTime,visitorId) temp " +
					"GROUP BY temp.dateTime";
			// 人数Table
			Table peopleNumberTable = batchTableEnvironment.sqlQuery(sql);

			// 人数人次Table关联
			Table joinTable = peopleTimeTable.leftOuterJoin(peopleNumberTable).where("dateTime = tempDateTime")
					.select("dateTime, peopleNumber, peopleTime");

			// 昨天的日期
			String yearMonthDay = DateUtils.getPastDay(1, DateConstant.DATE_YEAR_MONTH_DAY);

			// 插入SQL
			String insertSql = "INSERT INTO frp_face_data_time(date, time, people_num, people_time, add_time) VALUES (?,?,?,?,?)";

			// 转为为Row
			MapOperator<FaceDataTimeDTO, Row> resultMap = batchTableEnvironment.toDataSet(joinTable, FaceDataTimeDTO.class)
					.map(faceDataTimeDTO -> {
						Row row = new Row(5);
						row.setField(0, yearMonthDay);
						// 日期时间
						String dateTime = faceDataTimeDTO.getDateTime();
						// 小时
						String time = dateTime.substring(8, dateTime.length() - 1);
						row.setField(1, time);
						row.setField(2, faceDataTimeDTO.getPeopleNumber());
						row.setField(3, faceDataTimeDTO.getPeopleTime());
						long currentTime = DateUtils.getCurrentTimestamp().getTime();
						row.setField(4, currentTime);
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
			env.execute("性别维度统计人数、人次");
		} catch (Exception e) {
			logger.error("性别维度统计人数、人次", e);
		}
	}
}
