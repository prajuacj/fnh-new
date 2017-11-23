package com.leimingtech.service.module.trade.dao.mapper;

/**
 * 支付流水表
 *
 * @author liukai
 * @date 2015/12/28
 */

import com.leimingtech.core.entity.base.PaymentTally;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

@SqlMapper
public interface PaymentTallyMapper {

    /**
     * 保存支付流水表
     * @param paymentTally
     */
    void savePaymentTally(PaymentTally paymentTally);

    /**
     * 修改支付流水表
     * @param paymentTally
     */
    void updatePaymentTally(PaymentTally paymentTally);

    /**
     * 通过id删除支付流水表
     * @param id
     */
    void deletePaymentTally(String id);

    /**
     * 通过id查询支付流水表
     * @param id 支付流水表id
     * @return
     */
    PaymentTally findPaymentTallyById(String id);
    /**
     * 支付单号查询
     * @param paymentTally
     * @return
     */
    PaymentTally findPaymentTally(PaymentTally paymentTally);

    /**
     * 分页查询支付流水信息
     * @param pager
     * @return
     */
    List<PaymentTally> findPagerList(Pager pager);
}
