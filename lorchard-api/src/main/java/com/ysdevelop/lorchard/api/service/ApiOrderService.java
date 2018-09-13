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
	
	/**
	 * 创建订单
	 * @param orderVo
	 * @return
	 */
	String createOrder(OrderVo orderVo);
	
	/**
	 * 获取商品集合
	 * @param queryMap
	 * @return
	 */
	PageInfo<OrderVo> list(Map<String, String> queryMap);
	
	/**
	 * 更新订单状态
	 * @param orderNo
	 * @param status
	 */
	void updateStatusByOrderNo(String orderNo,Integer status);
	
	/**
	 * 获取订单
	 * @param orderNo
	 * @return
	 */
	OrderVo getOrderByNo(String orderNo);
	
	/**
	 * 微信支付接口
	 * @param order
	 * @param request
	 * @return
	 */
	WxPayMpOrderResult prepareWxpay(OrderVo order, HttpServletRequest request);
	
	/**
	 * 确定完成订单
	 * @param result
	 * @param response
	 */
	void confirmOrder(WechatRefundApiResult result, HttpServletResponse response);
 
}
