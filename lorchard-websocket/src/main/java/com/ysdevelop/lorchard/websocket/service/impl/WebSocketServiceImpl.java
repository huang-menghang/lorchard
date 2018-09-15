package com.ysdevelop.lorchard.websocket.service.impl;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
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
@Service
public class WebSocketServiceImpl implements WebSocketService {

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
				WebSocketMessage returnMessage = new WebSocketMessage();
				returnMessage.setMessageConent(ackMesasge);
				returnMessage.setMessageType(WebSocketMessageType.RETURN);
				// 发送消息给上线的用户
				ChannelHandlerContextManager.sendWebSocket(context, JSON.toJSONString(returnMessage));
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
	public void receiveMerchantMessage(List<MerchantMessage> messages) {
		if (messages != null) {
			// 获取到商家的Id
			Long merchantId = messages.get(Constant.DEFALULT_ZERO).getMerchantId();
			ChannelHandlerContext clientContext = ChannelHandlerContextManager.getContextByUserId(merchantId);
			if (clientContext == null || clientContext.isRemoved()) {
				throw new RuntimeException("尚未握手成功,无法向客户端发送WebSocket消息");
			}
			// 将我们的消息转成json,再发给客户端进行解析
			String jsonMessage = JSONArray.toJSONString(messages);
			logger.info("message--->" + jsonMessage);
			WebSocketMessage merchantMessage = new WebSocketMessage();
			merchantMessage.setFromMerchantId(merchantId);
			merchantMessage.setMessageConent(messages);
			// 设置商家信息
			merchantMessage.setMessageType(WebSocketMessageType.MERCHANT);
			// 将消息发送给
			ChannelHandlerContextManager.sendWebSocket(clientContext, JSON.toJSONString(merchantMessage));

		}

	}

}
