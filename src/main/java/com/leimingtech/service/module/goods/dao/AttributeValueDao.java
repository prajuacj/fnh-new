package com.leimingtech.service.module.goods.dao;

import com.leimingtech.core.entity.base.GoodsAttributeValue;

public interface AttributeValueDao {
	void save(GoodsAttributeValue attrValue);

    void deleteBatch(String id);

    void deleteByType(String id);

    void deleteBatchByType(String ids);

    /**
     * 通过id删除
     * @param attrId
     */
    void deleteById(String attrId);

    /**
     * 修改
     * @param attributeValue
     */
    void update(GoodsAttributeValue attributeValue);
    
    /**
     * 根据属性id删除属性值
     * @param attrId
     */
    void deleteByAttrId(String attrId);
}
