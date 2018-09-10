package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.AddressVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:19:56 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiAddressService {
	
	void addAddress(AddressVo address);

	List<AddressVo> listByMemberId(Long memberId);

	void updateState(AddressVo address);

	AddressVo getAddressByMemberId(Long memberId, Long id);

	AddressVo getAddressById(Long id);

	void editAddress(AddressVo address);
}
