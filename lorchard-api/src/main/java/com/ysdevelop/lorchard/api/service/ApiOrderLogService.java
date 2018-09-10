package com.ysdevelop.lorchard.api.service;

import com.ysdevelop.lorchard.api.entity.OrderLogVo;

/** 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 下午5:47:16 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiOrderLogService {

	/**
	 * 插入订单日志
	 * 
	 * @param accessLog
	 */
	void addOrderLog(OrderLogVo orderLog);
}
