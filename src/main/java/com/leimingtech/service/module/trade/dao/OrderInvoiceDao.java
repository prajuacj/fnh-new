package com.leimingtech.service.module.trade.dao;

import com.leimingtech.core.entity.base.OrderInvoice;

/**
 * 订单发票信息表
 * @author liukai
 */
public interface OrderInvoiceDao {
	/**
	 * 根据订单id查询订单发票信息
	 * @param orderId
	 * @return
	 */
	OrderInvoice findByOrderId(String orderId);
	
	/**
	 * 保存订单发票表
	 * @param orderInvoice
	 */
	void saveOrderInvoice(OrderInvoice orderInvoice);
}
