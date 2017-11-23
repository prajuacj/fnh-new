package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.TypeBrand;
import com.leimingtech.service.module.goods.dao.TypeBrandDao;
import com.leimingtech.service.module.goods.dao.mapper.TypeBrandMapper;

/**
 * Created by ss on 2014/10/15.
 */
@Repository
public class TypeBrandDaoImpl implements TypeBrandDao{

	@Resource
    private TypeBrandMapper tbMapper;

	@Override
	public List<TypeBrand> findListByType(String typeId) {
		return tbMapper.findListByType(typeId);
	}

	@Override
	public void batchSave(List<TypeBrand> list) {
		tbMapper.batchSave(list);
	}

	@Override
	public void delete(String typeId) {
		tbMapper.delete(typeId);
	}
	
	

}
