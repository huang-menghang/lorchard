package com.ysdevelop.lorchard.merchant.mapper;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 商家
 * */
import com.ysdevelop.lorchard.merchant.entity.Merchant;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;

public interface MerchantDao {
    /**
     * 添加商家
     * @param merchant
     * 
     * @return Integer 返回0则删除失败
     */
	Integer add(Merchant merchant);

	/**
	 * 根据姓名查找商家
	 * @param name 姓名
	 * 
	 * @return 商家信息
	 */
	BaseAuth getUserByName(String name);

	/**
	 * 根据id更改状态
	 * @param userId 商家id
	 * 
	 * @return Integer 返回0则更新失败
	 */
	Integer updateStatusById(Long userId);

}
