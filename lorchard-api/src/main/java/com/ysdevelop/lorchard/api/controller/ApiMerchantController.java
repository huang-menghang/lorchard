package com.ysdevelop.lorchard.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.MerchantVo;
import com.ysdevelop.lorchard.api.service.ApiMerchantService;
import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.HttpUtils;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:15:19 
 *
 * @Package com.ysdevelop.lorchard.api.controller
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@RestController
@RequestMapping(value="/merchant")
public class ApiMerchantController {
	@Autowired
	private ApiMerchantService apiMerchantService;
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public Result<MerchantVo> getMerchant(HttpServletRequest request){
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		Long merchantId = new Long(queryMap.get("merchantId"));
		MerchantVo apiMerchant = apiMerchantService.getById(merchantId);
		return Result.successData(apiMerchant);
	}
	
	@SystemControllerLog(description="访问商家")
	@RequestMapping(value="/firstVisit",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public void firstVisit(HttpServletRequest request){
		
	}
}
