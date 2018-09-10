package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.MerchantVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:17:48 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
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
