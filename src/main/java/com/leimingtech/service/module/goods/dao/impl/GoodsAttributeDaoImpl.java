package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import com.leimingtech.core.common.IdGen;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.GoodsAttribute;
import com.leimingtech.service.module.goods.dao.GoodsAttributeDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsAttributeMapper;

/**
 * Created by ss on 2014/10/15.
 */
@Repository
public class GoodsAttributeDaoImpl implements GoodsAttributeDao{

    @Autowired
    private GoodsAttributeMapper attrMapper;
    public List<GoodsAttribute> findListByType(String typeId) {
        return attrMapper.findListByType(typeId);
    }
    public List<GoodsAttribute> findByType(@Param("typeId") String typeId){
    	return attrMapper.findByType(typeId);
    }
    
    @Override
    public void save(GoodsAttribute goodsAttribute) {
        goodsAttribute.setAttrId(IdGen.uuid());
        attrMapper.save(goodsAttribute);
    }

    @Override
    public void update(GoodsAttribute goodsAttribute) {
        attrMapper.update(goodsAttribute);
    }

    @Override
    public void delete(String id) {
        attrMapper.delete(id);
    }

    @Override
    public GoodsAttribute findById(String id) {
        return attrMapper.findById(id);
    }

    @Override
    public List<GoodsAttribute> findList(GoodsAttribute goodsAttribute) {
        return attrMapper.findList(goodsAttribute);
    }

    @Override
    public void batchSave(List<GoodsAttribute> list) {
        for(GoodsAttribute goodsAttribute:list){
            goodsAttribute.setAttrId(IdGen.uuid());
        }
        attrMapper.batchSave(list);
    }

    @Override
    public void deleteBatch(String id) {
        attrMapper.deleteBatch(id);
    }

    @Override
    public void deleteByType(String id) {
        attrMapper.deleteByType(id);
    }

    @Override
    public void deleteBatchByType(String ids) {
        attrMapper.deleteBatchByType(ids);
    }

}
