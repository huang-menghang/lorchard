package com.ysdevelop.loarchard.merchant.mapper;

import com.ysdevelop.loarchard.merchant.entity.Merchant;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;

public interface MerchantDao {

	Integer add(Merchant merchant);

	BaseAuth getUserByName(String name);

}
