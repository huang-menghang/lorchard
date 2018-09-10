package com.ysdevelop.lorchard.api.entity;

import java.util.Date;

import com.ysdevelop.lorchard.common.utils.Constant;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:16:52 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public class SystemAccessLogVo {
	private Long id;
    /** 商家id **/
	private Long merchantId;
    /** 会员id **/
	private Long memberId;
    /** 产品id **/
	private Long goodsId;
	/** 日志类型  **/
	private Constant.SystemLogType logType;
    /** 描述 **/
	private String description;
    /** 创建时间  **/
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
	
	public Constant.SystemLogType getLogType() {
		return logType;
	}

	public void setLogType(Constant.SystemLogType logType) {
		this.logType = logType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
