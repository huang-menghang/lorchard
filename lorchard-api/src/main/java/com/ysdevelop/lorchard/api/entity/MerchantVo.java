package com.ysdevelop.lorchard.api.entity;

import java.util.Date;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:16:27 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: 商家
 *
 * @version V1.0
 *
 */
public class MerchantVo {
	private Long id;
	
	private Long merchantId;
	
	private String name;
	/**
	 * 温馨提示 滑动标题
	 */
	private String shopPrompt;
	
	private String shopLogo;
	
	private String shopDelivery;
	
	private Date createTime;

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

	public String getShopPrompt() {
		return shopPrompt;
	}

	public void setShopPrompt(String shopPrompt) {
		this.shopPrompt = shopPrompt;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopDelivery() {
		return shopDelivery;
	}

	public void setShopDelivery(String shopDelivery) {
		this.shopDelivery = shopDelivery;
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
