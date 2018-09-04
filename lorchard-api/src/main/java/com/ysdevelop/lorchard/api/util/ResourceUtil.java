package com.ysdevelop.lorchard.api.util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lochard.common.utils
 * 
 * @Description 资源文件配置读取工具类
 *
 * @Date 2018年8月29日
 *
 * @Version
 *
 */

public class ResourceUtil {

	private static ResourceUtil RESOURCE_UTIL = null;
	/** 默认配置文件路径 **/
	private static final String DEFAULT_PATH = "lorchard-common";

	private static ResourceBundle BUNDLE = java.util.ResourceBundle.getBundle(DEFAULT_PATH);

	private ResourceUtil() {

	}

	/**
	 * 工厂实现配置文件读取
	 *
	 * @param properties
	 *            配置为文件路径参数
	 * @return ResourceUtil 工具类
	 */
	public static ResourceUtil getInstance(String properties) {
		if (RESOURCE_UTIL == null) {
			RESOURCE_UTIL = new ResourceUtil();
		}
		if (properties != null) {
			BUNDLE = java.util.ResourceBundle.getBundle(properties);
		}
		return RESOURCE_UTIL;
	}

	/**
	 * 默认路径,工厂实现配置文件读取
	 * 
	 * @return ResourceUtil
	 */
	public static ResourceUtil getInstance() {
		if (RESOURCE_UTIL == null) {
			RESOURCE_UTIL = new ResourceUtil();
		}
		return RESOURCE_UTIL;
	}

	/**
	 * 主要功能:获取配置文件参数 注意事项:无
	 *
	 * @param name
	 *            参数名称
	 * @return 参数名称对应值
	 */
	public static String getConfigByName(String name) {
		String value = "";
		try {
			value = new String(BUNDLE.getString(name).getBytes("iso8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 主要功能:取得分隔符 注意事项:无
	 *
	 * @return 分隔符
	 */
	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

}
