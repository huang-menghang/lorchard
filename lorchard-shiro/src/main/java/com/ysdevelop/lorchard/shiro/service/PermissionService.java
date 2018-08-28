package com.ysdevelop.lorchard.shiro.service;

import java.util.Set;

public interface PermissionService {

	Set<String> listByUserName(String loginName);

}
