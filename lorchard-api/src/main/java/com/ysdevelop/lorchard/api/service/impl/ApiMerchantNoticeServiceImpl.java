package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.MerchantNoticeVo;
import com.ysdevelop.lorchard.api.mapper.ApiMerchantNoticeDao;
import com.ysdevelop.lorchard.api.service.ApiMerchantNoticeService;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:21:27 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
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
