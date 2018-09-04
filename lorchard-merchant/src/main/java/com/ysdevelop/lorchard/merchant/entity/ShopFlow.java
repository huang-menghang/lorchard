package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lorchard.merchant.entity
 * 
 * @Description 店铺每日流量
 * 
 * @Date 2018年8月31日
 * 
 * @Version
 * 
 */
public class ShopFlow {

	private Long id;
    
	/** 商家id **/
	private Long merchantId;
	
	/** 浏览量 **/
	private Integer pageView;
	/** 访客数 **/
	private Integer visitorNumber;
	/** 商品浏览量 **/
	private Integer goodsView;
	/** 商品访问数 **/
	private Integer goodsAceessNumber;
	/** 日期 **/
	private Date date;
	/** 创建时间 **/
	private Date createTime;

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

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public Integer getVisitorNumber() {
		return visitorNumber;
	}

	public void setVisitorNumber(Integer visitorNumber) {
		this.visitorNumber = visitorNumber;
	}

	public Integer getGoodsView() {
		return goodsView;
	}

	public void setGoodsView(Integer goodsView) {
		this.goodsView = goodsView;
	}

	public Integer getGoodsAceessNumber() {
		return goodsAceessNumber;
	}

	public void setGoodsAceessNumber(Integer goodsAceessNumber) {
		this.goodsAceessNumber = goodsAceessNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}