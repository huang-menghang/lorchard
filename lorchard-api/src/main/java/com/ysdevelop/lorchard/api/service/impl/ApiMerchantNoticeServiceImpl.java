package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.MerchantNoticeVo;
import com.ysdevelop.lorchard.api.mapper.ApiMerchantNoticeDao;
import com.ysdevelop.lorchard.api.service.ApiMerchantNoticeService;

/**
 * 
 * @author USER
 *
 */
@Service
public class ApiMerchantNoticeServiceImpl implements ApiMerchantNoticeService {
	
	@Autowired
	private ApiMerchantNoticeDao apiMerchantNoticeDao;
	
	@Override
	public List<MerchantNoticeVo> list(Long merchantId) {
		return apiMerchantNoticeDao.list(merchantId);
	}

}
