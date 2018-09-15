package com.ysdevelop.lorchard.mq.bo;

import java.util.Date;

import com.ysdevelop.lorchard.mq.define.MessageType;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lorchard.mq.bo
 * 
 * @Description 商家端消息队列
 * 
 * @Date 2018年8月29日
 * 
 * @Version
 * 
 */

public class MerchantMessage {

	/* 商家Id */
	private Long merchantId;
	/* 用户Id */
	private Long userId;
	/* 消息内容 */
	private String conent;
	/* 消息枚举类型 */
	private MessageType messageType;
	/* 消息创建时间 */
	private Date createTime;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getConent() {
		return conent;
	}

	public void setConent(String conent) {
		this.conent = conent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

}
