package model.dto;

/**
 * 人脸数据（时间）
 *
 * @author dxy
 * @date 2019/12/5 16:15
 */
public class FaceDataTimeDTO {
	/**
	 * 时间
	 */
	private String dateTime;
	/**
	 * 人数
	 */
	private Long peopleNumber;
	/**
	 * 人次
	 */
	private Long peopleTime;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
		return "FaceDataTimeDTO{" +
				"dateTime='" + dateTime + '\'' +
				", peopleNumber=" + peopleNumber +
				", peopleTime=" + peopleTime +
				'}';
	}
}
