package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.RefundLog;
import com.leimingtech.service.module.trade.dao.RefundLogDao;
import com.leimingtech.service.module.trade.service.RefundLogService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款表
 * 
 * @author liukai
 */
@Service
public class RefundLogServiceImpl implements RefundLogService {

	@Resource
	private RefundLogDao refundLogDao;

	/**
	 * 保存退款表
	 * 
	 * @param refundLog
	 */
	public void saveRefundLog(RefundLog refundLog) {
		refundLogDao.saveRefundLog(refundLog);
	}

	/**
	 * 修改退款表
	 * 
	 * @param refundLog
	 */
	public void updateRefundLog(RefundLog refundLog) {
		refundLogDao.updateRefundLog(refundLog);
	}

	/**
	 * 删除退款表
	 * 
	 * @param logId
	 */
	public void deleteRefundLog(String logId) {
		refundLogDao.deleteRefundLog(logId);
	}

	/**
	 * 分页查询退款总条数
	 * 
	 * @param refundLog
	 * @return
	 */
	public int findRefundLogCount(RefundLog refundLog) {
		return refundLogDao.findRefundLogCount(refundLog);
	}

	/**
	 * 分页查询退款表
	 * 
	 * @param pager
	 * @return
	 */
	public Pager findRefundLogList(Pager pager) {
		List<RefundLog> list = refundLogDao.findRefundLogList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过id主键查询
	 * 
	 * @param logId
	 * @return
	 */
	public RefundLog findRefundLogByLogId(String logId) {
		return refundLogDao.findRefundLogByLogId(logId);
	}

	/**
	 * 通过订单id查询退款信息
	 * 
	 * @param orderId
	 * @return
	 */
	public RefundLog findRefundLogByOrderId(String orderId) {
		return refundLogDao.findRefundLogByOrderId(orderId);
	}

	/**
	 * 卖家退款审核
	 * 
	 * @param logId
	 *            审核id
	 * @param refundState
	 *            退款状态
	 * @param refundMessage
	 *            退款备注
	 * @param sellerTime
	 *            卖家处理时间
	 */
	@Override
	public void updateRefundLogSeller(String logId, Integer refundState, String refundMessage, Long sellerTime) {
		refundLogDao.updateRefundLogSeller(logId, refundState, refundMessage, sellerTime);
	}
}
