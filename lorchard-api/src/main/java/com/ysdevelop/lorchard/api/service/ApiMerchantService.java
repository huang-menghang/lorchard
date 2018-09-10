package com.ysdevelop.lorchard.api.service;

import com.ysdevelop.lorchard.api.entity.MerchantVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:26 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
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
