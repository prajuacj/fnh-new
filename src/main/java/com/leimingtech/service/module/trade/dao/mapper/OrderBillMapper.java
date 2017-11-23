package com.leimingtech.service.module.trade.dao.mapper;

import com.leimingtech.core.entity.base.OrderBill;
import com.leimingtech.core.entity.base.OrderBillTotal;
import com.leimingtech.core.entity.vo.BillVo;
import com.leimingtech.core.entity.vo.OrderBillExcelVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 结算表
 *
 * @author liukai
 */
@SqlMapper
public interface OrderBillMapper {

    /**
     * 保存结算表
     *
     * @param orderBill
     */
    void saveOrderBill(OrderBill orderBill);

    /**
     * 修改结算表
     *
     * @param orderBill
     */
    void updateOrderBill(OrderBill orderBill);

    /**
     * 确认账单结算完成(必传条件:店铺id,结算单开始结束时间;必传值:结算状态,付款备注和付款时间)
     *
     * @param orderBillTotal
     */
    void updateConfirmSettled(OrderBillTotal orderBillTotal);

    /**
     * 根据id查询结算表
     *
     * @param id
     * @return
     */
    OrderBill findOrderBillById(String id);

    /**
     * 查询分页结算表数据
     *
     * @param pager
     * @return
     */
    List<OrderBill> findOrderBillPagerList(Pager pager);

    /**
     * 获取所有结算表数据
     *
     * @return
     */
    List<OrderBill> findOrderBillAllList();

    /**
     * 条件查询结算信息(无分页)
     *
     * @param orderBill
     * @return
     */
    List<OrderBill> findOrderBillList(OrderBill orderBill);

    /**
     * 条件查询结算详情,必传结算id
     *
     * @param orderBill
     * @return
     */
    OrderBill findOrderBillDetail(OrderBill orderBill);

    /**
     * 条件查询结算明细excel信息
     *
     * @param orderBill
     * @return
     */
    List<OrderBillExcelVo> findExcelVoList(OrderBill orderBill);

    /**
     * 分页查询结算管理总账单
     *
     * @param pager
     * @return
     */
    List<BillVo> findBillVoPagerList(Pager pager);

    /**
     * 查询结算管理总账单条数
     *
     * @param billVo
     * @return
     */
    int findBillVoCount(BillVo billVo);

    /**
     * 获得结算管理表里的最大结束时间
     * @return
     */
    String findMaxObtime();
}
