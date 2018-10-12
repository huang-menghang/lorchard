package com.ysdevelop.lorchard.merchant.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.HttpUtils;
import com.ysdevelop.lorchard.merchant.entity.GoodsCategory;
import com.ysdevelop.lorchard.merchant.service.GoodsCategoryService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

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
@RequestMapping(value = "/goodsCategory")
public class GoodsCategoryController {

	@Autowired
	private GoodsCategoryService categoryService;

	@RequestMapping(value = "", produces = "text/html;charset=UTF-8")
	public String index() {
		return "category/index";
	}

	@RequestMapping(value = "/pagination", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<GoodsCategory>> pagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		queryMap.put("merchantId", TokenManager.getUserId().toString());
		PageInfo<GoodsCategory> pageInfo = categoryService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}

	@RequestMapping(value = "/set", produces = "text/html;charset=UTF-8")
	public String set() {
		return "category/set";
	}

	@RequestMapping(value = "/parent", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<GoodsCategory>> parent(HttpServletRequest request) {
		Long merchantId=TokenManager.getUserId();
		return Result.successData(categoryService.listParent(merchantId));

	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> add(@Valid GoodsCategory category) {
		categoryService.add(category);
		return Result.success("添加成功");

	}

	/**查询商品商品分类*/
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<GoodsCategory> info(@PathVariable(value = "id") Integer id) {
		GoodsCategory category = categoryService.getById(id);
		return Result.successData(category);
	}

	/**修改商品分类*/
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> update(@Valid GoodsCategory category) {
		categoryService.update(category);
		return Result.success("产品分类修改成功");
	}
	
	/**查看商品详情*/
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public String info(){
		return "category/info";
	}

	/**修改商品*/
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> delete(@PathVariable Integer id) {
		categoryService.deleteById(id);
		return Result.success("产品删除成功");
	}
}
