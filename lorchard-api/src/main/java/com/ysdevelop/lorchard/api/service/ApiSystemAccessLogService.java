package com.ysdevelop.lorchard.api.service;

import com.ysdevelop.lorchard.api.entity.SystemAccessLogVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiSystemAccessLogService {

	/**
	 * 插入系统日志
	 * 
	 * @param accessLog
	 */
	void addSystemAccessLog(SystemAccessLogVo accessLog);

}
