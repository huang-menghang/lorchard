package com.ysdevelop.lorchard.api.key;

import com.ysdevelop.lorchard.common.redis.BasePrefix;

/**
 * 
 * @author USER
 *
 */
public class SessionKey extends BasePrefix {

	public SessionKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
    /**
     * 一个月的有效期
     */
	public static SessionKey sessionKey = new SessionKey(30*24*3600, "applet-session");
	
}
