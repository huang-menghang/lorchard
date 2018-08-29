package com.ysdevelop.lorchard.mq.define;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.mq.define
 * 
 * @Description 消息队列的类型
 *
 * @Date 2018年4月24日
 *
 * @Version
 *
 */
public enum MessageType {

	APPLY_SHOP(0, "申请店铺");
	private int index;
	private String value;

	private MessageType(Integer index, String value) {
		this.index = index;
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
		

	
}
