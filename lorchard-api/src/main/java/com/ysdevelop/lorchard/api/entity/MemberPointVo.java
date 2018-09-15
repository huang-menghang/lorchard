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
	
	/**商家id*/
	private Long merchantId;
	
	/**会员id*/
	private Long memberId;
	
	/**可用积分*/
	private Long availableScore;
	
	/**今日可得积分*/
	private Long todayScore;
	
	/**签到天数*/
	private Long totalDay;
	
	/**是否可签到标识*/
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
