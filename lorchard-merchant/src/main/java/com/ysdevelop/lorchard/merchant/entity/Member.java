package com.ysdevelop.lorchard.merchant.entity;
/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 会员实体类
 * 
 */
import java.util.Date;

public class Member {
	
	/**
	 * 会员id 
	 */
	private Long id;
	
	/**
	 * 商家id
	 */
	private Long merchantId;
	
	/**
	 * 会员性别 
	 */
	private Integer gender;
	
	/**
	 * 手机号 
	 */
	private String mobile;

	/**会员名称
	 * 昵称,二进制保存emoji表情
	 * */
	private byte[] nickname;
	/**
	 * 昵称显示
	 * */
	private String nicknameStr;
	
	private String openid;
	
	/**
	 *注册ip 
	 */
	private String registerIp;

	private String avatar;
	
	/**
	 *语言 
	 */
	private String language;
	
	private String country;

	private String city;

	/**pro*/
	private String province;

	/**状态*/
	private Integer status;
	/**
	 * 创建时间
	 * */
	private Date createTime;
	
	/**
	 *修改状态 
	 */
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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public byte[] getNickname() {
		return nickname;
	}

	public void setNickname(byte[] nickname) {
		this.nickname = nickname;
	}

	public String getNicknameStr() {
		this.nicknameStr = new String(nickname);
		return nicknameStr;
	}

	public void setNicknameStr(String nicknameStr) {
		this.nicknameStr = nicknameStr;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
