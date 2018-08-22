package com.ysdevelop.loarchard.merchant.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.shiro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Override
	public Set<String> getRoleByUserName(String loginName) {
		return null;
	}

}
