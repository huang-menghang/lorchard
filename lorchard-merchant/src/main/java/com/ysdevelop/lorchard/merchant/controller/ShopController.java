package com.ysdevelop.lorchard.merchant.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.merchant.entity.Shop;
import com.ysdevelop.lorchard.merchant.entity.ShopFlow;
import com.ysdevelop.lorchard.merchant.service.ShopFlowService;
import com.ysdevelop.lorchard.merchant.service.ShopService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@Autowired
	private ShopFlowService shopFlowService;

	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public String apply() {

		return "shop/apply";
	}

	/**
	 * 
	 * @param shop
	 *            店铺实体申请店铺
	 * 
	 * @return
	 */
	@RequestMapping(value = "apply", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> apply(@Valid Shop shop) {
		shopService.applyShop(shop);
		return Result.success("申请成功");
	}

	/**
	 * 商店概述
	 * 
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "shop/index";
	}

	/**
	 * 商店概述,每日前七天流量统计
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "/dailyShopFlow", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<ShopFlow>> dailyFlowStat() {
		if(TokenManager.getToken() == null){
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return Result.successData(shopFlowService.recentSevenDayStat(TokenManager.getUserId()));
	}

	@RequestMapping(value="/dashboard" ,method=RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Map<String,Integer>> dashboard(){
		if(TokenManager.getToken() == null){
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return Result.successData(shopFlowService.yesterdayStat(TokenManager.getUserId()));
	}
	/**
	 * 获得运营人名称
	 * 
	 * @return 运营人姓名
	 * 
	 */
	@RequestMapping(value="/operatorName" ,method=RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Shop> operatorName(){
		if(TokenManager.getToken() == null){
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return Result.successData(shopService.getOperatorName(TokenManager.getUserId()));
	}
	
	
	@RequestMapping(value = "/testMq", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> testMq() {
		shopService.testMq();
		return Result.success("测试目mq成功");
	}

}
