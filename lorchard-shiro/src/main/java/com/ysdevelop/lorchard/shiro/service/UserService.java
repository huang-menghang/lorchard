package com.ysdevelop.lorchard.shiro.service;

import com.ysdevelop.lorchard.shiro.entity.BaseAuth;

public interface UserService {
	 BaseAuth getUserByName(String name);
}
