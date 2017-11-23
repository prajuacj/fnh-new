package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.base.Pay;
import com.leimingtech.core.entity.base.PaymentTally;
import com.leimingtech.service.module.trade.common.PaymentTallyState;
import com.leimingtech.service.module.trade.dao.PaymentTallyDao;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.trade.service.PaymentTallyService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 支付流水表
 *
 * @author liukai
 * @date 2015/12/28
 */
@Service
public class PaymentTallyServiceImpl implements PaymentTallyService {

	@Resource
	private PaymentTallyDao paymentTallyDao;
	@Resource
	private OrderService orderService;

	/**
	 * 保存支付流水表
	 * 
	 * @param paymentTally
	 */
	@Override
	public void savePaymentTally(PaymentTally paymentTally) {
		paymentTallyDao.savePaymentTally(paymentTally);
	}

	/**
	 * 修改支付流水表
	 * 
	 * @param paymentTally
	 */
	@Override
	public void updatePaymentTally(PaymentTally paymentTally) {
		paymentTallyDao.updatePaymentTally(paymentTally);
	}

	/**
	 * 通过id删除支付流水表
	 * 
	 * @param id
	 */
	@Override
	public void deletePaymentTally(String id) {
		paymentTallyDao.deletePaymentTally(id);
	}

	/**
	 * 通过id查询支付流水表
	 * 
	 * @param id
	 *            支付流水表id
	 * @return
	 */
	@Override
	public PaymentTally findPaymentTallyById(String id) {
		return paymentTallyDao.findPaymentTallyById(id);
	}

	/**
	 * 保存支付流水记录
	 * 
	 * @param paytype
	 *            支付方式
	 * @param payname
	 *            支付名称
	 * @param pay
	 *            支付体
	 * @param paytrem
	 *            支付终端
	 * 
	 */
	@Override
	public void savePaymentTally(String paytype, String payname, Pay pay, Integer paytrem) {
		// 根据支付单号获取订单信息
		List<Order> orderlist = orderService.findByPaySn(pay.getPaySn());
		if (orderlist.size() != 0) {
			Order order = orderlist.get(0);
			PaymentTally paymentTally = new PaymentTally();
			paymentTally.setPaymentCode(paytype);// 保存支付类型
			paymentTally.setPaymentName(payname);// 支付名称
			paymentTally.setPaymentSn(pay.getPaySn());// 商城内部交易号
			paymentTally.setPaymentAmount(pay.getPayAmount());// 订单交易金额
			if (pay.getPaySn().contains("R")) {// 充值
				paymentTally.setTradeType(PaymentTallyState.PAYMENTTALLY_RECHARGE_PAY);
			} else {// 订单支付
				paymentTally.setTradeType(PaymentTallyState.PAYMENTTALLY_ORDER_PAY);
			}
			// 支付状态
			paymentTally.setPaymentState(PaymentTallyState.PAYMENTTALLY_STATE_NOSUCCESS);
			// 支付终端类型 1:PC;2:APP;3:h5
			if (paytrem == 1) {
				paymentTally.setPaymentFrom(PaymentTallyState.PAYMENTTALLY_TREM_PC);
			} else if (paytrem == 2) {
				paymentTally.setPaymentFrom(PaymentTallyState.PAYMENTTALLY_TREM_MB);
			} else {
				paymentTally.setPaymentFrom(PaymentTallyState.PAYMENTTALLY_TREM_H5);
			}
			// 用户id
			paymentTally.setBuyerId(order.getBuyerId());
			// 用户名
			paymentTally.setBuyerName(order.getBuyerName());
			// 保存生成时间
			paymentTally.setCreateTime(System.currentTimeMillis());
			// 保存流水表记录
			paymentTallyDao.savePaymentTally(paymentTally);
			// 释放资源
			paymentTally = null;
		}
	}

	/**
	 * 修改支付流水表
	 * 
	 * @param paymentsn
	 *            站内交易单号
	 * @param tradeSn
	 *            交易流水号
	 */
	@Override
	public void updatePaymentTally(String paymentsn, String tradeSn) {
		PaymentTally paymentTallys = findPaymentTally(paymentsn);
		// 判断流水状态是否修改
		if (paymentTallys != null
				&& paymentTallys.getPaymentState() == PaymentTallyState.PAYMENTTALLY_STATE_NOSUCCESS) {
			PaymentTally paymentTally = new PaymentTally();
			paymentTally.setPaymentState(PaymentTallyState.PAYMENTTALLY_STATE_SUCCESS);// 支付成功
			paymentTally.setPaymentSn(paymentsn);
			paymentTally.setTradeSn(tradeSn);
			paymentTallyDao.updatePaymentTally(paymentTally);
		}
	}

	/**
	 * 支付单号查询
	 * 
	 * @param paymentTally
	 * @return
	 */
	@Override
	public PaymentTally findPaymentTally(String paymentsn) {
		PaymentTally paymentTally = new PaymentTally();
		paymentTally.setPaymentSn(paymentsn);
		return paymentTallyDao.findPaymentTally(paymentTally);
	}

	/**
	 * 分页查询支付流水信息
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public Pager findPagerList(Pager pager) {
		List<PaymentTally> list = paymentTallyDao.findPagerList(pager);
		pager.setResult(list);
		return pager;
	}

}
