package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.TypeSpec;
import com.leimingtech.service.module.goods.dao.TypeSpecDao;
import com.leimingtech.service.module.goods.dao.mapper.TypeSpecMapper;

/**
 * Created by ss on 2014/10/15.
 */
@Repository
public class TypeSpecDaoImpl implements TypeSpecDao{
	@Resource
	private TypeSpecMapper typeSpecMapper;
	
	@Override
	public void batchSave(List<TypeSpec> list) {
		typeSpecMapper.batchSave(list);
	}

	@Override
	public void delete(String typeId) {
		typeSpecMapper.delete(typeId);
	}

	@Override
	public List<TypeSpec> findListByType(String typeId) {
		return typeSpecMapper.findListByType(typeId);
	}

	/**
	 * 通过规格id获取类型总数
	 * 
	 * @param specId
	 * @return
	 */
	@Override
	public int findTypeCountBySpec(String specId) {
		return typeSpecMapper.findTypeCountBySpec(specId);
	}

}
