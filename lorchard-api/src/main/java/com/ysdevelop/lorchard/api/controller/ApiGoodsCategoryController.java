package com.ysdevelop.lorchard.api.controller;

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
import com.ysdevelop.lorchard.api.entity.GoodsCategoryVo;
import com.ysdevelop.lorchard.api.service.ApiGoodsCategoryService;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.HttpUtils;

/**
 * 
 * @author USER
 *
 */
@Controller
@RequestMapping(value = "/goodsCategory")
public class ApiGoodsCategoryController {

	@Autowired
	private ApiGoodsCategoryService categoryService;

	@RequestMapping(value = "", produces = "text/html;charset=UTF-8")
	public String index() {
		return "category/index";
	}

	@RequestMapping(value = "/pagination", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<GoodsCategoryVo>> pagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		System.out.println(queryMap);
		PageInfo<GoodsCategoryVo> pageInfo = categoryService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
	
	@RequestMapping(value = "/parentPagination", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<GoodsCategoryVo>> parentPagination(Long parentId) {
		List<GoodsCategoryVo> listByParentId = categoryService.listByParentId(parentId);
		return Result.successData(listByParentId);
	}

	@RequestMapping(value = "/set", produces = "text/html;charset=UTF-8")
	public String set() {
		return "category/set";
	}

	@RequestMapping(value = "/parent", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<GoodsCategoryVo>> parent(HttpServletRequest request) {
		return Result.successData(categoryService.listParent());

	}

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> add(@Valid GoodsCategoryVo category) {
		categoryService.add(category);
		return Result.success("添加成功");

	}

	/**
	 * 查询商品商品分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<GoodsCategoryVo> info(@PathVariable(value = "id") Long id) {
		GoodsCategoryVo category = categoryService.getById(id);
		return Result.successData(category);
	}

	/**
	 * 修改商品分类
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> update(@Valid GoodsCategoryVo category) {
		categoryService.update(category);
		return Result.success("产品分类修改成功");
	}
	
	/**
	 * 查看商品详情
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public String info(){
		return "category/info";
	}

	/**
	 * 修改商品
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> delete(@PathVariable Long id) {
		categoryService.deleteById(id);
		return Result.success("产品删除成功");
	}
}
