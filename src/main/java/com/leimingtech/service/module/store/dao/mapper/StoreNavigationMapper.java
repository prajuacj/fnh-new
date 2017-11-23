package com.leimingtech.service.module.store.dao.mapper;

import com.leimingtech.core.entity.base.StoreNavigation;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@SqlMapper
public interface StoreNavigationMapper {

  List<StoreNavigation> findAll(@Param("id") String id);
   
}