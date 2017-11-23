package com.leimingtech.service.module.goods.dao.mapper;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.GoodsAttribute;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ss on 2014/10/15.
 */
@SqlMapper
public interface GoodsAttributeMapper {


    List<GoodsAttribute> findListByType(@Param("typeId") String typeId);
    List<GoodsAttribute> findByType(@Param("typeId") String typeId);

    void save(GoodsAttribute goodsAttribute);

    void update(GoodsAttribute goodsAttribute);

    void delete(@Param("attrId")String id);

    GoodsAttribute findById(@Param("attrId")String id);

    List<GoodsAttribute> findList(GoodsAttribute goodsAttribute);

    void batchSave(List<GoodsAttribute> list);

    void deleteBatch(@Param("typeId") String typeId);

    void deleteByType(@Param("typeId") String id);

    void deleteBatchByType(@Param("ids") String ids);
    
    void findDetailListByType(String typeId);
}
