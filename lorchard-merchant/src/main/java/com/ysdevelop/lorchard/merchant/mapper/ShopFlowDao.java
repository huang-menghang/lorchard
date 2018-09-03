package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;

import com.ysdevelop.lorchard.merchant.entity.ShopFlow;

public interface ShopFlowDao {
	/**
	 * 每日店铺流量统计
	 * 
	 * @return
	 * 
	 */
	Integer addDailyShopFlow();

	/**
	 * 店铺最近七天日活统计
	 * 
	 * @return
	 */
	List<ShopFlow> recentSevenDayStat();

}
