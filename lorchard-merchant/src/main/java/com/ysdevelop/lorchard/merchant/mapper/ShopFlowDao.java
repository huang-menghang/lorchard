package com.ysdevelop.lorchard.merchant.mapper;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.ShopFlow;

public interface ShopFlowDao {




	/**
	 * 每日店铺流量统计
	 * 
	 */
	void callShopFlowDailyProcedure();

	/**
	 * 店铺最近七天日活统计
	 * 
	 * @param merchantId 商家id
	 * 
	 * @return List<ShopFlow> 流量集合
	 */
	List<ShopFlow> recentSevenDayStat(Long merchantId);
	
	
	/**
	 * 获取昨日流量
	 * 
	 * @param merchantId 商家id
	 * 
	 * @return Map<String, Integer> 返回流量键值对
	 */
	Map<String, Integer> yesterdayStat(Long merchantId);
	
	
	/**
	 * 查询所有商家id
	 * 
	 * @return List<Long> 商家id集合
	 * */
	List<Long> getMerchantId();
	
	/**
	 *获得不同状态订单的条数
	 * @param status 传进来的状态值  
	 * 
	 * @param userId 商家id
	 * 
	 * @return Long 订单条数
	 * */
	Long getOrderCount(@Param(value="status")Integer status,@Param(value="merchantId")Long userId);

}
