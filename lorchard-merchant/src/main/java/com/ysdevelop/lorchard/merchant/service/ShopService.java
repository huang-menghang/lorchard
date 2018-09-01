package com.ysdevelop.lorchard.merchant.service;

import com.ysdevelop.lorchard.merchant.entity.Shop;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.loarchard.merchant.service
 * 
 * @Description TODO
 *
 * @Date 2018年8月27日
 *
 * @Version
 *
 */
public interface ShopService {
     /** 申请店铺 */
	void applyShop(Shop shop);
	
	/** 测试mq*/
	void testMq();

}
