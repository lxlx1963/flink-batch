package constant;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * 业务常量类
 *
 * @author dxy
 * @date 2018/6/21 14:41
 */
public final class BusinessConstant {
	/**
	 * 私有化构造器
	 */
	private BusinessConstant() {

	}

	/**
	 * 状态-启用
	 */
	public static final int STATUS_ENABLE = 0;
	/**
	 * 状态-禁用
	 */
	public static final int STATUS_DISABLE = 1;
	/**
	 * 操作日志状态-成功
	 */
	public static final int OPERATION_LONG_OPERATION_STATUS_SUCCESS = 1;
	/**
	 * 操作日志状态-失败
	 */
	public static final int OPERATION_LONG_OPERATION_STATUS_FAIL = 0;
	/**
	 * 不是超级管理员用户
	 */
	public static final int SUPER_ADMIN_USER_NO = 1;
	/**
	 * 不是超级管理员角色
	 */
	public static final int SUPER_ADMIN_ROLE_NO = 1;

	/**
	 * 首页轮询时间(单位：毫秒): 10分钟
	 */
	public static final Integer INDEX_PAGE_COUNT_ROLL_TIMER = 600000;

	/**
	 * Float-0.0F
	 */
	public static final float FLOAT_ZERO = 0.0F;
	/**
	 * 前10条
	 */
	public static final int TOP_SIZE = 10;
	/**
	 * 时间对应Map（在实际要求中，0点到5点的数据都归结到6点）
	 */
	private static final Map<String, String> TIME_TEMP_MAP = new HashMap<>();

	static {
		TIME_TEMP_MAP.put("6", "6:00");
		TIME_TEMP_MAP.put("7", "7:00");
		TIME_TEMP_MAP.put("8", "8:00");
		TIME_TEMP_MAP.put("9", "9:00");
		TIME_TEMP_MAP.put("10", "10:00");
		TIME_TEMP_MAP.put("11", "11:00");
		TIME_TEMP_MAP.put("12", "12:00");
		TIME_TEMP_MAP.put("13", "13:00");
		TIME_TEMP_MAP.put("14", "14:00");
		TIME_TEMP_MAP.put("15", "15:00");
		TIME_TEMP_MAP.put("16", "16:00");
		TIME_TEMP_MAP.put("17", "17:00");
		TIME_TEMP_MAP.put("18", "18:00");
		TIME_TEMP_MAP.put("19", "19:00");
		TIME_TEMP_MAP.put("20", "20:00");
		TIME_TEMP_MAP.put("21", "21:00");
		TIME_TEMP_MAP.put("22", "22:00");
		TIME_TEMP_MAP.put("23", "23:00");
	}

	/**
	 * 时间对应Map（在实际要求中，0点到5点的数据都归结到6点）
	 */
	public static final Map<String, String> TIME_MAP = ImmutableMap.copyOf(TIME_TEMP_MAP);

	/**
	 * 写字楼
	 */
	public static final String OFFICE_BUILDING = "写字楼";
	/**
	 * 商住楼
	 */
	public static final String COMMERCIAL_RESIDENTIAL_BUILDING = "商住楼";
	/**
	 * 综合体
	 */
	public static final String SYNTHESIS_BUILDING = "综合体";
	/**
	 * 中高端住宅
	 */
	public static final String MIDDLE_HIGH_END_RESIDENCE_BUILDING = "中高端住宅";
	/**
	 * 商业综合体
	 */
	public static final String COMMERCIAL_SYNTHESIS_BUILDING = "商业综合体";
	/**
	 * 6:00
	 */
	public static final String SIX_TIME = "6:00";
	/**
	 * 性别-不限
	 */
	public static final String SEX_STRING_UNLIMITED = "不限";
	/**
	 * 性别列表
	 */
	private static final List<String> SEX_TEMP_LIST = new ArrayList<>();

	static {
		SEX_TEMP_LIST.add("男");
		SEX_TEMP_LIST.add("女");
	}

	/**
	 * 性别-男
	 */
	public static final int SEX_MALE = 1;
	/**
	 * 性别-女
	 */
	public static final int SEX_FEMALE = 0;
	/**
	 * 性别-不限
	 */
	public static final int SEX_NO = 2;
	/**
	 * 性别-未知
	 */
	public static final int SEX_UNKNOW = 3;

	/**
	 * 性别
	 */
	public static final Map<Integer, String> SEX_MAP = ImmutableMap.of(
			SEX_MALE, "男",
			SEX_FEMALE, "女",
			SEX_NO, "不限");
	/**
	 * 性别列表
	 */
	public static final List<String> SEX_LIST = Collections.unmodifiableList(SEX_TEMP_LIST);

	/***--------------------------年龄范围（中文）-------------------------*/
	/**
	 * 19岁及以下
	 */
	public static final String UNDER_NINETEEN_CH = "19岁及以下";
	/**
	 * 20岁~25岁
	 */
	public static final String TWENTY_AND_TWENTYFIVE_CH = "20岁~25岁";
	/**
	 * 26岁~35岁
	 */
	public static final String TWENTYSIX_AND_THIRTYFIVE_CH = "26岁~35岁";
	/**
	 * 36岁~45岁
	 */
	public static final String THIRTYSIX_AND_FOURTYFIVE_CH = "36岁~45岁";
	/**
	 * 46岁~55岁
	 */
	public static final String FOURTYSIXE_AND_FIFTYFIVE_CH = "46岁~55岁";
	/**
	 * 56岁及以上
	 */
	public static final String ABOVE_FIFTYFIVE_CH = "56岁及以上";
	/**
	 * 年龄范围列表
	 */
	private static final List<String> AGE_RANGE_TEMP_LIST = new ArrayList<>();

	static {
		AGE_RANGE_TEMP_LIST.add(UNDER_NINETEEN_CH);
		AGE_RANGE_TEMP_LIST.add(TWENTY_AND_TWENTYFIVE_CH);
		AGE_RANGE_TEMP_LIST.add(TWENTYSIX_AND_THIRTYFIVE_CH);
		AGE_RANGE_TEMP_LIST.add(THIRTYSIX_AND_FOURTYFIVE_CH);
		AGE_RANGE_TEMP_LIST.add(FOURTYSIXE_AND_FIFTYFIVE_CH);
		AGE_RANGE_TEMP_LIST.add(ABOVE_FIFTYFIVE_CH);
	}

	public static final List<String> AGE_RANGE_LIST = Collections.unmodifiableList(AGE_RANGE_TEMP_LIST);

	/**
	 * 19岁及以下
	 */
	public static final String UNDER_NINETEEN = "19岁及以下";
	/**
	 * 20-25岁
	 */
	public static final String TWENTY_AND_TWENTYFIVE = "20-25岁";
	/**
	 * 26-35岁
	 */
	public static final String TWENTYSIX_AND_THIRTYFIVE = "26-35岁";
	/**
	 * 36-45岁
	 */
	public static final String THIRTYSIX_AND_FOURTYFIVE = "36-45岁";
	/**
	 * 446-55岁
	 */
	public static final String FOURTYSIXE_AND_FIFTYFIVE = "46-55岁";
	/**
	 * 56岁及以上
	 */
	public static final String ABOVE_FIFTYFIVE = "56岁及以上";
	/**
	 * 年龄段列表
	 */
	private static final List<String> AGE_TEMP_LIST = new ArrayList<>();

	static {
		AGE_TEMP_LIST.add(UNDER_NINETEEN);
		AGE_TEMP_LIST.add(TWENTY_AND_TWENTYFIVE);
		AGE_TEMP_LIST.add(TWENTYSIX_AND_THIRTYFIVE);
		AGE_TEMP_LIST.add(THIRTYSIX_AND_FOURTYFIVE);
		AGE_TEMP_LIST.add(FOURTYSIXE_AND_FIFTYFIVE);
		AGE_TEMP_LIST.add(ABOVE_FIFTYFIVE);
	}

	public static final List<String> AGE_LIST = Collections.unmodifiableList(AGE_TEMP_LIST);

	/**
	 * 年龄Map
	 */
	private static final Map<String, String> AGE_TEMP_MAP = new HashMap<>();

	static {
		AGE_TEMP_MAP.put(UNDER_NINETEEN_CH, UNDER_NINETEEN);
		AGE_TEMP_MAP.put(TWENTY_AND_TWENTYFIVE_CH, TWENTY_AND_TWENTYFIVE);
		AGE_TEMP_MAP.put(TWENTYSIX_AND_THIRTYFIVE_CH, TWENTYSIX_AND_THIRTYFIVE);
		AGE_TEMP_MAP.put(THIRTYSIX_AND_FOURTYFIVE_CH, THIRTYSIX_AND_FOURTYFIVE);
		AGE_TEMP_MAP.put(FOURTYSIXE_AND_FIFTYFIVE_CH, FOURTYSIXE_AND_FIFTYFIVE);
		AGE_TEMP_MAP.put(ABOVE_FIFTYFIVE_CH, ABOVE_FIFTYFIVE);
	}

	/**
	 * 年龄Map
	 */
	public static final Map<String, String> AGE_MAP = ImmutableMap.copyOf(AGE_TEMP_MAP);

	/***--------------------------年龄范围（英语）-------------------------*/
	/**
	 * UNDER_NINETEEN
	 */
	public static final String UNDER_NINETEEN_EN = "UNDER_NINETEEN";
	/**
	 * TWENTY_AND_TWENTYFIVE
	 */
	public static final String TWENTY_AND_TWENTYFIVE_EN = "TWENTY_AND_TWENTYFIVE";
	/**
	 * TWENTYSIX_AND_THIRTYFIVE
	 */
	public static final String TWENTYSIX_AND_THIRTYFIVE_EN = "TWENTYSIX_AND_THIRTYFIVE";
	/**
	 * HIRTYSIX_AND_FOURTYFIVE
	 */
	public static final String THIRTYSIX_AND_FOURTYFIVE_EN = "HIRTYSIX_AND_FOURTYFIVE";
	/**
	 * FOURTYSIXE_AND_FIFTYFIVE
	 */
	public static final String FOURTYSIXE_AND_FIFTYFIVE_EN = "FOURTYSIXE_AND_FIFTYFIVE";
	/**
	 * ABOVE_FIFTYFIVE
	 */
	public static final String ABOVE_FIFTYFIVE_EN = "ABOVE_FIFTYFIVE";
	/**
	 * 此字典主要配置中文年龄范围对应英文年龄范围
	 */

	private static final Map<String, String> AGE_RANGE_TEMP_MAP = new HashMap<>();

	static {
		AGE_RANGE_TEMP_MAP.put(UNDER_NINETEEN_CH, UNDER_NINETEEN_EN);
		AGE_RANGE_TEMP_MAP.put(TWENTY_AND_TWENTYFIVE_CH, TWENTY_AND_TWENTYFIVE_EN);
		AGE_RANGE_TEMP_MAP.put(TWENTYSIX_AND_THIRTYFIVE_CH, TWENTYSIX_AND_THIRTYFIVE_EN);
		AGE_RANGE_TEMP_MAP.put(THIRTYSIX_AND_FOURTYFIVE_CH, THIRTYSIX_AND_FOURTYFIVE_EN);
		AGE_RANGE_TEMP_MAP.put(FOURTYSIXE_AND_FIFTYFIVE_CH, FOURTYSIXE_AND_FIFTYFIVE_EN);
		AGE_RANGE_TEMP_MAP.put(ABOVE_FIFTYFIVE_CH, ABOVE_FIFTYFIVE_EN);
	}

	/**
	 * 由于ImmutableMap.of()方法只能接受5对key,value
	 */
	public static final Map<String, String> AGE_RANGE_MAP = ImmutableMap.copyOf(AGE_RANGE_TEMP_MAP);

	/**
	 * 年龄-未知
	 */
	public static final String AGE_STRING_UNKNOW = "未知";
	/**
	 * 时间后缀（:00）
	 */
	public static final String TIME_COLON_ZERO_ZERO = ":00";
	/**
	 * 0.00
	 */
	public static final String ZERO_POINT_ZERO_ZERO = "0.00";
	/**
	 * 字符串-23
	 */
	public static final String STRING_TWENTY_THREE = "23";

	/**
	 * 时间点列表（从早上6点到晚上23点）
	 */
	private static final List<String> TIME_POINT_TEMP_LIST = new ArrayList<>();

	static {
		TIME_POINT_TEMP_LIST.add("06");
		TIME_POINT_TEMP_LIST.add("07");
		TIME_POINT_TEMP_LIST.add("08");
		TIME_POINT_TEMP_LIST.add("09");
		TIME_POINT_TEMP_LIST.add("10");
		TIME_POINT_TEMP_LIST.add("11");
		TIME_POINT_TEMP_LIST.add("12");
		TIME_POINT_TEMP_LIST.add("13");
		TIME_POINT_TEMP_LIST.add("14");
		TIME_POINT_TEMP_LIST.add("15");
		TIME_POINT_TEMP_LIST.add("16");
		TIME_POINT_TEMP_LIST.add("17");
		TIME_POINT_TEMP_LIST.add("18");
		TIME_POINT_TEMP_LIST.add("19");
		TIME_POINT_TEMP_LIST.add("20");
		TIME_POINT_TEMP_LIST.add("21");
		TIME_POINT_TEMP_LIST.add("22");
		TIME_POINT_TEMP_LIST.add("23");
	}

	/**
	 * 时间点列表（从早上6点到晚上23点）
	 */
	public static final List<String> TIME_POINT_LIST = Collections.unmodifiableList(TIME_POINT_TEMP_LIST);

	/**
	 * 市
	 */
	public static final String CHINA_SHI = "市";
	/**
	 * 6
	 */
	public static final String STRING_SIX = "6";
	/**
	 * -1
	 */
	public static final int MINUS_ONE = -1;
	/**
	 * 住宅类型列表
	 */
	private static final List<String> RESIDENCE_TYPE_TEMP_LIST = Lists.newLinkedList();

	static {
		RESIDENCE_TYPE_TEMP_LIST.add(MIDDLE_HIGH_END_RESIDENCE_BUILDING);
		RESIDENCE_TYPE_TEMP_LIST.add(COMMERCIAL_RESIDENTIAL_BUILDING);
		RESIDENCE_TYPE_TEMP_LIST.add(SYNTHESIS_BUILDING);
		RESIDENCE_TYPE_TEMP_LIST.add(OFFICE_BUILDING);
	}

	/**
	 * 住宅类型列表
	 */
	public static final List<String> RESIDENCE_TYPE_LIST = Collections.unmodifiableList(RESIDENCE_TYPE_TEMP_LIST);
	/**
	 * 19
	 */
	public static final int NINETEEN = 19;
	/**
	 * 20
	 */
	public static final int TWENTY = 20;
	/**
	 * 25
	 */
	public static final int TWENTY_FIVE = 25;
	/**
	 * 26
	 */
	public static final int TWENTY_SIX = 26;
	/**
	 * 35
	 */
	public static final int THIRTY_FIVE = 35;
	/**
	 * 36
	 */
	public static final int THIRTY_SIX = 36;
	/**
	 * 45
	 */
	public static final int FOURTY_FIVE = 45;
	/**
	 * 46
	 */
	public static final int FOURTY_SIX = 46;
	/**
	 * 55
	 */
	public static final int FIFTY_FIVE = 55;
	/**
	 * 是否是平宣广告-是
	 */
	public static final int DECLARE_GOODS_ADVERTISEMENT_YES = 0;
	/**
	 * 是否是平宣广告-否
	 */
	public static final int DECLARE_GOODS_ADVERTISEMENT_NO = 1;
	/**
	 * 广告状态-待审核
	 */
	public static final int ADVERTISEMENT_STATUS_AUDITING = 0;
	/**
	 * 广告状态-上线
	 */
	public static final int ADVERTISEMENT_STATUS_ONLINE = 1;
	/**
	 * 广告状态-下线
	 */
	public static final int ADVERTISEMENT_STATUS_OFFLINE = 2;
	/**
	 * 广告状态
	 */
	public static final Map<Integer, String> ADVERTISEMENT_STATUS_MAP = ImmutableMap.of(
			ADVERTISEMENT_STATUS_AUDITING, "待审核",
			ADVERTISEMENT_STATUS_ONLINE, "上线",
			ADVERTISEMENT_STATUS_OFFLINE, "下线");

	/**
	 * 0.3
	 */
	public static double ZERO_POINT_THREE = 0.3;
	/**
	 * 5
	 */
	public static int FIVE = 5;

	/**
	 * 批量保存条数
	 */
	public static final int BATCH_SAVING_SIZE  = 50000;
	/**
	 * "0"
	 */
	public static final String ZERO_STRING = "0";
	/**
	 * "0"
	 */
	public static final String ZERO_ZERO_STRING = "00";
	/**
	 * 5秒及以下
	 */
	public static final String LESS_THAN_FIVE_SECOND = "5秒以下";
	/**
	 * 5-8秒
	 */
	public static final String FIVE_AND_EIGHT_SECOND = "5-8秒";
	/**
	 * 8-12秒
	 */
	public static final String EIGHT_AND_TWELVE_SECOND = "8-12秒";
	/**
	 * 12秒以上
	 */
	public static final String MORE_THAN_TWELVE_SECOND = "12秒以上";
	/**
	 * 5000
	 */
	public static final long FIVE_THOUSAND = 5000L;
	/**
	 * 8000
	 */
	public static final long EIGHT_THOUSAND = 8000L;
	/**
	 * 12000
	 */
	public static final long TWELVE_THOUSAND = 12000L;
	/**
	 * 岁
	 */
	public static final String AGE_CH = "岁";
	/**
	 * 秒段列表
	 */
	public static final List<String> SECOND_RANGE_LIST = ImmutableList.of(
			LESS_THAN_FIVE_SECOND,
			FIVE_AND_EIGHT_SECOND,
			EIGHT_AND_TWELVE_SECOND,
			MORE_THAN_TWELVE_SECOND);


}
