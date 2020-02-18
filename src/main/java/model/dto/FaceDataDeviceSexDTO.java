package model.dto;

/**
 * 人脸数据（设备性别）
 *
 * @author dxy
 * @date 2019/12/5 16:15
 */
public class FaceDataDeviceSexDTO {
	/**
	 * 设备编码
	 */
	private String deviceCode;
	/**
	 * 性别
	 */
	private String gender;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
		return "FaceDataDeviceSexDTO{" +
				"deviceCode='" + deviceCode + '\'' +
				", gender='" + gender + '\'' +
				", residenceType='" + residenceType + '\'' +
				", peopleNumber=" + peopleNumber +
				", peopleTime=" + peopleTime +
				'}';
	}
}
