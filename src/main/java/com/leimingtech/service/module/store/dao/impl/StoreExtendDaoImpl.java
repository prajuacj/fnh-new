package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.entity.base.StoreExtend;
import com.leimingtech.service.module.store.dao.StoreExtendDao;
import com.leimingtech.service.module.store.dao.mapper.StoreExtendMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author llf
 * @Package com.leimingtech.service.module.store.dao.impl
 * @Description:
 * @date 2014/12/11 17:02
 */
@Repository
public class StoreExtendDaoImpl implements StoreExtendDao {

	@Resource
	private StoreExtendMapper storeExtendMapper;

	/**
	 * 保存店铺物流表
	 * 
	 * @param storeExtend
	 */
	public void saveStoreExtend(StoreExtend storeExtend) {
		storeExtendMapper.saveStoreExtend(storeExtend);
	}

	/**
	 * 修改店铺物流表
	 * 
	 * @param storeExtend
	 */
	public void updateStoreExtend(StoreExtend storeExtend) {
		storeExtendMapper.updateStoreExtend(storeExtend);
	}

	/**
	 * 删除店铺物流表
	 * 
	 * @param storeId
	 */
	public void deleteStoreExtend(String storeId) {
		storeExtendMapper.deleteStoreExtend(storeId);
	}

	/**
	 * 通过id查询店铺物流表
	 * 
	 * @param storeId
	 * @return
	 */
	public StoreExtend findByStoreId(String storeId) {
		return storeExtendMapper.findByStoreId(storeId);
	}
}
