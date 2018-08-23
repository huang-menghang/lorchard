package com.ysdevelop.loarchard.merchant.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysdevelop.lochard.common.result.Result;
import com.ysdevelop.lorchard.shiro.service.UserService;
import com.ysdevelop.lorchard.shiro.vo.LoginVo;

@Controller
@RequestMapping(value = "merchant")
public class MerchantController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "merchant/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String doLogin(@Valid LoginVo loginVo) {
		return null;
	}

	@RequestMapping(value = "/applyMerchant", method = RequestMethod.GET)
	public String applyMerchant() {
		return "merchant/merchant-information";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "merchant/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> doRegister(@Valid LoginVo loginVo,HttpSession session) {
		userService.register(loginVo,session);
		return Result.success("注册成功");
	}
}
