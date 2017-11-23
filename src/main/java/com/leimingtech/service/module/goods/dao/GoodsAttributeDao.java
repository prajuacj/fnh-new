package com.leimingtech.service.module.goods.dao;

import java.util.List;

import com.leimingtech.core.entity.GoodsAttribute;

/**
 * Created by ss on 2014/10/15.
 */
public interface GoodsAttributeDao {


    List<GoodsAttribute> findListByType(String typeId);
    
    List<GoodsAttribute> findByType(String typeId);
    
    void save(GoodsAttribute goodsAttribute);

    void update(GoodsAttribute goodsAttribute);

    void delete(String id);

    GoodsAttribute findById(String id);

    List<GoodsAttribute> findList(GoodsAttribute goodsAttribute);

    void batchSave(List<GoodsAttribute> list);

    void deleteBatch(String id);

    void deleteByType(String id);

    void deleteBatchByType(String ids);

}
