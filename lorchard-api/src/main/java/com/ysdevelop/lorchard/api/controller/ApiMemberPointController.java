package com.ysdevelop.lorchard.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.MemberPointVo;
import com.ysdevelop.lorchard.api.service.ApiMemberPointService;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.ApiConstant;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月13日 上午11:45:58 
 *
 * @Package com.ysdevelop.lorchard.api.controller
 *
 * @Description: 用户积分控制类
 *
 * @version V1.0
 *
 */
@RestController
@RequestMapping(value = "/memberPoint")
public class ApiMemberPointController {
	@Autowired
	private ApiMemberPointService memberPointService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Result<MemberPointVo> membershipPoint(MemberPointVo memberPoint) {
		MemberPointVo memberPointVo = memberPointService.getById(memberPoint);
		return Result.successData(memberPointVo);
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public Result<? extends Object> update(MemberPointVo memberPoint) {
		try {
			memberPointService.update(memberPoint);
		} catch (Exception e) {
			return Result.successData(ApiConstant.REPEATPOINT);
		}
		return Result.successData(memberPoint);
	}

}
