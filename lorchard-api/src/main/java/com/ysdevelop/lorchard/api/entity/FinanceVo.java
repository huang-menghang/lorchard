package com.ysdevelop.lorchard.api.entity;

import java.util.Date;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月21日 下午3:53:32 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public class FinanceVo {
	
	/**
	 * 流水表id
	 * */
	private Long id;
	
	/**
	 * 订单号
	 * */
	private String orderNo;
	
	/**
	 * 一条订单的金额
	 * */
	private Double orderPendingBalance;
	
	/**
	 * 商家总金额
	 * */
	private Double totalAmount;
	
	/**
	 * 商家id
	 * */
	private Long merchantId;
	
	/**
	 * 创建时间
	 * */
	private Date createTime;
	
	/**
	 * 订单佣金
	 * */
	private Double commission;
	
	/**
	 * 商家总佣金
	 * */
	private Double totalCommission;
	
	/**
	 * 提现金额
	 * */
	private Double cash;
	
	/**
	 * 可用余额
	 * */
	private Double balance;
	
	/**
	 *资金状态，0代表订单收益，1代表提现提现资金
	 * */
	private Long status;
	
	/**
	 *商家姓名
	 * */
	private String name;
	
	/**
	 *商家银行卡号
	 * */
	private String bankNumber;
	
	/**
	 *商家开户银行
	 * */
	private String openBank;
	
	/**
	 *商家提现记录的id
	 * */
	private Long withdrawalId;
	
	/**
	 *流水金额
	 * */
	private Double account;
	
	/**
	 * 订单id
	 * */
	private Long orderId;
	
	/**
	 * 提现总佣金
	 * */
	private Double totalCash;
	
	/**
	 * 会员id
	 * */
	private Long memberId;
	
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getTotalCash() {
		return totalCash;
	}

	public void setTotalCash(Double totalCash) {
		this.totalCash = totalCash;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public Double getOrderPendingBalance() {
		return orderPendingBalance;
	}

	public void setOrderPendingBalance(Double orderPendingBalance) {
		this.orderPendingBalance = orderPendingBalance;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(Double totalCommission) {
		this.totalCommission = totalCommission;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public Long getWithdrawalId() {
		return withdrawalId;
	}

	public void setWithdrawalId(Long withdrawalId) {
		this.withdrawalId = withdrawalId;
	}

	
	
	
}
