package com.ysdevelop.lorchard.api.mapper;



import com.ysdevelop.lorchard.api.entity.MerchantFeedbackVo;

/**
 * 
 * 
 * @author 徐一鸣 
 *
 * @Date 2018年9月11日 下午3:54:03 
 *
 * @Package com.ysdevelop.lorchard.api.mapper
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
public interface ApiMerchantFeedbackDao {
	
	/**
	 * 添加用户反馈意见
	 * @param merchantFeedback
	 * @return
	 */
	Integer add(MerchantFeedbackVo merchantFeedback);
}
