package com.ysdevelop.lorchard.merchant.task.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ysdevelop.lorchard.merchant.service.ShopFlowService;
import com.ysdevelop.lorchard.merchant.task.ShopFlowTask;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lorchard.merchant.task.impl
 * 
 * @Description 每日店铺流量统计
 *
 * @Date 2018年8月31日
 *
 * @Version
 *
 */
@Component
public class ShopFlowTaskImpl implements ShopFlowTask {

	@Autowired
	private ShopFlowService flowService;
	
	@Scheduled(cron="0 0 2 * * ?")
	@Override
	public void dailyStatTask() {
		

	}

}
