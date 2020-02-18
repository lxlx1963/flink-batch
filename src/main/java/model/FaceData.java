package model;

/**
 * FaceData
 *
 * @author dxy
 * @date 2019/11/5 10:11
 */
public class FaceData {
	/**
	 * 年龄段
	 */
	private String ageRange;
	/**
	 * 进入时间
	 */
	private Long enterTime;
	/**
	 * 日期时间
	 */
	private String dateTime;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * id
	 */
	private Long id;
	/**
	 * visitorId
	 */
	private Long visitorId;
	/**
	 * 设备编码
	 */
	private String deviceCode;

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public Long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Long visitorId) {
		this.visitorId = visitorId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Override
	public String toString() {
		return "FaceData{" +
				"ageRange='" + ageRange + '\'' +
				", enterTime=" + enterTime +
				", dateTime='" + dateTime + '\'' +
				", gender='" + gender + '\'' +
				", id=" + id +
				", visitorId=" + visitorId +
				", deviceCode='" + deviceCode + '\'' +
				'}';
	}
}
