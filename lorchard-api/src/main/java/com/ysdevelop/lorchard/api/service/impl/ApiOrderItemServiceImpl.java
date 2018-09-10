package com.ysdevelop.lorchard.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.api.entity.OrderItemVo;
import com.ysdevelop.lorchard.api.mapper.ApiOrderItemDao;
import com.ysdevelop.lorchard.api.service.ApiOrderItemService;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月10日 上午10:21:39 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiOrderItemServiceImpl implements ApiOrderItemService {

	@Autowired
	private ApiOrderItemDao itemDao;

	@Transactional
	@Override
	public Integer batchInsert(List<OrderItemVo> orderItems) {
		if (orderItems == null || orderItems.size() == 0) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return itemDao.batchInsert(orderItems);
	}
}
