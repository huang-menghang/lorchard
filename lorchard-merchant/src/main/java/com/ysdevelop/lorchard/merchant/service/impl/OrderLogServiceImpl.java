package com.ysdevelop.lorchard.merchant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.merchant.entity.OrderLog;
import com.ysdevelop.lorchard.merchant.mapper.OrderLogDao;
import com.ysdevelop.lorchard.merchant.service.OrderLogService;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service.impl
 *
 * @Description 订单日志
 *
 * @Date 2018年10月12日
 *
 * @Version
 */
@Service
public class OrderLogServiceImpl implements OrderLogService {

	@Autowired
	private OrderLogDao orderLogDao;
	
	/**
	 * 插入订单日志
	 * */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addOrderLog(OrderLog orderLog) {
		orderLogDao.addOrderLog(orderLog);
	}

}
