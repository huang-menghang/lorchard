package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.SpellingGroupVo;

/** 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年10月10日 上午11:18:38 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiSpellingGroupDao {

	List<Long> goodsIdList(Long activityId);

	SpellingGroupVo getByMerchantId(Long merchantId);

}