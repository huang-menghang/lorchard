package com.ysdevelop.lorchard.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ysdevelop.lochard.common.observer.Observer;
import com.ysdevelop.lochard.common.observer.Subject;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.mq.define.MessageType;
import com.ysdevelop.lorchard.mq.service.MerchantMessageConsumer;
import com.ysdevelop.lorchard.service.MerchantService;

@Service
public class MerchantServiceImpl implements MerchantService, Observer, InitializingBean {

	@Autowired
	private MerchantMessageConsumer messageConsumer;

	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void prepareShop(Long merchantId) {
		logger.info("merchantId--->"+merchantId);
		// 生成店铺二维码
		this.generateMerchantQr(merchantId);
		
	}
	
	
	private void generateMerchantQr(Long merchantId){
		
		
		
		
	}

	@Override
	public void update(Subject subject, Object param) {
		if (param != null) {
			try {
				MerchantMessage message = JSON.parseObject((String) param, MerchantMessage.class);
				logger.info("messgae mq ---->"+JSON.toJSONString(message));
				// 获取消息枚举类型
				MessageType type = message.getMessageType();
				switch (type) {
				// 申请店铺
				case APPLY_SHOP:
					// 准备店铺的一些数据
					this.prepareShop(message.getMerchantId());
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("message convert error");
			}
		} else {
			logger.error("message is error");
			throw new NullPointerException("message is null");
		}

	}

	// 添加观察者
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("添加观察者.....");
		messageConsumer.addObserver(this);
	}

}
