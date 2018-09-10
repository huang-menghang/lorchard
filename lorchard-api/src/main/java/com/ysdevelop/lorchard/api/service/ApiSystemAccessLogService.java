package com.ysdevelop.lorchard.api.service;

import com.ysdevelop.lorchard.api.entity.SystemAccessLogVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:20:41 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
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
