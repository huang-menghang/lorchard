package com.ysdevelop.lorchard.merchant.mapper;

import com.ysdevelop.lorchard.merchant.entity.Shop;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.mapper
 *
 * @Description 设置
 *
 * @Date 2018年10月12日
 *
 * @Version
 */
public interface SetDao {
	
	
	/**
	 * 
	 * 查询商铺信息
	 * 
	 * @param merchantId  商家id
	 * 
	 * @return Shop shop 店铺信息
	 */
	Shop getByMerchantId(Long merchantId);
	
	
	/**
	 * 
	 * 修改商铺信息
	 * 
	 * @param shop 商品信息
	 * 
	 * @return Integer 0代表成功，1代表失败
	 */
	Integer update(Shop shop);

}
