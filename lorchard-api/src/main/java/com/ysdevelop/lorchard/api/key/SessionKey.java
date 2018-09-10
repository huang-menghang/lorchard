package com.ysdevelop.lorchard.api.key;

import com.ysdevelop.lorchard.common.redis.BasePrefix;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:17:16 
 *
 * @Package com.ysdevelop.lorchard.api.key
 *
 * @Description: TODO
 *
 * @version V1.0
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
