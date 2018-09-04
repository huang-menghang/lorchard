package com.ysdevelop.lorchard.api.service;

import com.ysdevelop.lorchard.api.entity.MerchantVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiMerchantService {
	
	/**
	 * 获取指定商家
	 * @param merchantId
	 * @return
	 */
	MerchantVo getById(Long merchantId);
}
