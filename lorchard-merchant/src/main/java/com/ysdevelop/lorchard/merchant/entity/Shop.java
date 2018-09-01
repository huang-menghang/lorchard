package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.ysdevelop.lorchard.common.validator.IsEmail;
import com.ysdevelop.lorchard.common.validator.IsMobile;
import com.ysdevelop.lorchard.common.validator.IsQQ;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.entity
 * 
 * @Description 店铺实体
 * 
 * @Date 2018年8月27日
 * 
 * @Version
 * 
 */
public class Shop {

	private Long id;
	/* 商家id */
	private Long merchantId;

	@NotEmpty(message = "名字不能为空")
	private String name;
	/* 省 */
	@NotEmpty(message = "省不能为空")
	private String province;
	/* 市 */
	@NotEmpty(message = "市不能为空")
	private String city;
	/* 县 */
	private String town;
	/* 详细地址 */
	@NotEmpty(message = "详细地址不能为空")
	private String detailAddress;
	/* 描述 */
	@NotEmpty(message = "描述不能为空")
	private String description;
	/* 运营人姓名 */
	@NotEmpty(message = "运营人姓名不能为空")
	private String operatorName;
	/* 手机号码 */
	@NotEmpty(message = "手机号码不能为空")
	@IsMobile
	private String mobile;
	/* qq */
	@NotEmpty(message = "qq不能为空")
	@IsQQ
	private String qq;
	/* 微信号 */
	@NotEmpty(message = "微信不能为空")
	private String wechatNo;
	/* 邮箱 */
	@NotEmpty(message = "邮箱不能为空")
	@IsEmail
	private String email;
	/* 商家二维码 */
	private String merchantQr;

	/* 状态 */
	private Integer status;
	/* 创建时间 */
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechatNo() {
		return wechatNo;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMerchantQr() {
		return merchantQr;
	}

	public void setMerchantQr(String merchantQr) {
		this.merchantQr = merchantQr;
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
