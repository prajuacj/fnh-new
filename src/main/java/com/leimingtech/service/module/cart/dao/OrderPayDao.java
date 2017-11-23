package com.leimingtech.service.module.cart.dao;


import java.util.List;

import com.leimingtech.core.entity.base.OrderPay;
import com.leimingtech.service.utils.page.Pager;


/**
 *
 */
public interface OrderPayDao {

	/**
	 * 根据用户id查询
	 * @param pager
	 * @return
	 */
    List<OrderPay> queryBuyerId(Pager pager);
    
    /**
     * 保存
     * @param orderPay
     */
    void saveOrderPay(OrderPay orderPay);
    
    /**
     * 通过id查询
     * @param orderPay
     * @return
     */
    OrderPay findById(OrderPay orderPay);
    
    /**
     * 通过支付单号和买家id修改状态
     * @param orderPay
     */
    void updateOrderPayState(OrderPay orderPay);
}
