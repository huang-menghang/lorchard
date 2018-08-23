package com.ysdevelop.loarchard.merchant.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;
import com.ysdevelop.lochard.common.result.Result;
import com.ysdevelop.lochard.common.utils.Constant;
import com.ysdevelop.lorchard.shiro.service.UserService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;
import com.ysdevelop.lorchard.shiro.vo.LoginVo;

@Controller
@RequestMapping(value = "merchant")
public class MerchantController {

	@Autowired
	private UserService userService;

	@Autowired
	private Producer producer;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "merchant/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String doLogin(@Valid LoginVo loginVo) {
		return null;
	}

	// 生成图像验证码
	@RequestMapping("/captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		TokenManager.getSession().setAttribute(Constant.KAPTCHA_SESSION_KEY, text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
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
	public Result<String> doRegister(@Valid LoginVo loginVo, HttpSession session) {
		userService.register(loginVo, session);
		return Result.success("注册成功");
	}
}
