package com.ysdevelop.lorchard.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.MerchantBannerVo;
import com.ysdevelop.lorchard.api.entity.MerchantFeedbackVo;
import com.ysdevelop.lorchard.api.entity.MerchantNoticeVo;
import com.ysdevelop.lorchard.api.entity.MerchantVo;
import com.ysdevelop.lorchard.api.service.ApiMerchantBannerService;
import com.ysdevelop.lorchard.api.service.ApiMerchantFeedbackService;
import com.ysdevelop.lorchard.api.service.ApiMerchantNoticeService;
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
	
	@Autowired
	private ApiMerchantBannerService apiMerchantBannerService;
	
	@Autowired
	private ApiMerchantNoticeService apiMerchantNoticeService;
	
	@Autowired
	private ApiMerchantFeedbackService apiMerchantFeedbackService;
	
	/**
	 * 获取商家
	 * @param request
	 * @return
	 */
	@RequestMapping(value="",method = RequestMethod.GET)
	public Result<MerchantVo> getMerchant(HttpServletRequest request){
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		Long merchantId = new Long(queryMap.get("merchantId"));
		MerchantVo apiMerchant = apiMerchantService.getById(merchantId);
		return Result.successData(apiMerchant);
	}
	
	/**
	 * 访问商家日志
	 * @param request
	 */
	@SystemControllerLog(description="访问商家")
	@RequestMapping(value="/firstVisit",method = RequestMethod.GET,produces="application/json;charset=utf-8")
	public void firstVisit(HttpServletRequest request){
		
	}
	
	/**
	 * 获取商家公告
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value="/merchantNotice",method = RequestMethod.GET)
	public Result<List<MerchantNoticeVo>> getNotice(Long merchantId){
		List<MerchantNoticeVo> apiMerchantNotice = apiMerchantNoticeService.list(merchantId);
		return Result.successData(apiMerchantNotice);
	}
	
	/**
	 * 获取商家轮播图
	 * @param merchantId
	 * @return
	 */
	@RequestMapping(value="/merchantBanner",method = RequestMethod.GET)
	public Result<List<MerchantBannerVo>> getMerchantBanner(Long merchantId){
		List<MerchantBannerVo> apiMerchantBanner = apiMerchantBannerService.list(merchantId);
		return Result.successData(apiMerchantBanner);
	}
	
	/**
	 * 添加商家意见反馈
	 * @param merchantFeedback
	 * @return
	 */
	@RequestMapping(value="/addFeedback",method = RequestMethod.POST)
	public Result<String> addFeedback(@RequestBody MerchantFeedbackVo merchantFeedback){
		apiMerchantFeedbackService.add(merchantFeedback);
		return Result.successData("添加反馈意见成功");
	}
}
