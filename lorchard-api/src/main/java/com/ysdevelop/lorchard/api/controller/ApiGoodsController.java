package com.ysdevelop.lorchard.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.GoodsVo;
import com.ysdevelop.lorchard.api.service.ApiGoodsService;
import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.common.utils.HttpUtils;

/**
 * 
 * @author USER
 *
 */
@RestController
@RequestMapping(value = "/goods")
public class ApiGoodsController {
	
	@Autowired
	private ApiGoodsService goodsService;
	
	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public Result<List<GoodsVo>> pagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<GoodsVo> pageInfo = goodsService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
	
	@SystemControllerLog(description="访问商品",logType=Constant.SystemLogType.GOODS)
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public Result<GoodsVo> getDetails(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		Long goodId = new Long(queryMap.get("goodsId"));
		return Result.successData(goodsService.getById(goodId));
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public Result<GoodsVo> getValidate(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		Long goodId = new Long(queryMap.get("goodsId"));
		return Result.successData(goodsService.getById(goodId));
	}   
	
}

