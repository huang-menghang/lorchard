package com.ysdevelop.lochard.common.utils;

import java.util.UUID;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lochard.common.utils
 * 
 * @Description 随机Token生成类
 *
 * @Date 2018年8月29日
 *
 * @Version
 *
 */

public class RandomTokenUtil {
	/**
	 * 随机生成uuid然后生成随机token 
	 * @return
	 *
	 */
	public static String getToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
