package com.ysdevelop.lorchard.websocket.service.impl;

import io.netty.channel.ChannelHandlerContext;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.websocket.bo.WebSocketMessage;
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
@Service
public class WebSocketServiceImpl implements WebSocketService, MessageListener {

	@Override
	public void receiveWebsocketMessage(WebSocketMessage message, ChannelHandlerContext context) {

	}

	@Override
	public void onMessage(Message message) {
		// 处理来消息队列的消息

	}

}
