package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.MerchantBannerVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiMerchantBannerDao {
	
	/**
	 * 获取商家轮播图
	 * @param merchantId
	 * @return
	 */
	List<MerchantBannerVo> list(Long merchantId);
}
