package com.leimingtech.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.base.PredepositLog;
import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.base.ReturnLog;
import com.leimingtech.core.entity.base.ShopPointsLog;
import com.leimingtech.core.entity.vo.RefundReturnBillVo;
import com.leimingtech.core.entity.vo.RefundReturnDetailVo;
import com.leimingtech.core.entity.vo.ReturnDetailVo;
import com.leimingtech.service.module.goods.service.GoodsSpecService;
import com.leimingtech.service.module.member.common.PointsLogType;
import com.leimingtech.service.module.member.service.MemberService;
import com.leimingtech.service.module.member.service.ShopPointsLogService;
import com.leimingtech.service.module.product.service.ProductService;
import com.leimingtech.service.module.setting.service.SettingService;
import com.leimingtech.service.module.trade.common.OrderState;
import com.leimingtech.service.module.trade.common.RefundReturnState;
import com.leimingtech.service.module.trade.dao.RefundReturnDao;
import com.leimingtech.service.module.trade.dao.ReturnLogDao;
import com.leimingtech.service.module.trade.service.OrderGoodsService;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.trade.service.PredepositLogService;
import com.leimingtech.service.module.trade.service.RefundReturnService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款退货ServiceImpl
 *
 * @author liukai
 * @version 2015-11-02
 */
@Service
public class RefundReturnServiceImpl implements RefundReturnService {

	/** 退款退货DAO接口 */
	@Resource
	private RefundReturnDao refundReturnDao;

	@Resource
	private ReturnLogDao returnLogDao;

	@Resource
	private OrderGoodsService orderGoodsService;

	@Resource
	private OrderService orderService;

	@Resource
	private MemberService memberService;

	@Resource
	private SettingService settingService;

	@Resource
	private ShopPointsLogService shopPointsLogService;

	@Resource
	private PredepositLogService predepositLogService;

	@Resource
	private GoodsSpecService goodsSpecService;

	@Resource
	private ProductService productService;

	/**
	 * 查询分页退款退货数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findRefundReturnPagerList(Pager pager) {
		List<RefundReturn> list = refundReturnDao.findRefundReturnPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 查询分页退款退货数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	public Pager findBillPagerList(Pager pager) {
		List<RefundReturnBillVo> list = refundReturnDao.findBillPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过id获取单条退款退货数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public RefundReturn findRefundReturnById(String id) {
		return refundReturnDao.findRefundReturnById(id);
	}

	/**
	 * 条件查询退款退货总条数
	 * 
	 * @param refundReturn
	 * @return
	 */
	public int findRefundReturnCount(RefundReturn refundReturn) {
		return refundReturnDao.findRefundReturnCount(refundReturn);
	}

	/**
	 * 通过id删除退款退货数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteRefundReturnById(String id) {
		refundReturnDao.deleteRefundReturnById(id);
	}

	/**
	 * 获取所有退款退货数据
	 * 
	 * @return
	 */
	@Override
	public List<RefundReturn> findRefundReturnAllList() {
		return refundReturnDao.findRefundReturnAllList();
	}

	/**
	 * 查询退款退货详情,必传退货id,可传用户id和店铺id,不需要传null
	 * 
	 * @param refundId
	 * @param buyerId
	 * @param storeId
	 * @return
	 */
	public ReturnDetailVo findRefundReturnDetail(String refundId, String buyerId, String storeId) {
		RefundReturn refundReturn = new RefundReturn();
		refundReturn.setRefundId(refundId);
		if (buyerId != null) {
			refundReturn.setBuyerId(buyerId);
		}
		if (storeId != null) {
			refundReturn.setStoreId(storeId);
		}
		return refundReturnDao.findRefundReturnDetail(refundReturn);
	}

	/**
	 * 根据退款id查询退款详情,必传退款id,可传用户id和店铺id,不需要传null
	 * 
	 * @param refundId
	 * @param buyerId
	 * @param storeId
	 * @return
	 */
	public RefundReturnDetailVo findRefundReturnDetailVo(String refundId, String buyerId, String storeId) {
		RefundReturn refundReturn = new RefundReturn();
		refundReturn.setRefundId(refundId);
		if (buyerId != null) {
			refundReturn.setBuyerId(buyerId);
		}
		if (storeId != null) {
			refundReturn.setStoreId(storeId);
		}
		return refundReturnDao.findRefundReturnDetailVo(refundReturn);
	}

	/**
	 * 退货买家发货
	 * 
	 * @param refundId
	 * @param expressName
	 * @param invoiceNo
	 */
	public void updaterefundReturnDelivery(String refundId, String expressName, String invoiceNo) {
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if (refundReturn.getGoodsState() != null && refundReturn.getGoodsState() == RefundReturnState.GOODS_STATE_UNSHIP
				&& refundReturn.getRefundType() == RefundReturnState.TYPE_RETURN) {
			// Express express = expressService.findById(expressId);
			refundReturn.setGoodsState(RefundReturnState.GOODS_STATE_NOT_RECEIVING); // 物流状态
			refundReturn.setExpressId("0"); // 物流公司编号
			refundReturn.setInvoiceNo(invoiceNo); // 物流单号
			refundReturn.setExpressName(expressName); // 物流公司名称
			refundReturn.setShipTime(System.currentTimeMillis()); // 发货时间
			refundReturnDao.updateRefundReturn(refundReturn);

			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); // 退货表id
			returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE + ""); // 退货状态信息
			returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE + ""); // 下一步退货状态信息
			returnLog.setStateInfo("买家已发货,等待卖家收货"); // 退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			returnLog.setOperator(refundReturn.getBuyerName()); // 操作人
			// 保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}

	/**
	 * 卖家退货审核
	 * 
	 * @param refundId
	 *            记录ID
	 * @param sellerState
	 *            卖家处理状态
	 * @param sellerMessage
	 *            卖家备注
	 * @param operator
	 *            操作人
	 */
	public void updateRefundReturnSeller(String refundId, Integer sellerState, String sellerMessage, String operator) {
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if (refundReturn.getSellerState() == RefundReturnState.SELLER_STATE_PENDING_AUDIT
				&& refundReturn.getRefundType() == RefundReturnState.TYPE_RETURN) {
			// 判断卖家是否同意
			if (sellerState == RefundReturnState.SELLER_STATE_AGREE) { // 若同意,修改商品状态为带发货
				refundReturn.setGoodsState(RefundReturnState.GOODS_STATE_UNSHIP); // 物流状态
				refundReturn.setSellerState(RefundReturnState.SELLER_STATE_AGREE); // 卖家处理状态
			} else {
				refundReturn.setSellerState(RefundReturnState.SELLER_STATE_DISAGREE); // 卖家处理状态
			}
			refundReturn.setSellerMessage(sellerMessage); // 卖家备注
			refundReturn.setSellerTime(System.currentTimeMillis()); // 卖家处理时间
			refundReturnDao.updateRefundReturn(refundReturn);

			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); // 退货表id
			// 判断卖家同意或拒绝
			if (sellerState == RefundReturnState.SELLER_STATE_DISAGREE) {
				returnLog.setReturnState(RefundReturnState.SELLER_STATE_DISAGREE + ""); // 退货状态信息
				returnLog.setChangeState(RefundReturnState.SELLER_STATE_DISAGREE + ""); // 下一步退货状态信息
				returnLog.setStateInfo("卖家已拒绝退货服务申请"); // 退货状态描述
			} else if (sellerState == RefundReturnState.SELLER_STATE_AGREE) {
				returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE + ""); // 退货状态信息
				returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE + ""); // 下一步退货状态信息
				returnLog.setStateInfo("退货服务已通过卖家审核,请买家尽快将商品寄回"); // 退货状态描述
			}
			returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			returnLog.setOperator(operator); // 操作人
			// 保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}

	/**
	 * 卖家退款审核
	 * 
	 * @param refundId
	 *            记录ID
	 * @param sellerState
	 *            卖家处理状态
	 * @param sellerMessage
	 *            卖家备注
	 * @param operator
	 *            操作人
	 */
	public void updateRefundOrderSeller(String refundId, Integer sellerState, String sellerMessage, String operator) {
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if (refundReturn.getSellerState() == RefundReturnState.SELLER_STATE_PENDING_AUDIT
				&& refundReturn.getRefundType() == RefundReturnState.TYPE_REFUND) {
			// 判断卖家是否同意
			if (sellerState == RefundReturnState.SELLER_STATE_AGREE) { // 若同意,修改商品状态为带发货
				refundReturn.setSellerState(RefundReturnState.SELLER_STATE_AGREE); // 卖家处理状态
				refundReturn.setRefundState(RefundReturnState.REFUND_STATE_PENDING); // 申请状态
			} else {
				refundReturn.setSellerState(RefundReturnState.SELLER_STATE_DISAGREE); // 卖家处理状态
			}
			refundReturn.setSellerMessage(sellerMessage); // 卖家备注
			refundReturn.setSellerTime(System.currentTimeMillis()); // 卖家处理时间
			refundReturnDao.updateRefundReturn(refundReturn);

			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); // 退款表id
			// 判断卖家同意或拒绝
			if (sellerState == RefundReturnState.SELLER_STATE_DISAGREE) {
				returnLog.setReturnState(RefundReturnState.SELLER_STATE_DISAGREE + ""); // 退款状态信息
				returnLog.setChangeState(""); // 下一步退款状态信息
				returnLog.setStateInfo("卖家已拒绝退款申请"); // 退款状态描述
			} else if (sellerState == RefundReturnState.SELLER_STATE_AGREE) {
				returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE + ""); // 退款状态信息
				returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE + ""); // 下一步退款状态信息
				returnLog.setStateInfo("卖家已同意退款"); // 退款状态描述
			}
			returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			returnLog.setOperator(operator); // 操作人
			// 保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}

	/**
	 * 退货卖家收货
	 * 
	 * @param refundId
	 *            记录ID
	 * @param receiveMessage
	 *            收货备注
	 * @param operator
	 *            操作人
	 */
	public void updateRefundReturnConfirm(String refundId, String receiveMessage, String operator) {
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if (refundReturn.getGoodsState() != null
				&& refundReturn.getGoodsState() == RefundReturnState.GOODS_STATE_NOT_RECEIVING
				&& refundReturn.getRefundType() == RefundReturnState.TYPE_RETURN) {
			// 通过订单id查询订单信息
			Order order = orderService.findById(refundReturn.getOrderId());
			OrderGoods orderGoods = orderGoodsService.findById(refundReturn.getOrderGoodsId());

			refundReturn.setGoodsState(RefundReturnState.GOODS_STATE_RECEIVED); // 物流状态
			refundReturn.setReceiveMessage(receiveMessage); // 收货备注
			refundReturn.setReceiveTime(System.currentTimeMillis()); // 收货时间
			refundReturn.setDelayTime(System.currentTimeMillis() - refundReturn.getShipTime()); // 收货延迟时间
			refundReturn.setRefundState(RefundReturnState.REFUND_STATE_PENDING); // 申请状态
			refundReturnDao.updateRefundReturn(refundReturn);

			/**
			 * 修改订单项退货数量
			 */
			orderGoods.setGoodsReturnNum(refundReturn.getGoodsNum());
			// 修改订单项
			orderGoodsService.updateOrderGoods(orderGoods);

			/**
			 * 修改订单退货数量和退货状态
			 */
			// 创建一个订单退货的订单项个数,初始为1,代表当前退货的订单项
			int goodsSize = 1;

			for (OrderGoods orderGoods1 : order.getOrderGoodsList()) {
				// 判断当前订单项是否已退货
				if (orderGoods1.getGoodsReturnNum() != null && orderGoods1.getGoodsReturnNum() != 0) { // 已退货
					goodsSize += 1;
				}
			}

			Order newOrder = new Order();
			newOrder.setOrderId(refundReturn.getOrderId());
			newOrder.setReturnNum(order.getReturnNum() + refundReturn.getGoodsNum());
			// 判断订单中订单项是否都退货
			if (order.getOrderGoodsList().size() > goodsSize) {
				newOrder.setReturnState(OrderState.RETURN_STATE_SOM);
			} else if (order.getOrderGoodsList().size() == goodsSize) {
				newOrder.setReturnState(OrderState.RETURN_STATE_ALL);
			}
			// 修改订单
			orderService.updateOrder(newOrder);

			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); // 退货表id
			returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE + ""); // 退货状态信息
			returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE + ""); // 下一步退货状态信息
			returnLog.setStateInfo("卖家已收货,等待系统审核退款"); // 退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			returnLog.setOperator(operator); // 操作人
			// 保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}

	/**
	 * 退款退货管理员审核退款手动操作
	 * 
	 * @param refundId
	 *            记录ID
	 * @param adminMessage
	 *            管理员备注
	 */
	public void updateRefundReturnAdminAudi(String refundId, String adminMessage) {
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if (refundReturn.getRefundState() != null
				&& refundReturn.getRefundState() == RefundReturnState.REFUND_STATE_PENDING) {
			// 通过订单id查询订单信息
			Order order = orderService.findById(refundReturn.getOrderId());

			refundReturn.setAdminMessage(adminMessage); // 管理员备注
			refundReturn.setAdminTime(System.currentTimeMillis()); // 管理员处理时间
			refundReturn.setRefundState(RefundReturnState.REFUND_STATE_FINISH); // 申请状态
			refundReturnDao.updateRefundReturn(refundReturn);

			/**
			 * 退还用户余额,减去用户积分
			 */
			Member member = memberService.findById(refundReturn.getBuyerId());
			if (member != null) {

				// 判断是否使用余额
				if (refundReturn.getPredepositAmount().doubleValue() > 0) {
					// 预存款可用金额 = 原有金额 + 退还金额
					double available = member.getAvailablePredeposit().doubleValue();
					double rdeAmount;
					// 判断退款金额是否大于余额支付金额
					if (refundReturn.getRefundAmount().doubleValue() > refundReturn.getPredepositAmount()
							.doubleValue()) {
						available += refundReturn.getPredepositAmount().doubleValue();
						rdeAmount = refundReturn.getPredepositAmount().doubleValue();
					} else {
						available += refundReturn.getRefundAmount().doubleValue();
						rdeAmount = refundReturn.getRefundAmount().doubleValue();
					}

					member.setAvailablePredeposit(BigDecimal.valueOf(available)); // 预存款可用金额
					memberService.updateMember(member);

					// 创建一个新的变更日志实体
					PredepositLog predepositLog = new PredepositLog();
					predepositLog.setLgMemberId(member.getMemberId()); // 会员编号
					predepositLog.setLgMemberName(member.getMemberName()); // 会员名称
					predepositLog.setLgType("order_return"); // 操作类型:退款退回的预存款
					predepositLog.setLgAvAmount(BigDecimal.valueOf(available)); // 可用金额变更0表示未变更
					predepositLog.setLgFreezeAmount(BigDecimal.valueOf(0)); // 冻结金额变更0表示未变更
					predepositLog.setLgAddAmount(BigDecimal.valueOf(rdeAmount)); // 存入金额就等于退款金额
					predepositLog.setLgDesc("订单退款退还余额"); // 描述
					predepositLog.setCreateTime(System.currentTimeMillis()); // 添加时间
					// 保存预存款变更日志表
					predepositLogService.savePdl(predepositLog);
				}

				// 应减积分=退款金额
				double refundPoints = refundReturn.getRefundAmount().doubleValue();

				/**
				 * 订单积分修改,订单积分日志保存
				 */
				Integer points = Integer.parseInt(new java.text.DecimalFormat("0").format(refundPoints));
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
					rankPoint -= Integer.valueOf(rankSettingPoints) * points;
				} else { // 若没设置购买商品等级积分,则按(一元等于一积分计算)
					rankPoint -= points;
				}
				if (StringUtils.isNotBlank(consSettingPoints)) {
					consPoint -= Integer.valueOf(consSettingPoints) * points;
				} else { // 若没设置购买商品消费积分,则按(一元等于一积分计算)
					consPoint -= points;
				}
				if (consPoint <= 0) { // 如果积分为负数则把积分改为0
					consPoint = 0;
				}

				// 修改用户积分
				member.setMemberRankPoints(rankPoint);
				member.setMemberConsumePoints(consPoint);
				// memberService.updateMember(member);

				ShopPointsLog shopPointsLog = new ShopPointsLog();
				shopPointsLog.setMemberid(member.getMemberId());
				shopPointsLog.setMembername(member.getMemberName());
				shopPointsLog.setAdminid("1");
				shopPointsLog.setAdminname("admin");
				shopPointsLog.setPoints(-consPoint);
				shopPointsLog.setCreateTime(System.currentTimeMillis());
				shopPointsLog.setType(PointsLogType.POINTS_TYPE_ORDERPAY); // 积分操作类型
				shopPointsLog.setDesc("付款完成");
				shopPointsLog.setStage("退款成功,减去会员积分");
				shopPointsLog.setObjectId(order.getOrderId());

				// 保存会员积分日志表
				shopPointsLogService.save(shopPointsLog);
			}

			/**
			 * 修改订单退款金额和退款状态
			 */
			// 创建一个订单退款的最大退款总数,因为订单项退款最大金额按比例计算,存在误差,所以退款总数=订单项退款最大金额相加
			double totalReturnPrice = 0.00;
			// 新建一个订单当前全部退款金额(包括本次退款的金额)
			double refundedAmount = 0.00;

			for (OrderGoods orderGoods1 : order.getOrderGoodsList()) {
				totalReturnPrice += orderGoods1.getGoodsPayPrice().doubleValue();
			}

			Order newOrder = new Order();
			newOrder.setOrderId(refundReturn.getOrderId());
			if (order.getRefundAmount() != null) {
				refundedAmount = order.getRefundAmount().doubleValue() + refundReturn.getRefundAmount().doubleValue();
			} else {
				refundedAmount = refundReturn.getRefundAmount().doubleValue();
			}
			newOrder.setRefundAmount(BigDecimal.valueOf(refundedAmount));
			// 判断订单是否全部退款
			if (totalReturnPrice > refundedAmount) {
				newOrder.setRefundState(OrderState.REFUND_STATE_SOM);
			} else {
				newOrder.setRefundState(OrderState.REFUND_STATE_ALL);
			}
			// 订单解锁
			newOrder.setLockState(OrderState.ORDER_LOCK_STATE_NO);

			// 判断售前售后退款
			if (refundReturn.getOrderGoodsId().equals("0")) {
				newOrder.setOrderState(OrderState.ORDER_STATE_CANCLE);
			}
			// 修改订单
			orderService.updateOrder(newOrder);

			// 修改商品库存
			// 判断是否为售前退款
			if (refundReturn.getOrderGoodsId().equals("0")) { // 售前退款
				// 修改商品库存和销量
				for (OrderGoods orderGoods : order.getOrderGoodsList()) {
					// 查询商品规格表,实时查找信息
					GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(orderGoods.getSpecId());
					if (goodsSpec != null) {
						// 增加商品库存和销量,传值为负数
						goodsSpec.setSpecSalenum(-orderGoods.getGoodsNum().intValue());
						productService.updateStorage(goodsSpec);
					}
				}
			}

			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); // 退货表id
			returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE + ""); // 退货状态信息
			returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE + ""); // 下一步退货状态信息
			returnLog.setStateInfo("系统审核退款成功"); // 退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			returnLog.setOperator("系统"); // 操作人
			// 保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}

	/**
	 * 退款退货管理员审核退款
	 * 
	 * @param refundId
	 *            记录ID
	 * @param adminMessage
	 *            管理员备注
	 */
	public void updateRefundReturnAudiReturn(String refundId, String adminMessage) {
		RefundReturn refundReturn = refundReturnDao.findRefundReturnById(refundId);
		if (refundReturn.getRefundState() != null
				&& refundReturn.getRefundState() == RefundReturnState.REFUND_STATE_PENDING) {
			// 通过订单id查询订单信息
			Order order = orderService.findById(refundReturn.getOrderId());

			refundReturn.setAdminMessage(adminMessage); // 管理员备注
			refundReturn.setAdminTime(System.currentTimeMillis()); // 管理员处理时间
			refundReturn.setRefundState(RefundReturnState.REFUND_STATE_FINISH); // 申请状态
			refundReturnDao.updateRefundReturn(refundReturn);

			/**
			 * 退还用户余额,减去用户积分
			 */
			Member member = memberService.findById(refundReturn.getBuyerId());
			if (member != null) {
				// 判断是否使用余额
				if (refundReturn.getPredepositAmount().doubleValue() > 0) {
					// 预存款可用金额 = 原有金额 + 退还金额
					double available = member.getAvailablePredeposit().doubleValue();
					double rdeAmount;
					// 判断退款金额是否大于余额支付金额
					if (refundReturn.getRefundAmount().doubleValue() > refundReturn.getPredepositAmount()
							.doubleValue()) {
						available += refundReturn.getPredepositAmount().doubleValue();
						rdeAmount = refundReturn.getPredepositAmount().doubleValue();
					} else {
						available += refundReturn.getRefundAmount().doubleValue();
						rdeAmount = refundReturn.getRefundAmount().doubleValue();
					}

					member.setAvailablePredeposit(BigDecimal.valueOf(available)); // 预存款可用金额
					memberService.updateMember(member);

					// 创建一个新的变更日志实体
					PredepositLog predepositLog = new PredepositLog();
					predepositLog.setLgMemberId(member.getMemberId()); // 会员编号
					predepositLog.setLgMemberName(member.getMemberName()); // 会员名称
					predepositLog.setLgType("order_return"); // 操作类型:退款退回的预存款
					predepositLog.setLgAvAmount(BigDecimal.valueOf(available)); // 可用金额变更0表示未变更
					predepositLog.setLgFreezeAmount(BigDecimal.valueOf(0)); // 冻结金额变更0表示未变更
					predepositLog.setLgAddAmount(BigDecimal.valueOf(rdeAmount)); // 存入金额就等于退款金额
					predepositLog.setLgDesc("订单退款退还余额"); // 描述
					predepositLog.setCreateTime(System.currentTimeMillis()); // 添加时间
					// 保存预存款变更日志表
					predepositLogService.savePdl(predepositLog);
				}

				// 应减积分=退款金额
				double refundPoints = refundReturn.getRefundAmount().doubleValue();

				/**
				 * 订单积分修改,订单积分日志保存
				 */
				Integer points = Integer.parseInt(new java.text.DecimalFormat("0").format(refundPoints));
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
					rankPoint -= Integer.valueOf(rankSettingPoints) * points;
				} else { // 若没设置购买商品等级积分,则按(一元等于一积分计算)
					rankPoint -= points;
				}
				if (StringUtils.isNotBlank(consSettingPoints)) {
					consPoint -= Integer.valueOf(consSettingPoints) * points;
				} else { // 若没设置购买商品消费积分,则按(一元等于一积分计算)
					consPoint -= points;
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
				shopPointsLog.setPoints(-consPoint);
				shopPointsLog.setCreateTime(System.currentTimeMillis());
				shopPointsLog.setType(PointsLogType.POINTS_TYPE_ORDERPAY); // 积分操作类型
				shopPointsLog.setDesc("付款完成");
				shopPointsLog.setStage("退款成功,减去会员积分");
				shopPointsLog.setObjectId(order.getOrderId());

				// 保存会员积分日志表
				shopPointsLogService.save(shopPointsLog);
			}

			/**
			 * 修改订单退款金额和退款状态
			 */
			// 创建一个订单退款的最大退款总数,因为订单项退款最大金额按比例计算,存在误差,所以退款总数=订单项退款最大金额相加
			double totalReturnPrice = 0.00;
			// 新建一个订单当前全部退款金额(包括本次退款的金额)
			double refundedAmount = 0.00;

			for (OrderGoods orderGoods1 : order.getOrderGoodsList()) {
				totalReturnPrice += orderGoods1.getGoodsPayPrice().doubleValue();
			}

			Order newOrder = new Order();
			newOrder.setOrderId(refundReturn.getOrderId());
			if (order.getRefundAmount() != null) {
				refundedAmount = order.getRefundAmount().doubleValue() + refundReturn.getRefundAmount().doubleValue();
			} else {
				refundedAmount = refundReturn.getRefundAmount().doubleValue();
			}
			newOrder.setRefundAmount(BigDecimal.valueOf(refundedAmount));
			// 判断订单是否全部退款
			if (totalReturnPrice > refundedAmount) {
				newOrder.setRefundState(OrderState.REFUND_STATE_SOM);
			} else {
				newOrder.setRefundState(OrderState.REFUND_STATE_ALL);
			}
			// 判断售前售后退款
			if (refundReturn.getOrderGoodsId().equals("0")) {
				newOrder.setOrderState(OrderState.ORDER_STATE_CANCLE);
			}
			// 订单解锁
			newOrder.setLockState(OrderState.ORDER_LOCK_STATE_NO);
			// 修改订单
			orderService.updateOrder(newOrder);

			// 修改商品库存
			// 判断是否为售前退款
			if (refundReturn.getOrderGoodsId().equals("0")) { // 售前退款
				// 修改商品库存和销量
				for (OrderGoods orderGoods : order.getOrderGoodsList()) {
					// 查询商品规格表,实时查找信息
					GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(orderGoods.getSpecId());
					if (goodsSpec != null) {
						// 增加商品库存和销量,传值为负数
						goodsSpec.setSpecSalenum(-orderGoods.getGoodsNum().intValue());
						productService.updateStorage(goodsSpec);
					}
				}
			}

			ReturnLog returnLog = new ReturnLog();
			returnLog.setReturnId(refundReturn.getRefundId()); // 退货表id
			returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE + ""); // 退货状态信息
			returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE + ""); // 下一步退货状态信息
			returnLog.setStateInfo("系统审核退款成功"); // 退货状态描述
			returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
			returnLog.setOperator("系统"); // 操作人
			// 保存退货日志
			returnLogDao.saveReturnLog(returnLog);
		}
	}

	/**
	 * 根据退款批次号修改管理员审核状态(只限支付宝)
	 * 
	 * @param batchNo
	 *            退款批次号
	 * @param adminMessage
	 *            管理员备注
	 */
	public void updateRefundReturnByBatchNo(String batchNo, String adminMessage) {
		List<RefundReturn> refundReturnList = refundReturnDao.findRefundReturnByBatchNo(batchNo);
		if (refundReturnList != null) {
			for (RefundReturn refundReturn : refundReturnList) {
				if (refundReturn.getRefundState() != null
						&& refundReturn.getRefundState() == RefundReturnState.REFUND_STATE_PENDING) {
					// 通过订单id查询订单信息
					Order order = orderService.findById(refundReturn.getOrderId());

					refundReturn.setAdminMessage(adminMessage); // 管理员备注
					refundReturn.setAdminTime(System.currentTimeMillis()); // 管理员处理时间
					refundReturn.setRefundState(RefundReturnState.REFUND_STATE_FINISH); // 申请状态
					refundReturnDao.updateRefundReturn(refundReturn);

					/**
					 * 退还用户余额
					 */
					Member member = memberService.findById(refundReturn.getBuyerId());
					if (member != null) {
						// 判断是否使用余额
						if (refundReturn.getPredepositAmount().doubleValue() > 0) {
							// 预存款可用金额 = 原有金额 + 退还金额
							double available = member.getAvailablePredeposit().doubleValue();
							double rdeAmount;
							// 判断退款金额是否大于余额支付金额
							if (refundReturn.getRefundAmount().doubleValue() > refundReturn.getPredepositAmount()
									.doubleValue()) {
								available += refundReturn.getPredepositAmount().doubleValue();
								rdeAmount = refundReturn.getPredepositAmount().doubleValue();
							} else {
								available += refundReturn.getRefundAmount().doubleValue();
								rdeAmount = refundReturn.getRefundAmount().doubleValue();
							}

							member.setAvailablePredeposit(BigDecimal.valueOf(available)); // 预存款可用金额
							memberService.updateMember(member);

							// 创建一个新的变更日志实体
							PredepositLog predepositLog = new PredepositLog();
							predepositLog.setLgMemberId(member.getMemberId()); // 会员编号
							predepositLog.setLgMemberName(member.getMemberName()); // 会员名称
							predepositLog.setLgType("order_return"); // 操作类型:退款退回的预存款
							predepositLog.setLgAvAmount(BigDecimal.valueOf(available)); // 可用金额变更0表示未变更
							predepositLog.setLgFreezeAmount(BigDecimal.valueOf(0)); // 冻结金额变更0表示未变更
							predepositLog.setLgAddAmount(BigDecimal.valueOf(rdeAmount)); // 存入余额等于退款金额
							predepositLog.setLgDesc("订单退款退还余额"); // 描述
							predepositLog.setCreateTime(System.currentTimeMillis()); // 添加时间
							// 保存预存款变更日志表
							predepositLogService.savePdl(predepositLog);
						}

						// 应减积分=退款金额
						double refundPoints = refundReturn.getRefundAmount().doubleValue();

						/**
						 * 订单积分修改,订单积分日志保存
						 */
						Integer points = Integer.parseInt(new java.text.DecimalFormat("0").format(refundPoints));
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
							rankPoint -= Integer.valueOf(rankSettingPoints) * points;
						} else { // 若没设置购买商品等级积分,则按(一元等于一积分计算)
							rankPoint -= points;
						}
						if (StringUtils.isNotBlank(consSettingPoints)) {
							consPoint -= Integer.valueOf(consSettingPoints) * points;
						} else { // 若没设置购买商品消费积分,则按(一元等于一积分计算)
							consPoint -= points;
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
						shopPointsLog.setPoints(-consPoint);
						shopPointsLog.setCreateTime(System.currentTimeMillis());
						shopPointsLog.setType(PointsLogType.POINTS_TYPE_ORDERPAY); // 积分操作类型
						shopPointsLog.setDesc("付款完成");
						shopPointsLog.setStage("退款成功,减去会员积分");
						shopPointsLog.setObjectId(order.getOrderId());

						// 保存会员积分日志表
						shopPointsLogService.save(shopPointsLog);
					}

					/**
					 * 修改订单退款金额和退款状态
					 */
					// 创建一个订单退款的最大退款总数,因为订单项退款最大金额按比例计算,存在误差,所以退款总数=订单项退款最大金额相加
					double totalReturnPrice = 0.00;
					// 新建一个订单当前全部退款金额(包括本次退款的金额)
					double refundedAmount = 0.00;

					for (OrderGoods orderGoods1 : order.getOrderGoodsList()) {
						totalReturnPrice += orderGoods1.getGoodsPayPrice().doubleValue();
					}

					Order newOrder = new Order();
					newOrder.setOrderId(refundReturn.getOrderId());
					if (order.getRefundAmount() != null) {
						refundedAmount = order.getRefundAmount().doubleValue()
								+ refundReturn.getRefundAmount().doubleValue();
					} else {
						refundedAmount = refundReturn.getRefundAmount().doubleValue();
					}
					newOrder.setRefundAmount(BigDecimal.valueOf(refundedAmount));
					// 判断订单是否全部退款
					if (totalReturnPrice > refundedAmount) {
						newOrder.setRefundState(OrderState.REFUND_STATE_SOM);
					} else {
						newOrder.setRefundState(OrderState.REFUND_STATE_ALL);
					}
					// 判断售前售后退款
					if (refundReturn.getOrderGoodsId().equals("0")) {
						newOrder.setOrderState(OrderState.ORDER_STATE_CANCLE);
					}
					// 订单解锁
					newOrder.setLockState(OrderState.ORDER_LOCK_STATE_NO);
					// 修改订单
					orderService.updateOrder(newOrder);

					// 修改商品库存
					// 判断是否为售前退款
					if (refundReturn.getOrderGoodsId().equals("0")) { // 售前退款
						// 修改商品库存和销量
						for (OrderGoods orderGoods : order.getOrderGoodsList()) {
							// 查询商品规格表,实时查找信息
							GoodsSpec goodsSpec = goodsSpecService.findByGoodsSpecId(orderGoods.getSpecId());
							if (goodsSpec != null) {
								// 增加商品库存和销量,传值为负数
								goodsSpec.setSpecSalenum(-orderGoods.getGoodsNum().intValue());
								productService.updateStorage(goodsSpec);
							}
						}
					}

					ReturnLog returnLog = new ReturnLog();
					returnLog.setReturnId(refundReturn.getRefundId()); // 退货表id
					returnLog.setReturnState(RefundReturnState.SELLER_STATE_AGREE + ""); // 退货状态信息
					returnLog.setChangeState(RefundReturnState.SELLER_STATE_AGREE + ""); // 下一步退货状态信息
					returnLog.setStateInfo("系统审核退款成功"); // 退货状态描述
					returnLog.setCreateTime(System.currentTimeMillis()); // 创建时间
					returnLog.setOperator("系统"); // 操作人
					// 保存退货日志
					returnLogDao.saveReturnLog(returnLog);
				}
			}
		}

	}

	/**
	 * 退款退货保存退款批次号
	 * 
	 * @param refundId
	 *            记录ID
	 * @param batchNo
	 *            退款批次号
	 */
	public void saveBatchNo(String refundId, String batchNo) {
		RefundReturn refundReturn = new RefundReturn();
		refundReturn.setRefundId(refundId); // 记录ID
		refundReturn.setBatchNo(batchNo); // 退款批次号
		refundReturnDao.updateRefundReturn(refundReturn);
	}
}
