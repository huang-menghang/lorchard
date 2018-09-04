package com.ysdevelop.lorchard.api.util;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.smurfs.util
 * 
 * @Description 该类主要提供三方api
 * 
 * @Date 2018年5月18日
 * 
 * @Version
 * 
 */
@SuppressWarnings("static-access")
public class ApiMemberUtils {
	
	/**
	 * getOpenid
	 */
	public static final String GET_OPENID = ResourceUtil.getInstance("lorchard-api").getConfigByName("wx.getOpenid");
	
	/**
	 * 小程序APPID
	 */
	public static final String APPID = ResourceUtil.getInstance("lorchard-api").getConfigByName("wx.appId");
	
	/**
	 * 小程序SECRET
	 */
	public static final String SECRET = ResourceUtil.getInstance("lorchard-api").getConfigByName("wx.secret");
	
	/**
	 * grantType
	 */
	public static final String GRANT_TYPE = ResourceUtil.getInstance("lorchard-api").getConfigByName("wx.grantType");
	
	/**
	 * 微信支付商家号
	 */
	//public static final String MCH_ID = ResourceUtil.getInstance("lorchard-api").getConfigByName("wx.mchId");
	/**
	 * 微信支付商家秘钥
	 */
	//public static final String KEY = ResourceUtil.getInstance("lorchard-api").getConfigByName("wx.paySignKey");
	/**
	 * 支付成功回调地址
	 */
	//public static final String NOTITY_URL = ResourceUtil.getInstance("lorchard-api").getConfigByName("wx.notifyUrl");
    /**
     * 签名方式,固定值
     */
	//public static final String SIGNTYPE = "MD5";
    /**
     * 交易类型,小程序JSAPI
     */
	//public static final String TRADAETYPE = ResourceUtil.getInstance("smurfs-admin").getConfigByName("wx.tradeType");
	
	/**
	 * 替换字符串
	 * @param APPID
	 * @param REDIRECT_URI
	 * @param SCOPE
	 * @return
	 */
//	public static String getCode(String appId, String redirectUrl, String scope) {
//		return String.format(ResourceUtil.getInstance("smurfs-admin").getConfigByName("wx.getCode"), appId, redirectUrl, scope);
//	}

	/**
	 * 替换字符串
	 * @param CODE
	 * @return
	 */
	public static String getWebAccess(String code) {
		return String.format(GET_OPENID,APPID,SECRET,GRANT_TYPE,code);
	}

	/**
	 * 替换字符串
	 * @param access_token
	 * @param openid
	 * @return
	 */
//	public static String getUserMessage(String accessToken, String openid) {
//		return String.format(ResourceUtil.getInstance("smurfs-admin").getConfigByName("wx.userMessage"), accessToken, openid);
//	}

	/**
	 * 替换字符串
	 * @return
	 */
//	public static String getToken() {
//		return String.format(ResourceUtil.getInstance("smurfs-admin").getConfigByName("wx.token"), APPID,ResourceUtil.getInstance("smurfs-admin").getConfigByName("wx.secret"));
//	}
	
	/**
	 * 替换字符串
	 * @param access_token
	 * @return
	 */
//	public static String getQR(String accessToken) {
//		return String.format(ResourceUtil.getInstance("smurfs-admin").getConfigByName("wx.wxacode"),accessToken);
//	}
	
	public static JSONObject getDecodeInfo(String encryptedData, String sessionKey, String iv) {
		// 被加密的数据
		byte[] dataByte = Base64.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decode(sessionKey);
		// 偏移量
		byte[] ivByte = Base64.decode(iv);
		try {
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			//初始化
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidParameterSpecException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return null;

	}

}
