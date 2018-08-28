package com.ysdevelop.lorchard.shiro.core.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;



import com.ysdevelop.lochard.common.redis.JedisManager;
import com.ysdevelop.lochard.common.utils.SerializeUtil;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lorchard.shiro.core.cache
 * 
 * @Description TODO
 *
 * @Date 2018年8月28日
 *
 * @Version
 *
 */


@SuppressWarnings("unchecked")
public class JedisShiroCache<K, V> implements Cache<K, V> {

	private Logger logger = Logger.getLogger(this.getClass());

	private static final String REDIS_SHIRO_CACHE = "shiro-cache";
    @Autowired
	private JedisManager jedisManager;

	private String name;

	public JedisShiroCache(String name, JedisManager jedisManager) {
		this.name = name;
		this.jedisManager = jedisManager;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public V get(K key) throws CacheException {
		byte[] byteKey = SerializeUtil.serialize(buildCacheKey(key));
		byte[] byteValue = new byte[0];
		try {
			byteValue = jedisManager.getValueByKey(byteKey);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get value by error");
		}
		return (V) SerializeUtil.deserialize(byteValue);
	}

	@Override
	public V put(K key, V value) throws CacheException {
		V pervious = get(key);
		try {
			jedisManager.saveValueByKey(SerializeUtil.serialize(buildCacheKey(key)), SerializeUtil.serialize(value), 180000);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("put cache error");
		}
		return pervious;
	}

	@Override
	public V remove(K key) throws CacheException {
		V previous = get(key);
		try {
			jedisManager.deleteByKey(SerializeUtil.serialize(buildCacheKey(key)));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("put cache error");
		}
		return previous;
	}

	@Override
	public void clear() throws CacheException {
	

	}

	@Override
	public int size() {
		if(keys()==null){
			return 0;
		}
		return keys().size();
	}

	@Override
	public Set<K> keys() {
		return null;
	}

	@Override
	public Collection<V> values() {
		return null;
	}

	private String buildCacheKey(Object key) {
		return REDIS_SHIRO_CACHE + getName() + ":" + key;
	}

}
