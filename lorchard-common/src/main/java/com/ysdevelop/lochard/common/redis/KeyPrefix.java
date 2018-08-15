package com.ysdevelop.lochard.common.redis;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.common.redis
 * 
 * @Description 设置redis key name 与 redis过期时间 
 *
 * @Date 2018年4月13日
 *
 * @Version
 *
 */
public interface KeyPrefix {
		
	public int expireSeconds();
	
	public String getPrefix();
	
}
