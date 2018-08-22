package com.ysdevelop.lorchard.shiro.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ysdevelop.lorchard.shiro.core.RedisSimpleByteSource;
import com.ysdevelop.lorchard.shiro.core.cache.impl.JedisShiroCacheManager;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.service.PermissionService;
import com.ysdevelop.lorchard.shiro.service.RoleService;
import com.ysdevelop.lorchard.shiro.service.UserService;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.shiro.realm
 * 
 * @Description TODO
 * 
 * @Date 2018年4月23日
 * 
 * @Version
 * 
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private  JedisShiroCacheManager cacheManager;


	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		String name = (String) authenticationToken.getPrincipal();
		BaseAuth user = userService.getUserByName(name);
		if (user == null) {
			throw new UnknownAccountException();
		}
		Set<String> roles = roleService.getRoleByUserName(user.getLoginName());
		user.setRoleSet(roles);
		Set<String> permissions = permissionService.listByUserName(user.getLoginName());
		user.setPermissionSet(permissions);
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPswd(),
				new RedisSimpleByteSource(user.getSalt()), getName());
		return authenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		BaseAuth user = (BaseAuth) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(user.getRoleSet());
		authorizationInfo.setStringPermissions(user.getPermissionSet());
		return authorizationInfo;
	}

	/**
	 *
	 */
	public void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除用户的授权信息
	 * 
	 * @param username
	 */
	public void clearAuthorizationInfo(String username) {
		Cache<Object, Object> cache = cacheManager.getCache("com.ysdevelop.lorchard.shiro.realm.UserRealm.authenticationCache");
		cache.remove(username);
	}
	
	public static UserRealm getUserRealm() {
		RealmSecurityManager realmSecurityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		UserRealm userRealm = (UserRealm) realmSecurityManager.getRealms().iterator().next();
		return userRealm;
	}
}
