package com.ysdevelop.lorchard.merchant.mapper;

import com.ysdevelop.lorchard.merchant.entity.OrderLog;


/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.mapper
 *
 * @Description 订单日志
 *
 * @Date 2018年9月11日
 *
 * @Version
 */
public interface OrderLogDao {
	
	/**
	 * 插入订单日志
	 * 
	 * @param accessLog
	 */
	void addOrderLog(OrderLog orderLog);
}
