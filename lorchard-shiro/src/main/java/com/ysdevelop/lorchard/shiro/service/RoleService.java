package com.ysdevelop.lorchard.shiro.service;

import java.util.Set;

public interface RoleService {

	Set<String> getRoleByUserName(String loginName);

}
