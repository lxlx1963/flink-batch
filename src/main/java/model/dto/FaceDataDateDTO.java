package model.dto;

/**
 * 人脸数据（日期）
 *
 * @author dxy
 * @date 2019/12/5 16:15
 */
public class FaceDataDateDTO {
	/**
	 * 人数
	 */
	private Long peopleNumber;
	/**
	 * 人次
	 */
	private Long peopleTime;

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
		return "FaceDataDateDTO{" +
				"peopleNumber=" + peopleNumber +
				", peopleTime=" + peopleTime +
				'}';
	}
}
