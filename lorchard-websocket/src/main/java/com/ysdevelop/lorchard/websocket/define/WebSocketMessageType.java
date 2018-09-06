package com.ysdevelop.lorchard.websocket.define;

public enum WebSocketMessageType {

	ON_LINE(0, "上线");
	private int index;
	private String value;

	private WebSocketMessageType(int index, String value) {
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
