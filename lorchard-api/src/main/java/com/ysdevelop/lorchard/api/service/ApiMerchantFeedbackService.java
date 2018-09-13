package com.ysdevelop.lorchard.api.service;

import com.ysdevelop.lorchard.api.entity.MerchantFeedbackVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月11日 下午3:54:14 
 *
 * @Package com.ysdevelop.lorchard.api.service
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiMerchantFeedbackService {
	/**
	 * 添加反馈意见
	 * @param merchantFeedback
	 */
	void add(MerchantFeedbackVo merchantFeedback);
}
