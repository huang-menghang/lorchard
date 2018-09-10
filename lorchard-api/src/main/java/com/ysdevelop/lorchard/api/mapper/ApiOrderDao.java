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

	List<OrderVo> getOrderVoList(OrderVo orderVo);

	Integer add(OrderVo orderVo);
	
	Integer updateOrderByNo(OrderVo order);

	Integer updateStatusByOrderNo(@Param(value = "orderNo")String orderNo,@Param(value = "status")Integer status);

	List<OrderVo> list(@Param(value = "queryMap")Map<String, String> queryMap);

	OrderVo getOrderByNo(String orderNo);
}
