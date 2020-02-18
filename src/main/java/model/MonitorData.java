package model;

/**
 * 监播数据
 *
 * @author dxy
 * @date 2019/11/25 14:21
 */
public class MonitorData {
	/**
	 * 广告名称
	 */
	private String advertisementName;
	/**
	 * 设备类型
	 */
	private Integer deviceType;
	/**
	 * 日期小时
	 */
	private String dateTime;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 观看人次
	 */
	private Integer watchNumber;
	/**
	 * 机器码
	 */
	private String deviceNumber;
	/**
	 * 播放时长
	 */
	private Double duration;
	/**
	 * 触达人次
	 */
	private Integer touchNumber;
	/**
	 * 观看时长
	 */
	private Integer playDuration;
	/**
	 * 屏幕类型
	 */
	private Integer screenType;
	/**
	 * 播放时间
	 */
	private Long playTime;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 曝光次数
	 */
	private Double exposuresNumber;
	/**
	 * 年龄段
	 */
	private String age;

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getWatchNumber() {
		return watchNumber;
	}

	public void setWatchNumber(Integer watchNumber) {
		this.watchNumber = watchNumber;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}

	public Integer getTouchNumber() {
		return touchNumber;
	}

	public void setTouchNumber(Integer touchNumber) {
		this.touchNumber = touchNumber;
	}

	public Integer getPlayDuration() {
		return playDuration;
	}

	public void setPlayDuration(Integer playDuration) {
		this.playDuration = playDuration;
	}

	public Integer getScreenType() {
		return screenType;
	}

	public void setScreenType(Integer screenType) {
		this.screenType = screenType;
	}

	public Long getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Long playTime) {
		this.playTime = playTime;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getExposuresNumber() {
		return exposuresNumber;
	}

	public void setExposuresNumber(Double exposuresNumber) {
		this.exposuresNumber = exposuresNumber;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "MonitorData{" +
				"advertisementName='" + advertisementName + '\'' +
				", deviceType=" + deviceType +
				", dateTime='" + dateTime + '\'' +
				", sex=" + sex +
				", watchNumber=" + watchNumber +
				", deviceNumber='" + deviceNumber + '\'' +
				", duration=" + duration +
				", touchNumber=" + touchNumber +
				", playDuration=" + playDuration +
				", screenType=" + screenType +
				", playTime=" + playTime +
				", projectName='" + projectName + '\'' +
				", exposuresNumber=" + exposuresNumber +
				", age='" + age + '\'' +
				'}';
	}
}
