package com.ysdevelop.lorchard.api.entity;

import java.util.Date;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:16:46 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: 商品轮播图
 *
 * @version V1.0
 *
 */
public class PreviewImagesVo {
	/**轮播图id*/
	private Long id;
	
	/**轮播图路径*/
	private String previewImagePath;
	
	/**轮播图索引*/
	private Long previewImageIndex;
	
	/**商品id*/
	private Long goodsId;
	
	private Long status;
	
	/**创建时间*/
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPreviewImagePath() {
		return previewImagePath;
	}

	public void setPreviewImagePath(String previewImagePath) {
		this.previewImagePath = previewImagePath;
	}

	public Long getPreviewImageIndex() {
		return previewImageIndex;
	}

	public void setPreviewImageIndex(Long previewImageIndex) {
		this.previewImageIndex = previewImageIndex;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
