package com.ysdevelop.lorchard.api.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:16:00 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: 商品
 *
 * @version V1.0
 *
 */
public class GoodsVo {
	
	/**商品ID*/
	private Long id;
	
	/**商家ID*/
	private Long merchantId;
	
	/**所属分类*/
	private Long parentId;
	
	@NotBlank(message = "商品名不能为空")
	private String name;
	
	@NotBlank(message = "描述不能为空")
	private String description;
	
	/**商品轮播图地址*/
	private List<PreviewImagesVo> previewImages;
	
	@NotBlank(message = "库存不能为空")
	private Long stock;
	
	@NotBlank(message = "销量不能为空")
	private Long sales;
	
	@NotBlank(message = "顶级分类不能为空")
	private String parentCategoryName;
	
	/**最低价*/
	private Double minPrice;
	
	/**正常价*/
	private Double originalPrice;
	
	/**商品规格,0表示500g,1表示1份*/
	private Long type;
	
	@NotBlank(message = "商品规格描述不能为空")
	private String specificationsDescription;
	
	/**商品评论*/
	private String comment;
	
	@NotBlank(message = "品牌不能为空")
	private Long brandID;
	
	@NotBlank(message = "批准文号不能为空")
	private String approvalNumber;
	
	/**商品视频路径*/
	private String videoPath;
	
	@NotBlank(message = "分类名不能为空")
	private String categoryName;

	/**推荐商品*/
	private Long recommend;
	
	/**商品好评数*/
	private Long numberGoodReputation;
	
	/**创建时间*/
	private Date createTime;
	
	/**更新时间*/
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Long getBrandID() {
		return brandID;
	}

	public void setBrandID(Long brandID) {
		this.brandID = brandID;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
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

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public List<PreviewImagesVo> getPreviewImages() {
		return previewImages;
	}

	public void setPreviewImages(List<PreviewImagesVo> previewImages) {
		this.previewImages = previewImages;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Long getRecommend() {
		return recommend;
	}

	public void setRecommend(Long recommend) {
		this.recommend = recommend;
	}

	public Long getNumberGoodReputation() {
		return numberGoodReputation;
	}

	public void setNumberGoodReputation(Long numberGoodReputation) {
		this.numberGoodReputation = numberGoodReputation;
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

	@Override
	public String toString() {
		return "GoodsVo [stock=" + stock + ", sales=" + sales + "]";
	}
	
	
}
