package com.ysdevelop.lorchard.shiro.vo;

public class LoginVo {
	// 姓名
	private String name;
	// 手机号码
	private String mobile;
	// 验证码
	private String verifyCode;
	// 手机验证号码
	private String verifyMoblieMessage;

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

}
