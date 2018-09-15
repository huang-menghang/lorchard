package com.ysdevelop.lorchard.mq.key;

import com.ysdevelop.lorchard.common.redis.BasePrefix;

public class MerchantMessageKey extends BasePrefix {

	public MerchantMessageKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	
	public static MerchantMessageKey messageKey = new MerchantMessageKey(0, "merchantMessage");

}
