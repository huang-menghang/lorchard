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
	 * @param userId
	 * 
	 * @return
	 */
	Integer updateStatusById(Long userId);
	/**
	 * 获取商家id
	 * @param userId
	 * 
	 * @return
	 */
	Long getBusinessStauts(Long userId);
	/**
	 * 店铺打烊，0代表营业，1代表打烊
	 * @param userId
	 * 
	 * @return
	 */
	Integer updateBusinessStautsToOne(Long userId);
	/**
	 * 店铺营业，0代表营业，1代表打烊
	 * @param userId
	 * 
	 * @return
	 */
	Integer updateBusinessStautsToZero(Long userId);

}
