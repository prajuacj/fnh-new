package com.leimingtech.service.module.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.OrderInvoice;
import com.leimingtech.service.module.trade.dao.OrderInvoiceDao;
import com.leimingtech.service.module.trade.service.OrderInvoiceService;

/**
 * 订单发票表
 * @author liukai
 */
@Service
public class OrderInvoiceServiceImpl implements OrderInvoiceService{
	
	@Resource
	private OrderInvoiceDao orderInvoiceDao;
	
	/**
	 * 根据订单id查询订单发票信息
	 * @param orderId
	 * @return
	 */
	public OrderInvoice findByOrderId(String orderId){
		return orderInvoiceDao.findByOrderId(orderId);
	}
	
	/**
	 * 保存订单发票表
	 * @param orderInvoice
	 */
	public void saveOrderInvoice(OrderInvoice orderInvoice){
		orderInvoiceDao.saveOrderInvoice(orderInvoice);
	}
}
