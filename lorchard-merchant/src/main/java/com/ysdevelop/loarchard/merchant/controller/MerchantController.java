package com.ysdevelop.loarchard.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="merchant")
public class MerchantController {

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "merchant/login";
	}
	
	@RequestMapping(value="/applyMerchant",method=RequestMethod.GET)
	public String applyMerchant(){
		return "merchant/merchant-information";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String register(){
		return "merchant/register";
	}
}
