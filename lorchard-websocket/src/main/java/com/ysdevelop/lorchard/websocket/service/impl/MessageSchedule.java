package com.ysdevelop.lorchard.websocket.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.mq.key.MerchantMessageKey;
import com.ysdevelop.lorchard.websocket.service.WebSocketService;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lorchard.websocket.service.impl
 * 
 * @Description 消息查看定时任务,定时查看redis中的消息,并将消息推送给webscoket客户端
 * 
 * @Date 2018年9月12日
 * 
 * @Version
 * 
 */
public class MessageSchedule implements Runnable {

	private RedisService redisService;

	private WebSocketService websocketService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public void setRedisService(RedisService redisService) {
		this.redisService = redisService;
	}

	public void setWebSocketService(WebSocketService websocketService) {
		this.websocketService = websocketService;
	}

	@Override
	public void run() {
		logger.info("start scan redis scan message key");
		// 获取所有的messageKey
		List<String> messageKeys = redisService.scanKeys(MerchantMessageKey.messageKey.getPrefix());
		try {
			if (messageKeys == null) {
				return;
			}
			// 遍历所有有效messageKey
			for (String messageKey : messageKeys) {
				String jsonMessage = null;
				// 将消息集合发送给客户端
				if ((jsonMessage = redisService.get(messageKey, String.class)) != null) {
					List<MerchantMessage> messages = JSONArray.parseArray(jsonMessage, MerchantMessage.class);
					if (messages != null) {
						websocketService.receiveMerchantMessage(messages);
					}
				}
				// 发送完毕删除key,下次扫描messageKey时候效率高点
				redisService.delete(messageKey);
			}
		} catch (Exception e) {
			logger.error("websocket client send fail");
			e.printStackTrace();
		}
		logger.info("finish scan redis scan message key");

	}

}
