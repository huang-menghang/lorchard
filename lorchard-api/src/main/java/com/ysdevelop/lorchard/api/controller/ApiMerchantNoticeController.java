package com.ysdevelop.lorchard.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.MerchantNoticeVo;
import com.ysdevelop.lorchard.api.service.ApiMerchantNoticeService;
import com.ysdevelop.lorchard.common.result.Result;

/**
 * 
 * @author USER
 *
 */
@RestController
@RequestMapping(value = "/merchantNotice")
public class ApiMerchantNoticeController {
	
	@Autowired
	private ApiMerchantNoticeService apiMerchantNoticeService;
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public Result<List<MerchantNoticeVo>> getNotice(Long merchantId){
		List<MerchantNoticeVo> apiMerchantNotice = apiMerchantNoticeService.list(merchantId);
		return Result.successData(apiMerchantNotice);
	}
}
