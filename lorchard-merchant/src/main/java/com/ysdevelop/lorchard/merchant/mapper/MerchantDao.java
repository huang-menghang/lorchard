package com.ysdevelop.lorchard.merchant.mapper;

import com.ysdevelop.lorchard.merchant.entity.Merchant;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;

public interface MerchantDao {
    /**
     * 添加商家
     * @param merchant
     * 
     * @return
     */
	Integer add(Merchant merchant);

	/**
	 * 根据姓名查找商家
	 * @param name
	 * 
	 * @return
	 */
	BaseAuth getUserByName(String name);

	/**
	 * 根据id更改状态
	 * @param userId
	 * 
	 * @return
	 */
	Integer updateStatusById(Long userId);

}
