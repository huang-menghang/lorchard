package com.ysdevelop.loarchard.merchant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.loarchard.merchant.entity.Shop;
import com.ysdevelop.loarchard.merchant.mapper.ShopDao;
import com.ysdevelop.loarchard.merchant.service.ShopService;
import com.ysdevelop.lochard.common.exception.WebServiceException;
import com.ysdevelop.lochard.common.result.CodeMsg;
import com.ysdevelop.lochard.common.utils.Constant;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Override
	public void applyShop(Shop shop) {
		if (shop == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Integer changCount = shopDao.addShop(shop);
		if (changCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

	}

}
