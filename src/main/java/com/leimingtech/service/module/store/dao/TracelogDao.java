package com.leimingtech.service.module.store.dao;


import com.leimingtech.core.entity.base.TraceLog;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 
 */
public interface TracelogDao {
	int countTraceLog(Pager pager) ;
	List<TraceLog> queryTraceLogList(Pager pager);
	void delete(String id) ;
	TraceLog findLogById(String id);
    void updateStateById(String id, Integer state);
}
