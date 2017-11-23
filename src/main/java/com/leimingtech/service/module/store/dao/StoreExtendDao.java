package com.leimingtech.service.module.store.dao;

import com.leimingtech.core.entity.base.StoreExtend;

/**
 * @author llf
 * @Package com.leimingtech.service.module.store.dao
 * @Description:
 * @date 2014/12/11 17:01
 */
public interface StoreExtendDao {

	/**
	 * 保存店铺物流表
	 * @param storeExtend
	 */
	void saveStoreExtend(StoreExtend storeExtend);
	
	/**
	 * 修改店铺物流表
	 * @param storeExtend
	 */
	void updateStoreExtend(StoreExtend storeExtend);
	
	/**
	 * 删除店铺物流表
	 * @param storeId
	 */
	void deleteStoreExtend(String storeId);
	
	/**
	 * 通过id查询店铺物流表
	 * @param storeId
	 * @return
	 */
	StoreExtend findByStoreId(String storeId);
}
