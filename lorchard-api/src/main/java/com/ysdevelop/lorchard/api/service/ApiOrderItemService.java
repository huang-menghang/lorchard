package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.OrderItemVo;

public interface ApiOrderItemService {

	Integer batchInsert(List<OrderItemVo> orderItems);

}
