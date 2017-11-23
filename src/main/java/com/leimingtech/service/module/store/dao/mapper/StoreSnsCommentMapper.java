package com.leimingtech.service.module.store.dao.mapper;

import com.leimingtech.core.entity.base.StoreSnsComment;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@SqlMapper
public interface StoreSnsCommentMapper {
	int countComment(Pager pager);
	
	List<StoreSnsComment> queryCommentList(Pager pager);
	
	StoreSnsComment findLogById(@Param("id") String id);

    void delete(@Param("id") String id);

    int updateStateById(@Param("id") String id, @Param("state") Integer state);
}