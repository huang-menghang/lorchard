package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.Activity;
import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.SpellingGroup;

public interface ActivityDao {
	
	/**
	 * 
	 * 添加拼团活动商品
	 * 
	 * @param SpellingGroup 活动商品
	 * 
	 * @return Integer 返回一个值如果等于0,则添加活动商品失败
	 * */
	Integer add(SpellingGroup spellingGroup);
	
	Integer addActivityGoods(@Param("activityGoods")List<Long> goodsId,@Param("spellingGroup")SpellingGroup spellingGroup);
	
	Integer update(@Param("totalNumber")Integer totalNumber, @Param("spellingGroupPrice")Double spellingGroupPrice,@Param("list")List<Long> goodsId);
	
	/**
	 * 
	 * 获查询所有活动
	 * 
	 * @param queryMap
	 * 
	 * @return List<Activity>返订单列表
	 */
	List<Activity> list(@Param(value = "queryMap")Map<String, String> queryMap);
	
	/**
	 * 
	 * 根据id改变活动状态，1代表结束活动
	 * 
	 * @param id
	 * 
	 * @return 返回0怎删除失败
	 */
	Integer deleteById(Integer id);
	
	SpellingGroup getById(Integer id);
	
	List<Goods> getGoodsById(Integer id);
}
