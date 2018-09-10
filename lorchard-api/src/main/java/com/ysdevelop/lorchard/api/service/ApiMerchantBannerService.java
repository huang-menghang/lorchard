package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.MerchantBannerVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:16 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
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
