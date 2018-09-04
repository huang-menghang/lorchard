package com.ysdevelop.lorchard.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.SystemAccessLogVo;
import com.ysdevelop.lorchard.api.mapper.ApiSystemAccessLogDao;
import com.ysdevelop.lorchard.api.service.ApiSystemAccessLogService;

/**
 * 
 * @author USER
 *
 */
@Service
public class ApiSystemAccessLogServiceImpl implements ApiSystemAccessLogService {
	
	@Autowired
	private ApiSystemAccessLogDao accessLogDao;
	
	@Override
	public void addSystemAccessLog(SystemAccessLogVo accessLog) {
		// TODO Auto-generated method stub
		accessLogDao.addSystemAccessLog(accessLog);
	}

}
