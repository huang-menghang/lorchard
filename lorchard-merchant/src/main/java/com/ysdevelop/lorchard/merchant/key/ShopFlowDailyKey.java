package com.ysdevelop.lorchard.merchant.key;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
import com.ysdevelop.lorchard.common.redis.BasePrefix;

public class ShopFlowDailyKey extends BasePrefix {

	public ShopFlowDailyKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static ShopFlowDailyKey flowDailyKey = new ShopFlowDailyKey(-1, "shopFlowDaily");

}
