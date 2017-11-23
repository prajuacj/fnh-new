package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.entity.base.StoreNavigation;
import com.leimingtech.service.module.store.dao.StoreNavigationDao;
import com.leimingtech.service.module.store.dao.mapper.StoreNavigationMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class StoreNavigationDaoImpl implements StoreNavigationDao {
	
   @Resource
   private StoreNavigationMapper storeNavigationMapper;

   @Override
   public List<StoreNavigation> findAll(String id){
	  return storeNavigationMapper.findAll(id);
   }
   
}