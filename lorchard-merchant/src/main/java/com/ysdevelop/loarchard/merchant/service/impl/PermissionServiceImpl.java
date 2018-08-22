package com.ysdevelop.loarchard.merchant.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.shiro.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService {

	@Override
	public Set<String> listByUserName(String loginName) {
		return null;
	}

}
