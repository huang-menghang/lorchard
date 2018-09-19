package com.ysdevelop.lorchard.merchant.task.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.ysdevelop.lorchard.common.redis.BasePrefix;
import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.merchant.entity.ShopFlow;
import com.ysdevelop.lorchard.merchant.key.ShopFlowDailyKey;
import com.ysdevelop.lorchard.merchant.mapper.ShopFlowDao;
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
	
	@Autowired
	private ShopFlowDao flowDao;
	
	@Autowired
	private RedisService redisService;
	
	@Scheduled(cron="0 0 2 * * ?")
	@Override
	public void dailyStatTask() {
		System.out.println("dailySata");
		flowService.dailySata();
		List<Long> merchantIds = flowDao.getMerchantId();
		for (Long merchantId : merchantIds) {
			List<ShopFlow> shopFlows = flowDao.recentSevenDayStat(merchantId);
			// 如果不为空,缓存结果值数据
			if (shopFlows != null) {
				System.out.println("merchantId"+merchantId);
				System.out.println("ShopFlowDailyKey.flowDailyKey"+ShopFlowDailyKey.flowDailyKey.getPrefix());
				System.out.println("JSONArray.toJSONString(shopFlows)"+JSONArray.toJSONString(shopFlows));
				storeJsonResultRedisDaily(merchantId, ShopFlowDailyKey.flowDailyKey, JSONArray.toJSONString(shopFlows));
			}
		}
	}

	
	private void storeJsonResultRedisDaily(Long merchantId, BasePrefix storeKey, String jsonResult) {
		if (jsonResult != null) {
			Long currentTimeNow = System.currentTimeMillis();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, Constant.DEFALULT_ONE);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Long curentTimeAfter = calendar.getTimeInMillis();
			int experiseTime = (int) ((curentTimeAfter - currentTimeNow) / 1000);
			if (experiseTime > 0) {
				storeKey.setExpireSeconds(experiseTime);
				redisService.set(storeKey, "" + merchantId, jsonResult);
			}
		}
	}
}
