package com.ysdevelop.lorchard.api.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class AddressVo{
	
	private Long id;

	private Long memberId;

	@NotEmpty(message = "收件人不能为空")
	private String consignee;
	
	private String mobile;
	
	@NotEmpty(message = "省名不能为空")
	private String province;

	@NotEmpty(message = "市名不能为空")
	private String city;

	private String diatrict;

	@NotEmpty(message = "详细地址不能为空")
	private String address;

	private Integer state;
	
	private Date createTime;
	
	private Date updateTime;

	public Long getId() { 
		return id;
	} 

	public void setId(Long id) {
		this.id = id;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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
	
}
