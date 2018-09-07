package com.ysdevelop.lorchard.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;
import com.ysdevelop.lorchard.api.service.ApiGoodsCategoryService;
import com.ysdevelop.lorchard.common.result.Result;

/**
 * 
 * @author USER
 *
 */
@RestController  
@RequestMapping(value = "/goodsCategory")
public class ApiGoodsCategoryController {

	@Autowired 
	private ApiGoodsCategoryService categoryService;
  
	@RequestMapping(value = "/parent",method = RequestMethod.GET)
	public Result<List<GoodsCategoryVo>> parent(HttpServletRequest request) {
		return Result.successData(categoryService.listParent());

	}

}
