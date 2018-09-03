package com.ysdevelop.lorchard.merchant.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.PreviewImages;
import com.ysdevelop.lorchard.merchant.service.GoodsService;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.HttpUtils;


/**
 * 
 * @author user
 * 
 * 
 */

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String index() {
		return "goods/index";
	}
	/**
	 * 获取index的商品列表
	 * */
	@RequestMapping(value = "/pagination", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<Goods>> pagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<Goods> pageInfo = goodsService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
	/**
	 * 跳转到修改或者添加界面
	 * */
	@RequestMapping(value = "/set", produces = "text/html;charset=UTF-8")
	public String set() {
		return "goods/set";
	}
	/**
	 * 通过商品的id查看详情
	 * */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Goods> info(@PathVariable(value = "id") Integer id) {
		Goods goods = goodsService.getById(id);
		return Result.successData(goods);
	}
	/**
	 * 获取商品的所有顶级分类
	 * */
	@RequestMapping(value = "/category", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<Goods>> category(HttpServletRequest request) {
		return Result.successData(goodsService.listCategory());

	}

	/**修改商品分类*/
	@RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> update(@Valid Goods goods) {
		goodsService.update(goods);
		return Result.success("商品修改成功");
	}
	
	

	/**查看商品详情*/
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public String info() {
		return "goods/info";
	}

	/**下架商品*/
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> delete(@PathVariable Integer id) {
		goodsService.deleteById(id);
		return Result.success("产品下架成功");
	}
	
	/**添加商品*/
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> add(@Valid Goods goods) {
		goodsService.add(goods);
		return Result.success("添加成功");

	}
	
	/**修改或添加商品图片*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "previewImages", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> previewImages(@RequestParam(value ="list") String previewImagesString) {
		//jackson对象
		ObjectMapper mapper = new ObjectMapper();
		//使用jackson将json转为List<User>
		JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, PreviewImages.class);   
		List<PreviewImages> previewImages = null;
		try {
			previewImages = (List<PreviewImages>)mapper.readValue(previewImagesString, jt);
			goodsService.previewImages(previewImages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success("商品修改成功");
	}
}
