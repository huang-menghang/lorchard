package com.ysdevelop.lorchard.api.task.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ysdevelop.lorchard.api.service.ApiMemberPointService;
import com.ysdevelop.lorchard.api.task.ApiMemberPointTask;

/**
 * 
 * @author 徐一鸣
 *
 * @Date 2018年9月14日 下午2:51:03
 *
 * @Package com.ysdevelop.lorchard.api.task.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Component
public class ApiMemberPointTaskImpl implements ApiMemberPointTask {
	
	@Autowired
	ApiMemberPointService memberPointService;
	
	/**
	 * 刷新签到状态
	 */
	@Scheduled(cron = "* * 6 * * ?")
	@Override
	public void refreshPointStatus() {
		System.out.println("执行任务开始");
		memberPointService.refreshPointStatus();
	}
}
