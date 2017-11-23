package com.leimingtech.service.module.goods.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.GoodsAttributeValue;
import com.leimingtech.core.orm.mybatis.SqlMapper;


/**
 * Created by ss on 2014/11/5.
 */
@SqlMapper
public interface GoodsAttributeValueMapper {

    void save(GoodsAttributeValue attrValue);

    void deleteBatch(@Param("typeId")String id);

    void deleteByType(@Param("typeId")String typeId);

    void deleteBatchByType(@Param("ids") String ids);
    
    /**
     * 新增属性值，有排序
     * @param attrValue
     */
    void saveAttrVal(GoodsAttributeValue attrValue);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(@Param("id") String id);
    
    /**
     * 根据属性id删除属性值
     * @param attrId
     */
    void deleteByAttrId(String attrId);

    /**
     * 修改
     * @param attributeValue
     */
    void update(GoodsAttributeValue attributeValue);
    
    /**
     * 根据属性id查询属性值
     * @param attrId
     * @return
     */
    List<GoodsAttributeValue> findListByAttr(Integer attrId);
}
