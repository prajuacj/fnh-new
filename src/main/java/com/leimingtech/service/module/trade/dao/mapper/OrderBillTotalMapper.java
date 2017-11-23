package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.OrderBillTotal;
import com.leimingtech.core.entity.vo.BillBulkTransferVo;
import com.leimingtech.core.entity.vo.OrderBillTotalExcelVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 结算单汇总表
 * @author liukai
 */
@SqlMapper
public interface OrderBillTotalMapper {
	
	/**
	 * 保存结算单汇总表
	 * @param orderBillTotal
	 */
	void saveOrderBillTotal(OrderBillTotal orderBillTotal);
	
	/**
	 * 修改结算单汇总表
	 * @param orderBillTotal
	 */
	void updateOrderBillTotal(OrderBillTotal orderBillTotal);
	
	/**
	 * 通过id查询结算单汇总表
	 * @param obtId
	 * @return
	 */
	OrderBillTotal findOrderBillTotalById(String obtId);
	
	/**
	 * 根据总账单编号查询总账单
	 * @param orderBillTotal
	 */
	OrderBillTotal findOrderBillTotalByObtNo(String obtNo);
	
	/**
	 * 查询分页结算单汇总表数据
	 * @param pager
	 * @return
	 */
	List<OrderBillTotal> findOrderBillTotalPagerList(Pager pager);
	
	/**
	 * 查询结算单汇总表excel数据
	 * @param orderBillTotal
	 * @return
	 */
	List<OrderBillTotalExcelVo> findTotalExcelVo(OrderBillTotal orderBillTotal);
	
	/**
	 * 根据结算总账单id信息查询结算批量转账所需信息
	 * @param obtIds 多个总账单id,中间以逗号隔开
	 * @return
	 */
	List<BillBulkTransferVo> findBulkTransferVoByIds(@Param("obtIds") String obtIds);
}
