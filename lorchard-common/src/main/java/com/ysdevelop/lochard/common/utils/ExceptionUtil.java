package com.ysdevelop.lochard.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lochard.common.utils
 * 
 * @Description 异常工具类
 * 
 * @Date 2018年8月10日
 * 
 * @Version
 * 
 */

public class ExceptionUtil {
	/**
	 * 返回错误信息字符串
	 * 
	 * @param ex
	 *            Exception
	 * @return 错误信息字符串
	 */
	public static String getExceptionMessage(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
	}
}
