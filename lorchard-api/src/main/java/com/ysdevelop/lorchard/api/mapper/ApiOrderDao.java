package com.ysdevelop.lorchard.api.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.OrderVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:18:00 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiOrderDao {
	
	/**
	 * 添加订单
	 * @param orderVo
	 * @return
	 */
	Integer add(OrderVo orderVo);
	
	/**
	 * 更新订单
	 * @param order
	 * @return
	 */
	Integer updateOrderByNo(OrderVo order);

	/**
	 * 更新订单状态
	 * @param orderNo
	 * @param status
	 * @return
	 */
	Integer updateStatusByOrderNo(@Param(value = "orderNo")String orderNo,@Param(value = "status")Integer status);
	
	/**
	 * 获取订单集合
	 * @param queryMap
	 * @return
	 */
	List<OrderVo> list(@Param(value = "queryMap")Map<String, String> queryMap);
	
	/**
	 * 获取订单
	 * @param orderNo
	 * @return
	 */
	OrderVo getOrderByNo(String orderNo);
}
