package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.ShopBarterLog;
import com.leimingtech.service.module.trade.dao.ShopBarterLogDao;
import com.leimingtech.service.module.trade.service.ShopBarterLogService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 换货处理记录ServiceImpl
 *
 * @author admin
 * @version 2015-12-22
 */
@Service
public class ShopBarterLogServiceImpl implements ShopBarterLogService {

	/** 换货处理记录DAO接口*/
	@Resource
	private ShopBarterLogDao shopBarterLogDao;
	
	/**
	 * 查询分页换货处理记录数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public Pager findShopBarterLogPagerList(Pager pager){
		List<ShopBarterLog> list = shopBarterLogDao.findShopBarterLogPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过id获取单条换货处理记录数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopBarterLog findShopBarterLogById(String id){
		return shopBarterLogDao.findShopBarterLogById(id);
	}

	/**
	 * 通过id删除换货处理记录数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopBarterLogById(String id){
		shopBarterLogDao.deleteShopBarterLogById(id);
	}

	/**
	 * 修改换货处理记录数据
	 * 
	 * @param shopBarterLog
	 */
	@Override
	public void updateShopBarterLog(ShopBarterLog shopBarterLog){
		shopBarterLogDao.updateShopBarterLog(shopBarterLog);
	}
	/**
	 * 保存换货处理记录数据
	 * 
	 * @param shopBarterLog
	 */
	@Override
	public void saveShopBarterLog(ShopBarterLog shopBarterLog){
		shopBarterLogDao.saveShopBarterLog(shopBarterLog);
	}
	/**
	 * 获取所有换货处理记录数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopBarterLog> findShopBarterLogAllList(){
		return shopBarterLogDao.findShopBarterLogAllList();
	}
    
	/**
	 * 通过换货id获取单条换货处理记录数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<ShopBarterLog> findListByBatterId(String id) {
		return shopBarterLogDao.findListByBatterId(id);
	}
	
}