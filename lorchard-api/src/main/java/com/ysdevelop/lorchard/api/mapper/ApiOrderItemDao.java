package com.ysdevelop.lorchard.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.GoodsVo;
import com.ysdevelop.lorchard.api.entity.OrderItemVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:18:06 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiOrderItemDao {
	
	/**
	 * 添加订单商品
	 * @param orderItems
	 * @return
	 */
	Integer batchInsert(@Param(value = "orderItems")List<OrderItemVo> orderItems);
	
	/**
	 * 获取订单商品集合
	 * @param queryMap 
	 * @return
	 */
	List<OrderItemVo> list(Map<String, String> queryMap);
	
	/**
	 * 根据订单号获取订单商品集合
	 * @param orderNo
	 * @return
	 */
	List<OrderItemVo> getOrderByNo(String orderNo);

	/**
	 * 通过商品id获取商品的库存和销量
	 * 
	 * @param 订单商品集合
	 * 
	 * @return 商品集合
	 * */
	List<GoodsVo> getStockAndSales(List<OrderItemVo> orderItems);
	
	/**
	 * 修改goods表的库存和销量
	 * 
	 * @param 订单商品集合
	 * 
	 * @return 判断是否修改成功
	 * */
	Integer updateStockAndSales(List<OrderItemVo> orderItems);
}
