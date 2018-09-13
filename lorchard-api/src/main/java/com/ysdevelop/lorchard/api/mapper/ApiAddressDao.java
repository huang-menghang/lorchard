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
	
	/**
	 * 添加地址
	 * @param address
	 */
	void addAddress(AddressVo address);
	
	/**
	 * 编辑地址
	 * @param address
	 */
	void editAddress(AddressVo address);
	
	/**
	 * 获取地址
	 * @param id
	 * @return
	 */
	AddressVo getAddressById(Long id);
	
	/**
	 * 修改为非默认
	 * @param address
	 */
	void editState(AddressVo address);
	
	/**
	 * 修改为默认
	 * @param address
	 */
    void updateState(AddressVo address);
    
    /**
     * 获取地址集合
     * @param memberId
     * @return
     */
	List<AddressVo> listByMemberId(Long memberId);
	
	/**
	 * 获取地址
	 * @param memberId
	 * @param id
	 * @return
	 */
	AddressVo getAddressByMemberId(@Param(value="memberId")Long memberId,@Param(value="id")Long id);
}
