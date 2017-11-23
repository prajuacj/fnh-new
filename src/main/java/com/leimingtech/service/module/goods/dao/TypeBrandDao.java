package com.leimingtech.service.module.goods.dao;

import com.leimingtech.core.entity.base.TypeBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ss on 2014/10/15.
 */
public interface TypeBrandDao {

	List<TypeBrand> findListByType(@Param("typeId") String typeId);
	
	/**
	 * 将List<TypeBrand> 插入表
	 * @param list 
	 */
    void batchSave(List<TypeBrand> list);
    
    /**
     * 根据id删除
     * @param typeId
     */
    void delete(String typeId);
}
