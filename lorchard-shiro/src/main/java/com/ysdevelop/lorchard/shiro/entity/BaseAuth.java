package com.ysdevelop.lorchard.shiro.entity;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class BaseAuth {

	private Long id;

	@NotBlank(message = "用户名不能为空")
	@Length(min = 6, max = 16, message = "密码长度必须在6-16之间")
	private transient String pswd;
	/** 盐值 **/
	private String salt;
	/** 角色id */
	@NotNull(message = "用户角色不能为空")
	private Long roleId;

	private Set<String> roleSet;
	/** 用户权限集合 **/
	private Set<String> permissionSet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Set<String> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<String> roleSet) {
		this.roleSet = roleSet;
	}

	public Set<String> getPermissionSet() {
		return permissionSet;
	}

	public void setPermissionSet(Set<String> permissionSet) {
		this.permissionSet = permissionSet;
	}

}
