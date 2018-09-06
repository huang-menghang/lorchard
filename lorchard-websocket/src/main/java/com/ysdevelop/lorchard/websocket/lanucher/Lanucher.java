package com.ysdevelop.lorchard.websocket.lanucher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.websocket.lanucher
 * 
 * @Description Spring 容器启动类
 * 
 * @Date 2018年9月5日
 * 
 * @Version
 * 
 */
public class Lanucher {

	/**
	 * websocket-server main 入口函数
	 * 
	 * @param args
	 */
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {

		// 启动spring 容器

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "lorchard-common.xml", "lorchard-mq.xml",
				"lorchard-websocket.xml" });

	}

}
