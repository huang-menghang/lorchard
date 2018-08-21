package com.ysdevelop.lorchard.shiro.core;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;






import com.ysdevelop.lochard.common.redis.JedisManager;
import com.ysdevelop.lochard.common.utils.SerializeUtil;
import com.ysdevelop.lorchard.shiro.core.session.CustomSessionManager;
import com.ysdevelop.lorchard.shiro.core.session.SessionStatus;


/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.shiro.core
 * 
 * @Description 将shiro session 放在redis中,做到分布式缓存
 * 
 * @Date 2018年4月20日
 * 
 * @Version
 * 
 */

@SuppressWarnings("unchecked")
public class RedisSessionDao extends AbstractSessionDAO {

	public static final String REDIS_SHIRO_SESSION = "ysdevelop-shiro-session:";

	public static final String REDIS_SHIRO_ALL = "*ysdevelop-shiro-session:*";
	@Autowired
	private JedisManager jedisManager;

	private Logger logger = Logger.getLogger(RedisSessionDao.class);


	@Override
	public void update(Session session) throws UnknownSessionException {
		save(session);
	}

	// 删除session
	@Override
	public void delete(Session session) {
		if (session == null) {
			logger.error("session can't be null");
			return;
		}
		Serializable id = session.getId();
		if (id == null) {
			throw new NullPointerException("sessionid is null");
		}
		try {
			jedisManager.deleteByKey(SerializeUtil.serialize(buildRedisSessionKey(id)));
		} catch (Exception e) {
			logger.error("delete session wrong");
		}

	}

	@Override
	public Collection<Session> getActiveSessions() {
        Collection<Session> sessions = null;
        try{
        	sessions = jedisManager.allSession(REDIS_SHIRO_ALL);
        }catch(Exception e){
        	logger.error("get sessions error");
        }
       
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		// 将session缓存到redis中
		Serializable sessionId = this.generateSessionId(session);
		logger.info("begin create session");
		this.assignSessionId(session, sessionId);
		this.save(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.error("session id is null");
			throw new NullPointerException("session is empty");
		}
		Session session = null;
		try {
			byte[] value = jedisManager.getValueByKey(SerializeUtil.serialize(buildRedisSessionKey(sessionId)));
			session = SerializeUtil.deserialize(value, Session.class);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get session can't reach");
		}
		return session;
	}

	/**
	 * 
	 * @param session
	 * 
	 *            创建session 时候需要将session放在redis中
	 */
	private void save(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session id is null");
			throw new NullPointerException("session is empty");
		}
		try {
			byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
			// 如果为设置状态则需要将
			if (session.getAttribute(CustomSessionManager.SESSION_STATUS) == null) {
				SessionStatus sessionStatus = new SessionStatus();
				session.setAttribute(CustomSessionManager.SESSION_STATUS, sessionStatus);
			}

			byte[] value = SerializeUtil.serialize(session);
			// 将session缓存在redis中,失效时间与session失效时间同步
			jedisManager.saveValueByKey(key, value, (int) (session.getTimeout() / 1000));

		} catch (Exception e) {
			logger.error("session save failed");
		}

	}

	// 创建session 的缓存中的key值
	private String buildRedisSessionKey(Serializable sessionId) {
		return REDIS_SHIRO_SESSION + sessionId;
	}

}
