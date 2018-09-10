package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.OrderItemVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:18:06 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiOrderItemDao {

	Integer batchInsert(@Param(value = "orderItems")List<OrderItemVo> orderItems);

	List<OrderItemVo> list();

	List<OrderItemVo> getOrderByNo(String orderNo);

}
