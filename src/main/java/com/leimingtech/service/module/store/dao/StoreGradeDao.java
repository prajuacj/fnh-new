package com.leimingtech.service.module.store.dao;


import com.leimingtech.core.entity.base.StoreGrade;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 
 */
public interface StoreGradeDao {
	List<StoreGrade> queryStoreGradeList(Pager pager);
	void save(StoreGrade grade);
	void delete(String id);
	StoreGrade queryById(String id);
	void update(StoreGrade grade) ;

    /**
     * 校验查重
     * @param storeGrade
     * @return
     */
	int queryCount(StoreGrade storeGrade);

	/**
	 * 查找店铺等级
	 * @param storeGrade
	 * @return
	 */
	List<StoreGrade> findStoreGradeList(StoreGrade storeGrade);
}
