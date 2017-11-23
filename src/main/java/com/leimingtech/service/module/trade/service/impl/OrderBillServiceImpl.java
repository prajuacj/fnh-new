package com.leimingtech.service.module.trade.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.leimingtech.core.common.DateUtils;
import com.leimingtech.core.common.MyBeanUtils;
import com.leimingtech.core.common.NumberUtils;
import com.leimingtech.core.entity.AliPayParse;
import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.base.OrderBill;
import com.leimingtech.core.entity.base.OrderBillTotal;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.vo.BillBulkTransferVo;
import com.leimingtech.core.entity.vo.BillVo;
import com.leimingtech.core.entity.vo.OrderBillExcelVo;
import com.leimingtech.core.entity.vo.OrderBillTotalExcelVo;
import com.leimingtech.core.entity.vo.RefundReturnBillVo;
import com.leimingtech.service.module.trade.common.BillState;
import com.leimingtech.service.module.trade.common.OrderState;
import com.leimingtech.service.module.trade.common.RefundReturnState;
import com.leimingtech.service.module.trade.dao.OrderBillDao;
import com.leimingtech.service.module.trade.dao.OrderBillTotalDao;
import com.leimingtech.service.module.trade.service.OrderBillService;
import com.leimingtech.service.module.trade.service.OrderGoodsService;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.trade.service.RefundReturnService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 结算表
 *
 * @author liukai
 */
@Service
public class OrderBillServiceImpl implements OrderBillService {

	@Resource
	private OrderBillDao orderBillDao;

	@Resource
	private OrderBillTotalDao orderBillTotalDao;

	@Resource
	private OrderService orderService;

	@Resource
	private OrderGoodsService orderGoodsService;

	@Resource
	private RefundReturnService refundReturnService;

	/**
	 * 生成结算信息
	 *
	 * @param obStartTime
	 *            开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime
	 *            结束时间(年月日 如:"2015-1-31")
	 */
	public void addBill(String obStartTime, String obEndTime) {
		// 生成订单结算信息
		this.addOrderBill(obStartTime, obEndTime);
		// 生成退款退货单结算信息
		this.addRefundReturnBill(obStartTime, obEndTime);
		// 生成结算总账单信息
		this.addOrderBillTotal(obStartTime, obEndTime);
	}

	/**
	 * 生成订单结算信息
	 *
	 * @param obStartTime
	 *            开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime
	 *            结束时间(年月日 如:"2015-1-31")
	 */
	public void addOrderBill(String obStartTime, String obEndTime) {
		Order order1 = new Order();
		// 得到结算表里最大结束时间
		String maxObtime = orderBillDao.findMaxObtime();
		if (StringUtils.isNotEmpty(maxObtime)) {
			if (Long.valueOf(maxObtime) < DateUtils.strToLong(obStartTime + " 00:00:00")) {
				order1.setStartTime(Long.valueOf(maxObtime));
			} else {
				order1.setStartTime(DateUtils.strToLong(obStartTime + " 00:00:00"));
			}
			order1.setEndTime(DateUtils.strToLong(obEndTime + " 23:59:59"));
		} else {
			// 为空的话说明是首次结算
			order1.setStartTime(null);
			order1.setEndTime(null);
		}
		order1.setOrderState(OrderState.ORDER_STATE_FINISH); // 查询所有订单状态已完成的

		// 获取时间段内的所有订单数据的条数
		int count = orderService.findOrderCount(order1);
		if (count == 0) {
			order1 = null;
			return;
		}
		// 以300条订单为一组,判断一共有多少组
		int i = count / 300 + 1;

		Pager pager = new Pager();
		pager.setCondition(order1);
		pager.setPageSize(300);
		List<Order> orderList;
		OrderBill orderBill;
		for (int pageNo = 1; pageNo <= i; pageNo++) {
			pager.setPageNo(pageNo);
			Pager rp = orderService.findBillOrderList(pager);
			orderList = (List<Order>) rp.getResult();
			if (orderList != null && orderList.size() != 0) {
				for (Order order : orderList) {
					// 判断订单是否为已完成订单
					if (order.getOrderState() == OrderState.ORDER_STATE_FINISH) {
						// 订单总金额
						double orderTotals = order.getOrderTotalPrice().doubleValue();
						// 店铺促销活动费用(订单优惠总金额)
						double storeCostTotals = order.getDiscount().doubleValue();
						/**
						 * 计算佣金金额:多个订单项佣金金额相加 订单项佣金金额=订单项实付金额*商品所在分类佣金比例
						 */
						double commisTotals = 0.00;
						for (OrderGoods orderGoods : order.getOrderGoodsList()) {
							// 当佣金比例不畏空的时候计算佣金
							if (orderGoods.getCommisRate() != null) {
								if (orderGoods.getCommisRate() > 0) {
									commisTotals += orderGoods.getGoodsPayPrice().doubleValue()
											* orderGoods.getCommisRate() / 100 * orderGoods.getGoodsNum();
								}
							}
						}
						/**
						 * 计算应结金额:应结金额=订单总金额-店铺促销活动费用-佣金金额
						 */
						double obResultTotals = orderTotals - storeCostTotals - commisTotals;

						// 新建一个Calendar,将开始日期塞入Calendar
						Calendar c = Calendar.getInstance();
						c.setTime(new Date(DateUtils.strToLong(obStartTime + " 00:00:00")));

						orderBill = new OrderBill();
						orderBill.setObNo(DateUtils.getDateStr("yyyyMMddHHmmss") + NumberUtils.getRandomNumber()); // 结算单编号(年月店铺ID)
						orderBill.setObStartTime(DateUtils.strToLong(obStartTime + " 00:00:00")); // 开始日期
						orderBill.setObEndTime(DateUtils.strToLong(obEndTime + " 23:59:59")); // 结束日期
						orderBill.setObOrderTotals(order.getOrderTotalPrice()); // 订单金额
						orderBill.setObShippingTotals(order.getShippingFee()); // 运费
						orderBill.setObCommisTotals(NumberUtils.getsetScale(BigDecimal.valueOf(commisTotals), 2)); // 佣金金额
						orderBill.setObStoreCostTotals(order.getPromoPrice()); // 店铺促销活动费用
						orderBill.setObResultTotals(NumberUtils.getsetScale(BigDecimal.valueOf(obResultTotals), 2)); // 应结金额
						orderBill.setOsMonth(c.get(Calendar.MONTH) + 1); // 结算单年月份(结算周期结束时间所在月份)
						orderBill.setOsYear(c.get(Calendar.YEAR)); // 结算单年份(结算周期结束时间所在年份)
						orderBill.setObState(BillState.OB_STATE_NOT_SETTLED); // 结算状态
						orderBill.setObStoreId(order.getStoreId()); // 店铺ID
						orderBill.setObStoreName(order.getStoreName()); // 店铺名
						orderBill.setCreateTime(System.currentTimeMillis()); // 生成结算单日期

						// 保存结算表
						orderBillDao.saveOrderBill(orderBill);

						/**
						 * 修改订单结算状态
						 */
						order.setBalanceState(OrderState.ORDER_BALANCE_YES);
						order.setBalanceTime(System.currentTimeMillis());
						orderService.updateOrder(order);

						orderBill = null;
					}
				}
			}
			orderList = null;
		}
		order1 = null;
		pager = null;
	}

	/**
	 * 生成退款退货单结算信息
	 *
	 * @param obStartTime
	 *            开始时间(年月日 如:"2015-1-1")
	 * @param obEndTime
	 *            结束时间(年月日 如:"2015-1-31")
	 */
	public void addRefundReturnBill(String obStartTime, String obEndTime) {
		RefundReturn refundReturn1 = new RefundReturn();
		refundReturn1.setRefundState(RefundReturnState.REFUND_STATE_FINISH); // 退款状态已完成
		// 得到结算表里最大结束时间
		String maxObtime = orderBillDao.findMaxObtime();
		if (StringUtils.isNotEmpty(maxObtime)) {
			if (Long.valueOf(maxObtime) < DateUtils.strToLong(obStartTime + " 00:00:00")) {
				refundReturn1.setStartTime(Long.valueOf(maxObtime));
			} else {
				refundReturn1.setStartTime(DateUtils.strToLong(obStartTime + " 00:00:00"));
			}
			refundReturn1.setEndTime(DateUtils.strToLong(obEndTime + " 23:59:59"));
		} else {
			// 为空的话说明是首次结算
			refundReturn1.setStartTime(DateUtils.strToLong(obStartTime + " 00:00:00"));
			refundReturn1.setEndTime(DateUtils.strToLong(obEndTime + " 23:59:59"));
		}
		// 获取时间段内的所有订单数据的条数
		int count = refundReturnService.findRefundReturnCount(refundReturn1);
		if (count == 0) {
			refundReturn1 = null;
			return;
		}
		// 以300条订单为一组,判断一共有多少组
		int i = count / 300 + 1;

		Pager pager = new Pager();
		pager.setCondition(refundReturn1);
		pager.setPageSize(300);
		OrderBill orderBill;
		List<RefundReturnBillVo> refundReturnBillVoList = null;
		for (int pageNo = 1; pageNo <= i; pageNo++) {
			pager.setPageNo(pageNo);
			Pager rp = refundReturnService.findBillPagerList(pager);
			refundReturnBillVoList = (List<RefundReturnBillVo>) rp.getResult();
			if (refundReturnBillVoList != null && refundReturnBillVoList.size() != 0) {
				for (RefundReturnBillVo refundReturnBillVo : refundReturnBillVoList) {
					// 判断退款退货单所在订单是否为已完成订单,且退款退货单是否已完成退款
					if (refundReturnBillVo.getOrderState() == OrderState.ORDER_STATE_FINISH
							&& refundReturnBillVo.getRefundState() == RefundReturnState.REFUND_STATE_FINISH) {
						/**
						 * 计算佣金金额 : 这里用作退还佣金 退货单佣金金额=订单项实付金额*商品所在分类佣金比例
						 */
						double commisTotals = 0.00;

						// 判断退款单是售后还是售前退款
						if (refundReturnBillVo.getOrderGoodsId().equals("0")) { // 售前退款
							Order order = orderService.findById(refundReturnBillVo.getOrderId());
							for (OrderGoods orderGoods : order.getOrderGoodsList()) {
								if (orderGoods.getCommisRate() > 0) {
									commisTotals += orderGoods.getGoodsPayPrice().doubleValue()
											* orderGoods.getCommisRate() / 100 * orderGoods.getGoodsNum();
								}
							}
						} else { // 非售前退款
							OrderGoods orderGoods = orderGoodsService.findById(refundReturnBillVo.getOrderGoodsId());
							if (refundReturnBillVo.getCommisRate() > 0) {
								commisTotals = orderGoods.getGoodsPayPrice().doubleValue() * orderGoods.getCommisRate()
										/ 100 * refundReturnBillVo.getGoodsNum();
							}
						}

						// 退单金额
						double obOrderReturnTotals = refundReturnBillVo.getRefundAmount().doubleValue();
						/**
						 * 计算应结金额:应结金额=-(退单金额-退还佣金)
						 */
						double obResultTotals = -(obOrderReturnTotals - commisTotals);

						// 新建一个Calendar,将开始日期塞入Calendar
						Calendar c = Calendar.getInstance();
						c.setTime(new Date(DateUtils.strToLong(obStartTime + " 00:00:00")));

						orderBill = new OrderBill();
						orderBill.setObNo(DateUtils.getDateStr("yyyyMMddHHmmss") + NumberUtils.getRandomNumber()); // 结算单编号(年月日时分+随机数)
						orderBill.setObStartTime(DateUtils.strToLong(obStartTime + " 00:00:00")); // 开始日期
						orderBill.setObEndTime(DateUtils.strToLong(obEndTime + " 23:59:59")); // 结束日期
						orderBill.setObOrderReturnTotals(BigDecimal.valueOf(obOrderReturnTotals)); // 退单金额
						orderBill.setObCommisReturnTotals(NumberUtils.getsetScale(BigDecimal.valueOf(commisTotals), 2)); // 退还佣金
						orderBill.setObResultTotals(NumberUtils.getsetScale(BigDecimal.valueOf(obResultTotals), 2)); // 应结金额
						orderBill.setOsMonth(c.get(Calendar.MONTH) + 1); // 结算单年月份(结算周期结束时间所在月份)
						orderBill.setOsYear(c.get(Calendar.YEAR)); // 结算单年份(结算周期结束时间所在年份)
						orderBill.setObState(BillState.OB_STATE_NOT_SETTLED); // 结算状态
						orderBill.setObStoreId(refundReturnBillVo.getStoreId()); // 店铺ID
						orderBill.setObStoreName(refundReturnBillVo.getStoreName()); // 店铺名
						orderBill.setCreateTime(System.currentTimeMillis()); // 生成结算单日期

						// 保存结算表
						orderBillDao.saveOrderBill(orderBill);
						orderBill = null;
					}
				}
			}
			refundReturnBillVoList = null;
		}
		refundReturn1 = null;
		pager = null;
	}

	/**
	 * 生成结算总账单信息
	 *
	 * @param obStartTime
	 * @param obEndTime
	 */
	public void addOrderBillTotal(String obStartTime, String obEndTime) {
		try {
			BillVo billVo = new BillVo();
			// 得到结算表里最大结束时间
			String maxObtime = orderBillDao.findMaxObtime();
			if (StringUtils.isNotEmpty(maxObtime)) {
				if (Long.valueOf(maxObtime) < DateUtils.strToLong(obStartTime + " 00:00:00")) {
					billVo.setObtStartTime(Long.valueOf(maxObtime));
				} else {
					billVo.setObtStartTime(DateUtils.strToLong(obStartTime + " 00:00:00"));
				}
				billVo.setObtEndTime(DateUtils.strToLong(obEndTime + " 23:59:59"));
			} else {
				// 为空的话说明是首次结算
				billVo.setObtStartTime(DateUtils.strToLong(obStartTime + " 00:00:00"));
				billVo.setObtEndTime(DateUtils.strToLong(obEndTime + " 23:59:59"));
			}
			// 查询本期生成的结算表总条数
			int count = this.findBillVoCount(billVo);
			if (count == 0) {
				billVo = null;
				return;
			}

			// 以300条结算单为一组,判断一共有多少组
			int i = count / 300 + 1;

			Pager pager = new Pager();
			pager.setCondition(billVo);
			pager.setPageSize(300);
			List<BillVo> billVoList;
			OrderBillTotal orderBillTotal;
			for (int pageNo = 1; pageNo <= i; pageNo++) {
				pager.setPageNo(pageNo);
				billVoList = orderBillDao.findBillVoPagerList(pager);
				if (billVoList != null && billVoList.size() != 0) {
					for (BillVo billVo1 : billVoList) {
						orderBillTotal = new OrderBillTotal();
						MyBeanUtils.copyBeanNotNull2Bean(billVo1, orderBillTotal);
						orderBillTotal.setObtNo(DateUtils.getDateStr("yyyyMMddHHmmssSSS"));
						orderBillTotal.setObtState(BillState.OB_STATE_NOT_SETTLED); // 结算状态
						// 插入订单结算汇总信息
						orderBillTotalDao.saveOrderBillTotal(orderBillTotal);
						orderBillTotal = null;
					}
				}
			}
			billVoList = null;
			pager = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 平台确认结算完成(批量)
	 *
	 * @param obtIds
	 *            多个结算id之间用逗号隔开
	 * @param payContent
	 *            支付备注
	 * @return 1:完成;2不存在;3:状态错误
	 */
	public void updateAdminAudit(String obtIds, String payContent) {
		String[] ids = obtIds.split(",");
		for (String id : ids) {
			OrderBillTotal orderBillTotal = orderBillTotalDao.findOrderBillTotalById(id);
			if (orderBillTotal != null) {
				if (orderBillTotal.getObtState() == BillState.OB_STATE_NOT_SETTLED) {
					orderBillTotal.setObtState(BillState.OB_STATE_SETTLED);
					orderBillTotal.setObtPayTime(System.currentTimeMillis());
					orderBillTotal.setObtPayContent(payContent);
					orderBillTotalDao.updateOrderBillTotal(orderBillTotal);
					orderBillDao.updateConfirmSettled(orderBillTotal);
				}
			}
		}
	}

	/**
	 * 根据支付宝回调信息修改总账单状态
	 *
	 * @param aliPayParseList
	 */
	public void updateOrderBillTotalByAP(List<AliPayParse> aliPayParseList) {
		OrderBillTotal orderBillTotal;
		for (AliPayParse aliPayParse : aliPayParseList) {
			// 判断操作是否成功
			if (aliPayParse.getSfFlag().equals("S")) { // 若"S"为成功
				// 根据流水号(总账单编号)查询总账单
				orderBillTotal = orderBillTotalDao.findOrderBillTotalByObtNo(aliPayParse.getSerialNumber());
				// 判断账单是否存在,并且是否未结算
				if (orderBillTotal != null && orderBillTotal.getObtState() == BillState.OB_STATE_NOT_SETTLED) {
					orderBillTotal.setObtState(BillState.OB_STATE_SETTLED);
					orderBillTotal.setObtPayTime(System.currentTimeMillis());
					orderBillTotal.setObtPayContent("支付宝转账");
					orderBillTotalDao.updateOrderBillTotal(orderBillTotal);
					orderBillDao.updateConfirmSettled(orderBillTotal);
				}
			}
		}
		orderBillTotal = null;
	}

	/**
	 * 查询分页结算表数据
	 *
	 * @param pager
	 * @return
	 */
	public Pager findOrderBillPagerList(Pager pager) {
		List<OrderBill> list = orderBillDao.findOrderBillPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 根据id查询结算表
	 *
	 * @param id
	 * @return
	 */
	public OrderBill findOrderBillById(String id) {
		return orderBillDao.findOrderBillById(id);
	}

	/**
	 * 条件查询结算详情
	 *
	 * @param orderBill
	 * @return
	 */
	public OrderBill findOrderBillDetail(OrderBill orderBill) {
		return orderBillDao.findOrderBillDetail(orderBill);
	}

	/**
	 * 条件查询结算信息(无分页)
	 *
	 * @param orderBill
	 * @return
	 */
	public List<OrderBill> findOrderBillList(OrderBill orderBill) {
		return orderBillDao.findOrderBillList(orderBill);
	}

	/**
	 * 通过结算id和店铺id查询结算详情
	 *
	 * @param obId
	 *            结算id
	 * @param storeId
	 *            店铺id
	 * @return
	 */
	public OrderBill findOrderBillByStore(String obId, String storeId) {
		OrderBill orderBill = new OrderBill();
		orderBill.setObId(obId);
		orderBill.setObStoreId(storeId);
		return orderBillDao.findOrderBillDetail(orderBill);
	}

	/**
	 * 条件查询结算excel信息
	 *
	 * @param orderBill
	 * @return
	 */
	public List<OrderBillExcelVo> findExcelVoList(OrderBill orderBill) {
		return orderBillDao.findExcelVoList(orderBill);
	}

	/**
	 * 分页查询结算管理总账单
	 *
	 * @param pager
	 * @return
	 */
	public Pager findBillVoPagerList(Pager pager) {
		List<BillVo> list = orderBillDao.findBillVoPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 查询结算管理总账单条数
	 *
	 * @param billVo
	 * @return
	 */
	public int findBillVoCount(BillVo billVo) {
		return orderBillDao.findBillVoCount(billVo);
	}

	/**
	 * 查询分页结算单汇总表数据
	 *
	 * @param pager
	 * @return
	 */
	public Pager findOrderBillTotalPagerList(Pager pager) {
		List<OrderBillTotal> list = orderBillTotalDao.findOrderBillTotalPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 查询结算单汇总表excel数据
	 *
	 * @param orderBillTotal
	 * @return
	 */
	public List<OrderBillTotalExcelVo> findTotalExcelVo(OrderBillTotal orderBillTotal) {
		return orderBillTotalDao.findTotalExcelVo(orderBillTotal);
	}

	/**
	 * 查询支付宝批量转账所需数据
	 *
	 * @param obtIds
	 *            多个结算总账单的id,中间用逗号隔开
	 * @return
	 */
	public Map<String, String> findAlipayBatchData(String obtIds) {
		Map<String, String> map = new HashMap<String, String>();
		String ids = "'" + obtIds.replaceAll(",", "','") + "'";
		List<BillBulkTransferVo> btList = orderBillTotalDao.findBulkTransferVoByIds(ids);
		// 新建一个转账笔数
		int batchNum = 0;
		// 新建一个付款总金额
		double batchAmount = 0.0;
		// 新建一个付款的详细数据
		String detailData = "";
		for (BillBulkTransferVo btVo : btList) {
			// 判断店铺所需的支付宝账号和姓名是否存在,判断转账金额是否大于0
			if (StringUtils.isNotBlank(btVo.getAlipayAcNumber()) && StringUtils.isNotBlank(btVo.getAlipayName())
					&& btVo.getPayAmount().doubleValue() > 0) {
				batchAmount += btVo.getPayAmount().doubleValue();
				batchNum += 1;
				detailData += btVo.getObtNo() + "^" + btVo.getAlipayAcNumber() + "^" + btVo.getAlipayName() + "^"
						+ btVo.getPayAmount() + "^" + "结算批量转账|";
			}
		}
		map.put("batchNum", batchNum + "");
		map.put("batchFee", batchAmount + "");
		map.put("detailData", detailData);
		return map;
	}

	/**
	 * 获得结算管理表里的最大结束时间
	 * 
	 * @return
	 */
	@Override
	public String findMaxObtime() {
		return orderBillDao.findMaxObtime();
	}

}
