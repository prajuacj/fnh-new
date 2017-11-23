package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.OrderLog;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * Created by rabook on 2014/11/15.
 */

@SqlMapper
public interface OrderLogMapper {
	
	/**
	 * 根据订单id查询订单日志
	 * @param orderId
	 * @return
	 */
	List<OrderLog> findByOrderId(String orderId);
	
	/**
     * 通过id查询订单日志
     * @param orderLogId 
     * @return
     */
	OrderLog findById(String orderLogId);
	
	/**
     * 插入orderLog实体
     * @param order
     * @return
     */
    void saveOrderLog(OrderLog orderLog);
	
    
    /**
     * 更新orderLog实体
     * @param order
     * @return
     */
    void updateOrderLog(OrderLog orderLog);
}
