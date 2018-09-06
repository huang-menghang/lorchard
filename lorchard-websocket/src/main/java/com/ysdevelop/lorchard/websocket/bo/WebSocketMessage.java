package com.ysdevelop.lorchard.websocket.bo;

import com.alibaba.fastjson.JSON;
import com.ysdevelop.lorchard.websocket.define.WebSocketMessageType;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.websocket.bo
 * 
 * @Description Websocket 消息实体类
 * 
 * @Date 2018年9月6日
 * 
 * @Version
 * 
 */
public class WebSocketMessage {
	/** 来自哪一个商家,一般用于标识上线时候的id **/
	private Long fromMerchantId;

	/** 哪一个会员产生了行为 **/
	private Long fromMemberId;

	/** 给那一个商家发送通知 **/
	private Long toMerchantId;

	/** 消息内容 **/
	private String messageConent;

	/** 消息类型 **/
	private WebSocketMessageType messageType;

	/**
	 * 将json消息体转成对应的实体
	 * 
	 * @param messageJson
	 * 
	 * @return
	 */
	public static WebSocketMessage create(String messageJson) {
		WebSocketMessage messages = null;

		if (messageJson != null) {
			messages = null;
		} else {
			try {
				messages = JSON.parseObject(messageJson, WebSocketMessage.class);
			} catch (Exception e) {
				e.printStackTrace();
				messages = null;
			}
		}

		return messages;
	}

	public Long getFromMerchantId() {
		return fromMerchantId;
	}

	public void setFromMerchantId(Long fromMerchantId) {
		this.fromMerchantId = fromMerchantId;
	}

	public Long getFromMemberId() {
		return fromMemberId;
	}

	public void setFromMemberId(Long fromMemberId) {
		this.fromMemberId = fromMemberId;
	}

	public Long getToMerchantId() {
		return toMerchantId;
	}

	public void setToMerchantId(Long toMerchantId) {
		this.toMerchantId = toMerchantId;
	}

	public String getMessageConent() {
		return messageConent;
	}

	public void setMessageConent(String messageConent) {
		this.messageConent = messageConent;
	}

	public WebSocketMessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(WebSocketMessageType messageType) {
		this.messageType = messageType;
	}

}
