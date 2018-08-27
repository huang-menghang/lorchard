package com.ysdevelop.loarchard.merchant.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysdevelop.loarchard.merchant.entity.Shop;
import com.ysdevelop.loarchard.merchant.service.ShopService;
import com.ysdevelop.lochard.common.result.Result;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value = "/apply",method=RequestMethod.GET)
	public String apply() {

		return "shop/apply";
	}
   /**
    * 
    * @param shop 店铺实体申请店铺
    *
    * @return
    */
	@RequestMapping(value = "apply",method=RequestMethod.POST)
	@ResponseBody
	public Result<String> apply(@Valid Shop shop) {
	    shopService.applyShop(shop); 
		return Result.success("申请成功");
	}

}
