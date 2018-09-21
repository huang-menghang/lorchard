package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.Order;
import com.ysdevelop.lorchard.merchant.entity.OrderItem;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.mapper
 *
 * @Description 订单dao层
 *
 * @Date 2018年9月10日
 *
 * @Version
 */
public interface OrderDao {
	/**
	 * 
	 * 获取所有订单
	 * 
	 * @param queryMap
	 * 
	 * @return List<Order>返订单列表
	 */
	List<Order> list(@Param(value = "queryMap")Map<String, String> queryMap);
	
	/**
	 * 
	 * 通过订单id获取订单详细信息
	 * 
	 * @param id 订单id
	 * 
	 * @return Order 订单
	 */
	Order getById(Integer id);
	
	/**
	 * 
	 * 通过订单id取消订单
	 * 
	 * @param id 订单id
	 * 
	 * @return Integer 返回0表示修改失败
	 */
	Integer updateById(Integer id);
	
	
	/**
	 * 修改订单的发货状态
	 * 
	 * @param id 订单id
	 * 
	 * @return Integer 返回0表示修改失败
	 * */
	Integer update(Integer id);
	
	/**
	 * 
	 * 通过订单号查询订单下的商品
	 * 
	 * @param queryMap
	 * 
	 * @return Order 订单
	 */
	List<OrderItem> listOrderItems(@Param(value = "queryMap")Map<String, String> queryMap);
	
	/**
	 *完成订单，向流水表插入数据
	 *
	 * @param id 订单id
	 * 
	 * @return Order 返回0表示修改失败
	 * */
	Integer completeFlow(Order order);
	
	/**
	 *完成订单，改变订单状态
	 *
	 * @param id 订单id
	 * 
	 * @return Order 返回0表示修改失败
	 * */
	Integer updateStatus(Order order);
	
	/**
	 *完成订单，向资产表插入数据
	 *
	 * @param id 订单id
	 * 
	 * @return Order 返回0表示修改失败
	 * */
	Integer completeFinance(Order order);
	
	/**
	 * 通过id来获取订单商品
	 * 
	 * @param id 订单id
	 * 
	 * @return 返回订单商品集合
	 * */
	List<OrderItem> getOrderItemById(Integer id);
	
	/**
	 * 通过商品id获取商品的库存和销量
	 * 
	 * @param 订单商品集合
	 * 
	 * @return 商品集合
	 * */
	List<Goods> getStockAndSales(@Param("orderItem")List<OrderItem> orderItemById);
	
	/**
	 * 修改goods表的库存和销量
	 * 
	 * @param 订单商品集合
	 * 
	 * @return 判断是否修改成功
	 * */
	Integer updateStockAndSales(@Param("OrderItem")List<OrderItem> orderItemById);
}
