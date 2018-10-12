package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.SpellingGroupVo;
import com.ysdevelop.lorchard.api.mapper.ApiSpellingGroupDao;
import com.ysdevelop.lorchard.api.service.ApiSpellingGroupService;

/** 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年10月10日 上午11:18:05 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiSpellingGroupServiceImpl implements ApiSpellingGroupService {
	
	@Autowired
	private ApiSpellingGroupDao spellingGroupDao;
	
	@Override
	public List<Long> goodsIdList(Long activityId) {
		return spellingGroupDao.goodsIdList(activityId);
	}

	@Override
	public SpellingGroupVo getByMerchantId(Long merchantId) {
		return spellingGroupDao.getByMerchantId(merchantId);
	}

}
