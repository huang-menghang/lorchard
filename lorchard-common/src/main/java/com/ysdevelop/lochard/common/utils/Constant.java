package com.ysdevelop.lochard.common.utils;


/**
 * 常量
 * 
 * @author laohuang
 * @email
 * @date
 */
public class Constant {
	/**
	 * 超级管理员ID
	 */
	public static final int SUPER_ADMIN = 1;

	/**
	 * 判断是否为0
	 */
	public static final Integer DEFALULT_ZERO = 0;

	/**
	 * 判断是否为0
	 */
	public static final Integer DEFALULT_ONE = 1;
	
	/**
	 * 判断是否为2
	 */
	public static final Integer DEFALULT_TWO = 2;

	/**
	 * ORACLE、MYSQL
	 */
	public static final String USE_DATA = "MYSQL";

	/**
	 * WEBSOCKET
	 * 
	 */
	public static final String WEB_SOCKET = "websocket";

	/**
	 * RESPONSE_GOOD_STATUS
	 */
	public static final Integer RESPONSE_GOOD = 200;

	/**
	 * REDIS_SHIRO_ALL
	 */
	public static final String REDIS_SHIRO_ALL = "*ysdevelop-shiro-session:*";
    
	/**
     * KAPTCHA_SESSION_KEY 
     */
	public static String KAPTCHA_SESSION_KEY = "kaptcha-session-key";
	
	
	
	/**
	 * 是否类型
	 */
	public enum YESNO {
		/**
		 * 是
		 */
		YES(0),
		/**
		 * 否
		 */
		NO(1);
		private Integer value;

		private YESNO(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public enum MemberStatus {
		UNACTIVATED(0, "未激活"), ACTIVATED(1, "激活"), GUEST(2, "豆客"), AGENT(3, "代理");
		private int index;
		private String value;

		private MemberStatus(Integer index, String value) {
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

	// 上传文件的类型
	public enum FileType {
		IMAGE(0, "图片"), VIDEO(1, "视频");
		private int index;
		private String chineseName;

		private FileType(int index, String chineseName) {
			this.index = index;
			this.chineseName = chineseName;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getChineseName() {
			return chineseName;
		}

		public void setChineseName(String chineseName) {
			this.chineseName = chineseName;
		}

	}

	// 上传文件的类型
	public enum HttpMethod {
		GET(0, "GET"), POST(1, "POST");
		private int index;
		private String value;

		private HttpMethod(int index, String value) {
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

	

	// 预约订单类型
	public enum OrderDepositType {
		UNPAYMENYT(0, "待付款"), UNDELIVERY(1, "待派送"), UNWAITINGORDER(2, "待接单"), WAITINGORDER(3, "已接单"), FINISHED(4, "已完成"), CLOSED(5, "关闭");
		private int index;
		private String value;

		private OrderDepositType(int index, String value) {
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

	// 订单类型
	public enum OrderType {
		UNPAYMENYT(0, "未付款"), UNSEND(1, "待发货"), UNRECEIVED(2, "待收货"), FINISHED(3, "已完成"), CLOSED(4, "关闭");
		private int index;
		private String value;

		private OrderType(int index, String value) {
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


}
