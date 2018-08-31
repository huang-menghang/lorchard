package com.ysdevelop.lorchard.common.observer;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lochard.common.observer
 * 
 * @Description 观察者模式,观察者主题
 *
 * @Date 2018年8月29日
 *
 * @Version
 *
 */
public interface Subject {

	/**
	 * 添加观察者
	 * @param observer 观察者
	 *
	 */
	void addObserver(Observer observer);
	
	/**
	 * 删除观察者
	 * @param observer
	 *
	 *
	 */
	void deleteObserver(Observer observer);
	
	/**
	 * 设置主题发生变化
	 */
	void setChange();
	 
	/**
	 * 清除变化
	 */
	void clearChange();
	
	/**
	 * 通知观察者,并传递参数 
	 * @param arg
	 *
	 *
	 */
	void notifyObserver(Object arg);
	
	
}
