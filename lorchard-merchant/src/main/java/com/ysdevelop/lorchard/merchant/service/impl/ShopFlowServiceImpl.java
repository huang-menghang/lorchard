package com.ysdevelop.lorchard.merchant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.merchant.entity.ShopFlow;
import com.ysdevelop.lorchard.merchant.mapper.ShopFlowDao;
import com.ysdevelop.lorchard.merchant.service.ShopFlowService;

@Service
public class ShopFlowServiceImpl implements ShopFlowService {

	@Autowired
	private ShopFlowDao flowDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void dailySata() {
		flowDao.addDailyShopFlow();
	}

	@Override
	public List<ShopFlow> listWeek() {
		return null;
	}

}
