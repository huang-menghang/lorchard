package com.ysdevelop.lorchard.merchant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 当前进行的活动，发布新活动，历史活动；
 * 
 *  @Date 2018年10月8日
 * 
 * @Version
 * */

@Controller
@RequestMapping(value="/activity")
public class ActivityController {	
	/**
	 * 活动管理
	 * */
	@RequestMapping(value="",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String index() {
		System.out.println("跳转到活动页面");
		return "activity/index";
	}
	/**
	 * 历史活动
	 * */
	@RequestMapping(value="/activityHistory",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String withdraw() {
		System.out.println("跳转到历史活动页面");
		return "activity/history";
	}
}
