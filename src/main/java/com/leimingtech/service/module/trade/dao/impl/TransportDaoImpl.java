package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.Transport;
import com.leimingtech.service.module.trade.dao.TransportDao;
import com.leimingtech.service.module.trade.dao.mapper.TransportMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 
 * @项目名称：leimingtech-seller
 * @类名称：TransportDaoImpl
 * @类描述： 运费模板
 * @创建人：shining
 * @创建时间：2014年12月7日 下午10:48:12
 * @修改人：shining
 * @修改时间：2014年12月7日 下午10:48:12
 * @修改备注：
 * @version
 * 
 */
@Repository
public class TransportDaoImpl implements TransportDao {
	@Resource
	private TransportMapper transportMapper;

	/**
	 * 查询列表
	 */
	@Override
	public List<Transport> queryList(Pager pager) {
		return transportMapper.queryList(pager);
	}

	@Override
	public Transport findById(String id) {
		return transportMapper.findById(id);
	}

	@Override
	public void save(Transport transport) {
		transport.setId(IdGen.uuid());
		transportMapper.save(transport);
	}

	@Override
	public void delete(String id) {
		transportMapper.delete(id);
	}

	@Override
	public void update(Transport transport) {
		transportMapper.update(transport);
	}
	
	/**
	 * 通过店铺id 获得当前的默认运费模板
	 * @param storeId
	 * @return
	 */
	public Transport getDefTransportByStoreId(String storeId){
		return transportMapper.getDefTransportByStoreId(storeId);
	}
}
