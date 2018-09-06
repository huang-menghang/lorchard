package com.ysdevelop.lorchard.websocket.service;

import com.ysdevelop.lorchard.websocket.bo.WebSocketMessage;

import io.netty.channel.ChannelHandlerContext;


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
     * @param message 
     * 
     * @param context 
     */
	void receiveWebsocketMessage(WebSocketMessage message, ChannelHandlerContext context);

}
