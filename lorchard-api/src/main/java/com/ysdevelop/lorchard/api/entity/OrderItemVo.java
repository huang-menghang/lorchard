package com.ysdevelop.lorchard.api.entity;

import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:16:32 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: 订单商品
 *
 * @version V1.0
 *
 */
public class OrderItemVo {

	private Long id;

	private String orderNo;

	private Long merchantId;
	
	private Long memberId;
	
	private Long goodsId;

	private String goodsName;

	private Double goodsPrice;
	
	private String goodsType;

	private List<PreviewImagesVo> previewImages;

	private Long parentId;

	private String parentCategoryName;

	private Integer itemNum;

	private Double itemPrice;

	private Date createTime;

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Double getGoodsPrice() {
		return goodsPrice;
	}
	
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public List<PreviewImagesVo> getPreviewImages() {
		return previewImages;
	}

	public void setPreviewImages(List<PreviewImagesVo> previewImages) {
		this.previewImages = previewImages;
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

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
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

	@Override
	public String toString() {
		return "OrderItemVo [id=" + id + ", orderNo=" + orderNo + ", memberId=" + memberId + ", goodsId=" + goodsId
				+ ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice + ", previewImages=" + previewImages
				+ ", parentId=" + parentId + ", parentCategoryName=" + parentCategoryName + ", itemNum=" + itemNum
				+ ", itemPrice=" + itemPrice + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
	
}
