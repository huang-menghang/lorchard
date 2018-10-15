package com.ysdevelop.lorchard.merchant.controller;
/**
 * 
 * @author oldHuang
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 商家控制器
 * 
 */
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
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
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
	/**
	 *验证登录 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> doLogin(@Valid LoginVo loginVo, HttpSession session) {
		// 验证码不一致则失败
		userService.login(loginVo);
		return Result.success("登录成功!");
	}

	/**生成图像验证码*/
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
		System.out.println("控制方法中获取的sessionId-->" + TokenManager.getSession().getId());
		TokenManager.getSession().setAttribute(Constant.KAPTCHA_SESSION_KEY, text);
		System.out.println("控制方法中获取的session--->"+TokenManager.getSession());
		System.out.println("控制方法中shiro保存的验证码--->"+TokenManager.getSession().getAttribute(Constant.KAPTCHA_SESSION_KEY));
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}
	/**
	 * 跳转到注册页面 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "merchant/register";
	}
	
	/**
	 * 注册
	 */
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
		message.setMerchantId(1L);
		message.setMessageType(MessageType.APPLY_SHOP);
		messageProducer.sendMessage(MessageKey.MERCHANT_KEY, JSON.toJSONString(message));
		return Result.success("消息发送成功");
	}
	
	//跳转协议界面
		@RequestMapping(value = "/agreement", method = RequestMethod.GET)
		public String agreement() {
			return "merchant/agreement";
		}
		/**
		 * 店铺状态获取，0代表营业，1代表打烊
		 * 
		 * @return
		 */
		@RequestMapping(value="/businessStauts" ,method=RequestMethod.GET, produces = "application/json;charset=utf-8")
		@ResponseBody
		public Result<Long> getBusinessStauts(){
			if(TokenManager.getToken() == null){
				throw new WebServiceException(CodeMsg.SERVER_ERROR);
			}
			Long businessStauts = userService.getBusinessStauts(TokenManager.getUserId());
			System.out.println("businessStauts--"+businessStauts);
			return Result.successData(businessStauts);
		}
		/**
		 * 店铺打烊，0代表营业，1代表打烊
		 * 
		 * @return
		 */
		@RequestMapping(value="/shopClosed" ,method=RequestMethod.PUT, produces = "application/json;charset=utf-8")
		@ResponseBody
		public Result<String> shopClosed(Long businessStauts){
			System.out.println("进来了 ");
			if(TokenManager.getToken() == null){
				throw new WebServiceException(CodeMsg.SERVER_ERROR);
			}
			userService.updateBusinessStautsToOne(TokenManager.getUserId(), businessStauts);
			return Result.successData("打烊成功");
		}
		/**
		 * 店铺开业，0代表营业，1代表打烊
		 * 
		 * @return
		 */
		@RequestMapping(value="/shopOpened" ,method=RequestMethod.PUT, produces = "application/json;charset=utf-8")
		@ResponseBody
		public Result<String> shopOpened(Long businessStauts){
			System.out.println("进来了 进来了");
			if(TokenManager.getToken() == null){
				throw new WebServiceException(CodeMsg.SERVER_ERROR);
			}
			userService.updateBusinessStautsToZero(TokenManager.getUserId(), businessStauts);
			return Result.successData("开铺成功");
		}

}
