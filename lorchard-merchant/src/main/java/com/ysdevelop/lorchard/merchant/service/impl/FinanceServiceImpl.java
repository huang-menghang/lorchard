package com.ysdevelop.lorchard.merchant.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.merchant.entity.Finance;
import com.ysdevelop.lorchard.merchant.entity.Order;
import com.ysdevelop.lorchard.merchant.mapper.FinanceDao;
import com.ysdevelop.lorchard.merchant.service.FinanceService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

@Service
public class FinanceServiceImpl implements FinanceService {
	@Autowired
	private FinanceDao financeDao;
	
	/**
	 * 分页查询资产下每一笔交易
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public PageInfo<Finance> list(Map<String, String> queryMap) {
		if (queryMap == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		// 获取分页条件的
		String pageSize = queryMap.get("limit");
		String pageNum = queryMap.get("page");
		if (pageSize == null || pageNum == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Integer integerPageSize = Integer.parseInt(pageSize);
		Integer integerPageNum = Integer.parseInt(pageNum);
		// 调用存储过程实现树形分类

		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);
		List<Finance> finance = financeDao.list(queryMap);

		PageInfo<Finance> pageInfo = new PageInfo<>(finance);
		return pageInfo;
	}
	
	/**
	 * 查询资产表中最后一条数据
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Finance getLast() {
		Finance finance = financeDao.getLast();
		Long merchantId = TokenManager.getUserId();
		Double allWithdrawal = financeDao.getAllWithdrawal(merchantId);
		if(allWithdrawal!=null&&allWithdrawal.isNaN()) {
			finance.setTotalCash(allWithdrawal);
		}
		return finance;
	}
	
	/**
	 * 将提现记录传到financeimpl进行操作
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override	
	public void withdraw(Double cash,String name,String bankNumber,String openBank,Long merchantId) {
		if (cash == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}	
		Long id = financeDao.queryId();
		if(id == null) {
			id= 1L;
		}else {
			id+=1;
		}
		
		
		Finance finance=new Finance();
		finance.setId(id);
		finance.setMerchantId(merchantId);
		finance.setCash(cash);
		finance.setName(name);
		finance.setBankNumber(bankNumber);
		finance.setOpenBank(openBank);
		
		Integer changeCount = financeDao.withdraw(finance);
		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.CASH_FAILURE);
		}	
		
		Double balance1 = financeDao.queryBalance(merchantId);
		if(balance1==null) {
			balance1=0.00;
		}
		Double balance=balance1-cash;
		if(balance<0) {
			throw new WebServiceException(CodeMsg.BALANCE_INSUFFICIENT);
		}
		Finance previousFinance = financeDao.getFinance(merchantId);
		Double totalCommission = previousFinance.getTotalCommission();
		
		Finance finance2=new Finance();
		finance2.setTotalCommission(totalCommission);
		finance2.setMerchantId(merchantId);
		finance2.setBalance(balance);
		finance2.setWithdrawalId(id);
		finance2.setAccount(cash);
		
		Integer changeCount2 = financeDao.balance(finance2);
		if (changeCount2 == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.CASH_FAILURE);
		}
		
	}
	
	/**
	 * 查询余额
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override	
	public Double queryBalance(Long merchantId) {
		Double balance = financeDao.queryBalance(merchantId);
		return balance;
	}

	/**
	 * 订单完成将数据添加进资产表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addFinance(Order order) {
		Finance finance = financeDao.getFinance(order.getOrderMerchantId());
		System.out.println("finance------->" + finance);
		//Double commissionRate = (从admin端获取具体的的佣金比例) 
		Double commission = order.getOrderPendingBalance()*0.01;
		//Double commission = order.getOrderPendingBalance()*Double commissionRate;
		if (finance == null) {
			finance = new Finance();
			finance.setOrderId(order.getId());
			finance.setBalance(order.getOrderPendingBalance() - commission);
			finance.setOrderNo(order.getOrderNo());
			finance.setMerchantId(order.getOrderMerchantId());
			finance.setMemberId(order.getOrderMemberId());
			finance.setCommission(commission);
			finance.setTotalCommission(commission);
			finance.setStatus((long) 0);
			finance.setAccount(order.getOrderPendingBalance());
			financeDao.insertFinance(finance);
		} else {
			Double orderPendingBalance = order.getOrderPendingBalance();
			Double balance = finance.getBalance();
			Double totalCommission = finance.getTotalCommission();
			finance.setOrderId(order.getId());
			finance.setBalance(orderPendingBalance + balance - commission);
			finance.setOrderNo(order.getOrderNo());
			finance.setMerchantId(order.getOrderMerchantId());
			finance.setMemberId(order.getOrderMemberId());
			finance.setCommission(1.00);
			finance.setTotalCommission(totalCommission + commission);
			finance.setStatus((long) 0);
			finance.setAccount(orderPendingBalance);
			financeDao.insertFinance(finance);
		}

	}

	
	/**
	 * 分页查询资产下每一笔提现
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public PageInfo<Finance> cashList(Map<String, String> queryMap) {
		if (queryMap == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		// 获取分页条件的
		String pageSize = queryMap.get("limit");
		String pageNum = queryMap.get("page");
		if (pageSize == null || pageNum == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Integer integerPageSize = Integer.parseInt(pageSize);
		Integer integerPageNum = Integer.parseInt(pageNum);
		// 调用存储过程实现树形分类
		
		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);
		List<Finance> finance = financeDao.cashList(queryMap);
		
		PageInfo<Finance> pageInfo = new PageInfo<>(finance);
		return pageInfo;
	}
}
