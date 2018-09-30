package com.ysdevelop.lorchard.merchant.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.common.utils.HttpUtils;
import com.ysdevelop.lorchard.merchant.entity.Order;
import com.ysdevelop.lorchard.merchant.entity.OrderItem;
import com.ysdevelop.lorchard.merchant.service.OrderService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;


/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.controller
 *
 * @Description 订单控制器
 *
 * @Date 2018年9月10日
 *
 * @Version
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	/**
	 * 全部订单
	 * */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String index() {
		return "order/index";
	}
	
	/**
	 * 获取商家id
	 * */
	@RequestMapping(value = "/orderMerchantId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Long> orderMerchantId() {
		Long orderMerchantId = TokenManager.getUserId();
		System.out.println("orderMerchantId2:"+orderMerchantId);
		return Result.successData(orderMerchantId);
	}
	
	/**
	 * 获取index的商品列表
	 * */
	@RequestMapping(value = "/pagination", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<Order>> pagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<Order> pageInfo = orderService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
	
	/**
	 * 通过id查找订单
	 * */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Order> info(@PathVariable(value = "id") Integer id) {
		Order order = orderService.getById(id);
		return Result.successData(order);
	}
	/**查看订单详情*/
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public String info() {
		return "order/info";
	}
	
	/**查看超时订单*/
	@RequestMapping(value = "/timeOut", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public String timeOut() {
		return "order/timeOut";
	}
	
	/**确认完成订单*/
	@RequestMapping(value = "/complete", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody 
	public Result<String> complete(@Valid Order order) {
		orderService.completeById(order);
		return Result.success("订单完成！");
	}
	
	/**订单退款*/
	@SystemControllerLog(description="订单退款成功",orderType=Constant.OrderType.CLOSED)
	@RequestMapping(value = "/refund", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody 
	public Result<String> delete(@Valid Order order) {
		orderService.updateById(order);
		return Result.success("订单退款成功");
	}
	
	
	
	/**
	 * 确认发货
	 * */
	@SystemControllerLog(description="发货成功,等待收货",orderType=Constant.OrderType.UNRECEIVED)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> update(@PathVariable Integer id,Integer orderStatus) {
		orderService.update(id,orderStatus);
		return Result.success("发货成功");
	}
	
	/**
	 * 通过orderNo查找商品
	 * */
	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<OrderItem>> items(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		List<OrderItem> itemsByOrderNo = orderService.getItemsByOrderNo(queryMap);
		return Result.successData(itemsByOrderNo);
	}
}
