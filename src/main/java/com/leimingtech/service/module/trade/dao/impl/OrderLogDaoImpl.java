package com.leimingtech.service.module.trade.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.OrderLog;
import com.leimingtech.service.module.trade.dao.OrderLogDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderLogMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rabook on 2014/11/15.
 */

@Repository
public class OrderLogDaoImpl implements OrderLogDao {

    @Resource
    private OrderLogMapper orderLogMapper;

    /**
     * 根据订单id查询订单日志
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderLog> findByOrderId(String orderId) {
        orderLogMapper.findByOrderId(orderId);
        return null;
    }

    /**
     * 通过id查询订单日志
     *
     * @param orderLogId
     * @return
     */
    @Override
    public OrderLog findById(String orderLogId) {
        return orderLogMapper.findById(orderLogId);
    }

    /**
     * 插入orderLog实体
     *
     * @param orderLog
     * @return
     */
    @Override
    public void saveOrderLog(OrderLog orderLog) {
        orderLog.setLogId(IdGen.uuid());
        orderLogMapper.saveOrderLog(orderLog);
    }

    /**
     * 更新orderLog实体
     *
     * @param orderLog
     * @return
     */
    @Override
    public void updateOrderLog(OrderLog orderLog) {
        orderLogMapper.updateOrderLog(orderLog);
    }

}
