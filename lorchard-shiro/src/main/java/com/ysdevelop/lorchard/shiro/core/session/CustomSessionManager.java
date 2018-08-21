package com.ysdevelop.lorchard.shiro.core.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;






import com.ysdevelop.lochard.common.redis.JedisManager;
import com.ysdevelop.lochard.common.utils.StringUtils;
import com.ysdevelop.lorchard.shiro.core.RedisSessionDao;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;



/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.shiro.core.session
 * 
 * @Description 此类用来手动管理redis中的session,可以用来踢出用户的操作,强制下线
 * 
 * @Date 2018年4月21日
 * 
 * @Version
 * 
 */
public class CustomSessionManager {
	public static String SESSION_STATUS = "ysdevelop-online-status";
    @Autowired
	private JedisManager jedisManager;

	private RedisSessionDao redisSessionDao;

	/**
	 * 根据ID查询 SimplePrincipalCollection
	 * @param userIds	用户ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Long...userIds){
		//把userIds 转成Set，好判断
		Set<Long> idset = (Set<Long>) StringUtils.array2Set(userIds);
		//获取所有session
		Collection<Session> sessions = redisSessionDao.getActiveSessions();
		//定义返回
		List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
		for (Session session : sessions) {
			//获取SimplePrincipalCollection
			Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			if(null != obj && obj instanceof SimplePrincipalCollection){
				//强转
				SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
				//判断用户，匹配用户ID。
				obj = spc.getPrimaryPrincipal();
				if(null != obj && obj instanceof BaseAuth){
					BaseAuth user = (BaseAuth)obj;
					//比较用户ID，符合即加入集合
					if(null != user && idset.contains(user.getId())){
						list.add(spc);
					}
				}
			}
		}
		return list;
	}
	
	
	public void setRedisSessionDao(RedisSessionDao redisSessionDao) {
		this.redisSessionDao = redisSessionDao;
	}
	
}
