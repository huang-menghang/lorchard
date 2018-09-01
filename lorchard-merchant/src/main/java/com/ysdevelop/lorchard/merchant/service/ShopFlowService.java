package com.ysdevelop.lorchard.merchant.service;

import java.util.List;

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
	List<ShopFlow> listWeek();

}
