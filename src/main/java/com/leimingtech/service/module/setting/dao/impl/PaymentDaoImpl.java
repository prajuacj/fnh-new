package com.leimingtech.service.module.setting.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Payment;
import com.leimingtech.service.module.setting.dao.PaymentDao;
import com.leimingtech.service.module.setting.dao.mapper.PaymentMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ss on 2014/10/10.
 */
@Repository
public class PaymentDaoImpl implements PaymentDao {

    @Resource
    private PaymentMapper paymentMapper;
    

	@Override
	public List<Payment> queryAll() {
		// TODO Auto-generated method stub
		return paymentMapper.queryAll();
	}

	@Override
	public void save(Payment payment) {
		payment.setPaymentId(IdGen.uuid());
		paymentMapper.save(payment);
	}

	@Override
	public void update(Payment payment) {
		// TODO Auto-generated method stub
		paymentMapper.update(payment);
	}

	@Override
	public Payment findById(String id) {
		return paymentMapper.findById(id);
	}

	@Override
	public void delete(String paymentId) {
		paymentMapper.delete(paymentId);
	}

    /**
     * 查询支付名称
     *
     * @param code
     * @return
     */
    @Override
    public String queryByCode(String code) {
        return paymentMapper.queryByCode(code);
    }
    
    @Override
    public Payment selectById(String id) {
    	// TODO Auto-generated method stub
    	return paymentMapper.selectById(id);
    }
	
    @Override
    public Payment selectByCode(String code) {
    	// TODO Auto-generated method stub
    	return paymentMapper.selectByCode(code);
    }

	@Override
	public List<Payment> querybystatelist(String paymentstate) {
		return paymentMapper.querybystatelist(paymentstate);
	}
}
