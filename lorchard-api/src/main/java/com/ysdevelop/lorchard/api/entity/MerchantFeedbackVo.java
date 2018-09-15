package com.ysdevelop.lorchard.api.entity;

import java.util.Date;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月11日 下午3:53:32 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: 商家意见反馈
 *
 * @version V1.0
 *
 */
public class MerchantFeedbackVo {
	private Long id;
	
	private Long merchantId;
	
	private Long memberId;
	
	private String feedbackType;
	
	private String message;
	
	private String mobile;
	
	private Date createTime;
	
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
