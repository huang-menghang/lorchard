package com.ysdevelop.lochard.common.exception;

import com.ysdevelop.lochard.common.result.CodeMsg;

public class WebServiceException extends RuntimeException {

	/**
	 * 
	 * @author OldHuang
	 * 
	 * @Package com.ysdevelop.result
	 * 
	 * @Description: 业务逻辑异常
	 * 
	 * @date 2018年4月2日
	 * 
	 * @version
	 * 
	 */
	private static final long serialVersionUID = 7147069598126673797L;

	private CodeMsg codeMsg;

	public WebServiceException(CodeMsg codeMsg) {
		super(codeMsg.getMsg());
		this.codeMsg = codeMsg;
	}

	public CodeMsg getCm() {
		return codeMsg;
	}

}

