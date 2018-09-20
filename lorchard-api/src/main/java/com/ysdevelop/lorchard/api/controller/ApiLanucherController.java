package com.ysdevelop.lorchard.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.websocket.lanucher.Lanucher;

/**
 * 
 * @author 徐一鸣
 *
 * @Date 2018年9月19日 上午11:04:33
 *
 * @Package com.ysdevelop.lorchard.api.controller
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@RestController
@RequestMapping(value = "/lanucher")
public class ApiLanucherController {
	
	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public void startLanucher() {
		Lanucher.main(null);
	}
}
