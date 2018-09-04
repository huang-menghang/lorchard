package com.ysdevelop.lorchard.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysdevelop.lorchard.api.entity.MerchantBannerVo;
import com.ysdevelop.lorchard.api.service.ApiMerchantBannerService;
import com.ysdevelop.lorchard.common.result.Result;

/**
 * 
 * @author USER
 *
 */
@Controller
@RequestMapping(value = "/merchantBanner")
public class ApiMerchantBannerController {
	
	@Autowired
	private ApiMerchantBannerService apiMerchantBannerService;
	
	@RequestMapping(value=" ",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Result<List<MerchantBannerVo>> getMerchantBanner(Long merchantId){
		List<MerchantBannerVo> apiMerchantBanner = apiMerchantBannerService.list(merchantId);
		return Result.successData(apiMerchantBanner);
	}
}
