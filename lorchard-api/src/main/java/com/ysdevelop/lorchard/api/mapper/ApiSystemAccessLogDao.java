package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.SystemAccessLogVo;

/**
 * 
 * @author USER
 *
 */
public interface ApiSystemAccessLogDao {

	/**
	 * 插入系统日志
	 * 
	 * @param accessLog
	 */
	void addSystemAccessLog(SystemAccessLogVo accessLog);

}
