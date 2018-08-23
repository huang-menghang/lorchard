package com.ysdevelop.loarchard.merchant.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.service.UserService;
import com.ysdevelop.lorchard.shiro.vo.LoginVo;
@Service
public class MerchantServiceImpl implements UserService {

	@Override
	public BaseAuth getUserByName(String name) {
		return null;
	}

	@Override
	public void register(LoginVo loginVo,HttpSession session) {
		
	}

}
