package com.ysdevelop.lorchard.merchant.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Activity;
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
	 * @param 
	 * 
	 * */
	void add(SpellingGroup spellingGroup);
	
	
	void update(Integer totalNumber,Double spellingGroupPrice);
	
	PageInfo<Activity> list(Map<String, String> queryMap);

	void deleteById(Integer id);
	
	SpellingGroup getById(Integer id);
}
