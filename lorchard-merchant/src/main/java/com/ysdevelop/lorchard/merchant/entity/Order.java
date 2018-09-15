package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;
import java.util.List;


/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.entity
 *
 * @Description 订单实体类
 *
 * @Date 2018年9月10日
 *
 * @Version
 */
public class Order {
	
	/**
	 * 订单id
	 * */
	private Long id;
	
	/**
	 * 订单号
	 * */
	private String orderNo;
	
	/**
	 * 商家id
	 * */
	private Long orderMerchantId;
	
	/**
	 * 收货人id
	 * */
	private Long orderMemberId;
	
	/**
	 * 收货人姓名
	 * */
	private String orderMemberName;
	
	/**
	 * 取货方式 0表示快递发货 ,1表示上门取货
	 * */
	private Long sendMethod;
	
	
	/**
	 * 省
	 * */
	private String province;
	
	/**
	 * 市
	 * */
	private String city;
	
	/**
	 * 县或者区
	 * */
	private String diatrict;
	
	/**
	 * 详细地址
	 * */
	private String address;
	
	/**
	 * 电话号码
	 * */
	private String mobile;
	
	/**
	 * 快递单号
	 * */
	private Long expressNo;
	
	/**
	 * 订单金额
	 * */
	private Double orderTotalPrice;
	
	/**
	 * 订单折扣
	 * */
	private Double orderDiscount;
	
	/**
	 * 订单支付金额
	 * */
	private Double orderPayPrice;
	
	/**
	 * 支付时间
	 * */
	private Date payTime;
	
	/**
	 * 确认收货时间
	 * */
	private Date confirmTime;
	
	/**
	 * 订单状态 0表示待付款,1表示待发货,2表示待收货,3表示退款中,4表示已取消,5表示已完成
	 * */
	private Long orderStatus;
	
	/**
	 * 订单状态
	 * */
	private String statusStr;
	
	/**
	 * 订单物品集合
	 * */
	private List<OrderItem> orderItems;
	
	/**
	 * 备注
	 * */
	private String remark;
	
	/**
	 * 订单物品运费
	 * */
	private Double freightPrice;
	
	/**
	 * 订单待支付金额
	 * */
	private Double orderPendingBalance;
	
	/**
	 * 创建订单时间
	 * */
	private String addDate;
	
	/**
	 * 修改订单时间
	 * */
	private String confirmDate;
	
	/**
	 * 创建时间
	 * */
	private Date createTime;
	
	/**
	 * 修改时间
	 * */
	private Date updateTime;
	
	/**
	 * 商品数
	 * */
	private Long count;
	
	/**
	 * 订单支付时间
	 * */
	private Date deliverTime;
	
	
	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
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

	public String getDiatrict() {
		return diatrict;
	}

	public void setDiatrict(String diatrict) {
		this.diatrict = diatrict;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Double getFreightPrice() {
		return freightPrice;
	}


	public void setFreightPrice(Double freightPrice) {
		this.freightPrice = freightPrice;
	}


	public Double getOrderPendingBalance() {
		return orderPendingBalance;
	}


	public void setOrderPendingBalance(Double orderPendingBalance) {
		this.orderPendingBalance = orderPendingBalance;
	}


	public Long getOrderMerchantId() {
		return orderMerchantId;
	}


	public void setOrderMerchantId(Long orderMerchantId) {
		this.orderMerchantId = orderMerchantId;
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


	public Long getOrderMemberId() {
		return orderMemberId;
	}


	public void setOrderMemberId(Long orderMemberId) {
		this.orderMemberId = orderMemberId;
	}


	public String getOrderMemberName() {
		return orderMemberName;
	}


	public void setOrderMemberName(String orderMemberName) {
		this.orderMemberName = orderMemberName;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}

	public Long getSendMethod() {
		return sendMethod;
	}


	public void setSendMethod(Long sendMethod) {
		this.sendMethod = sendMethod;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Long getExpressNo() {
		return expressNo;
	}


	public void setExpressNo(Long expressNo) {
		this.expressNo = expressNo;
	}


	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}


	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}


	public Double getOrderDiscount() {
		return orderDiscount;
	}


	public void setOrderDiscount(Double orderDiscount) {
		this.orderDiscount = orderDiscount;
	}


	public Double getOrderPayPrice() {
		return orderPayPrice;
	}


	public void setOrderPayPrice(Double orderPayPrice) {
		this.orderPayPrice = orderPayPrice;
	}


	public Date getPayTime() {
		return payTime;
	}


	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}


	public Date getConfirmTime() {
		return confirmTime;
	}


	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}


	public Long getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Long orderStatus) {
		this.orderStatus = orderStatus;
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
