package com.ysdevelop.lorchard.merchant.mapper;

import com.ysdevelop.lorchard.merchant.entity.Shop;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.mapper
 *
 * @Description 
 *
 * @Date 2018年10月12日
 *
 * @Version
 */
public interface ShopDao {
	
	/**
	 * 添加商铺
	 * 
	 * @param shop 商铺信息
	 * 
	 * @return Integer  判断是否修改成功,0代表失败
	 */
	Integer addShop(Shop shop);
	
	/**
	 * 查询运营人姓名
	 * 
	 * @param userId 商家id
	 * 
	 * @return Shop 运营人姓名
	 * */
	Shop getOperatorName(Long userId);
}
