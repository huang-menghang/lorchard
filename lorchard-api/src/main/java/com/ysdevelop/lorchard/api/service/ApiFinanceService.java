package com.ysdevelop.lorchard.api.service;

import com.ysdevelop.lorchard.api.entity.OrderVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月21日 下午3:53:16 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiFinanceService {
	/**
	 * 
	 * 将数据传到financeimpl进行操作
	 * 
	 * @param 
	 * 
	 * @return
	 */
	void addFinance(OrderVo order);
}
