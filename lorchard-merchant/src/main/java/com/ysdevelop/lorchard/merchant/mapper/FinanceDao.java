package com.ysdevelop.lorchard.merchant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.merchant.entity.Finance;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.mapper
 *
 * @Description 
 *
 * @Date 2018年9月13日
 *
 * @Version
 */
public interface FinanceDao {


	/**
	 * 
	 * 查询资金表最后一条数据
	 * 
	 * @return Finance finance
	 */
	Finance getLast();
	
	/**
	 * 
	 * 获取所有流水
	 * 
	 * @param queryMap
	 * 
	 * @return List<Finance>返订单列表
	 */
	List<Finance> list(@Param(value = "queryMap")Map<String, String> queryMap);
	
	/**
	 * 
	 * 插入提现记录到提现表
	 * 
	 * @param  finance 资产
	 * 
	 * @return 0表示失败
	 */
	Integer withdraw(Finance finance);
	
	/**
	 * 
	 * 插入提现记录到资产表
	 * 
	 * @param  finance 资产实体类
	 * 
	 * @return 0代表失败
	 */
	Integer balance(Finance finance);
	
	/**
	 * 
	 * 获取提现表最后一条记录的id
	 * 
	 * @return long 提现表id
	 */
	Long queryId();
	
	/**
	 * 
	 * 查询余额
	 * 
	 * @param merchantId 商家id
	 * 
	 * @return Double 余额
	 */
	Double queryBalance(Long merchantId);
	
	/**
	 * 
	 * 向资金表添加数据
	 * 
	 * @param   finance 资金
	 * 
	 * @return  Integer 0代表失败
	 */
	Integer insertFinance(Finance finance);
	
	/**
	 * 
	 * 获取商家id
	 * 
	 * @param orderMerchantId 商家id
	 * 
	 * @return Finance 资金
	 */
	Finance getFinance(Long orderMerchantId);
	
	/**
	 * 
	 * 查看提现记录
	 * 
	 * @param queryMap 
	 * 
	 * @return List<Finance>返回提现记录
	 */
	List<Finance> cashList(@Param(value = "queryMap")Map<String, String> queryMap);
	
	/**
	 * 获取提现总金额
	 * 
	 *  @param merchantId 商家id
	 * 
	 * @return Double 返回提现总金额
	 * */
	Double getAllWithdrawal(Long merchantId);
}
