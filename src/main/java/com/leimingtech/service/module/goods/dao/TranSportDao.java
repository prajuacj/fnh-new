package com.leimingtech.service.module.goods.dao;

import com.leimingtech.core.entity.Transport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranSportDao {

	public List<Transport> queryStoreList(String storeId);
    
    
}
