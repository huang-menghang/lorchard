package com.ysdevelop.lorchard.websocket.service.impl;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.mq.service.MerchantMessageConsumer;
import com.ysdevelop.lorchard.websocket.bo.WebSocketMessage;
import com.ysdevelop.lorchard.websocket.define.WebSocketMessageType;
import com.ysdevelop.lorchard.websocket.manager.ChannelHandlerContextManager;
import com.ysdevelop.lorchard.websocket.service.WebSocketService;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.websocket.service.impl
 * 
 * @Description 处理websocket消息业务类
 * 
 * @Date 2018年9月6日
 * 
 * @Version
 * 
 */

public class WebSocketServiceImpl implements WebSocketService, MessageListener {

	private Logger logger = LoggerFactory.getLogger(WebSocketServiceImpl.class);


	@Override
	public void receiveWebsocketMessage(WebSocketMessage message, ChannelHandlerContext context) {
		// 获取消息类型
		WebSocketMessageType messageType = message.getMessageType();
		try {
			switch (messageType) {
			// 处理上线消息
			case ON_LINE:
				Long clientMerchantId = message.getFromMerchantId();
				if (clientMerchantId == null) {
					String warnMessge = "未能获取到商家客户端的商家id";
					ChannelHandlerContextManager.sendWebSocket(context, warnMessge);
					throw new NullPointerException("客户端连接商家id为空");
				}
				// 处理上线消息,将client缓存到map中
				ChannelHandlerContextManager.addChannelHandlerContext(clientMerchantId, context);
				// 发送消息告知客户端连接成功
				logger.info("客户端商家id为" + clientMerchantId + " ,连接成功");
				String ackMesasge = "商家id为" + clientMerchantId + " ,上线成功";
				ChannelHandlerContextManager.sendWebSocket(context, ackMesasge);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("websocket server send message fail !!!");
			e.printStackTrace();
		}

	}

	@Override
	public void onMessage(Message message) {
		String messageStr = new String(message.getBody());
		System.out.println("message-->" + messageStr);
	}

}
