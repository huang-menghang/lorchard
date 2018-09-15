package com.ysdevelop.lorchard.api.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:16:37 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: 订单
 *
 * @version V1.0
 *
 */
public class OrderVo {

	private Long id;
	
	private String orderNo;

	private Long orderMerchantId;
	
	private Long orderMemberId;
	
	@NotEmpty(message = "收货人不能为空")
	private String orderMemberName;

	/**邮寄和自取**/
	private Integer sendMethod;

	@NotEmpty(message = "省名不能为空")
	private String province;

	@NotEmpty(message = "市名不能为空")
	private String city;

	private String diatrict;

	private Integer isFirst;

	@NotEmpty(message = "详细地址不能为空")
	private String address;
	
	@NotEmpty(message = "联系人手机号码不能为空")
	private String mobile;

	private String remark;

	/**运费**/
	private Double freightPrice;
	
	@NotNull(message = "订单金额不能为空")
	private Double orderTotalPrice;

	/**代结金额**/
	private Double orderPendingBalance;
	
	/**订单折扣**/
	private Double orderDiscount;
	
	/**订单支付金额**/
	private Double orderPayPrice;
	
	/**订单状态**/
	private Integer orderStatus;
	
	/**快递单号**/
	private String expressNo;
	
	private List<OrderItemVo> orderItems;
	
	private String addDate;
	
	private String confirmDate;
	
	private String statusStr;
	
	/**支付时间**/
	private Date payTime;
	
	/**确认收货时间**/
	private Date confirmTime;
	
	private Date createTime;
	
	private Date updateTime;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getOrderMerchantId() {
		return orderMerchantId;
	}

	public void setOrderMerchantId(Long orderMerchantId) {
		this.orderMerchantId = orderMerchantId;
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

	public Integer getSendMethod() {
		return sendMethod;
	}

	public void setSendMethod(Integer sendMethod) {
		this.sendMethod = sendMethod;
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

	public String getDiatrict() {
		return diatrict;
	}

	public void setDiatrict(String diatrict) {
		this.diatrict = diatrict;
	}

	public Integer getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
	}

	public Double getOrderDiscount() {
		return orderDiscount;
	}

	public void setOrderDiscount(Double orderDiscount) {
		this.orderDiscount = orderDiscount;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	
	public Double getOrderPayPrice() {
		return orderPayPrice;
	}

	public void setOrderPayPrice(Double orderPayPrice) {
		this.orderPayPrice = orderPayPrice;
	}

	public void setOrderTotalPrice(Double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public Double getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(Double freightPrice) {
		this.freightPrice = freightPrice;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Double getOrderPendingBalance() {
		return orderPendingBalance;
	}

	public void setOrderPendingBalance(Double orderPendingBalance) {
		this.orderPendingBalance = orderPendingBalance;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderItemVo> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemVo> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
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

	@Override
	public String toString() {
		return "OrderVo [id=" + id + ", orderNo=" + orderNo + ", orderMerchantId=" + orderMerchantId
				+ ", orderMemberId=" + orderMemberId + ", orderMemberName=" + orderMemberName + ", sendMethod="
				+ sendMethod + ", province=" + province + ", city=" + city + ", diatrict=" + diatrict + ", isFirst="
				+ isFirst + ", address=" + address + ", mobile=" + mobile + ", remark=" + remark + ", freightPrice="
				+ freightPrice + ", orderTotalPrice=" + orderTotalPrice + ", orderPendingBalance=" + orderPendingBalance
				+ ", orderDiscount=" + orderDiscount + ", orderPayPrice=" + orderPayPrice + ", orderStatus="
				+ orderStatus + ", expressNo=" + expressNo + ", orderItems=" + orderItems + ", addDate=" + addDate
				+ ", confirmDate=" + confirmDate + ", statusStr=" + statusStr + ", payTime=" + payTime
				+ ", confirmTime=" + confirmTime + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	
}
