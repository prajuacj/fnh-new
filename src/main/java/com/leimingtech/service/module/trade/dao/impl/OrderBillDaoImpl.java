package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.OrderBill;
import com.leimingtech.core.entity.base.OrderBillTotal;
import com.leimingtech.core.entity.vo.BillVo;
import com.leimingtech.core.entity.vo.OrderBillExcelVo;
import com.leimingtech.service.module.trade.dao.OrderBillDao;
import com.leimingtech.service.module.trade.dao.mapper.OrderBillMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 结算表
 * @author liukai
 */
@Repository
public class OrderBillDaoImpl implements OrderBillDao{
	@Resource
	private OrderBillMapper orderBillMapper;
	
	/**
	 * 保存结算表
	 * @param orderBill
	 */
	public void saveOrderBill(OrderBill orderBill){
		orderBill.setObId(IdGen.uuid());
		orderBillMapper.saveOrderBill(orderBill);
	}
	
	/**
	 * 修改结算表
	 * @param orderBill
	 */
	public void updateOrderBill(OrderBill orderBill){
		orderBillMapper.updateOrderBill(orderBill);
	}
	
	/**
	 * 确认账单结算完成(必传条件:店铺id,结算单开始结束时间;必传值:结算状态,付款备注和付款时间)
	 * @param orderBillTotal
	 */
	public void updateConfirmSettled(OrderBillTotal orderBillTotal){
		orderBillMapper.updateConfirmSettled(orderBillTotal);
	}
	
	/**
	 * 根据id查询结算表
	 * @param id
	 * @return
	 */
	public OrderBill findOrderBillById(String id){
		return orderBillMapper.findOrderBillById(id);
	}
	
	/**
	 * 查询分页结算表数据
	 * @param pager
	 * @return
	 */
	public List<OrderBill> findOrderBillPagerList(Pager pager){
		return orderBillMapper.findOrderBillPagerList(pager);
	}
	
	/**
	 * 获取所有结算表数据
	 * @return
	 */
	public List<OrderBill> findOrderBillAllList(){
		return orderBillMapper.findOrderBillAllList();
	}
	
	/**
	 * 条件查询结算信息(无分页)
	 * @param orderBill 
	 * @return
	 */
	public List<OrderBill> findOrderBillList(OrderBill orderBill){
		return orderBillMapper.findOrderBillList(orderBill);
	}
	
	/**
	 * 条件查询结算详情,必传结算id
	 * @param orderBill
	 * @return
	 */
	public OrderBill findOrderBillDetail(OrderBill orderBill){
		return orderBillMapper.findOrderBillDetail(orderBill);
	}
	
	/**
	 * 条件查询结算excel信息
	 * @param orderBill
	 * @return
	 */
	public List<OrderBillExcelVo> findExcelVoList(OrderBill orderBill){
		return orderBillMapper.findExcelVoList(orderBill);
	}
	
	/**
	 * 分页查询结算管理总账单
	 * @param pager 
	 * @return
	 */
	public List<BillVo> findBillVoPagerList(Pager pager){
		return orderBillMapper.findBillVoPagerList(pager);
	}
	
	/**
	 * 查询结算管理总账单条数
	 * @param billVo
	 * @return
	 */
	public int findBillVoCount(BillVo billVo){
		return orderBillMapper.findBillVoCount(billVo);
	}


	/**
	 * 获得结算管理表里的最大结束时间
	 * @return
	 */
	@Override
	public String findMaxObtime() {
		return orderBillMapper.findMaxObtime();
	}


}
