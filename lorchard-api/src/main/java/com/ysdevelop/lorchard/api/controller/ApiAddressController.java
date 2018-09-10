package com.ysdevelop.lorchard.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ysdevelop.lorchard.api.entity.AddressVo;
import com.ysdevelop.lorchard.api.service.ApiAddressService;
import com.ysdevelop.lorchard.common.result.Result;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:14:45 
 *
 * @Package com.ysdevelop.lorchard.api.controller
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@RestController
@RequestMapping(value = "/address")
public class ApiAddressController {

	@Autowired
	ApiAddressService apiAddressService;

	// 添加地址
	@RequestMapping(method = RequestMethod.POST, value = "")
	public Result<Long> add(@Valid AddressVo address) {
		apiAddressService.addAddress(address);
		return Result.successData(address.getId());
	}

	// 详细地址界面
	@RequestMapping(method = RequestMethod.GET, value = "")
	public Result<AddressVo> detail(@RequestParam(value = "id", required = false) Long id) {
		AddressVo addressInfo = apiAddressService.getAddressById(id);
		return Result.successData(addressInfo);
	}

	// 修改地址
	@RequestMapping(method = RequestMethod.PUT, value = "")
	public Result<String> edit(@Valid AddressVo address) {
		apiAddressService.editAddress(address);
		return Result.successData("修改成功");
	}

	// 地址列表
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public Result<List<AddressVo>> list(Long memberId) {
		List<AddressVo> address = apiAddressService.listByMemberId(memberId);
		return Result.successData(address);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/updateState")
	public Result<String> updateState(AddressVo address) {
		apiAddressService.updateState(address);
		return Result.successData("已设置默认地址");
	}

	@RequestMapping(method = RequestMethod.GET, value = "/state")
	public Result<AddressVo> state(Long memberId, @RequestParam(required = false, value = "id") Long id) {
		AddressVo address = apiAddressService.getAddressByMemberId(memberId, id);
		return Result.successData(address);
	}
}
