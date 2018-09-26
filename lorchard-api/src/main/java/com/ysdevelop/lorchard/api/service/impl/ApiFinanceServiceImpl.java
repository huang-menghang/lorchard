package com.ysdevelop.lorchard.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.api.entity.FinanceVo;
import com.ysdevelop.lorchard.api.entity.OrderVo;
import com.ysdevelop.lorchard.api.mapper.ApiFinanceDao;
import com.ysdevelop.lorchard.api.service.ApiFinanceService;

@Service
public class ApiFinanceServiceImpl implements ApiFinanceService {
	@Autowired
	private ApiFinanceDao financeDao;
	
	/**
	 * 订单完成将数据添加进资产表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addFinance(OrderVo order) {
		FinanceVo finance = financeDao.getFinance(order.getOrderMerchantId());
		System.out.println("finance------->" + finance);
		//Double commissionRate = (从admin端获取具体的的佣金比例) 
		Double commission = order.getOrderPendingBalance()*0.01;
		//Double commission = order.getOrderPendingBalance()*Double commissionRate;
		if (finance == null) {
			finance = new FinanceVo();
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
}
