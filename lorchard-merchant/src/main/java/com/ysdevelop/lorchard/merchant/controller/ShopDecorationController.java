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
@RequestMapping(value = "/shopDecoration")
public class ShopDecorationController {

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {

		return "decoration/index";
	}
	
}
