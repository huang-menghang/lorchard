package com.ysdevelop.lorchard.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;
import com.ysdevelop.lorchard.api.service.ApiGoodsCategoryService;
import com.ysdevelop.lorchard.common.result.Result;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:14:34 
 *
 * @Package com.ysdevelop.lorchard.api.controller
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@RestController  
@RequestMapping(value = "/goodsCategory")
public class ApiGoodsCategoryController {

	@Autowired 
	private ApiGoodsCategoryService categoryService;
  
	/**
	 * 获取顶级分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/parent",method = RequestMethod.GET)
	public Result<List<GoodsCategoryVo>> parent(Long merchantId) {
		return Result.successData(categoryService.listParent(merchantId));

	}

}
