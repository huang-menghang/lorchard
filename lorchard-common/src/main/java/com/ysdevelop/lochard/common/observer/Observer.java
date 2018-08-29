package com.ysdevelop.lochard.common.observer;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lochard.common.observer
 * 
 * @Description 观察者模式,观察者定义
 *
 * @Date 2018年8月29日
 *
 * @Version
 *
 */
public interface Observer {
    /**
     * 
     * @param subject 观察者主题
     * 
     * @param arg 传递参数
     */
	void update(Subject subject,Object arg);
	
}
