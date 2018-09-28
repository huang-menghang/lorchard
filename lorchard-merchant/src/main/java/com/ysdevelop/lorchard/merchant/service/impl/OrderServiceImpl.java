package com.ysdevelop.lorchard.merchant.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysdevelop.lorchard.common.exception.WebServiceException;
import com.ysdevelop.lorchard.common.observer.Observer;
import com.ysdevelop.lorchard.common.observer.Subject;
import com.ysdevelop.lorchard.common.result.CodeMsg;
import com.ysdevelop.lorchard.common.utils.ApiConstant;
import com.ysdevelop.lorchard.merchant.entity.Goods;
import com.ysdevelop.lorchard.merchant.entity.Order;
import com.ysdevelop.lorchard.merchant.entity.OrderItem;
import com.ysdevelop.lorchard.merchant.mapper.OrderDao;
import com.ysdevelop.lorchard.merchant.service.FinanceService;
import com.ysdevelop.lorchard.merchant.service.OrderService;
import com.ysdevelop.lorchard.mq.bo.MerchantMessage;
import com.ysdevelop.lorchard.mq.service.MerchantMessageConsumer;


/**
 * @author zesen
 *
 * @Package com.ysdevelop.lorchard.merchant.service.impl
 *
 * @Description 订单
 *
 * @Date 2018年9月10日
 *
 * @Version
 */
@Service
public class OrderServiceImpl implements OrderService, Observer, InitializingBean {

	@Autowired
	private MerchantMessageConsumer messageConsumer;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private FinanceService financeService;
	
	private Integer orderStatusIsFive =5;
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public PageInfo<Order> list(Map<String, String> queryMap) {
		if (queryMap == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		System.out.println(queryMap.get("orderMerchantId"));
		// 获取分页条件的
		String pageSize = queryMap.get("limit");
		String pageNum = queryMap.get("page");
		if (pageSize == null || pageNum == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Integer integerPageSize = Integer.parseInt(pageSize);
		Integer integerPageNum = Integer.parseInt(pageNum);

		PageHelper.startPage(integerPageNum, integerPageSize, Boolean.TRUE);
		List<Order> order = orderDao.list(queryMap);

		PageInfo<Order> pageInfo = new PageInfo<>(order);
		return pageInfo;
	}

	/**
	 * 通过id获取订单的详细信息
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Order getById(Integer id) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		Order order = orderDao.getById(id);
		return order;
	}

	/**
	 * 通过id取消订单,增加库存,减少销量
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateById(Integer id, Integer orderStatus) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		
		if (orderStatus.equals(orderStatusIsFive)) {
			throw new WebServiceException(CodeMsg.IS_COMPLETED);
		} else {
			Integer updateById = orderDao.updateById(id);
			if(updateById== ApiConstant.DEFALULT_ZERO) {
				throw new WebServiceException(CodeMsg.CANCAL_ORDER_ERROR);
			}else {
				List<OrderItem> orderItemById = orderDao.getOrderItemById(id);
				List<Goods> stockAndSales = orderDao.getStockAndSales(orderItemById);
				for (OrderItem orderItem : orderItemById) {
					for (Goods goods : stockAndSales) {
						if(orderItem.getGoodsId()==goods.getId()) {
							orderItem.setStock(goods.getStock());
							orderItem.setSales(goods.getSales());
						}
					}
				}
				Integer updateStockAndSales = orderDao.updateStockAndSales(orderItemById);
				if(updateStockAndSales== ApiConstant.DEFALULT_ZERO) {
					throw new WebServiceException(CodeMsg.CANCAL_ORDER_ERROR);
				}
			}
		}

	}

	/**
	 * 订单发货
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(Integer id, Integer orderStatus) {
		if (id == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		if (orderStatus == ApiConstant.DEFALULT_ONE) {
			Integer update = orderDao.update(id);
			if(update== ApiConstant.DEFALULT_ZERO) {
				throw new WebServiceException(CodeMsg.DELIVER_ERROR);
			}
		} else {
			throw new WebServiceException(CodeMsg.ISNOT_PENDINGDELIVERY);
		}
	}

	/**
	 * 获取订单下的所有商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public List<OrderItem> getItemsByOrderNo(Map<String, String> queryMap) {
		List<OrderItem> listOrderItems = orderDao.listOrderItems(queryMap);
		
		return listOrderItems;
	}
	
	/**
	 * 订单完成
	 * */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void completeById(Order order) {
		if (order.getId() == null) {
			throw new WebServiceException(CodeMsg.SERVER_ERROR);
		}
		if (order.getOrderStatus() == ApiConstant.DEFALULT_TWO) {
			financeService.addFinance(order);
			orderDao.updateStatus(order);
		} else {
			throw new WebServiceException(CodeMsg.ISNOT_COMPLETE);
		}
	}
	
	
	@Override
	public void update(Subject subject, Object param) {
		if (param != null) {
			try {
				MerchantMessage message = JSON.parseObject((String) param, MerchantMessage.class);
				logger.info("messgae mq ---->"+JSON.toJSONString(message));
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("message convert error");
			}
		} else {
			logger.error("message is error");
			throw new NullPointerException("message is null");
		}

	}

	// 添加观察者
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("添加观察者.....");
		messageConsumer.addObserver(this);
	}
}
