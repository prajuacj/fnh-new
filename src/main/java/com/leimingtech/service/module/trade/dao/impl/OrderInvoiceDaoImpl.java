package com.leimingtech.service.module.trade.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.OrderInvoice;
import com.leimingtech.service.module.trade.dao.OrderInvoiceDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderInvoiceMapper;

/**
 * 订单发票表
 * @author liukai
 */
@Repository
public class OrderInvoiceDaoImpl implements OrderInvoiceDao{
	
	@Resource
	private OrderInvoiceMapper orderInvoiceMapper;
	
	/**
	 * 根据订单id查询订单发票信息
	 * @param orderId
	 * @return
	 */
	public OrderInvoice findByOrderId(String orderId){
		return orderInvoiceMapper.findByOrderId(orderId);
	}
	
	/**
	 * 保存订单发票表
	 * @param orderInvoice
	 */
	public void saveOrderInvoice(OrderInvoice orderInvoice){
		orderInvoice.setInvId(IdGen.uuid());
		orderInvoiceMapper.saveOrderInvoice(orderInvoice);
	}

}
