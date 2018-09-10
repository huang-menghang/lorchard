package com.ysdevelop.lorchard.api.service;

import java.util.List;

import com.ysdevelop.lorchard.api.entity.AddressVo;

public interface ApiAddressService {
	
	void addAddress(AddressVo address);

	List<AddressVo> listByMemberId(Long memberId);

	void updateState(AddressVo address);

	AddressVo getAddressByMemberId(Long memberId, Long id);

	AddressVo getAddressById(Long id);

	void editAddress(AddressVo address);
}
