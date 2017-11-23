package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Invoice;
import com.leimingtech.service.module.trade.dao.InvoiceDao;
import com.leimingtech.service.module.trade.dao.mapper.InvoiceMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 发票dao
 * @author cgl
 * 2015年08月14日16:14:55
 */
@Repository
public class InvoiceDaoImpl implements InvoiceDao{

	@Autowired
	private InvoiceMapper invoiceMapper;
	
	@Override
	public void save(Invoice invoice) {
		// TODO Auto-generated method stub
		invoice.setInvId(IdGen.uuid());
		invoiceMapper.save(invoice);
	}

	@Override
	public void delete(String invId) {
		// TODO Auto-generated method stub
		invoiceMapper.delete(invId);
	}
	
	@Override
	public void deleteByCondition(Invoice invoice){
		invoiceMapper.deleteByCondition(invoice);
	}

	@Override
	public void update(Invoice invoice) {
		// TODO Auto-generated method stub
		invoiceMapper.update(invoice);
	}

	@Override
	public Invoice findByInvId(String invId) {
		// TODO Auto-generated method stub
		return invoiceMapper.findByInvId(invId);
	}

	@Override
	public int findPageCount(Invoice invoice) {
		// TODO Auto-generated method stub
		return invoiceMapper.findPageCount(invoice);
	}

	@Override
	public List<Invoice> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return invoiceMapper.findPageList(pager);
	}
	
	/**
	 * 查询列表页不分页
	 * @return
	 */
	@Override
	public List<Invoice> findInvoiceList(Invoice invoice){
		return invoiceMapper.findInvoiceList(invoice);
	}
	
	/**
	 * 修改当前用户下的发票都为不是默认
	 * @param memeberId
	 */
	public void updateDef(String memberId){
		invoiceMapper.updateDef(memberId);
	}
}
