package com.leimingtech.service.module.goods.dao.impl;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.GoodsAttributeValue;
import com.leimingtech.service.module.goods.dao.AttributeValueDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsAttributeValueMapper;

@Repository
public class AttributeValueDaoImpl implements AttributeValueDao {

	@Resource
    private GoodsAttributeValueMapper attrMapper;
	
	@Override
	public void save(GoodsAttributeValue attrValue) {
		attrValue.setAttrValueId(IdGen.uuid());
		attrMapper.save(attrValue);
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

	@Override
	public void deleteById(String attrId) {
		attrMapper.deleteById(attrId);
	}

	@Override
	public void update(GoodsAttributeValue attributeValue) {
		attrMapper.update(attributeValue);
	}
	
	/**
     * 根据属性id删除属性值
     * @param attrId
     */
    public void deleteByAttrId(String attrId){
    	attrMapper.deleteByAttrId(attrId);
    }

}
