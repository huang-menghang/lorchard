package com.ysdevelop.lorchard.merchant.controller;
/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 验证码控制器
 * 
 */
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.common.result.Result;

@RestController
@RequestMapping(value = "/smscode")
public class SmsCodeController {

	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public Result<Integer> send(String mobile, HttpSession session) {
		return Result.successData(123456);
	}

}
