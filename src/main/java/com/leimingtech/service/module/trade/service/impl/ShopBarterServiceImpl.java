package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.common.StringUtils;
import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.base.ShopBarter;
import com.leimingtech.core.entity.base.ShopBarterLog;
import com.leimingtech.core.entity.vo.BarterDetailVo;
import com.leimingtech.service.module.trade.common.BarterState;
import com.leimingtech.service.module.trade.common.OrderState;
import com.leimingtech.service.module.trade.dao.ShopBarterDao;
import com.leimingtech.service.module.trade.dao.ShopBarterLogDao;
import com.leimingtech.service.module.trade.service.OrderGoodsService;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.trade.service.ShopBarterService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 换货表的增删改查ServiceImpl
 *
 * @author gyh
 * @version 2015-12-22
 */
@Service
public class ShopBarterServiceImpl implements ShopBarterService {

	/**
	 * 换货表的增删改查DAO接口
	 */
	@Resource
	private ShopBarterDao shopBarterDao;

	@Resource
	private ShopBarterLogDao shopBarterLogDao;

	@Resource
	private OrderService orderService;

	@Resource
	private OrderGoodsService orderGoodsService;

	/**
	 * 查询分页换货表的增删改查数据
	 *
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopBarterPagerList(Pager pager) {
		List<ShopBarter> list = shopBarterDao.findShopBarterPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过barterId获取单条换货表的增删改查数据
	 *
	 * @param barterId
	 * @return
	 */
	@Override
	public ShopBarter findShopBarterByBarterId(String barterId) {
		return shopBarterDao.findShopBarterByBarterId(barterId);
	}

	/**
	 * 通过barterId删除换货表的增删改查数据
	 *
	 * @param barterId
	 */
	@Override
	public void deleteShopBarterByBarterId(String barterId) {
		shopBarterDao.deleteShopBarterByBarterId(barterId);
	}

	/**
	 * 修改换货表的增删改查数据
	 *
	 * @param shopBarter
	 */
	@Override
	public void updateShopBarter(ShopBarter shopBarter) {
		shopBarterDao.updateShopBarter(shopBarter);
	}

	/**
	 * 保存换货表的增删改查数据
	 *
	 * @param shopBarter
	 */
	@Override
	public void saveShopBarter(ShopBarter shopBarter) {
		shopBarterDao.saveShopBarter(shopBarter);
	}

	/**
	 * 获取所有换货表的增删改查数据
	 *
	 * @return
	 */
	@Override
	public List<ShopBarter> findShopBarterAllList() {
		return shopBarterDao.findShopBarterAllList();
	}

	/**
	 * 查询退款退货详情,必传退货id,可传用户id和店铺id,不需要传null
	 *
	 * @param barterId
	 * @param buyerId
	 * @param storeId
	 * @return
	 */
	@Override
	public BarterDetailVo findBarterDetail(String barterId, String buyerId, String storeId) {
		ShopBarter shopBarter = new ShopBarter();
		shopBarter.setBarterId(barterId);
		if (StringUtils.isNotEmpty(buyerId)) {
			shopBarter.setBuyerId(buyerId);
		}
		if (StringUtils.isNotEmpty(storeId)) {
			shopBarter.setStoreId(storeId);
		}
		return shopBarterDao.findBarterDetail(shopBarter);
	}

	/**
	 * 卖家换货审核
	 *
	 * @param barterId
	 *            记录ID
	 * @param sellerState
	 *            卖家处理状态
	 * @param sellerMessage
	 *            卖家备注
	 * @param operator
	 *            操作人
	 */
	@Override
	public void updateBarterSeller(String barterId, Integer sellerState, String sellerMessage, String operator) {
		ShopBarter shopBarter = shopBarterDao.findShopBarterByBarterId(barterId);
		if (shopBarter.getSellerState() == BarterState.SELLER_STATE_PENDING_AUDIT) {
			// 判断卖家是否同意
			if (sellerState == BarterState.SELLER_STATE_AGREE) { // 若同意,修改商品状态为带发货
				shopBarter.setGoodsState(BarterState.GOODS_STATE_UNSHIP); // 物流状态
				shopBarter.setSellerState(BarterState.SELLER_STATE_AGREE); // 卖家处理状态
			} else {
				shopBarter.setSellerState(BarterState.SELLER_STATE_DISAGREE); // 卖家处理状态
			}
			shopBarter.setSellerMessage(sellerMessage); // 卖家备注
			shopBarter.setSellerTime(System.currentTimeMillis()); // 卖家处理时间
			shopBarterDao.updateShopBarter(shopBarter);

			ShopBarterLog barterLog = new ShopBarterLog();
			barterLog.setBarterId(shopBarter.getBarterId());// 换货表id
			// 判断卖家同意或拒绝
			if (sellerState == BarterState.SELLER_STATE_DISAGREE) {
				barterLog.setBarterState(BarterState.SELLER_STATE_DISAGREE + ""); // 换货状态信息
				barterLog.setChangeState(BarterState.SELLER_STATE_DISAGREE + ""); // 下一步退货状态信息
				barterLog.setStateInfo("卖家已拒绝换货服务申请"); // 换货状态描述
			} else if (sellerState == BarterState.SELLER_STATE_AGREE) {
				barterLog.setBarterState(BarterState.SELLER_STATE_AGREE + ""); // 换货状态信息
				barterLog.setChangeState(BarterState.SELLER_STATE_AGREE + ""); // 下一步换货状态信息
				barterLog.setStateInfo("换货服务已通过卖家审核,请买家尽快将商品寄回"); // 换货状态描述
			}
			barterLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			barterLog.setOperator(operator); // 操作人
			// 保存换货日志
			shopBarterLogDao.saveShopBarterLog(barterLog);
		}

	}

	/**
	 * 换货买家发货
	 *
	 * @param barterId
	 * @param expressName
	 * @param invoiceNo
	 */
	@Override
	public void updateBarterDelivery(String barterId, String expressName, String invoiceNo) {
		ShopBarter shopBarter = shopBarterDao.findShopBarterByBarterId(barterId);
		if (shopBarter.getGoodsState() != null && shopBarter.getGoodsState() == BarterState.GOODS_STATE_UNSHIP) {
			// Express express = expressService.findById(expressId);

			shopBarter.setGoodsState(BarterState.GOODS_STATE_NOT_RECEIVING); // 物流状态
			shopBarter.setBuyerExpressId("0"); // 物流公司编号
			shopBarter.setBuyerInvoiceNo(invoiceNo); // 物流单号
			shopBarter.setBuyerExpressName(expressName); // 物流公司名称
			shopBarter.setBuyerShipTime(System.currentTimeMillis()); // 发货时间
			shopBarterDao.updateShopBarter(shopBarter);

			ShopBarterLog barterLog = new ShopBarterLog();
			barterLog.setBarterId(shopBarter.getBarterId()); // 换货表id
			barterLog.setBarterState(BarterState.SELLER_STATE_AGREE + ""); // 换货状态信息
			barterLog.setChangeState(BarterState.SELLER_STATE_AGREE + ""); // 下一步换货状态信息
			barterLog.setStateInfo("买家已发货,等待卖家收货"); // 换货状态描述
			barterLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			barterLog.setOperator(shopBarter.getBuyerName()); // 操作人
			// 保存换货日志
			shopBarterLogDao.saveShopBarterLog(barterLog);
		}
	}

	/**
	 * 换货卖家收货
	 *
	 * @param barterId
	 *            记录ID
	 * @param receiveMessage
	 *            收货备注
	 * @param operator
	 *            操作人
	 */
	@Override
	public void updateBarterConfirm(String barterId, String receiveMessage, String operator) {
		ShopBarter shopBarter = shopBarterDao.findShopBarterByBarterId(barterId);
		if (shopBarter.getGoodsState() != null && shopBarter.getGoodsState() == BarterState.GOODS_STATE_NOT_RECEIVING) {
			// 通过订单id查询订单信息
			Order order = orderService.findById(shopBarter.getOrderId());

			OrderGoods orderGoods = orderGoodsService.findById(shopBarter.getOrderGoodsId());
			shopBarter.setGoodsState(BarterState.GOODS_STATE_RECEIVED); // 物流状态
			shopBarter.setSellerReceiveMessage(receiveMessage); // 收货备注
			shopBarter.setSellerReceiveTime(System.currentTimeMillis()); // 收货时间
			shopBarter.setSellerDelayTime(System.currentTimeMillis() - shopBarter.getBuyerShipTime()); // 收货延迟时间
			shopBarterDao.updateShopBarter(shopBarter);

			/**
			 * 修改订单换货数量和换货状态
			 */
			// 创建一个订单换货的订单项个数,初始为1,代表当前换货的订单项
			int goodsSize = 1;
			for (OrderGoods orderGoods1 : order.getOrderGoodsList()) {
				// 判断当前订单项是否已换货
				if (orderGoods1.getGoodsBarterNum() != null && orderGoods1.getGoodsBarterNum() != 0) { // 已换货
					goodsSize += 1;
				}
			}
			Order newOrder = new Order();
			newOrder.setOrderId(shopBarter.getOrderId());
			newOrder.setBarterNum(order.getBarterNum() + shopBarter.getGoodsNum());
			// 判断订单中订单项是否都换货
			if (order.getOrderGoodsList().size() > goodsSize) {
				newOrder.setBarterState(OrderState.BARTER_STATE_SOM);
			} else if (order.getOrderGoodsList().size() == goodsSize) {
				newOrder.setBarterState(OrderState.BARTER_STATE_ALL);
			}
			// 修改订单
			orderService.updateOrder(newOrder);
			ShopBarterLog barterLog = new ShopBarterLog();
			barterLog.setBarterId(shopBarter.getBarterId()); // 换货表id
			barterLog.setBarterState(BarterState.SELLER_STATE_AGREE + "");// 换货状态信息
			barterLog.setChangeState(BarterState.SELLER_STATE_AGREE + ""); // 下一步换货状态信息
			barterLog.setStateInfo("卖家已收货,等待卖家发货"); // 换货状态描述
			barterLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			barterLog.setOperator(operator); // 操作人
			// 保存换货日志
			shopBarterLogDao.saveShopBarterLog(barterLog);
		}
	}

	/**
	 * 换货卖家发货
	 *
	 * @param barterId
	 *            记录ID
	 * @param seName
	 *            物流公司名称
	 * @param shippingCode
	 *            运单号
	 * @param shippingExpressId
	 *            物流公司编号
	 */
	@Override
	public void updateBarterSellerDelivery(String barterId, String seName, String shippingCode,
			String shippingExpressId) {
		ShopBarter shopBarter = shopBarterDao.findShopBarterByBarterId(barterId);
		if (shopBarter.getGoodsState() != null && shopBarter.getGoodsState() == BarterState.GOODS_STATE_RECEIVED) {
			// Express express = expressService.findById(expressId);
			shopBarter.setGoodsState(BarterState.GOODS_STATE_BUYER_NOT_RECEIVING); // 物流状态
			if (StringUtils.isNoneBlank(shippingExpressId)) {
				shopBarter.setSellerExpressId(shippingExpressId); // 物流公司编号
			}
			shopBarter.setSellerInvoiceNo(shippingCode); // 物流单号
			shopBarter.setSellerExpressName(seName); // 物流公司名称
			shopBarter.setSellerShipTime(System.currentTimeMillis()); // 发货时间
			shopBarterDao.updateShopBarter(shopBarter);

			ShopBarterLog barterLog = new ShopBarterLog();
			barterLog.setBarterId(shopBarter.getBarterId()); // 换货表id
			barterLog.setBarterState(BarterState.SELLER_STATE_AGREE + ""); // 换货状态信息
			barterLog.setChangeState(BarterState.SELLER_STATE_BUYER + ""); // 下一步换货状态信息
			barterLog.setStateInfo("卖家已发货,等待买家收货"); // 换货状态描述
			barterLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			barterLog.setOperator(shopBarter.getStoreName()); // 操作人
			// 保存换货日志
			shopBarterLogDao.saveShopBarterLog(barterLog);
		}
	}

	/**
	 * 买家确认收货
	 *
	 * @param barterId
	 *            记录ID
	 */
	@Override
	public void updateBarterSellerDelivery(String barterId) {
		ShopBarter shopBarter = shopBarterDao.findShopBarterByBarterId(barterId);
		shopBarter.setGoodsState(BarterState.GOODS_STATE_BUYER_RECEIVING); // 物流状态发货已完成
		shopBarter.setBuyerReceiveTime(System.currentTimeMillis());// 买家收货时间
		shopBarterDao.updateShopBarter(shopBarter);

		Order newOrder = new Order();
		newOrder.setOrderId(shopBarter.getOrderId());
		// 订单解锁
		newOrder.setLockState(OrderState.ORDER_LOCK_STATE_NO);
		newOrder.setOrderState(OrderState.ORDER_STATE_FINISH);// liudongjie
																// 换货完成状态改为交易成功
		// 修改订单
		orderService.updateOrder(newOrder);

		ShopBarterLog barterLog = new ShopBarterLog();
		barterLog.setBarterId(barterId); // 换货表id
		barterLog.setBarterState(BarterState.SELLER_STATE_BUYER + ""); // 换货状态信息
		barterLog.setChangeState(BarterState.BARTER_FINSH_STATE + ""); // 下一步换货状态信息
		barterLog.setStateInfo("买家已收货,换货流程结束!"); // 换货状态描述
		barterLog.setCreateTime(System.currentTimeMillis()); // 创建时间
		barterLog.setOperator(shopBarter.getBuyerName()); // 操作人
		// 保存换货日志
		shopBarterLogDao.saveShopBarterLog(barterLog);
	}
}