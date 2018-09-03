package com.ysdevelop.lorchard.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysdevelop.lorchard.api.entity.MerchantNoticeVo;
import com.ysdevelop.lorchard.api.service.ApiMerchantNoticeService;
import com.ysdevelop.lorchard.common.result.Result;

/**
 * 
 * @author USER
 *
 */
@Controller
@RequestMapping(value = "/merchantNotice")
public class ApiMerchantNoticeController {
	
	@Autowired
	private ApiMerchantNoticeService apiMerchantNoticeService;
	
	@RequestMapping(value="",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Result<List<MerchantNoticeVo>> getNotice(Long merchantId){
		List<MerchantNoticeVo> apiMerchantNotice = apiMerchantNoticeService.list(merchantId);
		return Result.successData(apiMerchantNotice);
	}
}
