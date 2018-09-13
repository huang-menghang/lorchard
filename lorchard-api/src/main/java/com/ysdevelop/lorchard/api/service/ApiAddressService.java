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
	
	/**
	 * 添加地址
	 * @param address
	 */
	void addAddress(AddressVo address);
	
	/**
	 * 获取地址集合
	 * @param memberId
	 * @return
	 */
	List<AddressVo> listByMemberId(Long memberId);
	
	/**
	 * 更新地址状态
	 * @param address
	 */
	void updateState(AddressVo address);
	
	/**
	 * 获取地址
	 * @param memberId
	 * @param id
	 * @return
	 */
	AddressVo getAddressByMemberId(Long memberId, Long id);
	
	/**
	 * 获取地址
	 * @param id
	 * @return
	 */
	AddressVo getAddressById(Long id);
	
	/**
	 * 编辑地址
	 * @param address
	 */
	void editAddress(AddressVo address);
}
