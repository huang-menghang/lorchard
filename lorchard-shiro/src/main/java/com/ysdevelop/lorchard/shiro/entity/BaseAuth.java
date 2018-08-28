package com.ysdevelop.lorchard.shiro.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lorchard.shiro.entity
 * 
 * @Description 用户基类
 *
 * @Date 2018年8月27日
 *
 * @Version
 *
 */

public class BaseAuth implements Serializable {

	/**
	 *  序列化接口实现
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "登录名不能为空")
	private String loginName;

	@NotBlank(message = "用户名不能为空")
	@Length(min = 6, max = 16, message = "密码长度必须在6-16之间")
	private transient String password;
	/** 盐值 **/
	private String salt;
	/** 角色id */
	@NotNull(message = "用户角色不能为空")
	private Long roleId;

	private Set<String> roleSet;
	/** 用户权限集合 **/
	private Set<String> permissionSet;
	/** 用户状态 0未激活,1激活  **/
	private Integer status;
	
	private Date createTime;
	
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
