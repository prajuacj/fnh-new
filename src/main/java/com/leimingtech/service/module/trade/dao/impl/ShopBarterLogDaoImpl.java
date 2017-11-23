package com.leimingtech.service.module.trade.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.ShopBarterLog;
import com.leimingtech.service.module.trade.dao.ShopBarterLogDao;
import com.leimingtech.service.module.trade.dao.mapper.ShopBarterLogMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 换货处理记录daoImpl
 *
 * @author admin
 * @version 2015-12-22
 */
@Repository
public class ShopBarterLogDaoImpl implements ShopBarterLogDao {
	
	/** 换货处理记录mapper接口*/
	@Resource
	private ShopBarterLogMapper shopBarterLogMapper;
	
	/**
	 * 查询分页换货处理记录数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopBarterLog> findShopBarterLogPagerList(Pager pager){
		return shopBarterLogMapper.findShopBarterLogPagerList(pager);
	}

	/**
	 * 通过id获取单条换货处理记录数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopBarterLog findShopBarterLogById(String id){
		return shopBarterLogMapper.findShopBarterLogById(id);
	}

	/**
	 * 通过id删除换货处理记录数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopBarterLogById(String id){
		shopBarterLogMapper.deleteShopBarterLogById(id);
	}

	/**
	 * 修改换货处理记录数据
	 * 
	 * @param shopBarterLog
	 */
	@Override
	public void updateShopBarterLog(ShopBarterLog shopBarterLog){
		shopBarterLogMapper.updateShopBarterLog(shopBarterLog);
	}
	/**
	 * 保存换货处理记录数据
	 * 
	 * @param shopBarterLog
	 */
	@Override
	public void saveShopBarterLog(ShopBarterLog shopBarterLog){
		shopBarterLog.setId(IdGen.uuid());
		shopBarterLogMapper.saveShopBarterLog(shopBarterLog);
	}
	/**
	 * 获取所有换货处理记录数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopBarterLog> findShopBarterLogAllList(){
		return shopBarterLogMapper.findShopBarterLogAllList();
	}
    
	/**
	 * 通过换货id获取单条换货处理记录数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<ShopBarterLog> findListByBatterId(String id) {
		return shopBarterLogMapper.findListByBatterId(id);
	}
	
}