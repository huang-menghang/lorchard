package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.api.entity.AddressVo;
import com.ysdevelop.lorchard.api.mapper.ApiAddressDao;
import com.ysdevelop.lorchard.api.service.ApiAddressService;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:21:01 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiAddressServiceImpl implements ApiAddressService {

	@Autowired
	ApiAddressDao apiAddressDao;

	@Transactional
	@Override
	public void addAddress(AddressVo address) {
		apiAddressDao.addAddress(address);
		System.out.println("address--->" + address.getId());
		if (address.getState() == Constant.YESNO.YES.getValue()) {
			apiAddressDao.editState(address);
		}
	}

	@Override
	public List<AddressVo> listByMemberId(Long memberId) {
		return apiAddressDao.listByMemberId(memberId);
	}

	@Override
	public void updateState(AddressVo address) {
		apiAddressDao.updateState(address);
		apiAddressDao.editState(address);
	}
	
	@Override
	public AddressVo getAddressByMemberId(Long memberId,Long id) {
		if (memberId == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return apiAddressDao.getAddressByMemberId(memberId,id);
	}

	@Override
	public AddressVo getAddressById(Long id) {
		return apiAddressDao.getAddressById(id);
	}

	@Override
	public void editAddress(AddressVo address) {
		apiAddressDao.editAddress(address);
		System.out.println("address--->" + address.getId());
		if (address.getState() == Constant.YESNO.YES.getValue()) {
			apiAddressDao.editState(address);
		}
	}
	
}
