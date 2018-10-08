package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.entity
 *
 * @Description 活动实体类
 *
 * @Date 2018年10月8日
 *
 * @Version
 */
public class Activity {
	
	/**
	 * 活动id
	 */
	private Long id;
	
	/**
	 * 商家id
	 */
	private Long merchantId;
	
	/**
	 * 拼团开始时间
	 */
	private Date startTime;
	
	/**
	 * 拼团结束时间
	 */
	private Date endTime;
	
	/**
	 * 活动状态
	 */
	private Long activityStatus;
	
	/**
	 * 活动名称
	 */
	private String activityName;

	/**
	 * 活动类型
	 */
	private String activityType;
	
	
	
	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Long activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	
	
}
