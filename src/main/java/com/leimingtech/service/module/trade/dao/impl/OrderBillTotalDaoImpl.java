package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.OrderBillTotal;
import com.leimingtech.core.entity.vo.BillBulkTransferVo;
import com.leimingtech.core.entity.vo.OrderBillTotalExcelVo;
import com.leimingtech.service.module.trade.dao.OrderBillTotalDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderBillTotalMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 结算单汇总表
 * @author liukai
 */
@Repository
public class OrderBillTotalDaoImpl implements OrderBillTotalDao{
	
	@Resource
	private OrderBillTotalMapper orderBillTotalMapper;
	
	/**
	 * 保存结算单汇总表
	 * @param orderBillTotal
	 */
	public void saveOrderBillTotal(OrderBillTotal orderBillTotal){
		orderBillTotal.setObtId(IdGen.uuid());
		orderBillTotalMapper.saveOrderBillTotal(orderBillTotal);
	}
	
	/**
	 * 修改结算单汇总表
	 * @param orderBillTotal
	 */
	public void updateOrderBillTotal(OrderBillTotal orderBillTotal){
		orderBillTotalMapper.updateOrderBillTotal(orderBillTotal);
	}
	
	/**
	 * 通过id查询结算单汇总表
	 * @param obtId
	 * @return
	 */
	public OrderBillTotal findOrderBillTotalById(String obtId){
		return orderBillTotalMapper.findOrderBillTotalById(obtId);
	}
	
	/**
	 * 根据总账单编号查询总账单
	 * @param obtNo
	 */
	public OrderBillTotal findOrderBillTotalByObtNo(String obtNo){
		return orderBillTotalMapper.findOrderBillTotalByObtNo(obtNo);
	}
	
	/**
	 * 查询分页结算单汇总表数据
	 * @param pager
	 * @return
	 */
	public List<OrderBillTotal> findOrderBillTotalPagerList(Pager pager){
		return orderBillTotalMapper.findOrderBillTotalPagerList(pager);
	}
	
	/**
	 * 查询结算单汇总表excel数据
	 * @param orderBillTotal
	 * @return
	 */
	public List<OrderBillTotalExcelVo> findTotalExcelVo(OrderBillTotal orderBillTotal){
		return orderBillTotalMapper.findTotalExcelVo(orderBillTotal);
	}
	
	/**
	 * 根据结算总账单id信息查询结算批量转账所需信息
	 * @param obtIds 多个总账单id,中间以逗号隔开
	 * @return
	 */
	public List<BillBulkTransferVo> findBulkTransferVoByIds(String obtIds){
		return orderBillTotalMapper.findBulkTransferVoByIds(obtIds);
	}
}
