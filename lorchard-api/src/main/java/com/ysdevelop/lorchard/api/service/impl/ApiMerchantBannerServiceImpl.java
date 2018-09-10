package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.lorchard.api.entity.MerchantBannerVo;
import com.ysdevelop.lorchard.api.mapper.ApiMerchantBannerDao;
import com.ysdevelop.lorchard.api.service.ApiMerchantBannerService;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:21:22 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiMerchantBannerServiceImpl implements ApiMerchantBannerService {
	
	@Autowired
	private ApiMerchantBannerDao apiMerchantBannerDao;
	
	@Override
	public List<MerchantBannerVo> list(Long merchantId) {
		return apiMerchantBannerDao.list(merchantId);
	}

}
