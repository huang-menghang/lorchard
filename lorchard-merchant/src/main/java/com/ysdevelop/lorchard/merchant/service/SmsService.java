package com.ysdevelop.lorchard.merchant.service;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
public interface SmsService {
	
	/**
	 * 发送验证码
	 */
	void sendCodeMessage();
	
	/**
	 * 校验验证码
	 */
	void checkCodeMessgae();
	
}
