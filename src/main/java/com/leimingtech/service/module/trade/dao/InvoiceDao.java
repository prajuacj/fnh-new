package com.leimingtech.service.module.trade.dao;

import java.util.List;

import com.leimingtech.core.entity.base.Invoice;
import com.leimingtech.service.utils.page.Pager;

/**
 * 发票dao
 * @author cgl
 * 2015年08月14日16:14:55
 */
public interface InvoiceDao {
	
	/**
	 * 新增发票
	 */
	void save(Invoice invoice);
	
	/**
	 * 删除发票
	 */
	void delete(String invId);
	
	/**
	 * 根据条件删除
	 */
	void deleteByCondition(Invoice invoice);
	
	/**
	 * 修改
	 */
	void update(Invoice invoice);
	
	/**
	 * 通过主键id查找
	 */
	Invoice findByInvId(String invId);
	
	/**
	 * 总条数
	 */
	int findPageCount(Invoice invoice);
	
	/**
	 * 查询列表页
	 */
	List<Invoice> findPageList(Pager pager);
	
	/**
	 * 查询列表页不分页
	 * @return
	 */
	List<Invoice> findInvoiceList(Invoice invoice);
	
	/**
	 * 修改当前用户下的发票都为不是默认
	 * @param memeberId
	 */
	void updateDef(String memberId);
}
