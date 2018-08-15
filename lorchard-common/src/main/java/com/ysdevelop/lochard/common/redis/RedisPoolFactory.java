package com.ysdevelop.lochard.common.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.common.redis
 * 
 * @Description redis Factory 类用于获取jedisPool
 * 
 * @Date 2018年4月13日
 * 
 * @Version
 * 
 */
public class RedisPoolFactory {

	private RedisConfig redisConfig;

	public void setRedisConfig(RedisConfig redisConfig) {
		this.redisConfig = redisConfig;
	}
    
	public JedisPool JedisPoolFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
		poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
		poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
		poolConfig.setTimeBetweenEvictionRunsMillis(redisConfig.getTimeBetweenEvictionRunsMillis()*1000);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setNumTestsPerEvictionRun(redisConfig.getNumTestsPerEvictionRun());
		poolConfig.setMinEvictableIdleTimeMillis(redisConfig.getMinEvictableIdleTimeMillis());
		JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout() * 1000,
				redisConfig.getPassword(), 0);
		return jp;
	}

}
