package com.leimingtech.service.module.trade.dao;

import com.leimingtech.core.entity.StoreGoodsSalCount;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.vo.OrderGoodsVo;

import java.util.List;
import java.util.Map;

/**
 * Created by rabook on 2014/11/17.
 */
public interface OrderGoodsDao {
	
	/**
	 * 插入订单项
	 * @param orderGoods
	 */
    void saveOrderGoods(OrderGoods orderGoods);

    /**
     * 	修改订单项
     * @param orderGoods
     */
    void updateOrderGoods(OrderGoods orderGoods);
    
    /**
     * 根据订单id查询订单项
     * @param orderId	订单id
     * @return
     */
    List<OrderGoods> findByOrderId(String orderId);
    
    /**
     * 根据id查询订单项
     * @param recId 订单项id
     * @return
     */
    OrderGoods findById(String recId);
    
    /**
     * 根据物品id查询物品订单信息
     * @return
     */
    List<OrderGoodsVo> findOrderGoodsVoByGoodsId(String goodsId);
    
    /**
     * 根据订单id删除订单项
     * @param orderId 订单id
     */
    void deleteByOrderId(String orderId);
    
    /**
	 * 订单商品项详情,必传订单id,可传用户id和店铺id
	 * @param orderGoods
	 * @return
	 */
    OrderGoods findOrderGoodsDetail(OrderGoods orderGoods);
    /**
   	 * 时间段，店铺id查找内容
   	 * @param map
   	 * @return
   	 */
   	List<StoreGoodsSalCount> storeDoodsSalCount(Map<String,Object> map);
}
