package com.ysdevelop.lorchard.shiro.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Role {
	
	private Long id;
	/** 角色名称 **/
	@NotBlank(message = "角色名称不能为空")
	private String name;
	/** 角色所拥有的权限集合 **/
	private List<Permission> userPermissions;
	
	/** 描述  **/
	private String description;
	@NotEmpty(message = "请选择该角色所拥有的权限")
	/** 角色所拥有的权限ID集合 **/
	private Long ids[];
	
	private Date createTime ;
	
	private Date updateTime ;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getUserPermissions() {
		return userPermissions;
	}

	public void setUserPermissions(List<Permission> userPermissions) {
		this.userPermissions = userPermissions;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
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
