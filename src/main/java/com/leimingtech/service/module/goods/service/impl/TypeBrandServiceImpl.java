package com.leimingtech.service.module.goods.service.impl;

import com.leimingtech.core.entity.base.TypeBrand;
import com.leimingtech.service.module.goods.dao.TypeBrandDao;
import com.leimingtech.service.module.goods.service.TypeBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeBrandServiceImpl implements TypeBrandService{

    @Resource
    private TypeBrandDao typeBrandDao;

	@Override
	public List<TypeBrand> findListByType(String typeId) {
		
		List<TypeBrand> brandlist = typeBrandDao.findListByType(typeId);
		if(brandlist!=null && brandlist.size()>0){
			return brandlist;
		}else{
			return null;
		}
	}

	@Override
	public void delete(String typeId) {
		if(typeId != null && !"".equals(typeId)){
			typeBrandDao.delete(typeId);
		}
	}
}
