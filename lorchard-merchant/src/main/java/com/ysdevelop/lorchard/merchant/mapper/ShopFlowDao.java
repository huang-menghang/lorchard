package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import com.ysdevelop.lorchard.merchant.entity.ShopFlow;

public interface ShopFlowDao {
	/**
	 * 每日店铺流量统计
	 * 
	 * @return
	 * 
	 */
	void callShopFlowDailyProcedure();

	/**
	 * 店铺最近七天日活统计
	 * 
	 * @return
	 */
	List<ShopFlow> recentSevenDayStat(Long merchantId);
	
	
	/**
	 * 
	 * @param merchantId
	 * @return
	 */
	Map<String, Integer> yesterdayStat(Long merchantId);
	
	
	/**
	 * 查询所有商家id
	 * 
	 * @return 商家id集合
	 * */
	List<Long> getMerchantId();
}
