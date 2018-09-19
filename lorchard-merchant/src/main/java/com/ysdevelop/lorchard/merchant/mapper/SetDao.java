package com.ysdevelop.lorchard.merchant.mapper;

import com.ysdevelop.lorchard.merchant.entity.Shop;

public interface SetDao {
	
	
	/**
	 * 
	 * 查询商铺信息
	 * 
	 * @param  商家id
	 * 
	 * @return Shop shop
	 */
	Shop getByMerchantId(Long merchantId);
	
	
	/**
	 * 
	 * 修改商铺信息
	 * 
	 * @param  Shop shop
	 * 
	 * @return 0代表成功，1代表失败
	 */
	Integer update(Shop shop);

}
