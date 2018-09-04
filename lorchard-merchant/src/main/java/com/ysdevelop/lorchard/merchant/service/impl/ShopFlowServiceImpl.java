package com.ysdevelop.lorchard.merchant.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.merchant.entity.ShopFlow;
import com.ysdevelop.lorchard.merchant.key.ShopDashBoardStataKey;
import com.ysdevelop.lorchard.merchant.key.ShopFlowDailyKey;
import com.ysdevelop.lorchard.merchant.mapper.ShopFlowDao;
import com.ysdevelop.lorchard.merchant.service.ShopFlowService;

@Service
public class ShopFlowServiceImpl implements ShopFlowService {

	@Autowired
	private ShopFlowDao flowDao;

	@Autowired
	private RedisService redisService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void dailySata() {
		flowDao.callShopFlowDailyProcedure();
	}

	@Override
	public List<ShopFlow> recentSevenDayStat(Long merchantId) {
		if (merchantId == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		// 将每日数据缓存到redis中,需要的时候获取出
		String jsonResult = redisService.get(ShopFlowDailyKey.flowDailyKey, merchantId + "", String.class);
		// 如果不为空直接获取
		if (jsonResult != null) {
			List<ShopFlow> shopFlows = JSONArray.parseArray(jsonResult, ShopFlow.class);
			return shopFlows;
		}
		// 如果为空
		else {
			List<ShopFlow> shopFlows = flowDao.recentSevenDayStat(merchantId);
			// 如果不为空缓存数据
			if (shopFlows != null) {
				System.out.println("shopFlows--->" + shopFlows);
				Long currentTimeNow = System.currentTimeMillis();
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, Constant.DEFALULT_ONE);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				Long curentTimeAfter = calendar.getTimeInMillis();
				int experiseTime = (int) ((curentTimeAfter - currentTimeNow) / 1000);
				System.out.println("experiseTime--->" + experiseTime);
				if (experiseTime > 0) {
					ShopFlowDailyKey.flowDailyKey.setExpireSeconds(experiseTime);
					String shopFlowJson = JSONArray.toJSONString(shopFlows);
					System.out.println("shopFlowJson--->" + shopFlowJson);
					redisService.set(ShopFlowDailyKey.flowDailyKey, "" + merchantId, shopFlowJson);

				}
			}

			return shopFlows;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> yesterdayStat(Long merchantId) {
		if (merchantId == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		String jsonResult = redisService.get(ShopDashBoardStataKey.dashBoardKey, merchantId + "", String.class);
		if (jsonResult != null) {
			Map<String, Integer> mapResult = JSON.parseObject(jsonResult, Map.class);
			return mapResult;
		} else {
			Map<String, Integer> mapResult = flowDao.yesterdayStat(merchantId);
			// 将map缓存到redis中
			if (mapResult != null) {
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
					ShopDashBoardStataKey.dashBoardKey.setExpireSeconds(experiseTime);
					String resultJson = JSON.toJSONString(mapResult);
					System.out.println("resultJson--->"+resultJson);
					redisService.set(ShopDashBoardStataKey.dashBoardKey, "" + merchantId, resultJson);
				}
			}
			return mapResult;
		}

	}

}
