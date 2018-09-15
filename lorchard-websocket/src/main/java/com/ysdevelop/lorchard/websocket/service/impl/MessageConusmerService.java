package com.ysdevelop.lorchard.websocket.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import com.ysdevelop.lorchard.websocket.service.MessageConsumer;


public class MessageConusmerService implements MessageConsumer {

	private Logger logger = LoggerFactory.getLogger(MessageConusmerService.class);

	@Override
	public void onMessage(Message message) {
		logger.info("message ---->" + new String(message.getBody()));
	}

}
