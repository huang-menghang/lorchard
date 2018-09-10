package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.AddressVo;

public interface ApiAddressDao {

	void addAddress(AddressVo address);
	
	void editAddress(AddressVo address);
	
	AddressVo getAddressById(Long id);
	
	//修改为非默认
	void editState(AddressVo address);
	
	//修改为默认
    void updateState(AddressVo address);

	List<AddressVo> listByMemberId(Long memberId);

	AddressVo getAddressByMemberId(@Param(value="memberId")Long memberId,@Param(value="id")Long id);
}
