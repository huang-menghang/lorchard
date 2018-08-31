package com.ysdevelop.lorchard.common.redis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.common.utils.SerializeUtil;



/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.shiro.core.cache
 * 
 * @Description 该类用来处理redis缓存session以及权限,登录授权
 * 
 * @Date 2018年4月20日
 * 
 * @Version
 * 
 */
@SuppressWarnings("unchecked")
public class JedisManager {
	/**
	 * 从spring上下文获取jedisPool
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public byte[] getValueByKey(byte[] key) throws Exception {
		Jedis jedis = null;
		byte[] result = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.get(key);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			returnBrokenResource(jedis);
			jedis = null;
			return null;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			returnToPool(jedis);
		}
		return result;

	}

	public void deleteByKey(byte[] key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long result = jedis.del(key);
			logger.info("删除Session: " + result);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			returnBrokenResource(jedis);
			jedis = null;
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			returnToPool(jedis);
		}

	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param expireTime
	 *            根据k-v 保存到redis
	 */
	public void saveValueByKey(byte[] key, byte[] value, int expireTime) throws Exception {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			if (expireTime > 0) {
				jedis.expire(key, expireTime);
			}
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			returnBrokenResource(jedis);
			jedis = null;
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			returnToPool(jedis);
		}

	}

	public Collection<Session> allSession(String redisShiroSession) {
		Jedis jedis = null;
		Set<Session> sessions = new HashSet<Session>();
		try {
			jedis = jedisPool.getResource();
			Set<byte[]> byteKeys = jedis.keys((Constant.REDIS_SHIRO_ALL).getBytes());
			if (byteKeys != null && byteKeys.size() > 0) {
				for (byte[] bs : byteKeys) {
					Session obj = SerializeUtil.deserialize(jedis.get(bs), Session.class);
					if (obj instanceof Session) {
						sessions.add(obj);
					}

				}

			}
		} catch (JedisConnectionException e) {
			e.printStackTrace();
			returnBrokenResource(jedis);
			jedis = null;
			return null;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			returnToPool(jedis);
		}

		return sessions;
	}

	private void returnBrokenResource(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
			jedisPool.returnResource(jedis);
		}
	}

	private void returnToPool(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
			jedisPool.returnResource(jedis);
		}
	}
}
