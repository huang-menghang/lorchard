package com.ysdevelop.lorchard.mq.service;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	Logger logger = Logger.getLogger(this.getClass());

	public void sendMessage(String queueKey, Object object) {
		if (queueKey == null || object != null) {
			amqpTemplate.convertAndSend(queueKey, object);
		} else {
			logger.error("queueKey is null or object param is null");
			throw new NullPointerException("queueKey is null or object param is null");
		}

	}
}
