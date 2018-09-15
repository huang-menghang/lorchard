package com.ysdevelop.lorchard.api.entity;

import java.util.Date;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月13日 上午11:41:07 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: 用户积分
 *
 * @version V1.0
 *
 */
public class MemberPointVo {
	private Long id;
	
	private Long merchantId;
	
	private Long memberId;
	
	private Long availableScore;
	
	private Long todayScore;
	
	private Long totalDay;
	
	private Integer status;
	
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

	public Long getTodayScore() {
		return todayScore;
	}

	public void setTodayScore(Long todayScore) {
		this.todayScore = todayScore;
	}

	public Long getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(Long totalDay) {
		this.totalDay = totalDay;
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

	public Long getAvailableScore() {
		return availableScore;
	}

	public void setAvailableScore(Long availableScore) {
		this.availableScore = availableScore;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
