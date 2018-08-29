package com.ysdevelop.lorchard.shiro.service;

import javax.servlet.http.HttpSession;

import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.vo.LoginVo;

/**
 * 
 * @author oldHuang
 * 
 * @Package
 * 
 * @Description TODO
 * 
 * @Date 2018年8月29日
 * 
 * @Version
 * 
 */
public interface UserService {
	/**
	 * 根据用户名得到用户
	 * 
	 * @param name
	 * 
	 * @return
	 */
	BaseAuth getUserByName(String name);

	/**
	 * 注册
	 * 
	 * @param loginVo
	 *            登录用户
	 * 
	 * @param session
	 *            会话
	 */
	void register(LoginVo loginVo, HttpSession session);

	/**
	 * 登录用户
	 * 
	 * @param loginVo
	 * 
	 */
	void login(LoginVo loginVo);

	/**
	 * 修改用户状态
	 * 
	 * @param userId
	 * 
	 */
	void updateStatusById(Long userId);
}
