package com.ysdevelop.lorchard.websocket.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.mq.key.MerchantMessageKey;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lorchard.websocket.service.impl
 * 
 * @Description 消息查看定时任务,定时查看redis中的消息
 * 
 * @Date 2018年9月12日
 * 
 * @Version
 * 
 */
public class MessageSchedule implements Runnable {

	private RedisService redisService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void setRedisService(RedisService redisService) {
		System.out.println("redis service--->" + redisService);
		this.redisService = redisService;
	}

	@Override
	public void run() {
		
		
		
		
		
      
	}

}
