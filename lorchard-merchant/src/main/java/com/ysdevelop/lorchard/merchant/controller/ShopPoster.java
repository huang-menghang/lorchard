package com.ysdevelop.lorchard.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * */
@Controller
@RequestMapping(value = "/shopPoster")
public class ShopPoster {

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {

		return "shopPoster/index";
	}
	
}
