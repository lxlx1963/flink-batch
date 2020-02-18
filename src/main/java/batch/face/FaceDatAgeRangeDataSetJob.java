package batch.face;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import com.xinchao.common.constant.DateConstant;
import com.xinchao.common.util.DateUtils;
import com.xinchao.flink.model.FaceData;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCOutputFormat;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.types.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * FaceDatAgeRangeDataSetJob
 *
 * 年龄段维度统计人数、人次
 *
 * @author dxy
 * @date 2019/11/22 15:48
 */
public class FaceDatAgeRangeDataSetJob {
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(FaceDatAgeRangeDataSetJob.class);

	public static void main(String[] args) {
		try {
			// ExecutionEnvironment
			final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
			// 文件路径
			String filePath = "hdfs://dev-base-bigdata-bj1-01:8020/flume/face/";
			// 读取文件，获得DataSource
			DataSource<String> dataSource = env.readTextFile(filePath);
			// 转化数据
			MapOperator<String, FaceData> faceDataMap = dataSource.map(faceDataStr -> JSON.parseObject(faceDataStr, FaceData.class));
			// 昨天的日期
			String yearMonthDay = DateUtils.getPastDay(1, DateConstant.DATE_YEAR_MONTH_DAY);
			// 数据库连接信息
//			String url = "jdbc:mysql://192.168.237.128:3306/xc_frp_dashboard_flink_test?characterEncoding=UTF-8&autoReconnect=true&useSSL=false&allowMultiQueries=true&useAffectedRows=true";
			String url = "jdbc:mysql://10.10.0.235:3306/xc_frp_dashboard_flink?characterEncoding=UTF-8&autoReconnect=true&useSSL=false&allowMultiQueries=true&useAffectedRows=true";
			String username = "root";
//			String password = "Caidao123";
			String password = "CS<ty$!XC0TY";
			String driverClassName = "com.mysql.jdbc.Driver";
			// 插入SQL
			String insertSql = "INSERT INTO frp_face_data_age(date, age, people_num, people_time, add_time) VALUES (?,?,?,?,?)";
			// 以“年龄”分组，统计人次、人次（实现count功能）
			faceDataMap.groupBy("ageRange")
					.reduceGroup((GroupReduceFunction<FaceData, Tuple3<String, Integer, Integer>>) (in, out) -> {
						String key = "";
						// 人次
						int count = 0;
						// visitorId列表
						Set<Long> visitorIdSet = Sets.newHashSet();
						for (FaceData faceData : in) {
							key = faceData.getAgeRange();
							Long visitorId = faceData.getVisitorId();
							visitorIdSet.add(visitorId);
							++count;
						}
						out.collect(new Tuple3<>(key, visitorIdSet.size(), count));
					})
					.returns(TypeInformation.of(new TypeHint<Tuple3<String, Integer, Integer>>() {}))
					.map(tuple3 -> {
						Row row = new Row(5);
						row.setField(0, yearMonthDay);
						row.setField(1, tuple3.f0);
						row.setField(2, tuple3.f1);
						row.setField(3, tuple3.f2);
						long currentTime = DateUtils.getCurrentTimestamp().getTime();
						row.setField(4, currentTime);
						return row;
					}).output(JDBCOutputFormat.buildJDBCOutputFormat()
					.setDBUrl(url)
					.setUsername(username)
					.setPassword(password)
					.setDrivername(driverClassName)
					.setQuery(insertSql)
					.setBatchInterval(5000)
					.finish());

			// 执行任务
			env.execute("年龄段维度统计人数、人次");
		} catch (Exception e) {
			logger.error("FaceDatAgeRangeJob", e);
		}
	}
}
