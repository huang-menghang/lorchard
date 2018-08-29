package com.ysdevelop.lorchard.shiro.token;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;

import com.ysdevelop.lochard.common.utils.SpringContextUtils;
import com.ysdevelop.lorchard.shiro.core.session.CustomSessionManager;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.realm.UserRealm;
import com.ysdevelop.lorchard.shiro.vo.LoginVo;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lorchard.shiro.token
 * 
 * @Description Token 管理器
 * 
 * @Date 2018年8月29日
 * 
 * @Version
 * 
 */
public class TokenManager {

	// 用户登录管理
	public static final UserRealm USER_REALM = SpringContextUtils.getBean("userRealm", UserRealm.class);

	// 用户session管理
	public static final CustomSessionManager CUSTOMER_SESSION_MANAGER = SpringContextUtils.getBean("customSessionManager",
			CustomSessionManager.class);

	/**
	 * 获取当前登录的用户User对象
	 * 
	 * @return
	 */
	public static BaseAuth getToken() {
		BaseAuth token = (BaseAuth) SecurityUtils.getSubject().getPrincipal();
		return token;
	}

	/**
	 * 获取当前用户的Session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 把值放入到当前登录用户的Session里
	 * 
	 * @param key
	 * @param value
	 */
	public static void setVal2Session(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 从当前登录用户的Session里取值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getVal2Session(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 登录
	 * 
	 * @param user
	 * @param rememberMe
	 * @return
	 */
	public static BaseAuth login(LoginVo loginVo) {
		UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getName(), loginVo.getPassword());
		SecurityUtils.getSubject().login(token);
		return getToken();
	}

	/**
	 * 退出登录
	 */
	public static void logout() {
		BaseAuth token = TokenManager.getToken();
		UserRealm.getUserRealm().clearAuthorizationInfo(token.getLoginName());
		SecurityUtils.getSubject().logout();
	}

	/**
	 * 获取当前用户ID
	 * 
	 * @return
	 */
	public static Long getUserId() {
		return getToken() == null ? null : getToken().getId();
	}

	/**
	 * 根据UserIds 清空权限信息。
	 * 
	 * @param id
	 *            用户ID
	 */
	public static void clearUserAuthByUserId(Long... userIds) {
		if (userIds == null || userIds.length == 0) {
			return;
		}
		List<SimplePrincipalCollection> result = CUSTOMER_SESSION_MANAGER.getSimplePrincipalCollectionByUserId(userIds);
		for (SimplePrincipalCollection simplePrincipalCollection : result) {
			USER_REALM.clearCachedAuthorizationInfo(simplePrincipalCollection);
		}
	}

	/**
	 * 方法重载
	 * 
	 * @param userIds
	 */
	public static void clearUserAuthByUserId(List<Long> userIds) {
		if (null == userIds || userIds.size() == 0) {
			return;
		}
		clearUserAuthByUserId(userIds.toArray(new Long[0]));
	}

}
