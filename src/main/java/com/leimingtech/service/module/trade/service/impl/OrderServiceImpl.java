package com.leimingtech.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.DateUtils;
import com.leimingtech.core.common.MyBeanUtils;
import com.leimingtech.core.common.NumberUtils;
import com.leimingtech.core.entity.GoodsClass;
import com.leimingtech.core.entity.GoodsGeneralCount;
import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.OrderCount;
import com.leimingtech.core.entity.OrderExcel;
import com.leimingtech.core.entity.OrderSettlement;
import com.leimingtech.core.entity.OrderStaticExcel;
import com.leimingtech.core.entity.apibean.OrderApiBean;
import com.leimingtech.core.entity.apibean.OrderMemberApiBean;
import com.leimingtech.core.entity.base.Address;
import com.leimingtech.core.entity.base.Cart;
import com.leimingtech.core.entity.base.Coupon;
import com.leimingtech.core.entity.base.CouponMember;
import com.leimingtech.core.entity.base.Daddress;
import com.leimingtech.core.entity.base.Express;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.Invoice;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.OrderAddress;
import com.leimingtech.core.entity.base.OrderDaddress;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.base.OrderInvoice;
import com.leimingtech.core.entity.base.OrderLog;
import com.leimingtech.core.entity.base.OrderPay;
import com.leimingtech.core.entity.base.Payment;
import com.leimingtech.core.entity.base.PredepositLog;
import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.base.ReturnLog;
import com.leimingtech.core.entity.base.ShopBarter;
import com.leimingtech.core.entity.base.ShopBarterLog;
import com.leimingtech.core.entity.base.ShopPointsLog;
import com.leimingtech.core.entity.vo.CartOrderVo;
import com.leimingtech.core.entity.vo.CartVo;
import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.core.entity.vo.OrdermVo;
import com.leimingtech.service.module.calculate.service.CalculateService;
import com.leimingtech.service.module.cart.dao.CartDao;
import com.leimingtech.service.module.cart.service.AddressService;
import com.leimingtech.service.module.cart.service.CartService;
import com.leimingtech.service.module.cart.service.OrderPayService;
import com.leimingtech.service.module.coupon.service.CouponMemberService;
import com.leimingtech.service.module.coupon.service.CouponService;
import com.leimingtech.service.module.goods.service.GoodsClassService;
import com.leimingtech.service.module.goods.service.GoodsService;
import com.leimingtech.service.module.goods.service.GoodsSpecService;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.member.common.PointsLogType;
import com.leimingtech.service.module.member.service.MemberService;
import com.leimingtech.service.module.member.service.ShopPointsLogService;
import com.leimingtech.service.module.product.service.ProductService;
import com.leimingtech.service.module.promote.service.PromoteService;
import com.leimingtech.service.module.promotion.service.PromotionClassService;
import com.leimingtech.service.module.push.ServiceMessagePush;
import com.leimingtech.service.module.search.dao.GoodsSearchDao;
import com.leimingtech.service.module.setting.service.ExpressService;
import com.leimingtech.service.module.setting.service.PaymentService;
import com.leimingtech.service.module.setting.service.SettingService;
import com.leimingtech.service.module.strategy.common.StrategyTypes;
import com.leimingtech.service.module.strategy.vo.StrategyCondition;
import com.leimingtech.service.module.trade.common.BarterState;
import com.leimingtech.service.module.trade.common.OrderState;
import com.leimingtech.service.module.trade.common.RefundReturnState;
import com.leimingtech.service.module.trade.dao.OrderDao;
import com.leimingtech.service.module.trade.dao.OrderGoodsDao;
import com.leimingtech.service.module.trade.dao.OrderLogDao;
import com.leimingtech.service.module.trade.dao.RefundReturnDao;
import com.leimingtech.service.module.trade.dao.ReturnLogDao;
import com.leimingtech.service.module.trade.dao.ShopBarterDao;
import com.leimingtech.service.module.trade.dao.ShopBarterLogDao;
import com.leimingtech.service.module.trade.service.DaddressService;
import com.leimingtech.service.module.trade.service.InvoiceService;
import com.leimingtech.service.module.trade.service.OrderAddressService;
import com.leimingtech.service.module.trade.service.OrderDaddressService;
import com.leimingtech.service.module.trade.service.OrderInvoiceService;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.trade.service.PredepositLogService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 订单
 *
 * @author liukai
 */

@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private OrderGoodsDao orderGoodsDao;

	@Resource
	private OrderLogDao orderLogDao;

	@Resource
	private CartDao cartDao;

	@Resource
	private ReturnLogDao returnLogDao;

	@Resource
	private GoodsSpecService goodsSpecService;

	@Resource
	private OrderPayService orderPayService;

	@Resource
	private ProductService productService;

	@Resource
	private PaymentService paymentService;

	@Resource
	private AddressService addressService;

	@Resource
	private CouponService couponService;

	@Resource
	private CalculateService calculateService;

	@Resource
	private CartService cartService;

	@Resource
	private MemberService memberService;

	@Resource
	private ShopPointsLogService shopPointsLogService;

	@Resource
	private CouponMemberService couponMemberService;

	@Resource
	private InvoiceService invoiceService;

	@Resource
	private OrderInvoiceService orderInvoiceService;

	@Resource
	private ExpressService expressService;

	@Resource
	private PredepositLogService predepositLogService;

	@Resource
	private GoodsService goodsService;

	@Resource
	private SettingService settingService;

	@Resource
	private OrderAddressService orderAddressService;

	@Resource
	private DaddressService daddressService;

	@Resource
	private OrderDaddressService orderDaddressService;

	@Resource
	private PromotionClassService promotionClassService;

	@Resource
	private RefundReturnDao refundReturnDao;

	@Resource
	private GoodsClassService goodsClassService;

	@Resource
	private PromoteService promoteService;

	@Resource
	private ShopBarterDao shopBarterDao;

	@Resource
	private ShopBarterLogDao shopBarterLogDao;

	@Resource
	private LucenceService lucenceService;

	@Autowired
	private GoodsSearchDao goodsSearchDao;

	/**
	 * 删除订单
	 *
	 * @param orderId
	 *            订单id
	 */
	public void deleteOrder(String orderId) {
		orderDao.deleteOrder(orderId);
		// 删除订单的订单项
		orderGoodsDao.deleteByOrderId(orderId);
	}

	/**
	 * 修改订单
	 *
	 * @param order
	 */
	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}

	/**
	 * 修改订单状态
	 *
	 * @param order
	 *            订单实体,需orderaId,orderState字段,如果需要修改付款状态,需给paymentState字段赋值,
	 *            同时可以传入finnshedTime和shippingTime来更改订单完成和配送时间
	 * @param orderLog
	 *            订单日志,可传可不传,传可保存订单日志,不传不保存
	 */
	@Override
	public void updateOrderState(Order order, OrderLog orderLog) {
		orderDao.updateOrderState(order);
		// 如果有订单日志,保存订单日志表
		if (orderLog != null) {
			orderLog.setCreateTime(System.currentTimeMillis());
			orderLogDao.saveOrderLog(orderLog);
		}
	}

	/**
	 * 分页查询订单总条数
	 *
	 * @param order
	 *            可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	@Override
	public int findOrderCount(Order order) {
		return orderDao.findOrderCount(order);
	}

	/**
	 * 分页查询订单
	 *
	 * @param pager
	 *            可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	@Override
	public Pager findOrderList(Pager pager) {
		List<Order> list = orderDao.findOrderList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 分页查询结算所需订单
	 *
	 * @param pager
	 *            可加查询条件:订单状态,开始结束时间(starttime,endtime)endtime>订单完成时间>=starttime
	 * @return
	 */
	public Pager findBillOrderList(Pager pager) {
		List<Order> list = orderDao.findBillOrderList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 根据id查询订单,有订单项,订单日志
	 *
	 * @param orderId
	 * @return
	 */
	@Override
	public Order findById(String orderId) {
		return orderDao.findById(orderId);
	}

	/**
	 * 订单详情,必传订单id,可传用户id和店铺id,不需要传null
	 *
	 * @param orderId
	 *            订单id
	 * @param buyerId
	 *            用户id
	 * @param storeId
	 *            店铺id
	 * @return
	 */
	public Order findOrderDetail(String orderId, String buyerId, String storeId) {
		Order order = new Order();
		order.setOrderId(orderId);
		if (StringUtils.isNotBlank(buyerId)) {
			order.setBuyerId(buyerId);
		}
		if (StringUtils.isNotBlank(storeId)) {
			order.setStoreId(storeId);
		}
		return orderDao.findOrderDetail(order);
	}

	/**
	 * 根据订单编号查询订单信息
	 *
	 * @param orderSn
	 * @return
	 */
	@Override
	public Order findByOrderSn(String orderSn) {
		return orderDao.findByOrderSn(orderSn);
	}

	/**
	 * 提交订单
	 *
	 * @param cartIds
	 *            多个购物车id
	 * @param memberId
	 *            用户id
	 * @param map
	 *            存储买家留言信息,键为店铺id,值为店铺留言
	 * @param addressId
	 *            收货地址id
	 * @param paytype
	 *            支付方式 1:在线支付,2:货到付款
	 * @param freight
	 *            运费信息
	 * @param couponId
	 *            优惠券id
	 * @param invoiceId
	 *            发票id
	 * @param isPd
	 *            是否余额支付 1为是
	 * @return 返回OrderPay 订单支付表
	 */
	@Override
	public OrderPay addOrderReturnPaySn(String cartIds, String memberId, Map<String, String> map, String addressId,
			String paytype, String freight, String couponId, String invoiceId, Integer isPd) {
		try {
			// 通过用户id查询用户信息
			Member member = memberService.findById(memberId);
			// 创建一个新的订单支付编号dd
			String paySn = "P" + DateUtils.getDateStr("yyyyMMddHHmmssSSS");
			OrderPay orderPay = new OrderPay();
			orderPay.setPaySn(paySn);
			orderPay.setBuyerId(member.getMemberId());
			orderPay.setApiPayState("0");
			// 保存订单支付表
			orderPayService.saveOrderPay(orderPay);

			/**
			 * 订单发票信息
			 */
			// 新建一个订单发票
			OrderInvoice orderInvoice = new OrderInvoice();
			// 新建一个订单表
			Invoice invoice = null;
			// 判断买家是否选择填写发票信息
			if (StringUtils.isNotBlank(invoiceId)) { // 存在,查表
				invoice = invoiceService.findByInvId(invoiceId);
			}
			// 判断发票信息是否存在
			if (invoice != null) {
				MyBeanUtils.copyBeanNotNull2Bean(invoice, orderInvoice);
			} else { // 不存在新增信息
				orderInvoice.setInvTitle("个人"); // 订单发票台头
				orderInvoice.setMemberId(member.getMemberId()); // 订单发票用户id
				orderInvoice.setInvContent("2"); // 订单发票内容
				orderInvoice.setInvState("1"); // 订单发票类型 1:普通发票信息
			}
			// 新建一个发票信息字符串
			String invStr = "";
			// 判断发票类型
			if (orderInvoice.getInvState().equals("1")) { // 普通发票
				invStr += "普通发票&nbsp;&nbsp;" + orderInvoice.getInvTitle() + "&nbsp;&nbsp;";
			} else if (orderInvoice.getInvState().equals("2")) { // 增值税发票
				invStr += "增值税发票&nbsp;&nbsp;" + orderInvoice.getInvCompany() + "&nbsp;&nbsp;";
			}
			// 判断发票内容
			if (invoiceId.equals("")) {
				invStr = "不开发票";
			} else if (orderInvoice.getInvContent().equals("1")) { // 不开发票
				invStr += "不开发票";
			} else if (orderInvoice.getInvContent().equals("2")) { // 明细
				invStr += "明细";
			}

			/**
			 * 订单优惠券gt
			 */
			// 新建一个新的优惠券
			Coupon coupon = null;
			// 判断是否使用优惠券
			if (StringUtils.isNotBlank(couponId) && !couponId.equals("0")) { // 使用优惠券,查表
				coupon = couponService.getCouponById(couponId);
			}

			/**
			 * 保存订单收货地址
			 */
			// 查询收货地址信息
			Address address = addressService.queryById(addressId);
			OrderAddress orderAddress = new OrderAddress();
			MyBeanUtils.copyBeanNotNull2Bean(address, orderAddress);
			orderAddressService.saveOrderAddress(orderAddress);

			// 计算有订单的相关金额
			List<OrderVo> orderVoList = this.getAmount(cartIds, coupon, orderAddress, freight, isPd, member);

			/**
			 * 订单信息
			 */
			for (OrderVo orderVo : orderVoList) {
				Order order = new Order();
				order.setOrderSn(DateUtils.getDateStr("yyyyMMddHHmmssSSS")); // 订单编号
				order.setBuyerId(member.getMemberId()); // 购买用户id
				order.setStoreId(orderVo.getStoreId()); // 店铺id
				order.setStoreName(orderVo.getStoreName()); // 店铺名称
				order.setBuyerEmail(member.getMemberEmail()); // 购买用户邮箱
				order.setBuyerName(member.getMemberName()); // 购买用户名称
				order.setDaddressId("0"); // 发货地址id,暂时写死
				order.setAddressId(orderAddress.getAddressId()); // 收货地址id
				order.setGoodsAmount(orderVo.getGoodsAmount()); // 商品总价格
				order.setShippingFee(orderVo.getShippingFee()); // 运费金额
				order.setCouponId(orderVo.getCouponId()); // 优惠券id
				order.setCouponPrice(orderVo.getCouponPrice()); // 优惠券金额
				order.setPromoPrice(orderVo.getPromoPrice()); // 促销优惠金额
				order.setDiscount(orderVo.getDiscount()); // 优惠总金额
				order.setPredepositAmount(orderVo.getPredepositAmount()); // 余额支付金额
				order.setOrderAmount(orderVo.getOrderAmount()); // 订单应付金额
				order.setOrderTotalPrice(orderVo.getGoodsAmount().add(orderVo.getShippingFee())); // 订单总金额=商品总金额+运费
				order.setOrderMessage(map.get(orderVo.getStoreId())); // 订单留言
				order.setOrderType(OrderState.ORDER_TYPE_ORDINARY); // 订单类型 0.普通
																	// 1.团购
				order.setPaymentCode(paytype); // 支付方式名称代码
				order.setPaymentDirect("1"); // 支付类型:1是即时到帐,2是担保交易
				order.setPaymentId(paytype); // 支付方式id
				order.setPayId(orderPay.getPayId()); // 支付表id
				order.setPaySn(paySn); // 支付表编号
				order.setLockState(0); // 订单锁定状态:正常
				if ("1".equals(paytype)) {
					order.setPaymentName("在线支付"); // 支付方式名称
					// 判断有没有剩余支付金额
					if (orderVo.getOrderAmount().doubleValue() == 0) { // 若支付完成
						order.setOrderState(OrderState.ORDER_STATE_UNFILLED); // 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
						order.setPaymentTime(System.currentTimeMillis());
					} else { // 未支付完成
						order.setOrderState(OrderState.ORDER_STATE_NO_PATMENT); // 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}

				} else if ("2".equals(paytype)) {
					order.setPaymentName("货到付款"); // 支付方式名称
					// 判断有没有剩余支付金额
					if (orderVo.getOrderAmount().doubleValue() == 0) { // 若支付完成
						order.setOrderState(OrderState.ORDER_STATE_UNFILLED); // 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					} else { // 未支付完成
						order.setOrderState(OrderState.ORDER_STATE_SUBMIT); // 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
					}
				}
				// 判断有没有剩余支付金额
				if (orderVo.getOrderAmount().doubleValue() == 0) { // 若支付完成
					order.setPaymentState(OrderState.PAYMENT_STATE_YES); // 付款状态
				} else { // 未支付完成
					order.setPaymentState(OrderState.PAYMENT_STATE_NO); // 付款状态
				}

				order.setOutSn(""); // 订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
				order.setInvoice(invStr); // 订单发票信息
				order.setCreateTime(System.currentTimeMillis()); // 订单生成时间
				order.setBarterState(OrderState.BARTER_STATE_NO);// 无退货
				order.setBarterNum(0);// 换货数量初始化为0
				// 保存订单表
				orderDao.saveOrder(order);

				for (CartOrderVo cartOrderVo : orderVo.getCartOrderVoList()) {
					OrderGoods orderGoods = new OrderGoods();
					// 查询商品规格表,实时查找信息
					GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(cartOrderVo.getSpecId());
					// 查询商品表
					Goods goods = goodsService.findGoodById(cartOrderVo.getGoodsId());
					// 通过查询出的商品分类查询商品所在分类信息
					GoodsClass goodsClass = goodsClassService.findById(goods.getGcId());
					orderGoods.setGoodsId(cartOrderVo.getGoodsId());
					orderGoods.setGoodsImage(cartOrderVo.getGoodsImages());
					orderGoods.setGoodsName(cartOrderVo.getGoodsName());
					orderGoods.setGoodsNum(cartOrderVo.getGoodsNum().intValue());
					orderGoods.setGoodsPrice(goodsSpec.getSpecGoodsPrice());
					orderGoods.setOrderId(order.getOrderId());
					orderGoods.setSpecId(cartOrderVo.getSpecId());
					orderGoods.setSpecInfo(cartOrderVo.getSpecInfo());
					orderGoods.setStoreId(cartOrderVo.getStoreId());
					orderGoods.setBuyerId(order.getBuyerId());
					orderGoods.setGoodsPayPrice(cartOrderVo.getGoodsPayPrice());
					orderGoods.setGoodsPreAmount(cartOrderVo.getGoodsPreAmount());
					orderGoods.setGoodsReturnNum(0);
					orderGoods.setGoodsBarterNum(0);
					orderGoods.setGcId(goodsClass.getGcId());
					if (goodsClass.getExpenScale() != null) {
						orderGoods.setCommisRate(goodsClass.getExpenScale());
					} else {
						orderGoods.setCommisRate(0f);
					}
					// 减去商品库存
					goodsSpec.setSpecSalenum(cartOrderVo.getGoodsNum().intValue());
					productService.updateStorage(goodsSpec);
					orderGoodsDao.saveOrderGoods(orderGoods);
					cartDao.deleteCart(cartOrderVo.getCartId()); // 删除购物车数据
					// 更新商品索引
					// TODO 更新与2016-11-07 17:11 author ning.peng
					Goods goodsCondition = new Goods();
					goodsCondition.setGoodsId(cartOrderVo.getGoodsId());
					goods = goodsSearchDao.findOneGoodByCondition(goodsCondition);
					goods.setSalenum(goods.getSalenum() + goodsSpec.getSpecSalenum());
					lucenceService.creatOneIndex(goods);
				}

				OrderLog orderLog = new OrderLog();
				orderLog.setOperator(member.getMemberName());
				orderLog.setChangeState(OrderState.ORDER_STATE_UNFILLED + "");
				orderLog.setOrderId(order.getOrderId());
				orderLog.setOrderState(OrderState.ORDER_STATE_NO_PATMENT + "");
				orderLog.setStateInfo("提交订单");
				orderLog.setCreateTime(System.currentTimeMillis());
				// 保存订单日志
				orderLogDao.saveOrderLog(orderLog);

				orderInvoice.setOrderId(order.getOrderId());
				// 保存订单发票表
				orderInvoiceService.saveOrderInvoice(orderInvoice);
			}

			/**
			 * 修改优惠券使用情况
			 */
			// 判断是否使用优惠券
			if (coupon != null) {
				int couponusage = coupon.getCouponusage(); // 使用数量
				coupon.setCouponusage(couponusage + 1); // 使用数量加1
				// 修改优惠券表
				couponService.updateCoupon(coupon);

				CouponMember couponMember = new CouponMember();
				couponMember.setCouponId(couponId);
				couponMember.setCouponMemberId(member.getMemberId());
				couponMember.setCouponIsUser(1); // 优惠券已使用
				// 修改中间表
				couponMemberService.updateCouponMember(couponMember);
			}

			return orderPay;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 计算订单总价格,数据库中数据
	 *
	 * @param cartVo
	 * @return
	 */
	@Override
	public BigDecimal findAmount(CartVo cartVo) {
		double goodsAmount = 0.00;
		for (Cart cart : cartVo.getList()) {
			GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(cart.getSpecId());
			goodsAmount += goodsSpec.getSpecGoodsPrice().doubleValue() * cart.getGoodsNum();
		}
		return BigDecimal.valueOf(goodsAmount);
	}

	/**
	 * 根据订单支付编号查询订单
	 *
	 * @param paySn
	 * @return
	 */
	@Override
	public List<Order> findByPaySn(String paySn) {
		return orderDao.findByPaySn(paySn);
	}

	/**
	 * 根据订单支付id查询订单
	 *
	 * @param payId
	 * @return
	 */
	@Override
	public List<Order> findByPayId(String payId) {
		return orderDao.findByPayId(payId);
	}

	/**
	 * 根据支付单号查询订单总价,支付单号可传订单号和支付单号
	 *
	 * @param paySn
	 *            可传订单号和支付单号
	 * @return 返回PaySn只有paySn, orderList, payAmount三个字段有值
	 */
	public OrderPay findOrderPayBySn(String paySn) {
		OrderPay orderPay = new OrderPay();
		if ("P".equals(paySn.substring(0, 1))) { // 判断编号类型,支付单号
			// 通过支付单号查询订单集合
			List<Order> orderList = orderDao.findByPaySn(paySn);
			// 支付总额
			double amount = 0.00;
			double orderTotalAmount = 0.00;
			for (Order order : orderList) {
				amount += order.getOrderAmount().doubleValue();
				orderTotalAmount += order.getOrderTotalPrice().doubleValue();
			}
			// 将支付单号存入paySn
			orderPay.setPaySn(paySn);
			orderPay.setPayAmount(BigDecimal.valueOf(amount));
			orderPay.setOrderTotalPrice(BigDecimal.valueOf(orderTotalAmount));
		} else { // 订单编号
			List<Order> orderList = new ArrayList<Order>();
			// 通过订单编号查询单个订单
			Order order = orderDao.findByOrderSn(paySn);
			orderList.add(order);
			// 将订单号存入paySn
			orderPay.setPaySn(paySn);
			orderPay.setPayAmount(order.getOrderAmount());
			// 单个订单的时候存入订单状态
			orderPay.setOrderState(order.getOrderState());
			orderPay.setOrderTotalPrice(order.getOrderTotalPrice());
		}
		return orderPay;
	}

	/**
	 * 支付完成后,修改订单状态
	 *
	 * @param paySn
	 * @param tradeSn
	 *            支付流水号
	 * @param paymentBranch
	 *            支付分支
	 */
	public void updateOrderStatePayFinish(String paySn, String tradeSn, String paymentBranch) {
		// 新建一个订单总的应付金额
		double orderAmount = 0.0;
		// 新建一个余额支付金额
		double predepositAmount = 0.0;
		// 新建一个订单应加积分,应加积分=订单总金额-订单优惠金额
		double orderPoints = 0.0;

		String memberId = null;

		// 订单id
		List<Map<String, String>> orderIdList = new ArrayList<Map<String, String>>();

		if ("P".equals(paySn.substring(0, 1))) { // 判断编号类型,支付单号
			List<Order> orderList = orderDao.findByPaySn(paySn);
			for (Order order : orderList) {
				if (order != null) {
					memberId = order.getBuyerId();

					if (order.getPaymentState() == 0) {
						// 新建一个订单日志
						OrderLog orderLog = new OrderLog();
						orderLog.setOrderState(OrderState.ORDER_STATE_UNFILLED + "");
						orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
						orderLog.setStateInfo("订单付款完成");
						orderLog.setOrderId(order.getOrderId());
						orderLog.setOperator(order.getBuyerName());
						orderLog.setCreateTime(System.currentTimeMillis());
						// 保存订单日志
						orderLogDao.saveOrderLog(orderLog);
						// 修改订单状态
						Order newOrder = new Order();
						newOrder.setOrderId(order.getOrderId());
						newOrder.setOrderState(OrderState.ORDER_STATE_UNFILLED);
						newOrder.setPaymentState(OrderState.PAYMENT_STATE_YES);
						newOrder.setPaymentTime(System.currentTimeMillis());
						newOrder.setTradeSn(tradeSn);
						newOrder.setPaymentBranch(paymentBranch);
						orderDao.updateOrder(newOrder);
						// 累加订单支付金额和订单余额支付金额
						orderAmount += order.getOrderAmount().doubleValue();
						predepositAmount += order.getPredepositAmount().doubleValue();
						// 应加积分=订单总金额-订单优惠金额
						orderPoints += order.getOrderAmount().doubleValue();
					} else {
						// 新建一个订单日志
						OrderLog orderLog = new OrderLog();
						orderLog.setOrderState(OrderState.ORDER_STATE_UNFILLED + "");
						orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
						orderLog.setStateInfo("订单付款完成");
						orderLog.setOrderId(order.getOrderId());
						orderLog.setOperator(order.getBuyerName());
						orderLog.setCreateTime(System.currentTimeMillis());
						// 保存订单日志
						orderLogDao.saveOrderLog(orderLog);
						// 修改订单状态
						Order newOrder = new Order();
						newOrder.setOrderId(order.getOrderId());
						newOrder.setOrderState(OrderState.ORDER_STATE_UNFILLED);
						newOrder.setPaymentState(OrderState.PAYMENT_STATE_YES);
						newOrder.setPaymentTime(System.currentTimeMillis());
						newOrder.setTradeSn(tradeSn);
						newOrder.setPaymentBranch(paymentBranch);
						orderDao.updateOrder(newOrder);
						// 累加订单支付金额和订单余额支付金额
						orderAmount += order.getOrderAmount().doubleValue();
						predepositAmount += order.getPredepositAmount().doubleValue();
						// 应加积分=订单总金额-订单优惠金额
						orderPoints += order.getOrderAmount().doubleValue();
					}

					Map<String, String> orderMap = new HashMap<String, String>();
					orderMap.put("orderid", order.getOrderId());

					Map<String, String> orderPointsMap = new HashMap<String, String>();
					orderPointsMap.put("orderPoints", String.valueOf(orderPoints));
					orderPoints = 0.0;

					orderIdList.add(orderMap);
					orderIdList.add(orderPointsMap);
				}
			}
		} else {
			// 新建一个订单日志
			Order order = orderDao.findByOrderSn(paySn);
			if (order != null) {
				memberId = order.getBuyerId();

				if (order.getPaymentState() == 0) {
					OrderLog orderLog = new OrderLog();
					orderLog.setOrderState(OrderState.ORDER_STATE_UNFILLED + "");
					orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
					orderLog.setStateInfo("订单付款完成");
					orderLog.setOrderId(order.getOrderId());
					orderLog.setOperator(order.getBuyerName());
					orderLog.setCreateTime(System.currentTimeMillis());
					// 保存订单日志
					orderLogDao.saveOrderLog(orderLog);
					// 修改订单状态
					Order newOrder = new Order();
					newOrder.setOrderId(order.getOrderId());
					newOrder.setOrderState(OrderState.ORDER_STATE_UNFILLED);
					newOrder.setPaymentState(OrderState.PAYMENT_STATE_YES);
					newOrder.setPaymentTime(System.currentTimeMillis());
					newOrder.setTradeSn(tradeSn);
					newOrder.setPaymentBranch(paymentBranch);
					orderDao.updateOrder(newOrder);
					// 累加订单支付金额和订单余额支付金额
					orderAmount += order.getOrderAmount().doubleValue();
					predepositAmount += order.getPredepositAmount().doubleValue();
					// 应加积分=订单总金额-订单优惠金额
					orderPoints += order.getOrderAmount().doubleValue();
				}

				Map<String, String> orderMap = new HashMap<String, String>();
				orderMap.put("orderid", order.getOrderId());

				Map<String, String> orderPointsMap = new HashMap<String, String>();
				orderPointsMap.put("orderPoints", String.valueOf(orderPoints));
				orderPoints = 0.0;

				orderIdList.add(orderMap);
				orderIdList.add(orderPointsMap);
			}
		}
		Member member = memberService.findById(memberId);

		// 判断应付金额不为0和余额支付金额不为0(此时支付的余额为冻结状态)
		if (orderAmount != 0 && predepositAmount != 0 && memberId != null) {
			// 在冻结金额中减去支付完的余额
			double freeze = member.getFreezePredeposit().doubleValue() - predepositAmount;
			if (freeze > 0) {
				member.setFreezePredeposit(BigDecimal.valueOf(freeze));
			} else {
				member.setFreezePredeposit(BigDecimal.valueOf(0));
			}
			memberService.updateMember(member);

			// 创建一个新的变更日志实体
			PredepositLog predepositLog = new PredepositLog();
			predepositLog.setLgMemberId(member.getMemberId()); // 会员编号
			predepositLog.setLgMemberName(member.getMemberName()); // 会员名称
			predepositLog.setLgType("order_comb_pay"); // 操作类型:下单支付被冻结的预存款
			predepositLog.setLgAvAmount(BigDecimal.valueOf(0)); // 可用金额变更0表示未变更
			predepositLog.setLgFreezeAmount(BigDecimal.valueOf(freeze)); // 冻结金额变更0表示未变更
			predepositLog.setLgRdeAmount(BigDecimal.valueOf(predepositAmount)); // 支出余额等于0
			predepositLog.setLgDesc("订单支付冻结余额"); // 描述
			predepositLog.setCreateTime(System.currentTimeMillis()); // 添加时间
			// 保存预存款变更日志表
			predepositLogService.savePdl(predepositLog);
		}

		for (Map<String, String> itemMap : orderIdList) {
			String orderid = itemMap.get("orderid");
			orderPoints = Double.valueOf(itemMap.get("orderPoints")).doubleValue();

			/**
			 * 订单积分修改,订单积分日志保存
			 */
			Integer points = Integer.parseInt(new java.text.DecimalFormat("0").format(orderPoints));
			Integer rankPoint = member.getMemberRankPoints();
			Integer consPoint = member.getMemberConsumePoints();
			if (rankPoint == null)
				rankPoint = 0;
			if (consPoint == null)
				consPoint = 0;

			// 获取积分设置购买商品(一元等于多少积分)等级积分
			String rankSettingPoints = settingService.findByNameAndCode("points", "buygoods_rank");

			// 获取积分设置购买商品(一元等于多少积分)消费积分
			String consSettingPoints = settingService.findByNameAndCode("points", "buygoods_cons");
			if (StringUtils.isNotBlank(rankSettingPoints)) {
				rankPoint += Integer.valueOf(rankSettingPoints) * points;
			} else { // 若没设置购买商品等级积分,则按(一元等于一积分计算)
				rankPoint += points;
			}
			if (StringUtils.isNotBlank(consSettingPoints)) {
				consPoint += Integer.valueOf(consSettingPoints) * points;
			} else { // 若没设置购买商品消费积分,则按(一元等于一积分计算)
				consPoint += points;
			}

			// 修改用户积分
			/*
			 * member.setMemberRankPoints(rankPoint);
			 * member.setMemberConsumePoints(consPoint);
			 * memberService.updateMember(member);
			 */

			ShopPointsLog shopPointsLog = new ShopPointsLog();
			shopPointsLog.setMemberid(member.getMemberId());
			shopPointsLog.setMembername(member.getMemberName());
			shopPointsLog.setAdminid("1");
			shopPointsLog.setAdminname("admin");
			shopPointsLog.setPoints(consPoint);
			shopPointsLog.setCreateTime(System.currentTimeMillis());
			shopPointsLog.setType(PointsLogType.POINTS_TYPE_ORDERPAY); // 积分操作类型
			shopPointsLog.setDesc("付款完成");
			shopPointsLog.setStage("商品付款成功,增加会员积分");
			shopPointsLog.setObjectId(orderid);
			// 保存会员积分日志表
			shopPointsLogService.save(shopPointsLog);
		}
	}

	/**
	 * 根据订单状态查询订单数量
	 *
	 * @param order
	 *            可加查询条件:订单编号,店铺名称,订单状态,买家名称,支付名称编号,开始结束时间(starttime,endtime),店铺id,买家id
	 * @return
	 */
	public int findOrderCountByOrder(Order order) {
		return orderDao.findOrderCountByOrder(order);
	}

	/**
	 * 取消订单
	 *
	 * @param orderSn
	 *            订单编号
	 * @param cancelCause
	 *            取消原因
	 * @param opType
	 *            操作人(1:买家;2:卖家,3:系统定时)
	 * @return 1取消订单成功，0取消订单失败 出现异常
	 */
	public int updateCancelOrder(String orderSn, String cancelCause, Integer opType) {
		try {
			// 通过订单编号查询订单
			Order order = orderDao.findByOrderSn(orderSn);
			order.setCancelCause(cancelCause); // 取消原因
			order.setOrderState(OrderState.ORDER_STATE_CANCLE); // 订单状态
			order.setPaymentState(OrderState.PAYMENT_STATE_NO); // 订单支付状态
			order.setCreateTime(System.currentTimeMillis());
			// 修改商品库存和销量
			for (OrderGoods orderGoods : order.getOrderGoodsList()) {
				// 若订单已支付,此时用户取消订单需要申请退款
				if (order.getPaymentState() == OrderState.PAYMENT_STATE_YES) {
					this.addOrderRefund(order.getOrderId(), orderGoods.getGoodsPayPrice().doubleValue(), "取消订单",
							orderGoods.getRecId() + "", "");
				} else {
					// 查询商品规格表,实时查找信息
					GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(orderGoods.getSpecId());
					if (goodsSpec != null) {
						// 增加商品库存和销量,传值为负数
						goodsSpec.setSpecSalenum(-orderGoods.getGoodsNum().intValue());
						productService.updateStorage(goodsSpec);
					}
				}
			}
			// 修改订单状态
			orderDao.updateOrder(order);
			// 修改用户余额(若订单未支付,将用户冻结金额返还用户)
			if (order.getPaymentState() == OrderState.PAYMENT_STATE_NO) {
				Member member = memberService.findMemberById(order.getBuyerId());
				// 判断应付金额不为0和余额支付金额不为0(此时支付的余额为冻结状态)
				if (order.getOrderAmount().doubleValue() != 0 && order.getPredepositAmount().doubleValue() != 0) {
					// 在冻结金额中减去支付完的余额
					double freeze = member.getFreezePredeposit().doubleValue()
							- order.getPredepositAmount().doubleValue();
					// 在可用金额中加上退掉的余额支付金额
					double available = member.getAvailablePredeposit().doubleValue()
							+ order.getPredepositAmount().doubleValue();
					if (freeze > 0) {
						member.setFreezePredeposit(BigDecimal.valueOf(freeze));
					} else {
						member.setFreezePredeposit(BigDecimal.valueOf(0));
					}
					member.setAvailablePredeposit(BigDecimal.valueOf(available));
					memberService.updateMember(member);

					// 创建一个新的变更日志实体
					PredepositLog predepositLog = new PredepositLog();
					predepositLog.setLgMemberId(member.getMemberId()); // 会员编号
					predepositLog.setLgMemberName(member.getMemberName()); // 会员名称
					predepositLog.setLgType("order_comb_pay"); // 操作类型:下单支付被冻结的预存款
					predepositLog.setLgAvAmount(BigDecimal.valueOf(0)); // 可用金额变更0表示未变更
					predepositLog.setLgRdeAmount(BigDecimal.valueOf(order.getPredepositAmount().doubleValue()));
					predepositLog.setLgFreezeAmount(BigDecimal.valueOf(freeze)); // 冻结金额变更0表示未变更
					predepositLog.setLgDesc("订单支付冻结余额"); // 描述
					predepositLog.setCreateTime(System.currentTimeMillis()); // 添加时间
					// 保存预存款变更日志表
					predepositLogService.savePdl(predepositLog);
				}
			}

			// 订单日志
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderState(OrderState.ORDER_STATE_CANCLE + "");
			orderLog.setChangeState("");
			orderLog.setStateInfo("取消订单");
			orderLog.setOrderId(order.getOrderId());
			// 判断操作人
			switch (opType) {
			case 1:
				orderLog.setOperator(order.getBuyerName());
				break;
			case 2:
				orderLog.setOperator(order.getStoreName());
				break;
			case 3:
				orderLog.setOperator("系统定时取消");
				break;
			}

			orderLog.setCreateTime(System.currentTimeMillis());
			// 保存订单日志
			orderLogDao.saveOrderLog(orderLog);

			return 1;// 取消订单成功
		} catch (Exception e) {
			e.printStackTrace();
			return 0;// 取消订单失败 出现异常
		}
	}

	/**
	 * 订单完成
	 *
	 * @param orderSn
	 */
	public void updateFinishOrder(String orderSn) {
		// 通过订单编号查询订单
		Order order = orderDao.findByOrderSn(orderSn);
		order.setOrderState(OrderState.ORDER_STATE_FINISH); // 订单状态
		order.setFinnshedTime(System.currentTimeMillis());
		// 修改订单状态
		orderDao.updateOrderState(order);

		// 若为货到付款订单,增加积分
		if (order.getPaymentCode().equals("2")) {
			Member member = memberService.findById(order.getBuyerId());
			if (member != null) {
				// 应加积分=订单支付金额
				double orderPoints = order.getOrderAmount().doubleValue();

				/**
				 * 订单积分修改,订单积分日志保存
				 */
				Integer points = Integer.parseInt(new java.text.DecimalFormat("0").format(orderPoints));
				Integer rankPoint = member.getMemberRankPoints();
				Integer consPoint = member.getMemberConsumePoints();
				if (rankPoint == null)
					rankPoint = 0;
				if (consPoint == null)
					consPoint = 0;
				// 获取积分设置购买商品(一元等于多少积分)等级积分
				String rankSettingPoints = settingService.findByNameAndCode("points", "buygoods_rank");
				// 获取积分设置购买商品(一元等于多少积分)消费积分
				String consSettingPoints = settingService.findByNameAndCode("points", "buygoods_cons");
				if (StringUtils.isNotBlank(rankSettingPoints)) {
					rankPoint += Integer.valueOf(rankSettingPoints) * points;
				} else { // 若没设置购买商品等级积分,则按(一元等于一积分计算)
					rankPoint += points;
				}
				if (StringUtils.isNotBlank(consSettingPoints)) {
					consPoint += Integer.valueOf(consSettingPoints) * points;
				} else { // 若没设置购买商品消费积分,则按(一元等于一积分计算)
					consPoint += points;
				}

				// 修改用户积分
				member.setMemberRankPoints(rankPoint);
				member.setMemberConsumePoints(consPoint);
				memberService.updateMember(member);

				ShopPointsLog shopPointsLog = new ShopPointsLog();
				shopPointsLog.setMemberid(member.getMemberId());
				shopPointsLog.setMembername(member.getMemberName());
				shopPointsLog.setAdminid("1");
				shopPointsLog.setAdminname("admin");
				shopPointsLog.setPoints(consPoint);
				shopPointsLog.setCreateTime(System.currentTimeMillis());
				shopPointsLog.setType(PointsLogType.POINTS_TYPE_ORDERPAY); // 积分操作类型
				shopPointsLog.setDesc("付款完成");
				shopPointsLog.setStage("商品付款成功,增加会员积分");
				shopPointsLog.setObjectId(order.getOrderId());

				// 保存会员积分日志表
				shopPointsLogService.save(shopPointsLog);
			}
		}

		// 订单日志
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderState(OrderState.ORDER_STATE_FINISH + "");
		orderLog.setChangeState(OrderState.ORDER_STATE_FINISH + "");
		orderLog.setStateInfo("订单已完成");
		orderLog.setOrderId(order.getOrderId());
		orderLog.setOperator(order.getBuyerName());
		orderLog.setCreateTime(System.currentTimeMillis());
		// 保存订单日志
		orderLogDao.saveOrderLog(orderLog);
	}

	/**
	 * 订单确认(货到付款确认)
	 *
	 * @param orderSn
	 */
	public void updateConfirmOrder(String orderSn) {
		// 通过订单编号查询订单
		Order order = orderDao.findByOrderSn(orderSn);
		order.setOrderState(OrderState.ORDER_STATE_CONFIRM); // 订单状态
		// 修改订单状态
		orderDao.updateOrderState(order);
		// 订单日志
		OrderLog orderLog = new OrderLog();
		orderLog.setOrderState(OrderState.ORDER_STATE_CONFIRM + "");
		orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
		orderLog.setStateInfo("订单已确认");
		orderLog.setOrderId(order.getOrderId());
		orderLog.setOperator(order.getStoreName());
		orderLog.setCreateTime(System.currentTimeMillis());
		// 保存订单日志
		orderLogDao.saveOrderLog(orderLog);
	}

	/**
	 * 订单发货
	 *
	 * @param orderSn
	 *            订单编号
	 * @param daddressId
	 *            订单发货地址id
	 * @param shippingExpressId
	 *            配送公司id
	 * @param shippingCode
	 *            物流单号
	 * @param deliverExplain
	 *            发货备注
	 */
	@Override
	public void updateDeliveryOrder(String orderSn, String daddressId, String shippingExpressId, String shippingCode,
			String deliverExplain) {
		try {
			// 通过订单编号查询订单
			Order order = orderDao.findByOrderSn(orderSn);
			order.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING); // 订单状态
			order.setShippingTime(System.currentTimeMillis()); // 配送时间
			if (daddressId != null && !daddressId.equals("0") && !order.getDaddressId().equals(daddressId)) {
				OrderDaddress orderDaddress = new OrderDaddress();
				Daddress daddress = daddressService.findDaddressById(daddressId);
				MyBeanUtils.copyBeanNotNull2Bean(daddress, orderDaddress);
				orderDaddress.setOrderId(order.getOrderId());
				orderDaddressService.saveOrderDaddress(orderDaddress);
				order.setDaddressId(orderDaddress.getAddressId());
			}
			if (shippingExpressId != null && !shippingExpressId.equals("0")) {
				order.setShippingExpressId(shippingExpressId);
				Express express = expressService.findById(shippingExpressId);
				if (express != null) {
					order.setShippingExpressCode(express.getECode());
				} else {
					order.setShippingExpressCode("");
				}
			} else {
				order.setShippingExpressCode("");
			}
			if (StringUtils.isNotBlank(shippingCode)) {
				order.setShippingCode(shippingCode);
			} else {
				order.setShippingCode("");
			}
			if (StringUtils.isNotBlank(deliverExplain)) {
				order.setDeliverExplain(deliverExplain);
			}
			// 修改订单状态
			orderDao.updateOrder(order);
			// 订单日志
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
			orderLog.setChangeState(OrderState.ORDER_STATE_FINISH + "");
			orderLog.setStateInfo("订单已发货");
			orderLog.setOrderId(order.getOrderId());
			orderLog.setOperator(order.getStoreName());
			orderLog.setCreateTime(System.currentTimeMillis());
			// 保存订单日志
			orderLogDao.saveOrderLog(orderLog);

			/**
			 * APP推送发货信息
			 */
			// 订单商品集合
			List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
			// 获取购买商品名称
			String orderGoodsStr = "";
			for (int i = 0; i < orderGoodsList.size(); i++) {
				orderGoodsStr += orderGoodsList.get(i).getGoodsName();
				if (i != 0 && i != orderGoodsList.size() - 1) {
					orderGoodsStr += "、";
				}
			}
			ServiceMessagePush.orderShipPush("您购买的" + orderGoodsStr + "已发货", "订单已发货", "订单发货通知", order.getBuyerId(),
					order.getOrderId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改订单发货信息
	 *
	 * @param orderSn
	 *            订单编号
	 * @param daddressId
	 *            订单发货地址id
	 * @param shippingCode
	 *            物流单号
	 * @param deliverExplain
	 *            发货备注
	 */
	@Override
	public void updateOrderShipments(String orderSn, String daddressId, String shippingCode, String deliverExplain) {
		try {
			// 通过订单编号查询订单
			Order order = orderDao.findByOrderSn(orderSn);
			if (daddressId != null && !daddressId.equals("0") && !order.getDaddressId().equals(daddressId)) {
				OrderDaddress orderDaddress = new OrderDaddress();
				Daddress daddress = daddressService.findDaddressById(daddressId);
				MyBeanUtils.copyBeanNotNull2Bean(daddress, orderDaddress);
				orderDaddress.setOrderId(order.getOrderId());
				orderDaddressService.saveOrderDaddress(orderDaddress);
				order.setDaddressId(orderDaddress.getAddressId());
			}
			if (StringUtils.isNotBlank(shippingCode)) {
				order.setShippingCode(shippingCode);
			} else {
				order.setShippingCode("");
			}
			if (StringUtils.isNotBlank(deliverExplain)) {
				order.setDeliverExplain(deliverExplain);
			}
			// 修改订单
			orderDao.updateOrder(order);
			// 订单日志
			OrderLog orderLog = new OrderLog();
			orderLog.setOrderState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
			orderLog.setChangeState(OrderState.ORDER_STATE_FINISH + "");
			orderLog.setStateInfo("卖家修改订单发货信息");
			orderLog.setOrderId(order.getOrderId());
			orderLog.setOperator(order.getStoreName());
			orderLog.setCreateTime(System.currentTimeMillis());
			// 保存订单日志
			orderLogDao.saveOrderLog(orderLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 订单退款
	 *
	 * @param orderId
	 *            订单id
	 * @param refundAmount
	 *            订单款金额
	 * @param buyerMessage
	 *            退款原因
	 * @param orderGoodsId
	 *            订单项id:售前退款(发货前)传"",售后退款(发货后)传订单项id
	 * @param goodsImageMore
	 *            退款图片信息
	 */
	public void addOrderRefund(String orderId, Double refundAmount, String buyerMessage, String orderGoodsId,
			String goodsImageMore) {
		// 通过订单id查询订单信息
		Order order = orderDao.findById(orderId);
		OrderGoods orderGoods = null;
		// orderGoodsDao.findById(orderGoodsId);
		if (order != null) {
			// 判断是否为售后退款
			if (StringUtils.isNotBlank(orderGoodsId)) {
				orderGoods = orderGoodsDao.findById(orderGoodsId);
			}
			RefundReturn refundReturn = new RefundReturn();
			refundReturn.setRefundSn("8" + DateUtils.getDateStr("yyyyMMddHHmmssSSS")); // 申请编号
			refundReturn.setOrderId(order.getOrderId()); // 订单id
			refundReturn.setOrderSn(order.getOrderSn()); // 订单编号
			refundReturn.setStoreId(order.getStoreId()); // 店铺ID
			refundReturn.setStoreName(order.getStoreName()); // 店铺名称
			refundReturn.setBuyerId(order.getBuyerId()); // 买家ID
			refundReturn.setBuyerName(order.getBuyerName()); // 买家会员名
			refundReturn.setRefundAmount(BigDecimal.valueOf(refundAmount)); // 退款金额
			refundReturn.setOrderGoodsType(order.getOrderType()); // 订单商品类型
			refundReturn.setRefundType(RefundReturnState.TYPE_REFUND); // 申请类型
			refundReturn.setSellerState(RefundReturnState.SELLER_STATE_PENDING_AUDIT); // 卖家处理状态
			refundReturn.setReturnType(RefundReturnState.RETURN_TYPE_NOT_NEED); // 退货类型
			refundReturn.setOrderLock(RefundReturnState.ORDER_LOCK_NEED); // 订单锁定类型
			refundReturn.setPicInfo(goodsImageMore); // 图片
			refundReturn.setBuyerMessage(buyerMessage); // 申请原因
			refundReturn.setCreateTime(System.currentTimeMillis()); // 创建时间
			refundReturn.setRefundState(RefundReturnState.REFUND_STATE_PROCESSING); // 申请状态
			refundReturn.setGoodsState(RefundReturnState.GOODS_STATE_DEFAULT); // 物流状态
			// 判断是否为售后退款
			if (orderGoods != null) { // 是
				refundReturn.setGoodsId(orderGoods.getGoodsId()); // 商品ID
				refundReturn.setOrderGoodsId(orderGoods.getRecId()); // 订单商品ID
				refundReturn.setGoodsName(orderGoods.getGoodsName()); // 商品名称
				refundReturn.setGoodsNum(orderGoods.getGoodsNum()); // 商品数量
				refundReturn.setGoodsImage(orderGoods.getGoodsImage()); // 商品图片
				double predepositAmount = orderGoods.getGoodsPreAmount()
						.multiply(BigDecimal.valueOf(orderGoods.getGoodsNum())).doubleValue();
				refundReturn.setPredepositAmount(BigDecimal.valueOf(predepositAmount)); // 余额支付金额
			} else { // 不是
				String str = "";
				OrderGoods orderGoods1;
				Integer goodsNum = 0;
				for (int i = 0; i < order.getOrderGoodsList().size(); i++) {
					orderGoods1 = order.getOrderGoodsList().get(i);
					str += orderGoods1.getGoodsName();
					goodsNum += orderGoods1.getGoodsNum();
					if (i != order.getOrderGoodsList().size() - 1) {
						str += " + ";
					}
				}
				refundReturn.setGoodsId("0"); // 若为售前退款商品ID存0
				refundReturn.setOrderGoodsId("0"); // 若为售前退款订单商品ID存0
				refundReturn.setGoodsName(str); // 商品名称
				refundReturn.setGoodsNum(goodsNum); // 若为售前退款商品数量存0
				refundReturn.setPredepositAmount(order.getPredepositAmount()); // 余额支付金额
			}
			// 保存退货表
			refundReturnDao.saveRefundReturn(refundReturn);

			/**
			 * 锁定订单
			 */
			Order newOrder = new Order();
			newOrder.setOrderId(orderId);
			newOrder.setLockState(OrderState.ORDER_LOCK_STATE_YES);
			// 修改订单
			orderDao.updateOrder(newOrder);

			/**
			 * 修改订单项退款金额
			 */
			// 判断是否为售后退款
			if (orderGoods == null) {
				// 为售前退款,修改订单中所有订单项退款金额
				List<OrderGoods> orderGoodsList = orderGoodsDao.findByOrderId(orderId);
				for (OrderGoods orderGoods1 : orderGoodsList) {
					orderGoods1.setRefundAmount(doubleToBigDecimal(refundAmount));
					// 修改订单项
					orderGoodsDao.updateOrderGoods(orderGoods1);
				}
			} else {
				// 售后退款,修改当前订单项退款金额
				orderGoods.setRefundAmount(doubleToBigDecimal(refundAmount));
				// 修改订单项
				orderGoodsDao.updateOrderGoods(orderGoods);
			}

			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); // 退款表id
			returnLog.setReturnState(RefundReturnState.REFUND_STATE_PROCESSING + ""); // 退款状态信息
			returnLog.setChangeState(RefundReturnState.REFUND_STATE_PROCESSING + ""); // 下一步退款状态信息
			returnLog.setStateInfo("买家的退款服务已申请成功,等待卖家审核"); // 退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			returnLog.setOperator(refundReturn.getBuyerName()); // 操作人
			// 保存退款日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}

	/**
	 * 订单退货
	 *
	 * @param orderId
	 *            订单id
	 * @param goodsNum
	 *            订单退货数量
	 * @param buyerMessage
	 *            退货原因
	 * @param orderGoodsId
	 *            订单项id
	 * @param goodsImageMore
	 *            退货图片信息
	 */
	public void addOrderReturn(String orderId, Integer goodsNum, String buyerMessage, String orderGoodsId,
			String goodsImageMore) {
		// 通过订单id查询订单信息
		Order order = orderDao.findById(orderId);
		OrderGoods orderGoods = orderGoodsDao.findById(orderGoodsId);
		// 判断订单是否全部申请退货
		if (order.getReturnState() != OrderState.RETURN_STATE_ALL) { // 未全部
			if (orderGoods.getGoodsReturnNum() != null && orderGoods.getGoodsReturnNum() == 0) { // 判断当前需要退货的订单项是否已申请退货
				RefundReturn refundReturn = new RefundReturn();

				refundReturn.setRefundSn("9" + DateUtils.getDateStr("yyyyMMddHHmmssSSS")); // 申请编号
				refundReturn.setOrderId(orderId); // 订单id
				refundReturn.setOrderSn(order.getOrderSn()); // 订单编号
				refundReturn.setStoreId(order.getStoreId()); // 店铺ID
				refundReturn.setStoreName(order.getStoreName()); // 店铺名称
				refundReturn.setBuyerId(order.getBuyerId()); // 买家ID
				refundReturn.setBuyerName(order.getBuyerName()); // 买家会员名
				refundReturn.setGoodsId(orderGoods.getGoodsId()); // 商品ID
				refundReturn.setOrderGoodsId(orderGoodsId); // 订单商品ID
				refundReturn.setGoodsName(orderGoods.getGoodsName()); // 商品名称
				refundReturn.setGoodsNum(goodsNum); // 商品数量
				double predepositAmount = orderGoods.getGoodsPreAmount().multiply(BigDecimal.valueOf(goodsNum))
						.doubleValue();
				refundReturn.setPredepositAmount(BigDecimal.valueOf(predepositAmount)); // 余额支付金额
				double refundAmount = orderGoods.getGoodsPayPrice().multiply(BigDecimal.valueOf(goodsNum))
						.doubleValue();
				refundReturn.setRefundAmount(BigDecimal.valueOf(refundAmount)); // 退款金额
				refundReturn.setGoodsImage(orderGoods.getGoodsImage()); // 商品图片
				refundReturn.setOrderGoodsType(order.getOrderType()); // 订单商品类型
				refundReturn.setRefundType(RefundReturnState.TYPE_RETURN); // 申请类型
				refundReturn.setSellerState(RefundReturnState.SELLER_STATE_PENDING_AUDIT); // 卖家处理状态
				refundReturn.setReturnType(RefundReturnState.RETURN_TYPE_NEED); // 退货类型
				refundReturn.setOrderLock(RefundReturnState.ORDER_LOCK_NEED); // 订单锁定类型
				refundReturn.setPicInfo(goodsImageMore); // 图片
				refundReturn.setBuyerMessage(buyerMessage); // 申请原因
				refundReturn.setCreateTime(System.currentTimeMillis()); // 创建时间
				refundReturn.setRefundState(RefundReturnState.REFUND_STATE_PROCESSING); // 申请状态
				refundReturn.setGoodsState(RefundReturnState.GOODS_STATE_DEFAULT); // 物流状态
				// 保存退货表
				refundReturnDao.saveRefundReturn(refundReturn);

				/**
				 * 锁定订单
				 */
				Order newOrder = new Order();
				newOrder.setOrderId(orderId);
				newOrder.setLockState(OrderState.ORDER_LOCK_STATE_YES);
				// 修改订单
				orderDao.updateOrder(newOrder);

				/**
				 * 修改订单项退款金额
				 */
				orderGoods.setRefundAmount(orderGoods.getGoodsPayPrice());
				// 修改订单项
				orderGoodsDao.updateOrderGoods(orderGoods);

				ReturnLog returnLog = new ReturnLog();
				returnLog.setReturnId(refundReturn.getRefundId()); // 退货表id
				returnLog.setReturnState(RefundReturnState.REFUND_STATE_PROCESSING + ""); // 退货状态信息
				returnLog.setChangeState(RefundReturnState.REFUND_STATE_PROCESSING + ""); // 下一步退货状态信息
				returnLog.setStateInfo("买家的退货服务已申请成功,等待卖家审核"); // 退货状态描述
				returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
				returnLog.setOperator(refundReturn.getBuyerName()); // 操作人
				// 保存退货日志
				returnLogDao.saveReturnLog(returnLog);
			}
		}
	}

	/**
	 * 订单评价完成
	 *
	 * @param orderSn
	 */
	public void updateEvaluationOrder(String orderSn) {
		// 通过订单编号查询订单
		Order order = orderDao.findByOrderSn(orderSn);
		order.setEvaluationStatus(OrderState.ORDER_EVALUATION_YES); // 订单评价状态
		order.setEvaluationTime(System.currentTimeMillis()); // 订单评价时间
		// 修改订单状态
		orderDao.updateOrderState(order);
	}

	/**
	 * 订单结算完成
	 *
	 * @param orderSn
	 */
	public void updateBalanceOrder(String orderSn) {
		// 通过订单编号查询订单
		Order order = orderDao.findByOrderSn(orderSn);
		order.setBalanceState(OrderState.ORDER_BALANCE_YES); // 订单结算状态
		order.setBalanceTime(System.currentTimeMillis()); // 订单结算时间
		// 修改订单状态
		orderDao.updateOrder(order);
	}

	/**
	 * 订单批量结算
	 *
	 * @param ids
	 *            订单id,中间已","分隔
	 */
	public void updateBalanceOrderByIds(String ids) {
		// 将多个订单id分隔成一个id数组
		String[] orderIds = ids.split(",");
		for (String orderId : orderIds) {
			// 通过订单id查询订单
			Order order = orderDao.findById(orderId);
			order.setBalanceState(OrderState.ORDER_BALANCE_YES); // 订单结算状态
			order.setBalanceTime(System.currentTimeMillis()); // 订单结算时间
			// 修改订单状态
			orderDao.updateOrder(order);
		}
	}

	/**
	 * 根据支付单号更改订单支付方式id和支付方式名称代码
	 *
	 * @param paySn
	 *            支付单号
	 * @param paymentId
	 *            支付方式id
	 */
	public void updateOrderPaymentByPaySn(String paySn, String paymentId) {
		try {
			Payment payment = paymentService.findById(paymentId);
			if ("P".equals(paySn.substring(0, 1))) { // 判断编号类型,支付单号
				List<Order> orderList = orderDao.findByPaySn(paySn);
				for (Order order : orderList) {
					order.setPaymentCode(payment.getPaymentCode()); // 支付方式名称代码
					order.setPaymentId(payment.getPaymentId()); // 支付方式id
					order.setPaymentName(payment.getPaymentName()); // 支付方式名称
					orderDao.updateOrder(order);
				}
			} else { // 订单编号
				Order order = orderDao.findByOrderSn(paySn);
				order.setPaymentCode(payment.getPaymentCode()); // 支付方式名称代码
				order.setPaymentId(payment.getPaymentId()); // 支付方式id
				order.setPaymentName(payment.getPaymentName()); // 支付方式名称
				orderDao.updateOrder(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算有订单的相关金额
	 *
	 * @param cartIds
	 *            多个购物车id
	 * @param coupon
	 *            优惠券
	 * @param orderAddress
	 *            订单收货地址
	 * @param freight
	 *            运费信息
	 * @param isPd
	 *            是否使用余额 1为是
	 * @param member
	 *            用户信息
	 * @return
	 */
	public List<OrderVo> getAmount(String cartIds, Coupon coupon, OrderAddress orderAddress, String freight,
			Integer isPd, Member member) {
		try {
			// 新建一个OrderVo集合,存储店铺相关的金额
			List<OrderVo> list = new ArrayList<OrderVo>();

			List<CartVo> cartVoList = cartService.queryVOListByCartIds(cartIds);
			/**
			 * 计算订单运费信息
			 */
			cartService.getFreightForCartVo(cartVoList, freight, orderAddress.getCityId());

			// 新建一个所有订单应付总金额
			double allOrderAmount = 0.0;

			for (CartVo cartVo : cartVoList) {
				// 新建一个orderVo
				OrderVo orderVo = new OrderVo();
				// 将cartVo相同字段复制到OrderVo
				MyBeanUtils.copyBeanNotNull2Bean(cartVo, orderVo);
				// 订单运费
				orderVo.setShippingFee(BigDecimal.valueOf(cartVo.getGoodsTotalFreight()));
				// 计算订单商品总价
				BigDecimal goodsAmount = this.findAmount(cartVo);
				orderVo.setGoodsAmount(goodsAmount);
				// 订单总金额
				double orderTotalPrice = goodsAmount.add(orderVo.getShippingFee()).doubleValue();
				// 新建一个订单应付金额
				double orderAmount;
				// 店铺id
				String storeId = cartVo.getStoreId();

				//// ====================全平台促销时使用 start============//
				//// 计算订单优惠后的金额
				// orderAmount =
				//// this.getPromoAmount(orderVo.getGoodsAmount().doubleValue(),
				//// orderVo.getShippingFee().doubleValue());
				// orderVo.setPromoPrice(BigDecimal.valueOf(orderTotalPrice-orderAmount));
				//// //订单优惠金额
				//// ====================end================//

				/**
				 * 订单优惠相关
				 */
				promoteService.calcuPrice(orderVo, null);
				if (orderVo.getPromoPrice() != null) {
					orderAmount = orderTotalPrice - orderVo.getPromoPrice().doubleValue();
				} else {
					orderAmount = orderTotalPrice;
				}

				/**
				 * 优惠券使用信息
				 */
				// 新建一个优惠券金额
				double couponPrice = 0.0;
				// 判断是否使用优惠券
				if (coupon != null) { // 使用,查询优惠券信息
					// 优惠券的店铺id
					String couponStoreId = coupon.getStoreId();
					if (couponStoreId.equals(storeId)) {
						couponPrice = coupon.getCouponPrice().doubleValue();
						orderVo.setCouponId(coupon.getCouponId()); // 优惠券id
					}
				}
				orderVo.setCouponPrice(BigDecimal.valueOf(couponPrice)); // 优惠券金额
				orderVo.setDiscount(orderVo.getPromoPrice().add(orderVo.getCouponPrice())); // 优惠总金额

				// 判断是否优惠后金额大于优惠券优惠金额,若是优惠后金额小于优惠券优惠金额,则订单优惠金额为0
				if (orderAmount > couponPrice) { // 若是优惠后金额大于优惠券优惠金额,则减去优惠券金额
					orderAmount = orderAmount - couponPrice;
				} else {
					orderAmount = 0.0;
				}
				orderVo.setOrderAmount(BigDecimal.valueOf(orderAmount));
				// 所有订单应付金额
				allOrderAmount += orderAmount;
				// 完善订单项信息
				List<CartOrderVo> cartOrderVoList = this.setCartOrderVoColumn(orderVo, cartVo);
				orderVo.setCartOrderVoList(cartOrderVoList);
				list.add(orderVo);
			}
			// 判断是否使用余额
			if (isPd == 1) {
				// 创建一个新的余额变更日志实体
				PredepositLog predepositLog = new PredepositLog();
				predepositLog.setLgMemberId(member.getMemberId()); // 会员编号
				predepositLog.setLgMemberName(member.getMemberName()); // 会员名称

				// 获取用户余额
				double availablePredeposit = member.getAvailablePredeposit().doubleValue();
				// 冻结用户余额
				// 判断余额是否充足
				if (availablePredeposit >= allOrderAmount) { // 若余额充足
					// 用户余额=原有余额-订单应付总金额
					double available = availablePredeposit - allOrderAmount;
					member.setAvailablePredeposit(BigDecimal.valueOf(available));
					// 修改用户余额
					memberService.updateMember(member);

					// 修改订单的余额支付金额和应付金额
					for (OrderVo orderVo : list) {
						// 将订单总价放入订单余额支付金额
						orderVo.setPredepositAmount(orderVo.getOrderAmount());
						// 将订单应付金额设为0
						orderVo.setOrderAmount(BigDecimal.valueOf(0)); // liudongjie
																		// 显示支付金额为0
																		// 注掉
					}
					/**
					 * 完善余额日志表
					 */
					predepositLog.setLgType("order_pay"); // 操作类型:订单支付
					predepositLog.setLgAvAmount(BigDecimal.valueOf(availablePredeposit - allOrderAmount)); // 可用金额
					predepositLog.setLgRdeAmount(BigDecimal.valueOf(allOrderAmount)); // 支出余额就等于减去的余额
					predepositLog.setLgFreezeAmount(BigDecimal.valueOf(0)); // 冻结金额
				} else { // 余额不足
					// 冻结金额=原有的所有的余额+原有的冻结金额
					double freeze = member.getAvailablePredeposit().doubleValue()
							+ member.getFreezePredeposit().doubleValue();
					// 用户余额=0.0
					member.setAvailablePredeposit(BigDecimal.valueOf(0));
					member.setFreezePredeposit(BigDecimal.valueOf(freeze));
					// 修改用户余额
					memberService.updateMember(member);
					// 新建一个用掉的余额金额
					double oldAvailablePredeposit = 0.0;

					for (int i = 0; i < list.size(); i++) {
						OrderVo orderVo = list.get(i);
						// 判断是否为最后循环
						if (i == list.size() - 1) { // 若为最后一次
							double amount = orderVo.getOrderAmount().doubleValue();
							// 订单余额支付金额,余额不足时,最后循环,将剩余余额-已分配完的余额
							double predepositAmount = availablePredeposit - oldAvailablePredeposit;
							orderVo.setPredepositAmount(BigDecimal.valueOf(predepositAmount)); // 余额支付金额
							orderVo.setOrderAmount(BigDecimal.valueOf(amount - predepositAmount)); // 订单应付金额=原订单应付金额-余额支付金额
							predepositLog.setLgRdeAmount(BigDecimal.valueOf(predepositAmount)); // 支出的余额
						} else {
							double amount = orderVo.getOrderAmount().doubleValue();
							// 订单余额支付金额,余额不足时,按订单总价/所有需支付订单总价*用户所剩余额
							double predepositAmount = NumberUtils.round(
									((orderVo.getOrderAmount().doubleValue() / allOrderAmount) * availablePredeposit),
									2);
							// 记录已支付过的余额
							oldAvailablePredeposit += predepositAmount;
							orderVo.setPredepositAmount(BigDecimal.valueOf(predepositAmount)); // 余额支付金额
							orderVo.setOrderAmount(BigDecimal.valueOf(amount - predepositAmount)); // 订单应付金额=原订单应付金额-余额支付金额
							predepositLog.setLgRdeAmount(BigDecimal.valueOf(predepositAmount)); // 支出的余额
						}
					}
					/**
					 * 完善余额日志表
					 */
					predepositLog.setLgType("order_freeze"); // 操作类型:冻结余额
					predepositLog.setLgAvAmount(BigDecimal.valueOf(0)); // 可用金额

					predepositLog.setLgFreezeAmount(BigDecimal.valueOf(availablePredeposit)); // 冻结金额
				}

				predepositLog.setLgDesc("生成预付款支付信息"); // 描述
				predepositLog.setCreateTime(System.currentTimeMillis()); // 添加时间
				// 保存预存款变更日志表
				predepositLogService.savePdl(predepositLog);
			}

			// 给订单项余额支付金额赋值
			this.setOrderGoodsPreAmount(list);

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取订单优惠后的金额
	 *
	 * @param goodsAmount
	 *            商品总价
	 * @param freight
	 *            运费信息
	 * @return
	 */
	public double getPromoAmount(Double goodsAmount, Double freight) {

		/**
		 * 优惠信息
		 */
		StrategyCondition condition = new StrategyCondition();
		// condition.setPromoteValue(totalGoodsPrice);//优惠的金额 可以是邮费，打折，满减等
		condition.setOrderFreight(freight);
		double realPrice = calculateService.Calculate(goodsAmount, condition);

		return realPrice;
	}

	/**
	 * 按比例计算订单商品优惠金额
	 *
	 * @param orderVo
	 * @return
	 */
	public List<CartOrderVo> setCartOrderVoColumn(OrderVo orderVo, CartVo cartVo) {
		List<CartOrderVo> list = new ArrayList<CartOrderVo>();
		try {
			for (Cart cart : cartVo.getList()) {
				CartOrderVo cartOrderVo = new CartOrderVo();
				// 将cart相同字段复制到cartOrderVo
				MyBeanUtils.copyBeanNotNull2Bean(cart, cartOrderVo);
				// 商品订单项单个商品价格占订单商品总价格的比例
				double proportion = cart.getGoodsPrice() / orderVo.getGoodsAmount().doubleValue();
				/**
				 * 计算商品的应付金额
				 */
				// 新建一个商品不加运费的应付金额=商品应付金额-运费(预设,若包邮,判断为包邮后增加)
				double payPrice = (orderVo.getOrderAmount().subtract(orderVo.getShippingFee())).doubleValue();

				//// ====================全平台促销时使用 start============//
				/*
				 * //获取促销活动信息判断是否有包邮促销 PromotionClassVo promotionVo =
				 * promotionClassService.findVoByUse(); if (promotionVo != null)
				 * { for (Promotion p : promotionVo.getPromotionList()) {
				 * //有包邮促销 if (promotionVo.getPcId().equals(StrategyTypes.
				 * PROMOTIONAL_STRATEGY + "")) { //商品价格符合包邮条件 if
				 * (orderVo.getGoodsAmount().doubleValue() > p.getStartValue())
				 * { //商品包邮加回刚才减去的运费 payPrice +=
				 * orderVo.getShippingFee().doubleValue(); } } } }
				 */
				//// ====================end================//

				// 获取促销活动信息判断是否有免邮促销
				if (orderVo.getPromotType() != null
						&& orderVo.getPromotType().equals(StrategyTypes.PROMOTIONAL_STRATEGY)) {
					// 商品免邮加回刚才减去的运费
					payPrice += orderVo.getShippingFee().doubleValue();
				}

				cartOrderVo.setGoodsPayPrice(NumberUtils.getsetScale(BigDecimal.valueOf(payPrice * proportion), 2));
				list.add(cartOrderVo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 给订单项余额支付金额赋值
	 *
	 * @param list
	 */
	public void setOrderGoodsPreAmount(List<OrderVo> list) {
		for (OrderVo orderVo : list) {
			for (CartOrderVo cartOrderVo : orderVo.getCartOrderVoList()) {
				// 商品订单项单个商品价格占订单商品总价格的比例
				double proportion = cartOrderVo.getGoodsPrice() / orderVo.getGoodsAmount().doubleValue();
				// 余额支付金额占总支付金额的比例
				MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
				BigDecimal prePro = null;
				if ((orderVo.getOrderAmount().add(orderVo.getPredepositAmount()))
						.compareTo(BigDecimal.valueOf(0)) == 0) {
					prePro = orderVo.getPredepositAmount();
				} else {
					prePro = orderVo.getPredepositAmount()
							.divide((orderVo.getOrderAmount().add(orderVo.getPredepositAmount())), mc);
				}
				// BigDecimal prePro =
				// orderVo.getPredepositAmount().divide((orderVo.getOrderAmount().add(orderVo.getPredepositAmount())),mc);
				// 新建一个商品不加运费的余额支付金额=订单余额支付金额-运费*在线支付金额占总支付金额的比例(预设,若包邮,判断为包邮后增加)
				double preAmount = (orderVo.getPredepositAmount().subtract(orderVo.getShippingFee().multiply(prePro)))
						.doubleValue();

				//// ====================全平台促销时使用 start============//
				/*
				 * //获取促销活动信息判断是否有包邮促销 PromotionClassVo promotionVo =
				 * promotionClassService.findVoByUse(); if(promotionVo!=null){
				 * for(Promotion p :promotionVo.getPromotionList()){ //有包邮促销
				 * if(promotionVo.getPcId()==StrategyTypes.PROMOTIONAL_STRATEGY)
				 * { //商品价格符合包邮条件
				 * if(orderVo.getGoodsAmount().doubleValue()>p.getStartValue()){
				 * //商品包邮加回刚才减去的运费 preAmount +=
				 * orderVo.getShippingFee().doubleValue(); } } } }
				 */
				//// ====================end================//

				// 获取促销活动信息判断是否有免邮促销
				if (orderVo.getPromotType() != null
						&& orderVo.getPromotType().equals(StrategyTypes.PROMOTIONAL_STRATEGY)) {
					// 商品免邮加回刚才减去的运费
					preAmount += orderVo.getShippingFee().doubleValue();
				}

				cartOrderVo.setGoodsPreAmount(NumberUtils.getsetScale(BigDecimal.valueOf(preAmount * proportion), 2));
			}
		}
	}

	/**
	 * 修改订单项评价状态
	 *
	 * @param recId
	 *            订单项id
	 * @param order
	 */
	public void updateEvaluationStatus(Order order, String recId) {
		// 新建一个字段,记录订单项评价数量
		int esNum = 0;

		List<OrderGoods> list = order.getOrderGoodsList();
		for (OrderGoods orderGoods : list) {
			// 判断订单项是否为当前要评论的订单项
			if (orderGoods.getRecId().equals(recId)) { // 若订单项为当前评价订单号,修改
				orderGoods.setEvaluationStatus(OrderState.ORDER_EVALUATION_YES); // 订单项评价状态
				orderGoods.setEvaluationTime(System.currentTimeMillis()); // 订单项评价时间
				orderGoodsDao.updateOrderGoods(orderGoods);
			}
			// 判断当前商品是否支付
			if (orderGoods.getEvaluationStatus() == 1) { // 当前订单已评价
				esNum += 1; // 订单项评价数量加1
			}
		}

		/**
		 * 根据订单项评价个数修改订单评价状态
		 */
		if (esNum == list.size()) { // 若订单项全部评价
			Order upOrder = new Order();
			upOrder.setOrderId(order.getOrderId());
			upOrder.setEvaluationStatus(OrderState.ORDER_EVALUATION_YES); // 订单评价状态,已评价
			upOrder.setEvaluationTime(System.currentTimeMillis()); // 订单评价时间
			// 修改订单
			orderDao.updateOrder(upOrder);
		}
	}

	/**
	 * 根据不同条件查询订单状态
	 *
	 * @param map
	 *            可加查询条件:店铺id,订单状态,开始结束时间(starttime,endtime)，时间代号
	 * @return
	 */
	@Override
	public List<OrderCount> countorderbuy(Map<String, Object> map) {
		return orderDao.countorderbuy(map);
	}

	/**
	 * 根据不同条件查询订单状态
	 *
	 * @param map
	 *            可加查询条件:店铺id,订单状态
	 * @return
	 */
	@Override
	public List<OrderStaticExcel> findorderexcel(Map<String, Object> map) {
		return orderDao.findorderexcel(map);
	}

	/**
	 * 分页查询订单
	 *
	 * @param pager
	 *            可加查询条件:店铺id,订单状态
	 * @return
	 */
	@Override
	public Pager findOrderinfo(Pager pager) {
		List<OrdermVo> list = orderDao.findOrderinfo(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 订单换货
	 *
	 * @param orderId
	 *            订单id
	 * @param goodsNum
	 *            订单换货数量
	 * @param buyerMessage
	 *            换货原因
	 * @param orderGoodsId
	 *            订单项id
	 * @param goodsImageMore
	 *            换货图片信息
	 */
	@Override
	public void addOrderBarter(String orderId, Integer goodsNum, String buyerMessage, String orderGoodsId,
			String goodsImageMore) {
		// 通过订单id查询订单信息
		Order order = orderDao.findById(orderId);
		OrderGoods orderGoods = orderGoodsDao.findById(orderGoodsId);
		// 判断订单是否全部申请换货
		if (order.getBarterState() != OrderState.BARTER_STATE_ALL) { // 未全部
			if (orderGoods.getGoodsBarterNum() != null && orderGoods.getGoodsBarterNum() == 0) { // 判断当前需要换货的订单项是否已申请换货
				ShopBarter shopBarter = new ShopBarter();
				shopBarter.setBarterSn("9" + DateUtils.getDateStr("yyyyMMddHHmmssSSS"));// 申请编号
				shopBarter.setOrderId(orderId);// 订单id
				shopBarter.setOrderSn(order.getOrderSn()); // 订单编号
				shopBarter.setStoreId(order.getStoreId()); // 店铺ID
				shopBarter.setStoreName(order.getStoreName()); // 店铺名称
				shopBarter.setBuyerId(order.getBuyerId()); // 买家ID
				shopBarter.setBuyerName(order.getBuyerName()); // 买家会员名
				shopBarter.setGoodsId(orderGoods.getGoodsId()); // 商品ID
				shopBarter.setOrderGoodsId(orderGoodsId); // 订单商品ID
				shopBarter.setGoodsName(orderGoods.getGoodsName()); // 商品名称
				shopBarter.setGoodsNum(goodsNum); // 商品数量
				shopBarter.setGoodsImage(orderGoods.getGoodsImage()); // 商品图片
				shopBarter.setOrderGoodsType(order.getOrderType()); // 订单商品类型
				shopBarter.setSellerState(BarterState.SELLER_STATE_PENDING_AUDIT); // 卖家处理状态
				shopBarter.setOrderLock(BarterState.ORDER_LOCK_NEED); // 订单锁定类型
				shopBarter.setPicInfo(goodsImageMore); // 图片
				shopBarter.setBuyerMessage(buyerMessage); // 申请原因
				shopBarter.setCreateTime(System.currentTimeMillis()); // 创建时间
				shopBarter.setGoodsState(BarterState.GOODS_STATE_DEFAULT); // 物流状态
				// 保存退货表
				shopBarterDao.saveShopBarter(shopBarter);

				/**
				 * 修改订单项换货数量
				 */
				orderGoods.setGoodsBarterNum(shopBarter.getGoodsNum());
				// 修改订单项
				orderGoodsDao.updateOrderGoods(orderGoods);

				/**
				 * 锁定订单
				 */
				Order newOrder = new Order();
				newOrder.setOrderId(orderId);
				newOrder.setLockState(OrderState.ORDER_LOCK_STATE_YES);
				newOrder.setBarterState(80);// 部分换货
				// 修改订单
				orderDao.updateOrder(newOrder);

				ShopBarterLog shopBarterLog = new ShopBarterLog();
				shopBarterLog.setBarterId(shopBarter.getBarterId());
				shopBarterLog.setBarterState(BarterState.BUYER_BARTER_STATE + "");// 换货状态信息
																					// 申请换货
				shopBarterLog.setChangeState(BarterState.SELLER_STATE_PENDING_AUDIT + "");// 下一步换货状态信息
				shopBarterLog.setStateInfo("买家的换货服务已申请成功,等待卖家审核"); // 换货状态描述
				shopBarterLog.setCreateTime(System.currentTimeMillis()); // 创建时间
				shopBarterLog.setOperator(shopBarter.getBuyerName());// 操作人
				// 保存退货日志
				shopBarterLogDao.saveShopBarterLog(shopBarterLog);
			}
		}

	}

	/**
	 * 通过条件获取订单列表
	 *
	 * @param order
	 * @return
	 */
	public List<OrderExcel> findOrderByCondition(Order order) {
		return orderDao.findOrderByCondition(order);
	}

	/**
	 * 分页查询订单接口实体集合
	 * 
	 * @param pager
	 * @return
	 */
	public Pager findOrderApiBeanList(Pager pager) {
		List<OrderApiBean> list = orderDao.findOrderApiBeanList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 分页查询订单用户实体集合
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public Pager findOrderMemberApiBeanList(Pager pager) {
		List<OrderMemberApiBean> list = orderDao.findOrderMemberApiBeanList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 分页查询订单接口实体集合,取消订单的不显示
	 * 
	 * @param pager
	 * @return
	 */
	public Pager findOrderApiBeanList1(Pager pager) {
		List<OrderApiBean> list = orderDao.findOrderApiBeanList1(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 分页查询订单接口实体集合,取消订单的不显示
	 * 
	 * @param pager
	 * @return
	 */
	public Pager findOrderApiBeanList2(Pager pager) {
		List<OrderApiBean> list = orderDao.findOrderApiBeanList2(pager);
		pager.setResult(list);
		return pager;
	}

	public BigDecimal doubleToBigDecimal(double dou) {
		BigDecimal sty = new BigDecimal(dou);
		return sty;
	}

	/**
	 * 接口支付完成后,修改订单状态
	 *
	 * @param paySn
	 * @param tradeSn
	 *            支付流水号
	 * @param paymentBranch
	 *            支付分支
	 */
	public void updateOrderStatePayFinishForApp(String paySn, String tradeSn, String paymentBranch) {
		// 新建一个订单总的应付金额
		double orderAmount = 0.0;
		// 新建一个余额支付金额
		double predepositAmount = 0.0;
		// 新建一个订单应加积分,应加积分=订单支付金额
		double orderPoints = 0.0;

		String memberId = null;

		if ("P".equals(paySn.substring(0, 1))) { // 判断编号类型,支付单号
			List<Order> orderList = orderDao.findByPaySn(paySn);
			for (Order order : orderList) {
				if (order != null) {
					memberId = order.getBuyerId();
					if (order.getPaymentState() == 0) {
						// 新建一个订单日志
						OrderLog orderLog = new OrderLog();
						orderLog.setOrderState(OrderState.ORDER_STATE_UNFILLED + "");
						orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
						orderLog.setStateInfo("订单付款完成");
						orderLog.setOrderId(order.getOrderId());
						orderLog.setOperator(order.getBuyerName());
						orderLog.setCreateTime(System.currentTimeMillis());
						// 保存订单日志
						orderLogDao.saveOrderLog(orderLog);
						// 修改订单状态
						Order newOrder = new Order();
						newOrder.setOrderId(order.getOrderId());
						newOrder.setOrderState(OrderState.ORDER_STATE_UNFILLED);
						newOrder.setPaymentState(OrderState.PAYMENT_STATE_YES);
						newOrder.setPaymentTime(System.currentTimeMillis());
						newOrder.setTradeSn(tradeSn);
						newOrder.setPaymentBranch(paymentBranch);
						orderDao.updateOrder(newOrder);
						// 累加订单支付金额和订单余额支付金额
						orderAmount += order.getOrderAmount().doubleValue();
						predepositAmount += order.getPredepositAmount().doubleValue();
						// 应加积分=订单支付金额
						// 应加积分=订单支付金额
						orderPoints += order.getOrderAmount().doubleValue();
					} else {
						// 新建一个订单日志
						OrderLog orderLog = new OrderLog();
						orderLog.setOrderState(OrderState.ORDER_STATE_UNFILLED + "");
						orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
						orderLog.setStateInfo("订单付款完成");
						orderLog.setOrderId(order.getOrderId());
						orderLog.setOperator(order.getBuyerName());
						orderLog.setCreateTime(System.currentTimeMillis());
						// 保存订单日志
						orderLogDao.saveOrderLog(orderLog);
						// 修改订单状态
						Order newOrder = new Order();
						newOrder.setOrderId(order.getOrderId());
						newOrder.setOrderState(OrderState.ORDER_STATE_UNFILLED);
						newOrder.setPaymentState(OrderState.PAYMENT_STATE_YES);
						newOrder.setPaymentTime(System.currentTimeMillis());
						newOrder.setTradeSn(tradeSn);
						newOrder.setPaymentBranch(paymentBranch);
						orderDao.updateOrder(newOrder);
						// 累加订单支付金额和订单余额支付金额
						orderAmount += order.getOrderAmount().doubleValue();
						predepositAmount += order.getPredepositAmount().doubleValue();
						// 应加积分=订单总金额-订单优惠金额
						orderPoints += order.getOrderAmount().doubleValue();
					}
				}
			}
		} else {
			// 新建一个订单日志
			Order order = orderDao.findByOrderSn(paySn);
			if (order != null) {
				memberId = order.getBuyerId();
				if (order.getPaymentState() == 0) {
					OrderLog orderLog = new OrderLog();
					orderLog.setOrderState(OrderState.ORDER_STATE_UNFILLED + "");
					orderLog.setChangeState(OrderState.ORDER_STATE_NOT_RECEIVING + "");
					orderLog.setStateInfo("订单付款完成");
					orderLog.setOrderId(order.getOrderId());
					orderLog.setOperator(order.getBuyerName());
					orderLog.setCreateTime(System.currentTimeMillis());
					// 保存订单日志
					orderLogDao.saveOrderLog(orderLog);
					// 修改订单状态
					Order newOrder = new Order();
					newOrder.setOrderId(order.getOrderId());
					newOrder.setOrderState(OrderState.ORDER_STATE_UNFILLED);
					newOrder.setPaymentState(OrderState.PAYMENT_STATE_YES);
					newOrder.setPaymentTime(System.currentTimeMillis());
					newOrder.setTradeSn(tradeSn);
					newOrder.setPaymentBranch(paymentBranch);
					orderDao.updateOrder(newOrder);
					// 累加订单支付金额和订单余额支付金额
					orderAmount += order.getOrderAmount().doubleValue();
					predepositAmount += order.getPredepositAmount().doubleValue();
					// 应加积分=订单总金额-订单优惠金额
					orderPoints += order.getOrderAmount().doubleValue();
				}
			}
		}
		Member member = memberService.findById(memberId);

		// 判断应付金额不为0和余额支付金额不为0(此时支付的余额为冻结状态)
		if (orderAmount != 0 && predepositAmount != 0 && memberId != null) {
			// 在冻结金额中减去支付完的余额
			double freeze = member.getFreezePredeposit().doubleValue() - predepositAmount;
			if (freeze > 0) {
				member.setFreezePredeposit(BigDecimal.valueOf(freeze));
			} else {
				member.setFreezePredeposit(BigDecimal.valueOf(0));
			}
			memberService.updateMember(member);

			// 创建一个新的变更日志实体
			PredepositLog predepositLog = new PredepositLog();
			predepositLog.setLgMemberId(member.getMemberId()); // 会员编号
			predepositLog.setLgMemberName(member.getMemberName()); // 会员名称
			predepositLog.setLgType("order_comb_pay"); // 操作类型:下单支付被冻结的预存款
			predepositLog.setLgAvAmount(BigDecimal.valueOf(0)); // 可用金额变更0表示未变更
			predepositLog.setLgRdeAmount(BigDecimal.valueOf(predepositAmount)); // 支出余额
			predepositLog.setLgFreezeAmount(BigDecimal.valueOf(freeze)); // 冻结金额变更0表示未变更
			predepositLog.setLgDesc("订单支付冻结余额"); // 描述
			predepositLog.setCreateTime(System.currentTimeMillis()); // 添加时间
			// 保存预存款变更日志表
			predepositLogService.savePdl(predepositLog);
		}

		/**
		 * 订单积分修改,订单积分日志保存
		 */
		Integer points = Integer.parseInt(new java.text.DecimalFormat("0").format(orderPoints));
		Integer rankPoint = member.getMemberRankPoints();
		Integer consPoint = member.getMemberConsumePoints();
		if (rankPoint == null)
			rankPoint = 0;
		if (consPoint == null)
			consPoint = 0;
		// 获取积分设置购买商品(一元等于多少积分)等级积分
		String rankSettingPoints = settingService.findByNameAndCode("points", "buygoods_rank");
		// 获取积分设置购买商品(一元等于多少积分)消费积分
		String consSettingPoints = settingService.findByNameAndCode("points", "buygoods_cons");
		if (StringUtils.isNotBlank(rankSettingPoints)) {
			rankPoint += Integer.valueOf(rankSettingPoints) * points;
		} else { // 若没设置购买商品等级积分,则按(一元等于一积分计算)
			rankPoint += points;
		}
		if (StringUtils.isNotBlank(consSettingPoints)) {
			consPoint += Integer.valueOf(consSettingPoints) * points;
		} else { // 若没设置购买商品消费积分,则按(一元等于一积分计算)
			consPoint += points;
		}

		// 修改用户积分
		member.setMemberRankPoints(rankPoint);
		member.setMemberConsumePoints(consPoint);
		memberService.updateMember(member);

		ShopPointsLog shopPointsLog = new ShopPointsLog();
		shopPointsLog.setMemberid(member.getMemberId());
		shopPointsLog.setMembername(member.getMemberName());
		shopPointsLog.setAdminid("1");
		shopPointsLog.setAdminname("admin");
		shopPointsLog.setPoints(consPoint);
		shopPointsLog.setCreateTime(System.currentTimeMillis());
		shopPointsLog.setType(PointsLogType.POINTS_TYPE_ORDERPAY); // 积分操作类型
		shopPointsLog.setDesc("付款完成");
		shopPointsLog.setStage("商品付款成功,增加会员积分");
		// 保存会员积分日志表
		// shopPointsLogService.save(shopPointsLog);
	}

	/**
	 * 根据不同条件查询购买数量
	 * 
	 * @param map
	 *            可加查询条件:店铺id,订单状态,开始结束时间(starttime,endtime)
	 * @return
	 */
	public List<GoodsGeneralCount> findPurchaseRateIndex(Map map) {
		return orderDao.findPurchaseRateIndex(map);
	}

	/**
	 * 结算统计
	 */
	@Override
	public List<OrderSettlement> findBalance(Map mp) {
		return orderDao.findBalance(mp);
	}

	/**
	 * 收入总金额
	 * 
	 * @param storeId
	 * @return
	 */
	@Override
	public Float findSellerPriceCountByStoreId(String storeId) {
		return orderDao.findSellerPriceCountByStoreId(storeId);
	}
}
