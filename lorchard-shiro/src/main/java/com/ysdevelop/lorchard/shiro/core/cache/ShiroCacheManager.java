package com.ysdevelop.lorchard.shiro.core.cache;

import org.apache.shiro.cache.Cache;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.shiro.core.cache
 * 
 * @Description Shiro 缓存接口,实现此接口来缓存用户的登陆信息
 *
 * @Date 2018年4月21日
 *
 * @Version
 *
 */
public interface ShiroCacheManager {

	<K,V> Cache<K,V> getCache(String name);
	
	void destroy();
	
}
