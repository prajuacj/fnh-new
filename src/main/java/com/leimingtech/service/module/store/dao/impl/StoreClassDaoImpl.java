package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.entity.Classs;
import com.leimingtech.service.module.store.dao.StoreClassDao;
import com.leimingtech.service.module.store.dao.mapper.StoreClassMapper;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by rabook on 2015/3/29.
 */
@Repository
public class StoreClassDaoImpl implements StoreClassDao {

    @Resource
    private StoreClassMapper storeClassMapper;

    @Override
    public List<Classs> queryParentClassList() {
        return storeClassMapper.queryParentClassList();
    }

    @Override
    public List<Classs> queryChildrenClassList(String id) {
        return storeClassMapper.queryChildrenClassList(id);
    }
}
