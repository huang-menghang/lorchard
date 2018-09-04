package com.ysdevelop.lorchard.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.MerchantVo;
import com.ysdevelop.lorchard.api.mapper.ApiMerchantDao;
import com.ysdevelop.lorchard.api.service.ApiMerchantService;

/**
 * 
 * @author USER
 *
 */
@Service
public class ApiMerchantServiceImpl implements ApiMerchantService {
	
	@Autowired
	private ApiMerchantDao apiMerchantDao;
	
	@Override
	public MerchantVo getById(Long merchantId) {
		return apiMerchantDao.getById(merchantId);
	}

}
