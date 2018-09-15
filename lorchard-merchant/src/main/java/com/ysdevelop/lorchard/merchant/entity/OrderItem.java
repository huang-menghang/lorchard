package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;
import java.util.List;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.entity
 *
 * @Description 订单商品实体类
 *
 * @Date 2018年9月10日
 *
 * @Version
 */
public class OrderItem {
	
	/**
	 * 当前订单商品id
	 * */
	private Long id;
	
	/**
	 * 商家id
	 * */
	private Long merchantId;
	
	/**
	 * 订单号
	 * */
	private String orderNo;
	
	/**
	 * 用户id
	 * */
	private Long memberId;
	
	/**
	 * 商品id
	 * */
	private Long goodsId;
	
	/**
	 * 商品名
	 * */
	private String goodsName;
	
	/**
	 * 商品价格
	 * */
	private Double goodsPrice;
	
	/**
	 * 商品轮播图
	 * */
	private List<PreviewImages> previewImages;
	
	/**
	 * 商品顶级分类id
	 * */
	private Long parentId;
	
	/**
	 * 商品顶级分类名
	 * */
	private String parentCategoryName;
	
	/**
	 * 同一商品的商品数
	 * */
	private Integer itemNum;
	
	/**
	 * 同一商品的价格和=同一商品商品数*商品价格
	 * itemPrice=itemNum*goodsPrice
	 * */
	private Double itemPrice;
	
	/**
	 * 创建时间
	 * */
	private Date createTime;
	
	/**
	 * 修改时间
	 * */
	private Date updateTime;

	/**
	 * 商品规格
	 * */
	private String goodsType;
	
	
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

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

	public void setGoodsPrice(Double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public List<PreviewImages> getPreviewImages() {
		return previewImages;
	}

	public void setPreviewImages(List<PreviewImages> previewImages) {
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
	
	
}
