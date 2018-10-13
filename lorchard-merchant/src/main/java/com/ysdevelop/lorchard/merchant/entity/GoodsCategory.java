package com.ysdevelop.lorchard.merchant.entity;
/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 商品分类实体类
 * 
 */
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class GoodsCategory {
	
	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 商家id
	 */
	private Long merchantId;
    
	/**
	 * 类别名称
	 */
	@NotBlank(message = "分类不能为空")
	private String name;
	
	/**
	 * 类别描述
	 */
	@NotBlank(message = "描述不能为空")
	private String description;

	/**上级分类*/
	@NotNull(message="上级分类不能为空")
	private Long parentId;
	
	/**上级分类名称*/
	private String parentCategoryName;
	
	/**
	 * 图片路径
	 */
	@NotBlank(message = "分类图片不能为空")
	private String imagePath;
	
	/**分类排序,越往上,越大*/
	@NotNull(message="排列索引不能为空")
	private Integer index;
	
	/**
	 * 类别等级 
	 */
	private Integer level;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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
