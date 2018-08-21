package com.ysdevelop.loarchard.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/index")
public class IndexController {

	
    @RequestMapping(value="",method=RequestMethod.GET,produces="text/html;charset=utf-8")	
	public String index(){
		
    	return "main/index";
	}
    
    
	
	
}
