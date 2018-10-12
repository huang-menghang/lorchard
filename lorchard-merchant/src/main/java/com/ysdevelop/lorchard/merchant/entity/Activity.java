package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date startTime;
	
	/**
	 * 拼团结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date endTime;
	
	/**
	 * 活动名称
	 */
	private String activityName;

	/**
	 * 活动类型
	 */
	private Long activityType;
	/**
	 * 活动描述
	 */
	private String description;
	/**创建时间*/
	private Date createTime;
	
	/**更新时间*/
	private Date updateTime;
	/**
	 * 活动状态,0代表正在进行，1代表已经结束
	 */
	private Long activityStatus;
	
	
	public Long getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Long activityStatus) {
		this.activityStatus = activityStatus;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getActivityType() {
		return activityType;
	}

	public void setActivityType(Long activityType) {
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


	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	
	
}
