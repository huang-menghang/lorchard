package com.ysdevelop.lorchard.api.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.OrderVo;
import com.ysdevelop.lorchard.api.util.WechatRefundApiResult;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:36 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiOrderService{

	String createOrder(OrderVo orderVo);

	PageInfo<OrderVo> list(Map<String, String> queryMap);

	void updateStatusByOrderNo(String orderNo,Integer status);

	OrderVo getOrderByNo(String orderNo);

	WxPayMpOrderResult prepareWxpay(OrderVo order, HttpServletRequest request);

	void confirmOrder(WechatRefundApiResult result, HttpServletResponse response);
 
}
