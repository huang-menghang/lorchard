package com.ysdevelop.lorchard.mq.service;

import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.InitializingBean;

import com.ysdevelop.lochard.common.observer.Observer;
import com.ysdevelop.lochard.common.observer.Subject;
import com.ysdevelop.lochard.common.utils.Constant;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.lorchard.mq.service
 * 
 * @Description 商家队列消息消费者
 * 
 * @Date 2018年8月29日
 * 
 * @Version
 * 
 */

public class MerchantMessageConsumer implements MessageListener, Subject,InitializingBean {
	/**
	 * 观察者集合
	 */
	private Vector<Observer> messageObservers = null;
	/**
	 * 消息变化标识位
	 */
	private boolean change = false;

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void onMessage(Message message) {
		logger.info("begin receive message");
		setChange();
		if (message != null) {
			byte[] messageBytes = new byte[1024];
			// 通过主题发生消息到各个观察者
			if ((messageBytes = message.getBody()) != null) {
				notifyObserver(new String(messageBytes));
			} else {
				logger.error("message is null");
				throw new NullPointerException("message is null");
			}
		} else {
			logger.error("message is null");
			throw new NullPointerException("message is null");
		}

	}

	@Override
	public void addObserver(Observer observer) {
		logger.info("start add observer");
		logger.info("hashCode-->"+hashCode());
		if (observer != null) {
			this.messageObservers.add(observer);
		} else {
			logger.error("observer is null");
			throw new NullPointerException("observer is null");
		}
		logger.info("finish add observer");
	}

	@Override
	public void deleteObserver(Observer observer) {
		logger.info("start delete observer");
		if (observer != null) {
			messageObservers.remove(observer);
		} else {
			logger.error("observer is null");
			throw new NullPointerException("observer is null");
		}
		logger.info("finish delete observer ");

	}

	@Override
	public void setChange() {
		this.change = Boolean.TRUE;

	}

	@Override
	public void clearChange() {
		this.change = Boolean.FALSE;
	}

	@Override
	public void notifyObserver(Object message) {
		// 如果说没发变化直接返回
		if (!this.change) {
			return;
		}
		if (message != null) {
			System.out.println("messageConsumer--->"+hashCode());
			System.out.println("messageObservers--->"+this.messageObservers+",messageObservers--->"+messageObservers.size());
			if (this.messageObservers != null && messageObservers.size() > Constant.DEFALULT_ZERO) {
				// 遍历通知观察者
				for (Observer observer : messageObservers) {
					observer.update(this, message);
				}
			} else {
				logger.error("observers is null");
			}
		} else {
			logger.error("message is null");
			throw new NullPointerException("messge is null");
		}
		// 清除变化
		clearChange();

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("hash code --->"+hashCode());
		logger.info("init observers ---> ");
		messageObservers = new Vector<>(10);
	}

}
