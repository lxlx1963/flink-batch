package batch.monitor;

import com.alibaba.fastjson.JSON;
import constant.*;
import model.MonitorData;
import model.dto.MonitorDataAdvertisementDTO;
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
 * 广告维度清洗监播数据
 *
 * @author dxy
 * @date 2019/11/25 14:20
 */
public class MonitorDataAdvertisementJob {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(MonitorDataAdvertisementJob.class);

    public static void main(String[] args) {
        try {
            // ExecutionEnvironment
            final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
            // 创建BatchTableEnvironment
            BatchTableEnvironment batchTableEnvironment = BatchTableEnvironment.create(env);

            // 文件路径
            String filePath = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_MONITOR_FILE_PATH);
            if (StringUtils.isBlank(filePath)) {
                logger.error(WaringMessageConstant.FILE_PATH_IS_NULL);
                return;
            }

            // 读取文件，获得DataSource
            DataSource<String> dataSource = env.readTextFile(filePath);

            // 将JSON格式数据转化为MonitorData
            MapOperator<String, MonitorData> monitorDataSource = dataSource.map(jsonStr -> JSON.parseObject(jsonStr, MonitorData.class));

            // 添加过滤条件
            addCondition(monitorDataSource);

            // 注册数据集
            batchTableEnvironment.registerDataSet("MonitorData", monitorDataSource);

            // 设备Table
            Table table = getTable(batchTableEnvironment);

            // 昨天的日期
            String date = DateUtils.getPastDay(1, DateConstant.DATE_YEAR_MONTH_DAY);

            // 插入SQL
            String insertSql = "INSERT INTO frp_monitor_data_advertisement(advertisement_name, date, time, age, sex, exposures_sum, touch_sum, watch_sum, play_duration_sum, add_time) VALUES (?,?,?,?,?,?,?,?,?,?)";
            // 转为为Row
            MapOperator<MonitorDataAdvertisementDTO, Row> resultMap = batchTableEnvironment.toDataSet(table, MonitorDataAdvertisementDTO.class)
                    .map(dto -> {
                        Row row = new Row(10);
                        row.setField(0, dto.getAdvertisementName());
                        row.setField(1, date);
                        // 取dateTime的最后两位小时作为time
                        String time = dto.getDateTime().substring(8, 10);
                        row.setField(2, time);
                        String age = dto.getAge();
                        String ageStr = "";
                        //如果年龄为未知，不需要转化
                        if (BusinessConstant.AGE_STRING_UNKNOW.equals(age)) {
                            ageStr = age;
                        } else {
                            //鉴于前端需要使用"20~25岁"这种格式的数据，所以需要把"20岁~25岁"格式转化为"20~25岁"
                            ageStr = BusinessConstant.AGE_MAP.get(age);
                        }
                        row.setField(3, ageStr);
                        row.setField(4, dto.getSex());
                        row.setField(5, dto.getExposuresSum());
                        row.setField(6, dto.getTouchSum());
                        row.setField(7, dto.getWatchSum());
                        row.setField(8, dto.getPlayDurationSum());
                        // 当前时间戳
                        long currentTime = DateUtils.getCurrentTimestamp().getTime();
                        row.setField(9, currentTime);
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

            env.execute("广告维度清洗监播数据");
        } catch (Exception e) {
            logger.error("广告维度清洗监播数据", e);
        }
    }


    /**
     * 添加条件
     *
     * @param monitorDataSource MapOperator<String, MonitorData>
     */
    private static void addCondition(MapOperator<String, MonitorData> monitorDataSource) {
        //项目名称
        String projectName = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_PROJECT_NAME);
        if (StringUtils.isNotBlank(projectName)) {
            monitorDataSource.filter(monitorData -> ElasticsearchConstant.PROJECT_NAME.equals(monitorData.getProjectName()));
        }
        // 屏幕类型
        String screenType = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_SCREEN_TYPE);
        if (StringUtils.isNotBlank(screenType)) {
            monitorDataSource.filter(monitorData -> ElasticsearchConstant.SCREEN_TYPE.equals(monitorData.getScreenType()));
        }
        // 终端类型
        String deviceType = PropertiesUtils.getValueByKey(ApplicationPropertiesContstant.DASHBOARD_DEVICE_TYPE);
        if (StringUtils.isNotBlank(deviceType)) {
            monitorDataSource.filter(monitorData -> ElasticsearchConstant.DEVICE_TYPE.equals(monitorData.getDeviceType()));
        }
    }

    /**
     * 获取表
     *
     * @param batchTableEnvironment BatchTableEnvironment
     * @return Table
     */
    private static Table getTable(BatchTableEnvironment batchTableEnvironment) {
        // 查询SQL
        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append("SELECT advertisementName, dateTime, age, sex, ")
                .append("SUM(exposuresNumber) AS exposuresSum, ")
                .append("SUM(touchNumber) AS touchSum, ")
                .append("SUM(watchNumber) AS watchSum, ")
                .append("SUM(playDuration) AS playDurationSum ")
                .append("FROM MonitorData")
                .append(" GROUP BY advertisementName, dateTime, age, sex");

        return batchTableEnvironment.sqlQuery(sqlSb.toString());
    }

}
