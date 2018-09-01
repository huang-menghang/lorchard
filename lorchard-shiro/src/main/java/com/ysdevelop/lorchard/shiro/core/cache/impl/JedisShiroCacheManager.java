package com.ysdevelop.lorchard.shiro.core.cache.impl;

import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import com.ysdevelop.lorchard.common.redis.JedisManager;
import com.ysdevelop.lorchard.shiro.core.cache.JedisShiroCache;
import com.ysdevelop.lorchard.shiro.core.cache.ShiroCacheManager;




public class JedisShiroCacheManager implements ShiroCacheManager {
    
	@Autowired
	private JedisManager jedisManager;

	@Override
	public <K, V> Cache<K, V> getCache(String name) {
		return new JedisShiroCache<K, V>(name, getJedisManager());
	}

	@Override
	public void destroy() {

	}

	public JedisManager getJedisManager() {
		return jedisManager;
	}


}
