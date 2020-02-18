package model.dto;

/**
 * 监播数据设备维度
 *
 * @author dxy
 * @date 2019/11/25 17:11
 */
public class MonitorDataDeviceDTO {
	/**
	 * 广告名称
	 */
	private String deviceNumber;
	/**
	 * 日期小时
	 */
	private String dateTime;
	/**
	 * 年龄段
	 */
	private String age;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 观看人次
	 */
	private Integer watchSum;
	/**
	 * 触达人次
	 */
	private Integer touchSum;
	/**
	 * 观看时长
	 */
	private Integer playDurationSum;
	/**
	 * 曝光次数
	 */
	private Double exposuresSum;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 小区
	 */
	private String community;
	/**
	 * 住宅类型
	 */
	private String residenceType;

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getWatchSum() {
		return watchSum;
	}

	public void setWatchSum(Integer watchSum) {
		this.watchSum = watchSum;
	}

	public Integer getTouchSum() {
		return touchSum;
	}

	public void setTouchSum(Integer touchSum) {
		this.touchSum = touchSum;
	}

	public Integer getPlayDurationSum() {
		return playDurationSum;
	}

	public void setPlayDurationSum(Integer playDurationSum) {
		this.playDurationSum = playDurationSum;
	}

	public Double getExposuresSum() {
		return exposuresSum;
	}

	public void setExposuresSum(Double exposuresSum) {
		this.exposuresSum = exposuresSum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	@Override
	public String toString() {
		return "MonitorDataDeviceDTO{" +
				"deviceNumber='" + deviceNumber + '\'' +
				", dateTime='" + dateTime + '\'' +
				", age='" + age + '\'' +
				", sex=" + sex +
				", watchSum=" + watchSum +
				", touchSum=" + touchSum +
				", playDurationSum=" + playDurationSum +
				", exposuresSum=" + exposuresSum +
				", city='" + city + '\'' +
				", community='" + community + '\'' +
				", residenceType='" + residenceType + '\'' +
				'}';
	}
}
