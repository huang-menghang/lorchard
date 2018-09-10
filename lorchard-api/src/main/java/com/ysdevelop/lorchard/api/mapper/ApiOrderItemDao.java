package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.OrderItemVo;

public interface ApiOrderItemDao {

	Integer batchInsert(@Param(value = "orderItems")List<OrderItemVo> orderItems);

	List<OrderItemVo> list();

	List<OrderItemVo> getOrderByNo(String orderNo);

}
