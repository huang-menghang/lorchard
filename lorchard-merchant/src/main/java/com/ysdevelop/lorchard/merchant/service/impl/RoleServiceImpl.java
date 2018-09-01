package com.ysdevelop.lorchard.merchant.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.shiro.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Override
	public Set<String> getRoleByUserName(String loginName) {
		Set<String> role = new HashSet<>();
		role.add("admin");
		return role;
	}

}
