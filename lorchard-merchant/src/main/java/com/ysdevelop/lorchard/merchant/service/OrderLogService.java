package com.ysdevelop.lorchard.merchant.service;

import com.ysdevelop.lorchard.merchant.entity.OrderLog;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service
 *
 * @Description 订单日志
 *
 * @Date 2018年9月11日
 *
 * @Version
 */
public interface OrderLogService {
	
	/**
	 * 插入订单日志
	 * 
	 * @param orderLog 订单日志
	 */
	void addOrderLog(OrderLog orderLog);
}
