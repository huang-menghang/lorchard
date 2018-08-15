package com.ysdevelop.lochard.common.entity;

import java.util.List;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lochard.common.entity
 * 
 * @Description 树性结构数据
 *
 * @Date 2018年8月10日
 *
 * @Version
 *
 */


public class TreeNode {
	private Integer id;//树节点ID
	private String name;//树节点名称
	private Integer parentId;//父节点ID
	private String href;//节点URL
	private Integer level;//等级
	private List<TreeNode> children;//子节点集合
	private boolean spread = false;//节点是否展开
	private boolean checked = false;//节点是否选中
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public List<TreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	public boolean isSpread() {
		return spread;
	}
	public void setSpread(boolean spread) {
		this.spread = spread;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}