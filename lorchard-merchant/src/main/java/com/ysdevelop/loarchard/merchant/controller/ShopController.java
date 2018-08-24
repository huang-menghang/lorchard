package com.ysdevelop.loarchard.merchant.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysdevelop.loarchard.merchant.entity.Shop;
import com.ysdevelop.lochard.common.result.Result;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

	@RequestMapping(value = "/apply",method=RequestMethod.GET)
	public String apply() {

		return "shop/apply";
	}

	@RequestMapping(value = "apply",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> apply(@Valid Shop shop) {
		
		
		return null;
	}

}
