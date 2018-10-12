package com.ysdevelop.lorchard.api.entity;

import java.util.List;

/** 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年10月10日 下午4:53:02 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public class SpellingGroupOrderVo {
	
	private Long id;
	
	private Long merchantId;
	
	private Long inviteId;
	
	private Long memberId;
	
	private List<OrderVo> orders;

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

	public Long getInviteId() {
		return inviteId;
	}

	public void setInviteId(Long inviteId) {
		this.inviteId = inviteId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public List<OrderVo> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderVo> orders) {
		this.orders = orders;
	}
	
}
