package com.ysdevelop.lorchard.shiro.core.cache.impl;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

import com.ysdevelop.lorchard.shiro.core.cache.ShiroCacheManager;


/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.shiro.core.cache.impl
 * 
 * @Description 客户缓存管理
 * 
 * @Date 2018年4月21日
 * 
 * @Version
 * 
 */
public class CustomShiroCacheManager implements CacheManager, Destroyable {

	private ShiroCacheManager shiroCacheManager;

	public ShiroCacheManager getShiroCacheManager() {
		return shiroCacheManager;
	}

	public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
		this.shiroCacheManager = shiroCacheManager;
	}

	@Override
	public void destroy() throws Exception {
		shiroCacheManager.destroy();

	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return getShiroCacheManager().getCache(name);
	}

}
