package com.leimingtech.service.module.trade.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.OrderAddress;
import com.leimingtech.service.module.trade.dao.OrderAddressDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderAddressMapper;

/**
 * 订单收货地址
 * @author liukai
 */
@Repository
public class OrderAddressDaoImpl implements OrderAddressDao{
	
	@Resource
	private OrderAddressMapper orderAddressMapper;
	
	/**
	 * 保存订单收货地址
	 * @param orderAddress
	 */
	@Override
	public void saveOrderAddress(OrderAddress orderAddress) {
		orderAddress.setAddressId(IdGen.uuid());
		orderAddressMapper.saveOrderAddress(orderAddress);
	}
	
	/**
	 * 通过id查询订单收货地址
	 * @param addressId
	 * @return
	 */
	@Override
	public OrderAddress findById(String addressId) {
		return orderAddressMapper.findById(addressId);
	}
	
	/**
	 * 修改订单收货地址
	 * @param orderAddress
	 */
	@Override
	public void updateAddress(OrderAddress orderAddress) {
		orderAddressMapper.updateAddress(orderAddress);
	}

}
