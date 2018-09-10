package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.MerchantNoticeVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:21 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiMerchantNoticeService {
	
	/**
	 * 获取商家公告
	 * @param merchantId
	 * @return
	 */
	List<MerchantNoticeVo> list(Long merchantId);
}
