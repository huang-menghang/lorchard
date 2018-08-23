package com.ysdevelop.lorchard.shiro.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ysdevelop.lochard.common.validator.IsMobile;

public class LoginVo {
	// 姓名
	private String name;
	// 手机号码
	@NotEmpty(message = "手机号码不能为空")
	private String mobile;
	// 验证码
	private String verifyCode;
	// 手机验证号码
	@NotEmpty(message = "手机验证码不能为空")
	@Size(min=6,max=6,message="手机验证码长度为6位")
	private String verifyMoblieMessage;
     
	@NotEmpty(message="密码不能为空")
	private String password;

	private String surePassword;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getVerifyMoblieMessage() {
		return verifyMoblieMessage;
	}

	public void setVerifyMoblieMessage(String verifyMoblieMessage) {
		this.verifyMoblieMessage = verifyMoblieMessage;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurePassword() {
		return surePassword;
	}

	public void setSurePassword(String surePassword) {
		this.surePassword = surePassword;
	}

}
