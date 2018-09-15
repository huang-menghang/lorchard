package com.ysdevelop.lorchard.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysdevelop.lorchard.api.entity.MerchantFeedbackVo;
import com.ysdevelop.lorchard.api.mapper.ApiMerchantFeedbackDao;
import com.ysdevelop.lorchard.api.service.ApiMerchantFeedbackService;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;

/**
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月11日 下午3:54:34 
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiMerchantFeedbackServiceImpl implements ApiMerchantFeedbackService {
	@Autowired
	private ApiMerchantFeedbackDao merchantFeedbackDao;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(MerchantFeedbackVo merchantFeedback) {
		if (merchantFeedback == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		Integer changeCount = merchantFeedbackDao.add(merchantFeedback);
		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.CATEGORY_ADD_FAILED);
		}
	}

}
