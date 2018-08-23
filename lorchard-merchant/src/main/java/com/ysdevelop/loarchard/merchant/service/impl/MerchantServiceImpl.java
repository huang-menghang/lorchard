package com.ysdevelop.loarchard.merchant.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysdevelop.loarchard.merchant.entity.Merchant;
import com.ysdevelop.loarchard.merchant.mapper.MerchantDao;
import com.ysdevelop.lochard.common.exception.WebServiceException;
import com.ysdevelop.lochard.common.result.CodeMsg;
import com.ysdevelop.lochard.common.utils.Constant;
import com.ysdevelop.lorchard.shiro.core.helper.PasswordHelper;
import com.ysdevelop.lorchard.shiro.entity.BaseAuth;
import com.ysdevelop.lorchard.shiro.service.UserService;
import com.ysdevelop.lorchard.shiro.vo.LoginVo;

@Service
public class MerchantServiceImpl implements UserService {

	@Autowired
	private PasswordHelper passwordHelper;

	@Autowired
	private MerchantDao merchantDao;

	@Override
	public BaseAuth getUserByName(String name) {
		
		
		return null;
	}

	@Override
	public void register(LoginVo loginVo, HttpSession session) {
		if (loginVo == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		// 判断两次密码是否一致
		if (!loginVo.getPassword().equals(loginVo.getSurePassword())) {
			throw new WebServiceException(CodeMsg.MERCHANT_SUREPASSWORD_WRONG);
		}

		Merchant merchant = new Merchant();
		merchant.setLoginName(loginVo.getName());
		merchant.setPassword(loginVo.getPassword());
		merchant.setMobile(loginVo.getMobile());
		passwordHelper.encryptPassword(merchant);
		Integer changeCount = merchantDao.add(merchant);
		if (changeCount != Constant.DEFALULT_ONE) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

	}

}
