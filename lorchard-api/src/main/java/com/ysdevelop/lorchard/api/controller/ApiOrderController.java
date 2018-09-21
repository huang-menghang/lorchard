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
import com.ysdevelop.lorchard.api.entity.OrderVo;
import com.ysdevelop.lorchard.api.service.ApiOrderService;
import com.ysdevelop.lorchard.api.util.WechatRefundApiResult;
import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.ApiConstant;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.common.utils.HttpUtils;
import com.ysdevelop.lorchard.common.utils.XmlUtil;

/**
 * 
 * 
 * @author 徐一鸣
 *
 * @Date 2018年9月10日 上午10:15:37
 *
 * @Package com.ysdevelop.lorchard.api.controller
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@RestController
@RequestMapping(value = "/order")
public class ApiOrderController {

	@Autowired
	ApiOrderService orderService;
	
	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/createOrder")
	public Result<String> createOrder(@RequestBody OrderVo order) {
		String orderNo = orderService.createOrder(order);
		return Result.successData(orderNo);
	}
	
	/**
	 * 用户下单成功日志
	 * @param orderNo
	 * @return
	 */
	@SystemControllerLog(description="用户下单成功",orderType=Constant.OrderType.UNPAYMENYT)
	@RequestMapping(value = "/successCreate", method = RequestMethod.GET)
	public Result<String> successCreate(String orderNo) {
		
		return Result.successData("创建订单成功");
	}
	
	/**
	 * 获取商品订单集合
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public Result<List<OrderVo>> list(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<OrderVo> pageInfo = orderService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
	
	/**
	 * 取消订单
	 * @param orderNo
	 * @return
	 */
	@SystemControllerLog(description="取消订单",orderType=Constant.OrderType.CLOSED)
	@RequestMapping(method = RequestMethod.GET, value = "/close")
	public Result<String> closeOrder(String orderNo) {
		orderService.updateStatusByOrderNo(orderNo, ApiConstant.DEFALULT_FOUR);
		return Result.successData("取消订单成功");
	}
	
	/**
	 * 获取订单
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getOrder")
	public Result<OrderVo> getOrder(String orderNo) {
		OrderVo order = orderService.getOrderByNo(orderNo);
		return Result.successData(order);
	}

	/**
	 * 订单提交,准备微信支付的参数
	 * @param orderNo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/prePay", method = RequestMethod.GET)
	public Result<WxPayMpOrderResult> prepay(String orderNo, HttpServletRequest request) {
		OrderVo order = orderService.getOrderByNo(orderNo);
		WxPayMpOrderResult orderResult = orderService.prepareWxpay(order, request);
		return Result.successData(orderResult);
	}

	/**
	 * 微信支付接口回调通知,修改订单状态
	 * @param request
	 * @param response
	 */
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
			WechatRefundApiResult result = (WechatRefundApiResult) XmlUtil.xmlStrToBean(reponseXml,
					WechatRefundApiResult.class);
			orderService.confirmOrder(result, response);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * 支付成功日志
	 * @param orderNo
	 * @return
	 */
	@SystemControllerLog(description="支付成功,等待发货",orderType=Constant.OrderType.UNSEND)
	@RequestMapping(value = "/successPay", method = RequestMethod.GET)
	public Result<String> confirmOrder(String orderNo) {
		
		return Result.successData("支付成功");
	}
	
	/**
	 * 确认收货
	 * @param orderNo
	 * @param request
	 * @return
	 */
	@SystemControllerLog(description="用户已确定收货",orderType=Constant.OrderType.FINISHED)
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
	public Result<String> confirmOrder(String orderNo, HttpServletRequest request) {
		orderService.updateStatusByOrderNo(orderNo, ApiConstant.DEFALULT_FIVE);
		return Result.successData("确定收货成功");
	}
}
