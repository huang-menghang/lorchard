package com.ysdevelop.lorchard.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.MerchantBannerVo;
import com.ysdevelop.lorchard.api.service.ApiMerchantBannerService;
import com.ysdevelop.lorchard.common.result.Result;

/**
 * 
 * @author USER
 *
 */
@RestController
@RequestMapping(value = "/merchantBanner")
public class ApiMerchantBannerController {
	
	@Autowired
	private ApiMerchantBannerService apiMerchantBannerService;
	
	@RequestMapping(value=" ",method = RequestMethod.GET)
	public Result<List<MerchantBannerVo>> getMerchantBanner(Long merchantId){
		List<MerchantBannerVo> apiMerchantBanner = apiMerchantBannerService.list(merchantId);
		return Result.successData(apiMerchantBanner);
	}
}
