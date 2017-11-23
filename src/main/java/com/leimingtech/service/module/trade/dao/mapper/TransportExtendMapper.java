package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.TransportExtend;
import com.leimingtech.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface TransportExtendMapper {
	/**
	 * 
	 * @Title: queryByTransportId
	 * @Description: 根据 TransportId查询列表
	 * @param @param id
	 * @param @return 设定文件
	 * @return List<TransportExtend> 返回类型
	 * @throws
	 */
	List<TransportExtend> queryByTransportId(@Param("id") String id);

	void save(TransportExtend transportExtend);
	
	void delete(String transportId);
	
	/**
	 * 根据transportExtend作为查询条件,查询transportExtend
	 * @param transportExtend
	 * @return
	 */
	TransportExtend selectTransportExtendByCondition(TransportExtend transportExtend);
}
