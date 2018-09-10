package com.ysdevelop.lorchard.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.OrderLogVo;
import com.ysdevelop.lorchard.api.mapper.ApiOrderLogDao;
import com.ysdevelop.lorchard.api.service.ApiOrderLogService;

/** 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 下午5:48:31 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiOrderLogServiceImpl implements ApiOrderLogService {
	
	@Autowired
	private ApiOrderLogDao orderLogDao;
	
	@Override
	public void addOrderLog(OrderLogVo orderLog) {
		orderLogDao.addOrderLog(orderLog);
	}

}
