package com.ysdevelop.lorchard.api.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.OrderItemVo;
import com.ysdevelop.lorchard.api.entity.OrderVo;
import com.ysdevelop.lorchard.api.util.WechatRefundApiResult;

public interface ApiOrderService{

	OrderVo createOrder(List<OrderItemVo> orderItems);

	void updateOrderByNo(OrderVo order);

	PageInfo<OrderVo> list(Map<String, String> queryMap);

	void updateStatusByOrderNo(String orderNo,Integer status);

	OrderVo getOrderByNo(String orderNo);

	WxPayMpOrderResult prepareWxpay(OrderVo order, HttpServletRequest request);

	void confirmOrder(WechatRefundApiResult result, HttpServletResponse response);
 
}
