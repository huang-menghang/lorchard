package com.ysdevelop.loarchard.merchant.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.loarchard.merchant.entity.GoodsCategory;
import com.ysdevelop.loarchard.merchant.service.GoodsCategoryService;
import com.ysdevelop.lochard.common.result.Result;
import com.ysdevelop.lochard.common.utils.HttpUtils;
/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 商品分类,控制器
 *
 * @Date 2018年8月15日
 *
 * @Version
 *
 */

@Controller
@RequestMapping(value="/goodsCategory" )
public class GoodsCategoryController {

	@Autowired
	private GoodsCategoryService categoryService;
	
	
	
	@RequestMapping(value="",produces="text/html;charset=UTF-8")
	public String index(){
		return "category/index";
	}
	
	
	@RequestMapping(value="/pagination",produces="application/json;charset=utf-8")
	@ResponseBody
	public Result<List<GoodsCategory>> pagination(HttpServletRequest request){
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<GoodsCategory> pageInfo = categoryService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
	@RequestMapping(value="/info",produces="text/html;charset=UTF-8")
	public String info(){
		return "category/info";
	}
	
	
}
