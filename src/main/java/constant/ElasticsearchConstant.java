package constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ES常量类
 *
 * @author dxy
 * @date 2019/3/4 11:04
 */
public class ElasticsearchConstant {
	private ElasticsearchConstant() {}
	/**
	 * 按广告名称分组
	 */
	public static final String GROUP_BY_ADVERTISEMENT_NAME = "group_by_advertisement_name";
	/**
	 * 按时间分组
	 */
	public static final String GROUP_BY_DATE_TIME = "group_by_date_time";
	/**
	 * 按年龄分组
	 */
	public static final String GROUP_BY_AGE = "group_by_age";
	/**
	 * 按性别分组
	 */
	public static final String GROUP_BY_SEX = "group_by_sex";
	/**
	 * 按终端编码分组
	 */
	public static final String GROUP_BY_DEVICE_NUMBER = "group_by_device_number";
	/**
	 * 按终端编码分组
	 */
	public static final String GROUP_BY_DEVICE_CODE = "group_by_device_code";
	/**
	 * 按年龄段分组
	 */
	public static final String GROUP_BY_AGE_RANGE = "group_by_age_range";
	/**
	 * 按性别分组
	 */
	public static final String GROUP_BY_GENDER = "group_by_gender";
	/**
	 * 广告名称关键字
	 */
	public static final String ADVERTISEMENT_NAME_KEYWORD = "advertisementName.keyword";
	/**
	 * 日期时间关键字
	 */
	public static final String DATE_TIME_KEYWORD = "dateTime.keyword";
	/**
	 * 年龄关键字
	 */
	public static final String AGE_KEYWORD = "age.keyword";
	/**
	 * 性别
	 */
	public static final String SEX = "sex";
	/**
	 * 性别关键字
	 */
	public static final String GENDER_KEYWORD = "gender.keyword";
	/**
	 * 终端编码关键字
	 */
	public static final String DEVICE_NUMBER_KEYWORD = "deviceNumber.keyword";
	/**
	 * 终端编码关键字
	 */
	public static final String DEVICE_CODE_KEYWORD = "deviceCode.keyword";
	/**
	 * 年龄段关键字
	 */
	public static final String AGE_RANGE_KEYWORD = "ageRange.keyword";
	/**
	 * 曝光次数
	 */
	public static final String EXPOSURES_NUMBER = "exposuresNumber";
	/**
	 * 观看次数
	 */
	public static final String WATCH_NUMBER = "watchNumber";
	/**
	 * 触达人次
	 */
	public static final String TOUCH_NUMBER = "touchNumber";
	/**
	 * 观看时长
	 */
	public static final String VIEW_TIME = "viewTime";
	/**
	 * 播放时长
	 */
	public static final String PLAY_DURATION = "playDuration";
	/**
	 * 曝光次数总计
	 */
	public static final String EXPOSURES_SUM = "exposures_sum";
	/**
	 * 观看次数总计
	 */
	public static final String WATCH_SUM = "watch_sum";
	/**
	 * 触达人次总计
	 */
	public static final String TOUCH_SUM = "touch_sum";

	/**
	 * 观看时长总计
	 */
	public static final String VIEW_SUM = "view_sum";
	/**
	 * 播放时长总计
	 */
	public static final String PLAY_DURATION_SUM = "play_duration_sum";
	/**
	 * 性别-男
	 */
	public static final String SEX_MALE = "男";
	/**
	 * 性别-女
	 */
	public static final String SEX_FEMALE = "女";
	/**
	 * 性别-不限
	 */
	public static final String SEX_UNLIMITED = "不限";
	/**
	 * 性别-未知
	 */
	public static final String SEX_UNKNOW = "未知";
	/**
	 * 字符串-0
	 */
	public static final String STRING_ZERO = "0";
	/**
	 * 字符串-1
	 */
	public static final String STRING_ONE = "1";
	/**
	 * 字符串-2
	 */
	public static final String STRING_TWO = "2";
	/**
	 * 字符串-3
	 */
	public static final String STRING_THREE = "3";
	/**
	 * 索引前缀-face-monitor-
	 */
	public static final String FACE_MONITOR = "face-monitor-*";
	/**
	 * 2
	 */
	public static final int TWO = 2;

	/**
	 * 索引前缀-face-data-
	 */
	public static final String FACE_DATA = "face-data-*";
	/**
	 * trackId条数
	 */
	public static final String TRACK_ID_COUNT = "track_id_count";
	/**
	 * trackId
	 */
	public static final String TRACK_ID = "trackId";
	/**
	 * trackId条数
	 */
	public static final String ID_COUNT = "id_count";
	/**
	 * trackId
	 */
	public static final String ID = "id";
	/**
	 * 按人脸ID分组
	 */
	public static final String GROUP_BY_VISITOR_ID = "group_by_visitor_id";
	/**
	 * 人脸ID
	 */
	public static final String VISITOR_ID = "visitorId";

	/**
	 * STRING-M
	 */
	public static final String STRING_M = "M";
	/**
	 * STRING-F
	 */
	public static final String STRING_F = "F";

	/**
	 * 字符串-00
	 */
	public static final String STRING_ZERO_ZERO = "00";
	/**
	 * @timestamp
	 */
	public static final String AT_TIME_STAMP = "@timestamp";
	/**
	 * epoch_millis
	 */
	public static final String EPOCH_MILLIS = "epoch_millis";

	/**
	 * 按小区分组
	 */
	public static final String GROUP_BY_COMMUNITY = "group_by_community";
	/**
	 * 小区
	 */
	public static final String COMMUNITY_KEYWORD = "community.keyword";

	/**
	 * 按住宅类型分组
	 */
	public static final String GROUP_BY_RESIDENCE = "group_by_residence";

	/**
	 * 住宅类型
	 */
	public static final String RESIDENCE_KEYWORD = "residence.keyword";
	/**
	 * enterTime
	 */
	public static final String ENTER_TIME = "enterTime";
	/**
	 * 男女性别列表
	 */
	public static final List<String> SEX_LIST =  new ArrayList<>();
	static {
		SEX_LIST.add(SEX_MALE);
		SEX_LIST.add(SEX_FEMALE);
	}

	/**
	 * projectName
	 */
	public static final String PROJECT_NAME = "projectName";
	/**
	 *  adx
	 */
	public static final String PROJECT_NAME_ADX = "adx";
	/**
	 * SCREEN_TYPE
	 */
	public static final String SCREEN_TYPE = "screenType";
	/**
	 * 上屏
	 */
	public static final int SCREEN_TYPE_ABOVE = 0;
	/**
	 * 下屏
	 */
	public static final int SCREEN_TYPE_UNDER = 1;
	/**
	 * DEVICE_TYPE
	 */
	public static final String DEVICE_TYPE = "deviceType";
	/**
	 * 2代机
	 */
	public static final int DEVICE_TYPE_TWO = 2;
	/**
	 * 3代机
	 */
	public static final int DEVICE_TYPE_THREE = 3;
	/**
	 * index
	 */
	public static final String INDEX = "index";
	/**
	 * result
	 */
	public static final String RESULT = "result";
	/**
	 * 索引前缀保留数量Map
	 */
	public static final Map<String, Integer> indexPrefixSaveNumMap = new HashMap<>();
	static {
		indexPrefixSaveNumMap.put("client-api-0001-", 15);
		indexPrefixSaveNumMap.put("client-api-0002-", 15);
		indexPrefixSaveNumMap.put("client-api-0003-", 15);
		indexPrefixSaveNumMap.put("client-api-0004-", 15);
		indexPrefixSaveNumMap.put("product-asst-web-0001-", 15);
		indexPrefixSaveNumMap.put("product-asst-web-0002-", 15);
		indexPrefixSaveNumMap.put("product-mobile-api-0001-6.5.4-", 15);
		indexPrefixSaveNumMap.put("product-mobile-api-0002-6.5.4-", 15);
		indexPrefixSaveNumMap.put("product-mobile-api-0003-6.5.4-", 15);
		indexPrefixSaveNumMap.put("surveillance-to-", 10);
	}

	/**
	 * 机器码
	 */
	public static final String DEVICE_CODE = "deviceCode";

	/**
	 * 机器码
	 */
	public static final String DEVICE_NUMBER = "deviceNumber";
	/**
	 * 广告名称
	 */
	public static final String ADVERTISEMENT_NAME = "advertisementName";
	/**
	 * 播放时间
	 */
	public static final String PLAY_TIME = "playTime";
	/**
	 * secondRange
	 */
	public static final String SECOND_RANGE  = "secondRange";
	/**
	 * secondRange.keyword
	 */
	public static final String SECOND_RANGE_KEYWORD = "secondRange.keyword";
	/**
	 * group_by_second_range
	 */
	public static final String GROUP_BY_SECOND_RANGE = "group_by_second_range";
	/**
	 * ads
	 */
	public static final String ads = "ads";
	/**
	 * group_by_ads
	 */
	public static final String GROUP_BY_ADS = "group_by_ads";
	/**
	 * ads.keyword
	 */
	public static final String ADS_KEYWORD = "ads.keyword";

}
