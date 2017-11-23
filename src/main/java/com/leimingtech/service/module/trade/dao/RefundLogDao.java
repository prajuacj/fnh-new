package com.leimingtech.service.module.trade.dao;

import java.util.List;

import com.leimingtech.core.entity.base.RefundLog;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款表
 * @author liukai
 */
public interface RefundLogDao {
	
	/**
	 * 保存退款表
	 * @param refundLog
	 */
	void saveRefundLog(RefundLog refundLog);
	
	/**
	 * 修改退款表
	 * @param refundLog
	 */
	void updateRefundLog(RefundLog refundLog);
	
	/**
	 * 删除退款表
	 * @param logId
	 */
	void deleteRefundLog(String logId);
	
	/**
	 * 分页查询退款总条数
	 * @param refundLog
	 * @return
	 */
	int findRefundLogCount(RefundLog refundLog);
	
	/**
	 * 分页查询退款表
	 * @param pager
	 * @return
	 */
	List<RefundLog> findRefundLogList(Pager pager);
	
	/**
	 * 通过id主键查询
	 * @param logId
	 * @return
	 */
	RefundLog findRefundLogByLogId(String logId);
	
	/**
	 * 通过订单id查询退款信息
	 * @param orderId
	 * @return
	 */
	RefundLog findRefundLogByOrderId(String orderId);
	
	/**
	 * 卖家退款审核
	 * @param logId 审核id
	 * @param refundState 退款状态
	 * @param refundMessage 退款备注
	 * @param sellerTime 卖家处理时间
	 */
	void updateRefundLogSeller(String logId,Integer refundState,String refundMessage,Long sellerTime);
}
