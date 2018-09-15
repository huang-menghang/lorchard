package com.ysdevelop.lorchard.api.entity;

import java.util.Arrays;
import java.util.Date;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:16:13 
 *
 * @Package com.ysdevelop.lorchard.api.entity
 *
 * @Description: 用户
 *
 * @version V1.0
 *
 */
public class MemberVo {

	private Long id;
	
	/**商家id*/
	private Long merchantId;
	
	/**性别标识*/
	private Integer gender;

	private String mobile;

	/**会员名称昵称,二进制保存emoji表情*/
	private byte[] nickname; 
	
	/**昵称显示*/
	private String nicknameStr;
	
	private String openid;

	private String registerIp;
	
	/**头像*/
	private String avatar;

	private String language;

	private String country;

	private String city;
	
	private String token;

	private String province;

	/**状态*/
	private Integer status;
	
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
		if(nickname != null){
			this.nicknameStr = new String(nickname);			
		}
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
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", merchantId=" + merchantId + ", gender=" + gender + ", mobile=" + mobile
				+ ", nickname=" + Arrays.toString(nickname) + ", nicknameStr=" + nicknameStr + ", openid=" + openid
				+ ", registerIp=" + registerIp + ", avatar=" + avatar + ", language=" + language + ", country="
				+ country + ", city=" + city + ", token=" + token + ", province=" + province + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

	
}