package com.ysdevelop.lorchard.api.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.api.entity.MemberVo;
import com.ysdevelop.lorchard.api.entity.OrderItemVo;
import com.ysdevelop.lorchard.api.entity.OrderVo;
import com.ysdevelop.lorchard.api.entity.PreviewImagesVo;
import com.ysdevelop.lorchard.api.mapper.ApiGoodsDao;
import com.ysdevelop.lorchard.api.mapper.ApiOrderDao;
import com.ysdevelop.lorchard.api.mapper.ApiOrderItemDao;
import com.ysdevelop.lorchard.api.service.ApiMemberService;
import com.ysdevelop.lorchard.api.service.ApiOrderItemService;
import com.ysdevelop.lorchard.api.service.ApiOrderService;
import com.ysdevelop.lorchard.api.util.ApiConstant;
import com.ysdevelop.lorchard.api.util.WechatRefundApiResult;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.redis.RedisService;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.Constant;
import com.ysdevelop.lorchard.common.utils.NumberArithmeticUtils;
import com.ysdevelop.lorchard.common.utils.OrderNumberGeneratorUtil;
import com.ysdevelop.lorchard.common.utils.WechantAppletApiUtil;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.mq.constant.MessageKey;
import com.ysdevelop.lorchard.mq.define.MessageType;
import com.ysdevelop.lorchard.mq.key.MerchantMessageKey;
import com.ysdevelop.lorchard.mq.service.MessageProducer;

/**
 * 
 * 
 * @author 徐一鸣
 *
 * @Date 2018年9月10日 上午10:21:44
 *
 * @Package com.ysdevelop.lorchard.api.service.impl
 *
 * @Description: TODO
 *
 * @version V1.0
 *
 */
@Service
public class ApiOrderServiceImpl implements ApiOrderService, InitializingBean {

	@Autowired
	private ApiOrderDao orderDao;

	@Autowired
	private ApiMemberService memberService;

	@Autowired
	private ApiOrderItemDao orderItemDao;

	@Autowired
	private ApiGoodsDao goodsDao;

	@Autowired
	private ApiOrderItemService orderItemService;

	@Autowired
	private MessageProducer messageProducer;

	@Autowired
	private RedisService redisService;

	private WxPayService wxPayService;

	private WxPayConfig wxPayConfig;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void afterPropertiesSet() throws Exception {
		wxPayService = new WxPayServiceImpl();
		wxPayConfig = new WxPayConfig();
		wxPayConfig.setAppId(WechantAppletApiUtil.APPID);
		wxPayConfig.setMchId(WechantAppletApiUtil.MCH_ID);
		wxPayConfig.setMchKey(WechantAppletApiUtil.KEY);
		wxPayConfig.setNotifyUrl(WechantAppletApiUtil.NOTITY_URL);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public String createOrder(OrderVo order) {
		if (order.getOrderItems() == null || order.getOrderItems().size() == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		String orderNo = OrderNumberGeneratorUtil.get().toString();
		order.setOrderNo(orderNo);
		order.setOrderMerchantId(order.getOrderItems().get(0).getMerchantId());
		order.setOrderMemberId(order.getOrderItems().get(0).getMemberId());
		order.setOrderStatus(Constant.OrderType.UNPAYMENYT.getIndex());
		order.setOrderTotalPrice(getTotalPrice(order.getOrderItems()));
		Integer changeCount = orderDao.add(order);
		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		setItemOrderNo(order.getOrderItems(), orderNo);
		changeCount = orderItemService.batchInsert(order.getOrderItems());
		if (changeCount == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}

		return orderNo;
	}

	public void setItemOrderNo(List<OrderItemVo> orderItems, String orderNo) {
		for (int i = 0; i < orderItems.size(); i++) {
			OrderItemVo orderItem = orderItems.get(i);
			orderItem.setOrderNo(orderNo + "");
			orderItems.set(i, orderItem);
		}
	}

	private Double getTotalPrice(List<OrderItemVo> orderItems) {

		BigDecimal totalPrice = BigDecimal.ZERO;

		for (int i = 0; i < orderItems.size(); i++) {
			totalPrice = NumberArithmeticUtils.safeAdd(totalPrice, new BigDecimal(orderItems.get(i).getItemPrice()));
		}

		return totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@Override
	public PageInfo<OrderVo> list(Map<String, String> queryMap) {
		if (queryMap == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		// 获取分页条件的
		String pageSize = queryMap.get("limit");
		String pageNum = queryMap.get("page");
		if (pageSize == null || pageNum == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Integer integerPageSize = Integer.parseInt(pageSize);
		Integer integerPageNum = Integer.parseInt(pageNum);
		// 调用存储过程实现树形分类
		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);
		List<OrderVo> orders = orderDao.list(queryMap);
		List<OrderItemVo> orderItems = orderItemDao.list();
		List<PreviewImagesVo> listPreviewImage = goodsDao.listPreviewImage();
		setOrders(orders, orderItems, listPreviewImage);
		PageInfo<OrderVo> pageInfo = new PageInfo<>(orders);
		return pageInfo;
	}

	private void setOrders(List<OrderVo> orders, List<OrderItemVo> orderItems, List<PreviewImagesVo> listPreviewImage) {
		for (OrderItemVo orderItem : orderItems) {
			List<PreviewImagesVo> transitionPreviewImage = new ArrayList<>();
			for (PreviewImagesVo previewImages : listPreviewImage) {
				if (orderItem.getGoodsId() == previewImages.getGoodsId()) {
					transitionPreviewImage.add(previewImages);
				}
			}
			orderItem.setPreviewImages(transitionPreviewImage);
		}

		for (OrderVo order : orders) {
			List<OrderItemVo> transitionOrderItem = new ArrayList<>();
			for (OrderItemVo orderItem : orderItems) {
				if (order.getOrderNo().equals(orderItem.getOrderNo())) {
					transitionOrderItem.add(orderItem);
				}
			}
			order.setOrderItems(transitionOrderItem);
			setOrder(order);
		}
	}

	private void setOrder(OrderVo order) {
		Date date = order.getCreateTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		order.setAddDate(format.format(date));

		switch (order.getOrderStatus()) {
		case ApiConstant.DEFALULT_ZERO:
			order.setStatusStr(ApiConstant.STATUS_ZERO);
			break;
		case ApiConstant.DEFALULT_ONE:
			order.setStatusStr(ApiConstant.STATUS_ONE);
			break;
		case ApiConstant.DEFALULT_TWO:
			order.setStatusStr(ApiConstant.STATUS_TWO);
			break;
		case ApiConstant.DEFALULT_THREE:
			order.setStatusStr(ApiConstant.STATUS_THREE);
			break;
		case ApiConstant.DEFALULT_FOUR:
			order.setStatusStr(ApiConstant.STATUS_FOUR);
			break;
		case ApiConstant.DEFALULT_FIVE:
			order.setStatusStr(ApiConstant.STATUS_FIVE);
			break;
		default:
			break;
		}
	}

	@Override
	public void updateStatusByOrderNo(String orderNo,Integer status) {
		Integer count = orderDao.updateStatusByOrderNo(orderNo,status);
		if (count == Constant.DEFALULT_ZERO) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		if(status == ApiConstant.DEFALULT_FIVE){
			sendMessage(orderNo, MessageType.FINISHED);
		}
	}

	@Override
	public OrderVo getOrderByNo(String orderNo) {
		OrderVo order = orderDao.getOrderByNo(orderNo);
		List<OrderItemVo> orderItems = orderItemDao.getOrderByNo(orderNo);
		List<PreviewImagesVo> listPreviewImage = goodsDao.listPreviewImage();
		for (OrderItemVo orderItem : orderItems) {
			List<PreviewImagesVo> transitionPreviewImage = new ArrayList<>();
			for (PreviewImagesVo previewImages : listPreviewImage) {
				if (orderItem.getGoodsId() == previewImages.getGoodsId()) {
					transitionPreviewImage.add(previewImages);
				}
			}
			orderItem.setPreviewImages(transitionPreviewImage);
		}
		order.setOrderItems(orderItems);
		setOrder(order);
		return order;
	}

	@Override
	public WxPayMpOrderResult prepareWxpay(OrderVo order, HttpServletRequest request) {
		if (order == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		MemberVo memberVo = memberService.getMemberById(order.getOrderMemberId());
		if (memberVo == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		wxPayService.setConfig(wxPayConfig);
		caculatePayPrice(order);
		WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
		// 重新确认下订单
		if (order.getOrderStatus() == Constant.OrderType.UNPAYMENYT.getIndex()) {
			List<OrderItemVo> orderItems = order.getOrderItems();
			if (orderItems == null || orderItems.size() == Constant.DEFALULT_ZERO) {
				throw new WebServiceException(CodeMsg.SERVER_ERROR);
			}
			orderRequest.setBody(generateGoodsInfo(orderItems));
			orderRequest.setSpbillCreateIp("192.168.0.1");
			orderRequest.setOutTradeNo(order.getOrderNo().toString());
			orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee(order.getOrderPayPrice().toString()));
			orderRequest.setTradeType(WechantAppletApiUtil.TRADAETYPE);
			orderRequest.setOpenid(memberVo.getOpenid());
			try {
				WxPayMpOrderResult orderResult = wxPayService.createOrder(orderRequest);
				return orderResult;
			} catch (WxPayException e) {
				e.printStackTrace();
				throw new WebServiceException(CodeMsg.ORDER_CREATE_FAILED);
			}

		} else {
			throw new WebServiceException(CodeMsg.ORDER_PAYED);
		}

	}

	private void caculatePayPrice(OrderVo order) {
		BigDecimal payMoney = BigDecimal.ZERO;
		payMoney = NumberArithmeticUtils.safeAdd(new BigDecimal(order.getFreightPrice()),
				new BigDecimal(order.getOrderTotalPrice()));
		order.setOrderPayPrice(payMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	public String generateGoodsInfo(List<OrderItemVo> orderItems) {
		StringBuffer goodsInfo = new StringBuffer();
		for (int i = 0; i < orderItems.size(); i++) {
			goodsInfo.append(orderItems.get(i).getGoodsName() + "|");
		}

		return goodsInfo.toString();
	}

	@Override
	public void confirmOrder(WechatRefundApiResult result, HttpServletResponse response) {
		try {
			String resultCode = result.getResultCode();
			if (resultCode.equalsIgnoreCase(ApiConstant.FAIL)) {
				// 订单编号
				String outTradeNo = result.getOutTradeNo();
				logger.error("订单" + outTradeNo + "支付失败");
				response.getWriter().write(setXml("SUCCESS", "OK"));
			} else if (resultCode.equalsIgnoreCase(ApiConstant.SUCCESS)) {
				// 订单编号
				String orderNo = result.getOutTradeNo();
				sendMessage(orderNo, MessageType.UNDELIVERY);
				orderDao.updateStatusByOrderNo(orderNo, ApiConstant.DEFALULT_ONE);
				response.getWriter().write(setXml("SUCCESS", "OK"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public static String setXml(String returnCode, String returnMsg) {
		return "<xml><return_code><![CDATA[" + returnCode + "]]></return_code><return_msg><![CDATA[" + returnMsg
				+ "]]></return_msg></xml>";
	}

	private void sendMessage(String orderNo, MessageType messageType) {
		OrderVo order = orderDao.getOrderByNo(orderNo);
		MerchantMessage merchantMessage = new MerchantMessage();
		merchantMessage.setMerchantId(order.getOrderMerchantId());
		merchantMessage.setUserId(order.getOrderMemberId());
		merchantMessage.setConent(messageType.getValue());
		merchantMessage.setMessageType(messageType);
		merchantMessage.setCreateTime(new Date());

		try {
			messageProducer.sendMessage(MessageKey.MERCHANT_KEY, JSON.toJSONString(merchantMessage));
			String jsonMessage = redisService.get(MerchantMessageKey.messageKey, order.getOrderMerchantId().toString(),
					String.class);
			List<MerchantMessage> messages = JSON.parseArray(jsonMessage, MerchantMessage.class);
			if (messages == null || messages.isEmpty()) {
				messages = new ArrayList<MerchantMessage>();
			}
			messages.add(merchantMessage);
			jsonMessage = JSON.toJSONString(messages);
			redisService.set(MerchantMessageKey.messageKey, order.getOrderMerchantId().toString(), jsonMessage);
		} catch (Exception e) {
			logger.error("queueKey is null or object param is null");
		}
	}
}
