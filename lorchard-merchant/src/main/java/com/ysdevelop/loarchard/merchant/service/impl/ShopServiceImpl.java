package com.ysdevelop.loarchard.merchant.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.ysdevelop.loarchard.merchant.entity.Shop;
import com.ysdevelop.loarchard.merchant.mapper.ShopDao;
import com.ysdevelop.loarchard.merchant.service.ShopService;
import com.ysdevelop.lochard.common.exception.WebServiceException;
import com.ysdevelop.lochard.common.result.CodeMsg;
import com.ysdevelop.lochard.common.utils.Constant;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.mq.constant.MessageKey;
import com.ysdevelop.lorchard.mq.define.MessageType;
import com.ysdevelop.lorchard.mq.service.MessageProducer;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.service.UserService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageProducer messageProducer;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void applyShop(Shop shop) {
		if (shop == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Integer changCount = shopDao.addShop(shop);
		if (changCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		BaseAuth baseAuth = TokenManager.getToken();
		Long merchantId = null;
		if (baseAuth != null&& (merchantId = baseAuth.getId())!=null) {
			userService.updateStatusById(baseAuth.getId());
		} else {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		// 发送队列消息到平台服务器预处理商家的基本信息
		MerchantMessage message = new MerchantMessage();
		message.setCreateTime(new Date());
		message.setMerchantId(merchantId);
		message.setConent("apply Shop");
		message.setMessageType(MessageType.APPLY_SHOP);
		messageProducer.sendMessage(MessageKey.MERCHANT_KEY, JSON.toJSONString(message));
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

}
