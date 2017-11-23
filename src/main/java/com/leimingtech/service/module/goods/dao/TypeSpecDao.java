package com.leimingtech.service.module.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.TypeSpec;


/**
 * Created by ss on 2014/10/15.
 */
public interface TypeSpecDao {
	/**
	 * 将List<TypeSpec>插入表
	 * @param list
	 */
    void batchSave(List<TypeSpec> list);
    
    /**
     * 根据id删除
     * @param typeId
     */
    void delete(@Param("typeId") String typeId);
    
    /**
     * 根据分类id查询所有分类规格关联实体
     * @param typeId
     * @return
     */
    List<TypeSpec> findListByType(String typeId);

    /**
	 * 通过规格id获取类型总数
	 * 
	 * @param specId
	 * @return
	 */
	int findTypeCountBySpec(String specId);
}
