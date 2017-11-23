package com.leimingtech.service.module.mansongquota.dao;

import java.util.List;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopPMansongQuota;

/**
 * 满就送套餐表DAO接口
 *
 * @author admin
 * @version 2015-11-19
 */
public interface ShopPMansongQuotaDao {

	/**
	 * 查询分页满就送套餐表数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	List<ShopPMansongQuota> findShopPMansongQuotaPagerList(Pager pager);

	/**
	 * 通过id获取单条满就送套餐表数据
	 * 
	 * @param id
	 * @return
	 */
	ShopPMansongQuota findShopPMansongQuotaById(String id);

	/**
	 * 通过店铺id获取套餐表的数据
	 * @param storeId
	 * @return
	 */
	ShopPMansongQuota findShopPMansongQuotaByStoreId(String storeId);
	
	/**
	 * 通过id删除满就送套餐表数据
	 * 
	 * @param id
	 */
	void deleteShopPMansongQuotaById(String id);

	/**
	 * 修改满就送套餐表数据
	 * 
	 * @param shopPMansongQuota
	 */
	void updateShopPMansongQuota(ShopPMansongQuota shopPMansongQuota);

	/**
	 * 保存满就送套餐表数据
	 * 
	 * @param shopPMansongQuota
	 */
	void saveShopPMansongQuota(ShopPMansongQuota shopPMansongQuota);

	/**
	 * 获取所有满就送套餐表数据
	 * 
	 * @return
	 */
	List<ShopPMansongQuota> findShopPMansongQuotaAllList();
	
}