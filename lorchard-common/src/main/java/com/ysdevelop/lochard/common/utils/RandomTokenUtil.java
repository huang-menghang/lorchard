package com.ysdevelop.lochard.common.utils;

import java.util.UUID;

public class RandomTokenUtil {
	// 根据uuid随生成字符串
	public static String getToken() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
