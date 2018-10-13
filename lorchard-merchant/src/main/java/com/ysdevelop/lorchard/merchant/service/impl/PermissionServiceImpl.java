package com.ysdevelop.lorchard.merchant.service.impl;
/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.shiro.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Override
	public Set<String> listByUserName(String loginName) {
		Set<String> permissions = new HashSet<>();
		permissions.add("user:add");
		return permissions;
	}

}
