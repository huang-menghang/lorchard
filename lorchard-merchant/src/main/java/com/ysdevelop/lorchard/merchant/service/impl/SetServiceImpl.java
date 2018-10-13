package com.ysdevelop.lorchard.merchant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.merchant.entity.Shop;
import com.ysdevelop.lorchard.merchant.mapper.SetDao;
import com.ysdevelop.lorchard.merchant.service.SetService;

/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service.impl
 *
 * @Description 设置
 *
 * @Date 2018年10月12日
 *
 * @Version
 */
@Service
public class SetServiceImpl implements SetService {

	@Autowired
	private SetDao setDao;
	
	/**
	 * 查询商铺信息
	 * */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Shop getByMerchantId(Long merchantId) {
		if (merchantId == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		return setDao.getByMerchantId(merchantId);
	}

	/**
	 * 修改商铺信息
	 * */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void update(Shop shop) {
		if (shop == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		Integer changeCount = setDao.update(shop);

		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SHOP_UPDATE_ERROR);
		}

	}


}
