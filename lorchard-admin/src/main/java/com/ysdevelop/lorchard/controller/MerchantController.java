package com.ysdevelop.lorchard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author oldHuang
 *
 * @Package com.ysdevelop.lorchard.controller
 * 
 * @Description 管理平台商家控制器
 *
 * @Date 2018年8月28日
 *
 * @Version
 *
 */
@Controller
@RequestMapping(value="/merchant")
public class MerchantController {

	@RequestMapping(value="/hello")
	@ResponseBody
	public String hello(){
		return "hello";
	}
	
}
