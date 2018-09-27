package com.ysdevelop.lorchard.common.utils;


/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lochard.common.utils
 * 
 * @Description 小程序api工具类,获取小程序的公共配置
 * 
 * @Date 2018年8月30日
 * 
 * @Version
 * 
 */
@SuppressWarnings("static-access")
public class WechantAppletApiUtil {

	/* 小程序APPID */
	public static final String APPID = ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.appId");
	/* 微信支付商家号 */
	public static final String MCH_ID = ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.mchId");
	/* 微信支付商家秘钥 */
	public static final String KEY = ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.paySignKey");
	/* 支付成功回调地址 */
	public static final String NOTITY_URL = ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.notifyUrl");
	/* 签名方式,固定值 */
	public static final String SIGNTYPE = "MD5";
	/* 交易类型,小程序JSAPI */
	public static final String TRADAETYPE = ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.tradeType");

	/**
	 * 
	 * @param APPID
	 *            小程序appid
	 * 
	 * @param REDIRECT_URI
	 *            需要请求的连接的url
	 * 
	 * @param SCOPE
	 *            域
	 * 
	 * @return
	 */
	public static String getCode(String APPID, String REDIRECT_URI, String SCOPE) {
		return String.format(ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.getCode"), APPID, REDIRECT_URI, SCOPE);
	}

	/**
	 * 
	 * @param CODE
	 *            参数code,根据code,拼凑url
	 * 
	 * @return
	 */
	public static String getWebAccess(String CODE) {
		return String.format(ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.webAccessTokenhttps"), ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.appId"),
				ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.secret"), CODE);
	}

	/**
	 * 根据小程序api,获取用户的详细信息
	 * 
	 * @param access_token
	 * 
	 * @param openid
	 *            openId
	 * 
	 * @return
	 */
	public static String getUserMessage(String access_token, String openid) {
		return String.format(ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.userMessage"), access_token, openid);
	}

	/**
	 * 获取Token url 请求
	 * 
	 * @return
	 */
	public static String getToken() {
		return String.format(ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.token"), APPID, ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.secret"));
	}

	/**
	 * 
	 * @param access_token
	 * 
	 * @return 获取场景二维码Url
	 */
	public static String getQR(String access_token) {
		return String.format(ResourceUtil.getInstance("lorchard-common").getConfigByName("wx.wxacode"), access_token);
	}

	

}
