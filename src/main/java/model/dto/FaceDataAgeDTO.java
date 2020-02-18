package model.dto;

/**
 * 人脸数据（年龄段）
 *
 * @author dxy
 * @date 2019/3/1 20:22
 */
public class FaceDataAgeDTO {
	/**
	 * 年龄
	 */
	private String ageRange;
	/**
	 * 人数
	 */
	private Long peopleNumber;
	/**
	 * 人次
	 */
	private Long peopleTime;

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
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
		return "FaceDataAgeDTO{" +
				"ageRange='" + ageRange + '\'' +
				", peopleNumber=" + peopleNumber +
				", peopleTime=" + peopleTime +
				'}';
	}
}
