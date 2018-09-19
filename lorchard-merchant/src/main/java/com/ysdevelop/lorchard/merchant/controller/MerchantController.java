package com.ysdevelop.lorchard.merchant.controller;

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

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Producer;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.mq.constant.MessageKey;
import com.ysdevelop.lorchard.mq.define.MessageType;
import com.ysdevelop.lorchard.mq.service.MessageProducer;
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

	@Autowired
	private MessageProducer messageProducer;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "merchant/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> doLogin(@Valid LoginVo loginVo, HttpSession session) {
		// 验证码不一致则失败
		userService.login(loginVo);
		return Result.success("登录成功!");
	}

	// 生成图像验证码
	@RequestMapping("/captcha")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 生成文字验证码
		String text = producer.createText();
		System.out.println("veifyCodeP--->" + text);
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		System.out.println("sessionId-->" + TokenManager.getSession().getId());
		TokenManager.getSession().setAttribute(Constant.KAPTCHA_SESSION_KEY, text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
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

	/**
	 * 用户退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> logout() {
		TokenManager.logout();
		return Result.success("退出成功");

	}

	@RequestMapping(value = "/sendMqMessage", method = RequestMethod.GET)
	@ResponseBody
	public Result<String> sendMqMessage() {
		MerchantMessage message = new MerchantMessage();
		message.setConent("测试消息");
		message.setMerchantId(1l);
		message.setMessageType(MessageType.APPLY_SHOP);
		messageProducer.sendMessage(MessageKey.MERCHANT_KEY, JSON.toJSONString(message));
		return Result.success("消息发送成功");
	}

}
