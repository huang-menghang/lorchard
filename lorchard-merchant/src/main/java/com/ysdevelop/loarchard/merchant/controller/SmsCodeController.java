package com.ysdevelop.loarchard.merchant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lochard.common.result.Result;

@RestController
@RequestMapping(value = "/smscode")
public class SmsCodeController {

	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public Result<Integer> send(String mobile, HttpSession session) {
		return Result.successData(123);
	}

}
