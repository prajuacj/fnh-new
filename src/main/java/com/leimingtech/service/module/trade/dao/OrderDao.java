package com.leimingtech.service.module.trade.dao;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.GoodsBuyCount;
import com.leimingtech.core.entity.GoodsGeneralCount;
import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.OrderCount;
import com.leimingtech.core.entity.OrderExcel;
import com.leimingtech.core.entity.OrderSettlement;
import com.leimingtech.core.entity.OrderStaticExcel;
import com.leimingtech.core.entity.apibean.OrderApiBean;
import com.leimingtech.core.entity.apibean.OrderMemberApiBean;
import com.leimingtech.core.entity.vo.OrdermVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * 订单
 * @author liukai
 */
public interface OrderDao {
	
	/**
	 * 插入订单
	 * @param order
	 */
	void saveOrder(Order order);
	
	/**
	 * 删除订单
	 * @param orderId 订单id
	 */
	void deleteOrder(String orderId);
	
	/**
	 * 修改订单
	 * @param order
	 */
	void updateOrder(Order order);
	
	/**
	 * 修改订单状态
	 * @param order 订单实体,需orderaId,orderState字段,如果需要修改付款状态,需给paymentState字段赋值,
	 * 同时可以传入finnshedTime和shippingTime来更改订单完成和配送时间
	 */
	void updateOrderState(Order order);
	
	/**
	 * 分页查询订单总条数
	 * @param order 
	 * 			可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	int findOrderCount(Order order); 
	
	/**
	 * 分页查询订单
	 * @param pager 
	 * 			可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	List<Order> findOrderList(Pager pager);
	
	/**
	 * 分页查询结算所需订单
	 * @param pager 
	 * 			可加查询条件:订单状态,开始结束时间(starttime,endtime)endtime>订单完成时间>=starttime 
	 * @return 
	 */
	List<Order> findBillOrderList(Pager pager);
	
	/**
	 * 根据id查询订单,有订单项,订单日志
	 * @param orderId
	 * @return
	 */
	Order findById(String orderId);
	
	/**
	 * 订单详情,必传订单id,可传用户id和店铺id
	 * @param order
	 * @return
	 */
	Order findOrderDetail(Order order);
	
	/**
	 * 根据订单编号查询订单信息
	 * @param orderSn
	 * @return
	 */
	Order findByOrderSn(String orderSn);
	
	/**
	 * 根据订单支付编号查询订单
	 * @param paySn
	 * @return
	 */
	List<Order> findByPaySn(String paySn);
	
	/**
	 * 根据订单支付id查询订单
	 * @param payId
	 * @return
	 */
	List<Order> findByPayId(String payId);
	
	/**
	 * 根据订单状态查询订单数量
	 * @param order 可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	int findOrderCountByOrder(Order order);
	/**
	 * 根据不同条件查询订单状态
	 * @param map 可加查询条件:店铺id,订单状态,开始结束时间(starttime,endtime)，时间代号
	 * @return
	 */
	List<OrderCount> countorderbuy(Map<String,Object> map);
	/**
	 * 根据不同条件查询订单状态
	 * @param map 可加查询条件:店铺id,订单状态
	 * @return
	 */
	List<OrderStaticExcel> findorderexcel(Map<String,Object> map);
	/**
	 * 分页查询订单
	 * @param pager 
	 * 			可加查询条件:店铺id,订单状态
	 * @return
	 */
	List<OrdermVo> findOrderinfo(Pager pager);

	/**
	 * 通过条件获取订单列表
	 * @param order
	 * @return
	 */
	List<OrderExcel> findOrderByCondition(Order order);

	/**
	 * 分页查询订单接口实体集合
	 * @param pager
	 * @return
	 */
	List<OrderApiBean> findOrderApiBeanList(Pager pager);

	/**
	 * 分页查询订单用户实体集合
	 * @param pager
	 * @return
	 */
	List<OrderMemberApiBean> findOrderMemberApiBeanList(Pager pager);
	
	/**
	 * 分页查询订单接口实体集合，不显示取消订单
	 * @param pager
	 * @return
	 */
	List<OrderApiBean> findOrderApiBeanList1(Pager pager);
	
	/**
	 * 分页查询订单接口实体集合，不显示取消订单
	 * @param pager
	 * @return
	 */
	List<OrderApiBean> findOrderApiBeanList2(Pager pager);
	
	/**
	 * 根据不同条件查询购买数量
	 * @param pager 可加查询条件:店铺id,订单状态,开始结束时间(starttime,endtime)
	 * @return
	 */
	List<GoodsGeneralCount> findPurchaseRateIndex(Map map);

	/**
	 * 结算统计
	 * @param mp
	 * @return
	 */
	List<OrderSettlement> findBalance(Map mp);

	/**
	 * 收入总金额
	 * @param storeId
	 * @return
	 */
	Float findSellerPriceCountByStoreId(String storeId);
}
