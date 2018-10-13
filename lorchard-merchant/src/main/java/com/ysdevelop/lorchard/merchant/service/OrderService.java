package com.ysdevelop.lorchard.merchant.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Order;
import com.ysdevelop.lorchard.merchant.entity.OrderItem;


/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service
 *
 * @Description 订单
 *
 * @Date 2018年9月10日
 *
 * @Version
 */
public interface OrderService {

	/**
	 * 
	 * 分页查询所有订单还有对应每个商品
	 * 
	 * @param queryMap 分页
	 * 
	 * @return PageInfo<Order> 分页的Order
	 */
	PageInfo<Order> list(Map<String, String> queryMap);

	/**
	 * 通过id获取订单的详细信息
	 * 
	 * @param id 订单的id
	 * 
	 * @return Order 订单
	 */
	Order getById(Integer id);
	
	/**
	 * 通过id删除订单
	 * 
	 * @param id 订单的id
	 * 
	 * @param orderStatus 订单状态
	 * 
	 * */
	void updateById(Order order);
	
	/**
	 * 修改订单发货状态
	 * 
	 * @param id 订单的id
	 * 
	 * @param orderStatus 订单状态
	 * 
	 * */
	void update(Integer id,Integer orderStatus);
	
	/**
	 * 通过订单号获取商品
	 * 
	 * @param queryMap
	 * 
	 * @return List<OrderItem> 商品集合
	 * */
	List<OrderItem> getItemsByOrderNo(Map<String, String> queryMap);
	
	/**
	 * 将获取的值插入流水表中
	 * 
	 * @param order 对象的order
	 * */
	void completeById(Order order);
}
