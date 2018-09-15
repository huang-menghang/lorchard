package com.ysdevelop.lorchard.merchant.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Finance;
import com.ysdevelop.lorchard.merchant.entity.Order;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service
 *
 * @Description 
 *
 * @Date 2018年9月13日
 *
 * @Version
 */
public interface FinanceService {
	/**
	 * 
	 * 分页查询资产下每一笔交易
	 * 
	 * @param queryMap 分页
	 * 
	 * @return PageInfo<Finance> 分页的Finance
	 */
	PageInfo<Finance> list(Map<String, String> queryMap);
	
	/**
	 * 
	 * 查询资产表中最后一条数据
	 * 
	 * @return Finance数据
	 */
	Finance getLast();
	
	/**
	 * 
	 * 将提现记录传到financeimpl进行操作
	 * 
	 * @param Double cash,String name,String bankNumber,String openBank,Long merchantId
	 * 
	 */
	void withdraw(Double cash,String name,String bankNumber,String openBank,Long merchantId);
	
	/**
	 * 
	 * 查询余额
	 * 
	 * @param 商家id
	 * 
	 * @return balance商家资产余额
	 */
	Double queryBalance(Long merchantId);
	
	/**
	 * 
	 * 将数据传到financeimpl进行操作
	 * 
	 * @param 
	 * 
	 * @return
	 */
	void addFinance(Order order);
	
	/**
	 * 
	 * 分页查询资产下每一笔交易
	 * 
	 * @param queryMap 分页
	 * 
	 * @return PageInfo<Finance> 分页的Finance
	 */
	PageInfo<Finance> cashList(Map<String, String> queryMap);

}
