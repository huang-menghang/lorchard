package com.ysdevelop.lorchard.merchant.service;

import com.ysdevelop.lorchard.merchant.entity.Shop;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service
 *
 * @Description 
 *
 * @Date 2018年10月12日
 *
 * @Version
 */
public interface SetService {
	
	
	/**
	 * 
	 * 查询商铺信息
	 * 
	 * @param merchantId 商家id
	 * 
	 * @return  shop 店铺信息
	 */
	Shop getByMerchantId(Long merchantId);
	
	
	/**
	 * 
	 * 修改商铺信息
	 * 
	 * @param  shop 商家店铺
	 */
	void update(Shop shop);

}
