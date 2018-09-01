package com.ysdevelop.loarchard.merchant.key;

import com.ysdevelop.lochard.common.redis.BasePrefix;

public class AppletAccessTokenKey extends BasePrefix {

	public AppletAccessTokenKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static AppletAccessTokenKey accessTokenKey = new AppletAccessTokenKey(7000, "accessToken");

}
