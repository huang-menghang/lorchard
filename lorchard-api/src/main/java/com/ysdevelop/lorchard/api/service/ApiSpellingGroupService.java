package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.SpellingGroupVo;

/** 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年10月10日 上午11:17:38 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiSpellingGroupService {

	List<Long> goodsIdList(Long activityId);

	SpellingGroupVo getByMerchantId(Long merchantId);
	
	void addSpellingGroupOrder(Long merchantId, Long memberId,String orderNo);

	void increaseSpellingGroupOrder(Long inviteId,Long merchantId, Long memberId,String orderNo);
}
