package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.FinanceVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月21日 下午3:53:43 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiFinanceDao {
	/**
	 * 
	 * 向资金表添加数据
	 * 
	 * @param  Finance finance
	 * 
	 * @return 0代表成功，1代表失败
	 */
	Integer insertFinance(FinanceVo finance);
	
	/**
	 * 
	 * 获取商家id
	 * 
	 * @param 商家id
	 * 
	 * @return orderMerchantId
	 */
	FinanceVo getFinance(Long orderMerchantId);
	
}
