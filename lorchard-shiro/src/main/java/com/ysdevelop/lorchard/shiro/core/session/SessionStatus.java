package com.ysdevelop.lorchard.shiro.core.session;

import java.io.Serializable;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.shiro.core.session
 * 
 * @Description 设置session 是否被踢出,如果用户同时登陆两次需要将第一次的踢出后登陆
 * 
 * @Date 2018年4月21日
 * 
 * @Version
 * 
 */
public class SessionStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean onLineStatus = Boolean.TRUE;

	public Boolean isOnlineStatus() {
		return onLineStatus;
	}

	public Boolean getOnLineStatus() {
		return onLineStatus;
	}

	public void setOnLineStatus(Boolean onLineStatus) {
		this.onLineStatus = onLineStatus;
	}

}
