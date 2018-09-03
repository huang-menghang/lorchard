package com.ysdevelop.lorchard.merchant.entity;

import java.util.Date;

/**轮播图实体类*/
/**
 * @author user
 *
 */
public class PreviewImages {
	/**轮播图id*/
	private Long id;
	
	/**轮播图路径*/
	private String previewImagePath;
	
	/**轮播图索引*/
	private Long previewImageIndex;
	
	/**商品id*/
	private Long goodsId;
	
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
