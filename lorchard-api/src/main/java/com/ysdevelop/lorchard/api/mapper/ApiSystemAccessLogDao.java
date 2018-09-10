package com.ysdevelop.lorchard.api.mapper;

import com.ysdevelop.lorchard.api.entity.SystemAccessLogVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:18:13 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
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
