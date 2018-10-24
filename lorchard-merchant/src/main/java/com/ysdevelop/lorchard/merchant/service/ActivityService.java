package com.ysdevelop.lorchard.merchant.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Activity;
import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.SpellingGroup;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service
 *
 * @Description 活动添加
 *
 * @Date 2018年10月10日
 *
 * @Version
 */
public interface ActivityService {

	/**
	 * 添加拼团活动商品
	 * 
	 * @param spellingGroup 拼团活动
	 * 
	 * */
	void add(SpellingGroup spellingGroup);
	
	/**
	 * 修改活动商品的拼团人数和拼团价格
	 * 
	 * @param totalNumber 拼团人数
	 * 
	 * @param spellingGroupPrice 拼团价格
	 */
	void update(Integer totalNumber,Double spellingGroupPrice);
	
	/**
	 * 获取活动
	 * 
	 * @param queryMap
	 * 
	 * @return PageInfo<Activity> 活动分页
	 */
	PageInfo<Activity> list(Map<String, String> queryMap);
	
	/**
	 * 删除id
	 * 
	 * @param id 活动id
	 */
	void deleteById(Integer id);
	
	/**
	 * 通过活动id获取拼团活动
	 * 
	 * @param id 活动id
	 * 
	 * @return 拼团活动
	 */
	SpellingGroup getById(Integer id);
	
	PageInfo<Goods> getGoodsById(Map<String, String> queryMap);
}
