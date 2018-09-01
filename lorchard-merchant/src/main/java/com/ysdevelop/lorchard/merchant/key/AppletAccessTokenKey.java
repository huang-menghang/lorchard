package com.ysdevelop.lorchard.merchant.key;

import com.ysdevelop.lorchard.common.redis.BasePrefix;

public class AppletAccessTokenKey extends BasePrefix {

	public AppletAccessTokenKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static AppletAccessTokenKey accessTokenKey = new AppletAccessTokenKey(7000, "accessToken");

}
