package com.ysdevelop.lorchard.merchant.key;

import com.ysdevelop.lorchard.common.redis.BasePrefix;

public class ShopDashBoardStataKey  extends BasePrefix{

	public ShopDashBoardStataKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static ShopDashBoardStataKey dashBoardKey = new ShopDashBoardStataKey(-1, "shopDashoardStata");

	
}
