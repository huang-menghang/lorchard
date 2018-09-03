package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @Package com.ysdevelop.lorchard.merchant.entity
 * @author user
 */

public class Goods {
	/**商品ID*/
	private Long id;
	
	/**商家ID*/
	private Long merchantId;
	
	/**商品名称*/
	@NotBlank(message = "商品名不能为空")
	@Pattern(regexp ="^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "商品名不能有特殊字符")
	private String name;
	
	/**商品描述*/
	@NotBlank(message = "商品描述不能为空")
	@Pattern(regexp ="^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "商品描述不能有特殊字符")
	private String description;
	
	/**状态*/
	private Long status;  
		
	/**商品库存*/
	@NotNull(message="库存不能为空")
	@Min(value=0,message="库存不能小于0")
	private Long stock;
	
	/**商品销量*/
	private Long sales;

	/**商品轮播图*/
	private List<PreviewImages> previewImages;
	
	/**商品顶级分类*/
	private String parentCategoryName;

	/**折后价*/
	@NotNull(message="折后价不能为空")
	@Min(value=0,message="折后价不能小于0")
	private Double minPrice;
	
	/**父类id*/
	private Long parentId;
	
	/**正常价*/
	@NotNull(message="正常价不能为空")
	@Min(value=0,message="正常价不能小于0")
	private Double originalPrice;
	
	/**商品父类分类id*/
	private Long categoryId;
	
	/**创建时间*/
	private Date createTime;
	
	/**更新时间*/
	private Date updateTime;

	/**是否是推荐商品*/
	@NotNull(message="是否推荐不能为空")
	private Long recommend;
	
	/**商品规格,0表示500g,1表示1份*/
	@NotNull(message="商品规格不能为空")
	private Long type;
	
	
	/**商品规格描述*/
	@NotBlank(message = "商品规格描述不能为空")
	private String specificationsDescription;
	
	/**商品评论*/
	private String comment;

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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Long getSales() {
		return sales;
	}

	public void setSales(Long sales) {
		this.sales = sales;
	}

	public List<PreviewImages> getPreviewImages() {
		return previewImages;
	}

	public void setPreviewImages(List<PreviewImages> previewImages) {
		this.previewImages = previewImages;
	}

	public String getParentCategoryName() {
		return parentCategoryName;
	}

	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Long getRecommend() {
		return recommend;
	}

	public void setRecommend(Long recommend) {
		this.recommend = recommend;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getSpecificationsDescription() {
		return specificationsDescription;
	}

	public void setSpecificationsDescription(String specificationsDescription) {
		this.specificationsDescription = specificationsDescription;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
