package com.leimingtech.service.module.store.dao;


import com.leimingtech.core.entity.base.StoreSnsComment;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 
 */
public interface StoreSnsCommentDao {
	int countComment(Pager pager) ;
	List<StoreSnsComment> queryCommentList(Pager pager);
	void delete(String id) ;
	StoreSnsComment findLogById(String id);
    void updateStateById(String id, Integer state);
}
