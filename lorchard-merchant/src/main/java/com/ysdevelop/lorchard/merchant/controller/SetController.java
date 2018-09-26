package com.ysdevelop.lorchard.merchant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.merchant.entity.Shop;
import com.ysdevelop.lorchard.merchant.service.SetService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;



/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.controller
 *
 * @Description 商店设置
 *
 * @Date 2018年9月10日
 *
 * @Version
 */
@Controller
@RequestMapping(value = "/set")
public class SetController {

	@Autowired
	private SetService setService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String index() {
		return "set/set";
	}
	
	/**
	 * 查询商家id
	 * */
	@RequestMapping(value = "/merchantId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Long> merchantId() {
		Long merchantId = TokenManager.getUserId();
		return Result.successData(merchantId);
	}
	
	/**
	 * 查询商铺信息
	 * */
	@RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Shop> info(Long merchantId) {
		Shop shop = setService.getByMerchantId(merchantId);
		return Result.successData(shop);
	}

	/**
	 * 修改商铺信息
	 * */
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> update(@Valid Shop shop) {
		setService.update(shop);
		return Result.success("商城信息修改成功");
	}
	

}

