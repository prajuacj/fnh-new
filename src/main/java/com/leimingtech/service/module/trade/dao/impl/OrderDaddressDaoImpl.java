package com.leimingtech.service.module.trade.dao.impl;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.OrderDaddress;
import com.leimingtech.service.module.trade.dao.OrderDaddressDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderDaddressMapper;

/**
 * 订单发货地址
 * @author liukai
 */
@Repository
public class OrderDaddressDaoImpl implements OrderDaddressDao{
	
	@Resource
	private OrderDaddressMapper orderDaddressMapper;
	
	/**
	 * 保存订单发货地址
	 * @param daddress
	 */
	@Override
	public void saveOrderDaddress(OrderDaddress OrderDaddress) {
		OrderDaddress.setAddressId(IdGen.uuid());
		orderDaddressMapper.saveOrderDaddress(OrderDaddress);
	}
	
	/**
	 * 修改订单发货地址
	 * @param daddress
	 */
	@Override
	public void updateOrderDaddress(OrderDaddress orderDaddress) {
		orderDaddressMapper.updateOrderDaddress(orderDaddress);
	}
	
	/**
	 * 根据id查询订单发货地址
	 * @param addressId
	 * @return
	 */
	@Override
	public OrderDaddress findOrderDaddressById(String addressId) {
		return orderDaddressMapper.findOrderDaddressById(addressId);
	}
}
