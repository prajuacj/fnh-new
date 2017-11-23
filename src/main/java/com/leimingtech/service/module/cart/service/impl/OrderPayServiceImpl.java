package com.leimingtech.service.module.cart.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.OrderPay;
import com.leimingtech.service.module.cart.dao.OrderPayDao;
import com.leimingtech.service.module.cart.service.OrderPayService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderPayServiceImpl implements OrderPayService {
	
	@Resource
	private OrderPayDao orderPayDao;
	
	/**
	 * 根据用户id查询
	 * @param pager
	 * @return
	 */
	@Override
	public Pager queryBuyerId(Pager pager) {
		List<OrderPay> list=orderPayDao.queryBuyerId(pager);
		pager.setResult(list);
		return pager;
	}
	
	/**
     * 保存
     * @param orderPay
     */
	@Override
	public void saveOrderPay(OrderPay orderPay) {
		orderPayDao.saveOrderPay(orderPay);
	}
	
	/**
     * 通过id查询
     * @param orderPay
     * @return
     */
	@Override
	public OrderPay findById(OrderPay orderPay) {
		return orderPayDao.findById(orderPay);
	}
	
	/**
     * 通过支付单号和买家id修改状态
     * @param orderPay
     */
	@Override
	public void updateOrderPayState(OrderPay orderPay) {
		orderPayDao.updateOrderPayState(orderPay);
	}
}
