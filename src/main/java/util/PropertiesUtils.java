package util;

import com.google.common.io.Resources;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件类
 *
 * @author dxy
 * @date 2019/11/22 15:15
 */
public class PropertiesUtils {
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	/**
	 * 默认配置文件名称
	 */
	private static final String PROPERTIES_FILE_NAME = "application.properties";
	/**
	 * 激活配置文件名称
	 */
	private static final String ACTIVE_PROPERTIES_FILE_NAME = "active-properties-file-name";
	/**
	 * 错误提示信息
	 */
	private static final String INIT_ERROR_MESSAGE = "PropertiesUtils 加载配置文件失败";
	/**
	 * 激活配置文件名称为空
	 */
	private static final String ACTIVE_PROPERTIES_FILE_NAME_IS_NULL = "激活配置文件名称为空";
	/**
	 * Properties
	 */
	private static Properties ACTIVE_PROPERTIES = new Properties();

	static {
		try {
			// 获取默认配置文件内容
			InputStream inputStream = Resources.getResource(PROPERTIES_FILE_NAME).openStream();
			Properties applicationProperties = new Properties();
			applicationProperties.load(inputStream);
			// 获取激活配置文件全名
			String activePropertiesFileName = applicationProperties.getProperty(ACTIVE_PROPERTIES_FILE_NAME);
			if (StringUtils.isBlank(activePropertiesFileName)) {
				throw new NullPointerException(ACTIVE_PROPERTIES_FILE_NAME_IS_NULL);
			}
			// 加载激活文件
			InputStream profilesInputStream = Resources.getResource(activePropertiesFileName).openStream();
			ACTIVE_PROPERTIES.load(profilesInputStream);
		} catch (IOException e) {
			logger.error(INIT_ERROR_MESSAGE, e);
		}
	}

	/**
	 * 获取Properties对象
	 *
	 * @return PropertiesUtils
	 */
	public static Properties getProperties() {
		return ACTIVE_PROPERTIES;
	}

	/**
	 * 获取key对应的值
	 *
	 * @param key 键
	 * @return String
	 */
	public static String getValueByKey(String key) {
		return ACTIVE_PROPERTIES.getProperty(key);
	}

}
