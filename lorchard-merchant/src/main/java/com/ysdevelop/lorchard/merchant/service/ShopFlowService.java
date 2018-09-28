package com.ysdevelop.lorchard.merchant.service;

import java.util.List;
import java.util.Map;

import com.ysdevelop.lorchard.merchant.entity.ShopFlow;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lorchard.merchant.service
 * 
 * @Description 商铺流量
 * 
 * @Date 2018年8月31日
 * 
 * @Version
 * 
 */
public interface ShopFlowService {
	/**
	 * 每日统计
	 */
	void dailySata();

	/**
	 * 一星期流量展示
	 * 
	 * @return
	 */
	List<ShopFlow> recentSevenDayStat(Long merchantId);
    
	/**
     * 昨天流量统计
     * @param userId
     * @return
     */
	Map<String, Integer> yesterdayStat(Long userId);
	/**
	 * 将获取的不同状态订单的条数插入待办事项中
	 * 
	 * @param userId
	 * @return
	 * */
	ShopFlow getOrderCount(Long userId);
}
