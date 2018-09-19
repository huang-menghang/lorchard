package com.ysdevelop.lorchard.merchant.service;

import com.ysdevelop.lorchard.merchant.entity.Shop;

public interface SetService {
	
	
	/**
	 * 
	 * 查询商铺信息
	 * 
	 * @param  商家id
	 * 
	 * @return  shop 店铺信息
	 */
	Shop getByMerchantId(Long merchantId);
	
	
	/**
	 * 
	 * 修改商铺信息
	 * 
	 * @param  Shop shop 商家店铺
	 */
	void update(Shop shop);

}
