package com.ysdevelop.lorchard.merchant.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.common.utils.HttpClientRequestUtil;
import com.ysdevelop.lorchard.common.utils.JSONHelper;
import com.ysdevelop.lorchard.common.utils.QiniuUtil;
import com.ysdevelop.lorchard.common.utils.ResourceUtil;
import com.ysdevelop.lorchard.common.utils.WechantAppletApiUtil;
import com.ysdevelop.lorchard.merchant.entity.Shop;
import com.ysdevelop.lorchard.merchant.key.AppletAccessTokenKey;
import com.ysdevelop.lorchard.merchant.mapper.ShopDao;
import com.ysdevelop.lorchard.merchant.service.ShopService;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.mq.constant.MessageKey;
import com.ysdevelop.lorchard.mq.define.MessageType;
import com.ysdevelop.lorchard.mq.service.MessageProducer;
import com.ysdevelop.lorchard.shiro.service.UserService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private MessageProducer messageProducer;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void applyShop(Shop shop) {
		if (shop == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Long merchantId = TokenManager.getUserId();
		if (merchantId == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		// 生成商家二维码
		this.createMerchantQr(merchantId, shop);
        shop.setMerchantId(merchantId);
		Integer changCount = shopDao.addShop(shop);
		if (changCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		// 修改商家状态
		userService.updateStatusById(merchantId);
		// 清空用户缓存
	    TokenManager.logout();
		// 发送队列消息到平台服务器预处理商家的基本信息
		// MerchantMessage message = new MerchantMessage();
		// message.setCreateTime(new Date());
		// message.setMerchantId(merchantId);
		// message.setConent("apply Shop");
		// message.setMessageType(MessageType.APPLY_SHOP);
		// messageProducer.sendMessage(MessageKey.MERCHANT_KEY,
		// JSON.toJSONString(message));
	}

	@SuppressWarnings("static-access")
	private void createMerchantQr(Long merchantId, Shop shop) {

		String accessToken = redisService.get(AppletAccessTokenKey.accessTokenKey, "", String.class);
		System.out.println("accessToken"+accessToken);
		// 如果获取Token 为空
		if (accessToken == null) {
			String requestTokenUrl = WechantAppletApiUtil.getToken();
			String result = HttpClientRequestUtil.httpRequest(requestTokenUrl, Constant.HttpMethod.GET, null);
			if (result == null) {
				throw new WebServiceException(CodeMsg.SERVER_ERROR);
			}
			JSONObject jsonResult = null;
			String expiresIn = null;
			try {
				jsonResult = JSONObject.parseObject(result);
				accessToken = jsonResult.getString("access_token");
				expiresIn = jsonResult.getString("expires_in");
				// 将token 缓存到redis中,提高读写效率
				redisService.set(AppletAccessTokenKey.accessTokenKey, "", accessToken);
			} catch (Exception e) {
				logger.error("获取token发送异常");
				e.printStackTrace();
				throw new WebServiceException(CodeMsg.SERVER_ERROR);
			}
			if (accessToken == null || expiresIn == null) {
				logger.error("token 数据为空");
				throw new WebServiceException(CodeMsg.SERVER_ERROR);
			}
		}
		// 生成商家小程序场景二维码

		String qrUrl = WechantAppletApiUtil.getQR(accessToken);
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Long timeNow = System.currentTimeMillis();
		String qrFilePath = ResourceUtil.getInstance("upload").getConfigByName("upload.inviteCode.path");
		String merchantQrFilePath = qrFilePath + File.separator + merchantId + File.separator + timeNow + ".png";
		requestMap.put("width", new Integer(430));
		requestMap.put("page", "pages/index/index");
		requestMap.put("scene", merchantId + "");
		String jsonRequest = JSONHelper.bean2json(requestMap);		
		HttpClientRequestUtil.httpRequestCreateMerchantQr(qrUrl, jsonRequest, merchantQrFilePath);
		// 将图片生成上传到七牛云服务器
		String merchantQr = QiniuUtil.qiniuUpload(merchantQrFilePath);
        // 设置店铺二维码图片路径并删除本地的图片
		if (merchantQr != null) {
			shop.setMerchantQr(merchantQr);
			shop.setMerchantId(merchantId);
			File localQrFile = new File(merchantQrFilePath);
			if (localQrFile.exists()) {
				localQrFile.delete();
			}
		} else {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

	}

	@Override
	public void testMq() {
		Long merchantId = 1L;
		MerchantMessage message = new MerchantMessage();
		message.setCreateTime(new Date());
		message.setMerchantId(merchantId);
		message.setConent("apply Shop");
		message.setMessageType(MessageType.APPLY_SHOP);
		messageProducer.sendMessage(MessageKey.MERCHANT_KEY, JSON.toJSONString(message));

	}
	   /**
	    * 取得运营人姓名
	    * */
		@Override
		public Shop getOperatorName(Long userId) {
			Shop shop = shopDao.getOperatorName(userId);
			return shop;
		}
}
