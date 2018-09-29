package com.ysdevelop.lorchard.merchant.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.HttpUtils;
import com.ysdevelop.lorchard.merchant.entity.Finance;
import com.ysdevelop.lorchard.merchant.service.FinanceService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

/**
 * @author 
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 财务，资产，流水，对账单；
 * 
 *  @Date 2018年9月10日
 * 
 * @Version
 * */

@Controller
@RequestMapping(value="/finance")
public class FinanceController {
	
	@Autowired
	private FinanceService financeService;
	/**
	 * 全部资产
	 * */
	@RequestMapping(value="",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String index() {
		
		return "finance/index";
	}
	
	@RequestMapping(value="/financeReceived",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String withdraw() {
		return "finance/withdraw";
	}
	
	/**
	 * 获取商家id
	 * */
	@RequestMapping(value = "/financeMerchantId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Long> orderMerchantId() {
		Long orderMerchantId = TokenManager.getUserId();
		System.out.println("orderMerchantId"+orderMerchantId);
		return Result.successData(orderMerchantId);
	}
	/**
	 * 获取index的交易明细
	 * */
	@RequestMapping(value = "/pagination", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<Finance>> pagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<Finance> pageInfo = financeService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
	/**
	 * 获取资金余额和佣金
	 * */
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Finance> info() {
		Finance finance = financeService.getLast();
		return Result.successData(finance);
	}
	
	/**
	 * 插入提现记录
	 * */
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> withdraw(@Valid Double cash,String name,String bankNumber,String openBank,Long merchantId) {
		financeService.withdraw(cash,name,bankNumber,openBank,merchantId);
		return Result.success("提现成功");

	}
	
	/**
	 * 获取余额
	 * */
	@RequestMapping(value = "/queryBalance", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Double> queryBalance(HttpServletRequest request) {
		String merchantId1 = request.getParameter("merchantId");
		Long merchantId=Long.valueOf(merchantId1);
		Double balance = financeService.queryBalance(merchantId);
		return Result.successData(balance);

	}
	
	/**
	 * 获取index的交易明细
	 * */
	@RequestMapping(value = "/cashPagination", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<Finance>> cashPagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<Finance> pageInfo = financeService.cashList(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
}
