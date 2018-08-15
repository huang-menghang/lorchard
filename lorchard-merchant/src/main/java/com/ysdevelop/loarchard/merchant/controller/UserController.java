package com.ysdevelop.loarchard.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="user")
public class UserController {

	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(){
		return "user/index";
	}
	
}
