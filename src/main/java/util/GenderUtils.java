package util;

import com.xinchao.common.constant.ElasticsearchConstant;

/**
 * 性别工具类
 *
 * @author dxy
 * @date 2019/11/22 15:15
 */
public class GenderUtils {
	/**
	 * 性别转化
	 *
	 * @param gender
	 * @return
	 */
	public static String genderToSex(String gender) {
		String sex = "";
		switch (gender) {
			case ElasticsearchConstant.STRING_F:
				sex = ElasticsearchConstant.SEX_FEMALE;
				break;
			case ElasticsearchConstant.STRING_M:
				sex = ElasticsearchConstant.SEX_MALE;
				break;
			default:
				sex = ElasticsearchConstant.SEX_UNLIMITED;
				break;
		}
		return sex;
	}

}
