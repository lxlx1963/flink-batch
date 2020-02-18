package model.dto;

/**
 * 人脸数据（设备日期）
 *
 * @author dxy
 * @date 2019/12/5 16:15
 */
public class FaceDataDeviceDateDTO {
	/**
	 * 设备编码
	 */
	private String deviceCode;
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
		return "FaceDataDeviceDateDTO{" +
				"deviceCode='" + deviceCode + '\'' +
				", residenceType='" + residenceType + '\'' +
				", peopleNumber=" + peopleNumber +
				", peopleTime=" + peopleTime +
				'}';
	}
}
