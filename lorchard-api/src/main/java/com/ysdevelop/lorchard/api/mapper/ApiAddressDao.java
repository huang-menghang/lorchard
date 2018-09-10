package com.ysdevelop.lorchard.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ysdevelop.lorchard.api.entity.AddressVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:17:25 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
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
