package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.MerchantVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiMerchantDao {
	
	/**
	 * 获取指定商家
	 * @param merchantId
	 * @return
	 */
	MerchantVo getById(Long merchantId);
}
