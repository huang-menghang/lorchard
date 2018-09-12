package com.ysdevelop.lorchard.websocket.key;

import com.ysdevelop.lorchard.common.redis.BasePrefix;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lorchard.websocket.key
 * 
 * @Description Web
 *
 * @Date 2018年9月12日
 *
 * @Version
 *
 */
public class WebSocketMeassgeKey extends BasePrefix {

	public WebSocketMeassgeKey(int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}

	public static WebSocketMeassgeKey socketMeassgeKey = new WebSocketMeassgeKey(0, "websocketMessage");

}
