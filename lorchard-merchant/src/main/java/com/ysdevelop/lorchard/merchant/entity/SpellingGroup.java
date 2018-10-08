package com.ysdevelop.lorchard.merchant.entity;

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
public class SpellingGroup extends Activity{
	
	
	/**
	 * 拼团id
	 */
	private Long spellingGroupId;
	
	/**
	 * 商品
	 */
	private List<Goods> goods;
	
	/**
	 *商品id 
	 */
	private List<Long> goodsId;
	
	/**
	 * 拼团人数
	 */
	private Long spellingGroupNumber;
	
	
	
	public List<Long> getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(List<Long> goodsId) {
		this.goodsId = goodsId;
	}

	public Long getSpellingGroupNumber() {
		return spellingGroupNumber;
	}

	public void setSpellingGroupNumber(Long spellingGroupNumber) {
		this.spellingGroupNumber = spellingGroupNumber;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public Long getSpellingGroupId() {
		return spellingGroupId;
	}

	public void setSpellingGroupId(Long spellingGroupId) {
		this.spellingGroupId = spellingGroupId;
	}
	
}
