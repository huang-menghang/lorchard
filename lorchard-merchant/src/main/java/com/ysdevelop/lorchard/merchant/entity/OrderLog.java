package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;

import com.ysdevelop.lorchard.common.utils.Constant;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.entity
 *
 * @Description 订单日志
 *
 * @Date 2018年9月11日
 *
 * @Version
 */
public class OrderLog {
	/**
	 * 订单日志id
	 */
	private Long id;
	
	/** 
	 * 商家id 
	 */
	private Long merchantId;
	
	/**
	 * 会员id
	 */
	private Long memberId;
	
	/**
	 * 订单号
	 */
	private String orderNo;
	
	/**
	 * 订单日志类型
	 * */
	private Constant.OrderType orderType;
	
	/**
	 * 订单状态描述
	 * */
	private String description;
	
	/**
	 * 订单总价 
	 * */
	private Double orderPendingBalance;
	
	/**
	 * 创建时间
	 * */
	private Date createTime;
	
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Constant.OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(Constant.OrderType orderType) {
		this.orderType = orderType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getOrderPendingBalance() {
		return orderPendingBalance;
	}
	public void setOrderPendingBalance(Double orderPendingBalance) {
		this.orderPendingBalance = orderPendingBalance;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
