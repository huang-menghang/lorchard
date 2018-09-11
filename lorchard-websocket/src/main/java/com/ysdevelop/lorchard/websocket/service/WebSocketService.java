package com.ysdevelop.lorchard.websocket.service;

import io.netty.channel.ChannelHandlerContext;

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
     * 处理WebSocket Message
     * 
     * @param message webscoketMessage
     * 
     * @param context 客户端
     */
	void receiveWebsocketMessage(WebSocketMessage message, ChannelHandlerContext context);

}
