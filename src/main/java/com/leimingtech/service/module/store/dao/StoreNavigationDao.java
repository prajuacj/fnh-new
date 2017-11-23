package com.leimingtech.service.module.store.dao;

import com.leimingtech.core.entity.base.StoreNavigation;

import java.util.List;

public interface StoreNavigationDao {

   List<StoreNavigation> findAll(String id);
   
}