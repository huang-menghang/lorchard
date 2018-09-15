package com.ysdevelop.lorchard.websocket.service;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;

import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.websocket.bo.WebSocketMessage;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.websocket.service
 * 
 * @Description Websocket 处理
 * 
 * @Date 2018年9月5日
 * 
 * @Version
 * 
 */
public interface WebSocketService {
	/**
	 * 处理 客户端 WebSocket Message
	 * 
	 * @param message
	 *            webscoketMessage
	 * 
	 * @param context
	 *            客户端
	 */
	void receiveWebsocketMessage(WebSocketMessage message, ChannelHandlerContext context);

	/**
	 * 处理客户端api客户端商家信息
	 * 
	 * @param messages
	 *            消息集合
	 * 
	 */
	void receiveMerchantMessage(List<MerchantMessage> messages);

}
