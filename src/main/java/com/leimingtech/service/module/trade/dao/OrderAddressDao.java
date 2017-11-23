package com.leimingtech.service.module.trade.dao;

import com.leimingtech.core.entity.base.OrderAddress;

/**
 * 订单收货地址
 * @author liukai
 */
public interface OrderAddressDao {
	/**
	 * 保存订单收货地址
	 * @param orderAddress
	 */
	void saveOrderAddress(OrderAddress orderAddress);
	
	/**
	 * 通过id查询订单收货地址
	 * @param addressId
	 * @return
	 */
	OrderAddress findById(String addressId);
	
	/**
	 * 修改订单收货地址
	 * @param orderAddress
	 */
	void updateAddress(OrderAddress orderAddress);
}
