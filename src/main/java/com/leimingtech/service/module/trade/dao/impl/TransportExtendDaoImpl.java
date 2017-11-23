package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.TransportExtend;
import com.leimingtech.service.module.trade.dao.TransportExtendDao;
import com.leimingtech.service.module.trade.dao.mapper.TransportExtendMapper;

@Repository
public class TransportExtendDaoImpl implements TransportExtendDao {
	@Resource
	private TransportExtendMapper transportExtendMapper;

	/**
	 * 根据 TransportId查询列表
	 */
	@Override
	public List<TransportExtend> queryByTransportId(String id) {
		return transportExtendMapper.queryByTransportId(id);
	}

	@Override
	public void save(TransportExtend transportExtend) {
		transportExtend.setId(IdGen.uuid());
		transportExtendMapper.save(transportExtend);
	}

	@Override
	public void delete(String transportId) {
		// TODO Auto-generated method stub
		transportExtendMapper.delete(transportId);
	}

	/**
	 * 根据transportExtend作为查询条件,查询transportExtend
	 * @param transportExtend
	 * @return
	 */
	@Override
	public TransportExtend selectTransportExtendByCondition(TransportExtend transportExtend) {
		// TODO Auto-generated method stub
		return transportExtendMapper.selectTransportExtendByCondition(transportExtend);
	}
}
