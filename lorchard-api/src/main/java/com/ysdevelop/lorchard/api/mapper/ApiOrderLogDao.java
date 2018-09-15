package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.OrderLogVo;

/** 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 下午5:42:36 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiOrderLogDao {
	
	/**
	 * 添加订单日志
	 * @param orderLog
	 */
	void addOrderLog(OrderLogVo orderLog);
}
