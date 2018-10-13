package com.ysdevelop.lorchard.merchant.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.common.result.Result;
import com.ysdevelop.lorchard.common.utils.HttpUtils;
import com.ysdevelop.lorchard.merchant.entity.Activity;
import com.ysdevelop.lorchard.merchant.entity.SpellingGroup;
import com.ysdevelop.lorchard.merchant.service.ActivityService;
import com.ysdevelop.lorchard.shiro.token.TokenManager;

/**
 * @author 吴磊
 * 
 * @Package com.ysdevelop.loarchard.merchant.controller
 * 
 * @Description 当前进行的活动，发布新活动；
 * 
 *  @Date 2018年10月8日
 * 
 * @Version
 * */

@Controller
@RequestMapping(value="/activity")
public class ActivityController {	
	
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 活动管理
	 * */
	@RequestMapping(value="",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String index() {
		return "activity/index";
	}
	/**
	 * 新建活动
	 * */
	@RequestMapping(value="/activityNew",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String activityNew() {
		return "activity/set";
	}
	
	/**
	 * 查看详情
	 * */
	@RequestMapping(value="/info",method=RequestMethod.GET,produces="text/html;charset=UTF-8")
	public String info() {
		return "activity/info";
	}
	
	/**
	 *添加活动 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> add(@Valid SpellingGroup spellingGroup) {
		activityService.add(spellingGroup);
		System.out.println("添加活动");
		return Result.success("拼团活动发布成功");
	}
	
	/**
	 *修改活动商品的拼团总人数和拼团价格
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> update(Integer totalNumber,Double spellingGroupPrice) {
		System.out.println("update");
		activityService.update(totalNumber, spellingGroupPrice);
		return Result.success("拼团活动发布成功");
	}
	
	
	/**
	 * 获取商家id
	 * */
	@RequestMapping(value = "/merchantId", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<Long> merchantId() {
		Long merchantId = TokenManager.getUserId();
		return Result.successData(merchantId);
	}
	/**
	 * 获取活动的明细
	 * */
	@RequestMapping(value = "/pagination", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<List<Activity>> pagination(HttpServletRequest request) {
		Map<String, String> queryMap = HttpUtils.getParameterMap(request);
		PageInfo<Activity> pageInfo = activityService.list(queryMap);
		return Result.successPaginationData(pageInfo.getList(), pageInfo.getTotal());
	}
	/**下架商品*/
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<String> delete(@PathVariable Integer id) {
		activityService.deleteById(id);
		return Result.success("产品下架成功");
	}
	
	/**
	 * 通过id查看拼团活动
	 * */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result<SpellingGroup> info(@PathVariable(value = "id") Integer id) {
		SpellingGroup spellingGroup = activityService.getById(id);
		return Result.successData(spellingGroup);
	}
}
