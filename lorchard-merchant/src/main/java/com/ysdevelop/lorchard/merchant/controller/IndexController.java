package com.ysdevelop.lorchard.merchant.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ysdevelop.lorchard.common.annotation.SystemControllerLog;
import com.ysdevelop.lorchard.common.utils.Constant;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
	
	
	@SystemControllerLog(description = "用户进入首页",logType=Constant.SystemLogType.SHOP)
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	public String index(HttpServletRequest request) {

		return "main/index";
	}

}
