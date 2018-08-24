package com.ysdevelop.lorchard.shiro.service;

import javax.servlet.http.HttpSession;

import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.vo.LoginVo;

public interface UserService {
	 BaseAuth getUserByName(String name);
	 
	 void register(LoginVo loginVo,HttpSession session);
	 // 登录功能
	 void login(LoginVo loginVo);
}
