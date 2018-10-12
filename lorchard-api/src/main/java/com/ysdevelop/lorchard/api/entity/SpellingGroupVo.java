package com.ysdevelop.lorchard.api.entity;

import java.util.List;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.entity
 *
 * @Description 活动:拼团
 *
 * @Date 2018年10月8日
 *
 * @Version
 */
public class SpellingGroupVo extends ActivityVo{
	
	/**
	 * 商品
	 */
	private List<GoodsVo> goods;
	
	/**
	 *商品id 
	 */
	private List<Long> goodsId;
	
	public List<Long> getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(List<Long> goodsId) {
		this.goodsId = goodsId;
	}

	public List<GoodsVo> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsVo> goods) {
		this.goods = goods;
	}
}
