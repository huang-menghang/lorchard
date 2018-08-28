package com.ysdevelop.lorchard.shiro.service;

import javax.servlet.http.HttpSession;

import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.vo.LoginVo;

public interface UserService {
	 /** 根据姓名获取用户 **/
	 BaseAuth getUserByName(String name);
	 /** 用户注册功能 */
	 void register(LoginVo loginVo,HttpSession session);
	 /** 登录功能*/
	 void login(LoginVo loginVo);
	 /** 修改用户状态*/
	 void updateStatusById(Long userId);
}
