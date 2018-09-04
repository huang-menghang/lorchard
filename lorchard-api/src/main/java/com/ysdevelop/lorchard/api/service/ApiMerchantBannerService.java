package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.MerchantBannerVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiMerchantBannerService {
	
	/**
	 * 获取商家轮播图
	 * @param merchantId
	 * @return
	 */
	List<MerchantBannerVo> list(Long merchantId);
}
