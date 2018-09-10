package com.ysdevelop.lorchard.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.OrderItemVo;
import com.ysdevelop.lorchard.api.entity.OrderVo;
import com.ysdevelop.lorchard.api.service.ApiOrderService;
import com.ysdevelop.lorchard.api.util.ApiConstant;
import com.ysdevelop.lorchard.api.util.WechatRefundApiResult;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.HttpUtils;
import com.ysdevelop.lorchard.common.utils.XmlUtil;

/**
 * 
 * @author USER
 *
 */
@RestController
@RequestMapping(value = "/order")
public class ApiOrderController {

	@Autowired
	ApiOrderService orderService;

	@RequestMapping(method = RequestMethod.POST, value = "/addOrderItem")
	public Result<String> addOrderItem(@RequestBody List<OrderItemVo> orderItems) {
		OrderVo order = orderService.createOrder(orderItems);
		return Result.successData(order.getOrderNo());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderByNo")
	public Result<String> updateOrderByNo(@RequestBody OrderVo orderVo) {
		orderService.updateOrderByNo(orderVo);
		return Result.successData("订单更新成功");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public Result<List<OrderVo>> list(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<OrderVo> pageInfo = orderService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/close")
	public Result<String> closeOrder(String orderNo) {
		orderService.updateStatusByOrderNo(orderNo,ApiConstant.DEFALULT_FOUR);
		return Result.successData("取消订单成功");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getOrder")
	public Result<OrderVo> getOrder(String orderNo) {
		OrderVo order = orderService.getOrderByNo(orderNo);
		return Result.successData(order);
	}

	// 订单提交,准备微信支付的参数
	@RequestMapping(value = "/prePay", method = RequestMethod.GET)
	public Result<WxPayMpOrderResult> prepay(String orderNo, HttpServletRequest request) {
		OrderVo order = orderService.getOrderByNo(orderNo);
		WxPayMpOrderResult orderResult = orderService.prepareWxpay(order, request);
		return Result.successData(orderResult);
	}
	
	// 微信支付接口回调通知,修改订单状态
		@RequestMapping(value = "/notify", method = RequestMethod.POST)
		public void wxNotify(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("微信支付回调开始运行--->");
			
			try {
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				response.setHeader("Access-Control-Allow-Origin", "*");
				InputStream in = request.getInputStream();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				out.close();
				in.close();
				// xml数据
				String reponseXml = new String(out.toByteArray(), "utf-8");
				WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml, WechatRefundApiResult.class);
				orderService.confirmOrder(result, response);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		/**
		 * 确认收货
		 * @param orderNo
		 * @param request
		 * @return
		 */
		@RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
		public Result<String> confirmOrder(String orderNo, HttpServletRequest request) {
			orderService.updateStatusByOrderNo(orderNo,ApiConstant.DEFALULT_FIVE);
			return Result.successData("确定收货成功");
		}
}
