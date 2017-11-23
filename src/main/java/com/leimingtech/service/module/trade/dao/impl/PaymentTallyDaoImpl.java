package com.leimingtech.service.module.trade.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.PaymentTally;
import com.leimingtech.service.module.trade.dao.PaymentTallyDao;
import com.leimingtech.service.module.trade.dao.mapper.PaymentTallyMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 支付流水表
 *
 * @author liukai
 * @date 2015/12/28
 */
@Repository
public class PaymentTallyDaoImpl implements PaymentTallyDao {

    @Resource
    private PaymentTallyMapper paymentTallyMapper;

    /**
     * 保存支付流水表
     * @param paymentTally
     */
    @Override
    public void savePaymentTally(PaymentTally paymentTally) {
        paymentTally.setId(IdGen.uuid());
        paymentTallyMapper.savePaymentTally(paymentTally);
    }

    /**
     * 修改支付流水表
     * @param paymentTally
     */
    @Override
    public void updatePaymentTally(PaymentTally paymentTally) {
        paymentTallyMapper.updatePaymentTally(paymentTally);
    }

    /**
     * 通过id删除支付流水表
     * @param id
     */
    @Override
    public void deletePaymentTally(String id) {
        paymentTallyMapper.deletePaymentTally(id);
    }

    /**
     * 通过id查询支付流水表
     * @param id 支付流水表id
     * @return
     */
    @Override
    public PaymentTally findPaymentTallyById(String id) {
        return paymentTallyMapper.findPaymentTallyById(id);
    }
    
    /**
     * 通过id,支付单号查询
     * @param paymentTally
     * @return
     */
	@Override
	public PaymentTally findPaymentTally(PaymentTally paymentTally) {
		return paymentTallyMapper.findPaymentTally(paymentTally);
	}

    /**
     * 分页查询支付流水信息
     * @param pager
     * @return
     */
    @Override
    public List<PaymentTally> findPagerList(Pager pager) { return paymentTallyMapper.findPagerList(pager); }

}
