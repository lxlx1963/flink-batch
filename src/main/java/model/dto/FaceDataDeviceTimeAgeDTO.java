package model.dto;

/**
 * 人脸数据（设备小时年龄）
 *
 * @author dxy
 * @date 2019/12/5 16:15
 */
public class FaceDataDeviceTimeAgeDTO {
	/**
	 * 设备编码
	 */
	private String deviceCode;
	/**
	 * 日期小时
	 */
	private String dateTime;
	/**
	 * 年龄段
	 */
	private String ageRange;
	/**
	 * 住宅类型
	 */
	private String residenceType;
	/**
	 * 人数
	 */
	private Long peopleNumber;
	/**
	 * 人次
	 */
	private Long peopleTime;

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	public Long getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(Long peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public Long getPeopleTime() {
		return peopleTime;
	}

	public void setPeopleTime(Long peopleTime) {
		this.peopleTime = peopleTime;
	}

	@Override
	public String toString() {
		return "FaceDataDeviceTimeAgeDTO{" +
				"deviceCode='" + deviceCode + '\'' +
				", dateTime='" + dateTime + '\'' +
				", ageRange='" + ageRange + '\'' +
				", residenceType='" + residenceType + '\'' +
				", peopleNumber=" + peopleNumber +
				", peopleTime=" + peopleTime +
				'}';
	}
}
