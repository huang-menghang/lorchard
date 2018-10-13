package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.Activity;
import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.SpellingGroup;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.mapper
 *
 * @Description 活动
 *
 * @Date 2018年10月12日
 *
 * @Version
 */
public interface ActivityDao {
	
	/**
	 * 
	 * 添加拼团活动
	 * 
	 * @param spellingGroup 活动商品
	 * 
	 * @return Integer 返回一个值如果等于0,则添加活动失败
	 * */
	Integer add(SpellingGroup spellingGroup);
	
	
	/**
	 * 
	 * 添加拼团活动商品
	 * 
	 * @param goodsId 活动商品id 
	 * 
	 * @param spellingGroup 拼团活动
	 * 
	 * @return Integer 返回一个值如果等于0,则添加活动商品失败
	 * */
	Integer addActivityGoods(@Param("activityGoods")List<Long> goodsId,@Param("spellingGroup")SpellingGroup spellingGroup);
	
	/**
	 * 
	 * 修改拼团活动商品人数和拼团价
	 * 
	 * @param totalNumber 拼团人数 
	 * 
	 * @param spellingGroupPrice 拼团价
	 * 
	 * @param goodsId 活动商品id 
	 * 
	 * @return Integer 返回一个值如果等于0,则添加活动商品失败
	 * */
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
	 * @param id 活动id
	 * 
	 * @return 返回0则删除失败
	 */
	Integer deleteById(Integer id);
	
	/**
	 * 
	 * 通过id获取活动信息
	 * 
	 * @param id 活动id
	 * 
	 * @return SpellingGroup 活动
	 * */
	SpellingGroup getById(Integer id);
	
	/**
	 * 通过活动id获取活动商品信息
	 * 
	 * @param id 活动id
	 * 
	 * @return List<Goods> 商品集合
	 * */
	List<Goods> getGoodsById(Integer id);
}
