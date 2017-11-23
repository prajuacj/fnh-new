package com.leimingtech.service.module.log.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.SellerLog;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

@SqlMapper
public interface SellerLogDaoMapper {
	
	//查询总数
	int countSellerLog(SellerLog sellerLog);
		
	//查询日志,并且分页
	List<SellerLog> selectSellerLogByPager(Pager pager);

	//增加日志
	int saveSellerLog(SellerLog sellerLog);
		
	//删除日志
	int deleteSellerLog(String logId);
	
	//修改日志
	int updateSellerLog(SellerLog sellerLog);
		
	//查询所有日志
	List<SellerLog> selectAllSellerLog();
	
	
}
