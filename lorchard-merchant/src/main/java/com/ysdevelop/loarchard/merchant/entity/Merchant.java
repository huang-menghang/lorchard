package com.ysdevelop.loarchard.merchant.entity;

import com.ysdevelop.lorchard.shiro.entity.BaseAuth;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.entity
 * 
 * @Description 商家类
 * 
 * @Date 2018年8月27日
 * 
 * @Version
 * 
 */
public class Merchant extends BaseAuth {

	/**
	 *序列化接口 
	 */
	private static final long serialVersionUID = 7311521707329435951L;
	
	/**
	 * 商家的手机号码
	 */
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
