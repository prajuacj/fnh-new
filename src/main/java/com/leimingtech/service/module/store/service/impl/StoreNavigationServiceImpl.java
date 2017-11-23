package com.leimingtech.service.module.store.service.impl;

import com.leimingtech.core.entity.base.StoreNavigation;
import com.leimingtech.service.module.store.dao.StoreNavigationDao;
import com.leimingtech.service.module.store.service.StoreNavigationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreNavigationServiceImpl implements StoreNavigationService {

    @Resource
    private StoreNavigationDao storeNavigationDao;

    public List<StoreNavigation> findAll(String id) {
        return storeNavigationDao.findAll(id);
    }

}