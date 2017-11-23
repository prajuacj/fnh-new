package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.RefundLog;
import com.leimingtech.service.module.trade.dao.RefundLogDao;
import com.leimingtech.service.module.trade.dao.mapper.RefundLogMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 退款表
 * @author liukai
 */
@Repository
public class RefundLogDaoImpl implements RefundLogDao{
	
	@Resource 
	private RefundLogMapper refundLogMapper;
	
	/**
	 * 保存退款表
	 * @param refundLog
	 */
	public void saveRefundLog(RefundLog refundLog){
		refundLog.setLogId(IdGen.uuid());
		refundLogMapper.saveRefundLog(refundLog);
	}
	
	/**
	 * 修改退款表
	 * @param refundLog
	 */
	public void updateRefundLog(RefundLog refundLog){
		refundLogMapper.updateRefundLog(refundLog);
	}
	
	/**
	 * 删除退款表
	 * @param logId
	 */
	public void deleteRefundLog(String logId){
		refundLogMapper.deleteRefundLog(logId);
	}
	
	/**
	 * 分页查询退款总条数
	 * @param refundLog
	 * @return
	 */
	public int findRefundLogCount(RefundLog refundLog){
		return refundLogMapper.findRefundLogCount(refundLog);
	}
	
	/**
	 * 分页查询退款表
	 * @param pager
	 * @return
	 */
	public List<RefundLog> findRefundLogList(Pager pager){
		return refundLogMapper.findRefundLogList(pager);
	}
	
	/**
	 * 通过id主键查询
	 * @param logId
	 * @return
	 */
	public RefundLog findRefundLogByLogId(String logId){
		return refundLogMapper.findRefundLogByLogId(logId);
	}
	
	/**
	 * 通过订单id查询退款信息
	 * @param orderId
	 * @return
	 */
	public RefundLog findRefundLogByOrderId(String orderId){
		return refundLogMapper.findRefundLogByOrderId(orderId);
	}
	
	/**
	 * 卖家退款审核
	 * @param logId 审核id
	 * @param refundState 退款状态
	 * @param refundMessage 退款备注
	 * @param sellerTime 卖家处理时间
	 */
	@Override
	public void updateRefundLogSeller(String logId, Integer refundState,
			String refundMessage,Long sellerTime) {
		refundLogMapper.updateRefundLogSeller(logId, refundState, refundMessage, sellerTime);
	}
	
}
