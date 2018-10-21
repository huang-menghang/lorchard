package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;
import java.util.List;


/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.entity
 *
 * @Description 拼团订单类
 *
 * @Date 2018年10月9日
 *
 * @Version
 */
public class SpellingGroupOrder {
	private Long id;
	
	private Long merchantId;
	
	private Long inviteId;
	
	private Long memberId;
	
	private List<Order> orders;

	private List<String> orderNos;
	
	private String orderNoLast;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String goodsName;
	
	

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getOrderNoLast() {
		return orderNoLast;
	}

	public void setOrderNoLast(String orderNoLast) {
		this.orderNoLast = orderNoLast;
	}

	public List<String> getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(List<String> orderNos) {
		this.orderNos = orderNos;
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
