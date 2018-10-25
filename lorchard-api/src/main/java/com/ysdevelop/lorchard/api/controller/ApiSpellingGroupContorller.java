package com.ysdevelop.lorchard.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.GoodsVo;
import com.ysdevelop.lorchard.api.entity.SpellingGroupVo;
import com.ysdevelop.lorchard.api.service.ApiGoodsService;
import com.ysdevelop.lorchard.api.service.ApiSpellingGroupService;
import com.ysdevelop.lorchard.common.result.Result;

/**
 * 
 * @author 徐一鸣
 *
 * @Date 2018年10月10日 下午4:55:13
 *
 * @Package com.ysdevelop.lorchard.api.controller
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@RestController
@RequestMapping(value = "/group")
public class ApiSpellingGroupContorller {

	@Autowired
	private ApiGoodsService goodsService;

	@Autowired
	private ApiSpellingGroupService spellingGroupService;

	/**
	 * 获取团购商品
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/spellingGroup", method = RequestMethod.GET)
	public Result<List<GoodsVo>> spellingGroup(Long merchantId) {
		System.out.println("merchantId------>"+merchantId);
		SpellingGroupVo spellingGroupVo = spellingGroupService.getByMerchantId(merchantId);
		List<Long> goodsIds = spellingGroupService.goodsIdList(spellingGroupVo.getId());
		List<GoodsVo> list = goodsService.spellingGroupList(goodsIds);
		return Result.successData(list);
	}

	/**
	 * 生成团购订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/groupCreate", method = RequestMethod.GET)
	public Result<String> groupCreate(Long merchantId, Long memberId,String orderNo) {
		spellingGroupService.addSpellingGroupOrder(merchantId, memberId, orderNo);
		return Result.success("生成团购订单成功");
	}

	/**
	 * 更新团购订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/groupUpdate", method = RequestMethod.GET)
	public Result<String> groupUpdate(Long inviteId, Long merchantId, Long memberId,String orderNo) {
		spellingGroupService.increaseSpellingGroupOrder(inviteId,merchantId, memberId, orderNo);
		return Result.success("加入团购订单成功");
	}
	/**
	 *获取团购订单人数
	 * @param orderNo
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getGroupMember")
	public Result<Long> getGroupMember(String orderNo) {
		System.out.println("orderNo----->"+orderNo);
		Long count = spellingGroupService.getMemberCount(orderNo);
		System.out.println("count------->"+count);
		return Result.successData(count);
	}
}
