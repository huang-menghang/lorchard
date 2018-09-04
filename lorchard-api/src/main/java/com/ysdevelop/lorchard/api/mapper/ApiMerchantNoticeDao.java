package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.MerchantNoticeVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiMerchantNoticeDao {
	
	/**
	 * 获取商家公告
	 * @param merchantId
	 * @return
	 */
	List<MerchantNoticeVo> list(Long merchantId);
}
