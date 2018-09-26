package com.ysdevelop.lorchard.merchant.mapper;

import com.ysdevelop.lorchard.merchant.entity.Shop;

public interface ShopDao {

	Integer addShop(Shop shop);
	/**
	 * 查询运营人姓名
	 * 
	 * @return 运营人姓名
	 * */
	Shop getOperatorName(Long userId);
}
