package com.ysdevelop.lorchard.shiro.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class Permission {
	private Long id;

	/** 权限名称 **/
	@NotBlank(message = "权限名称不能为空")
	private String name;
	/** 权限等级 **/
	@Range(min = 1, max = 10, message = "权限等级在1-10之间")
	private Integer level;
	/** 权限url **/
	@NotBlank(message = "权限url不能为空")
	private String url;
	/** 权限描述 **/
	@NotBlank(message = "权限描述不能为空")
	private String description;
	/** 权限父类id **/
	private Long parentId;
	/** 权限链接 **/
	@NotBlank(message = "权限链接不能为空")
	private String href;

	private Date createTime;

	private Date updateTime;

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
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
