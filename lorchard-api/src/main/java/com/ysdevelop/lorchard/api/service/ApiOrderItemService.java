package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.OrderItemVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:31 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiOrderItemService {
	
	/**
	 * 添加商品集合
	 * @param orderItems
	 * @return
	 */
	Integer batchInsert(List<OrderItemVo> orderItems);

}
