package com.leimingtech.service.module.shopmessage.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.shopmessage.dao.ShopMessageznDao;
import com.leimingtech.core.entity.base.ShopMessagezn;
import com.leimingtech.service.module.shopmessage.dao.mapper.ShopMessageznMapper;

/**
 * 站内信daoImpl
 *
 * @author gyh
 * @version 2016-03-16
 */
@Repository
public class ShopMessageznDaoImpl implements ShopMessageznDao {
	
	/** 站内信mapper接口*/
	@Resource
	private ShopMessageznMapper shopMessageznMapper;
	
	/**
	 * 查询分页站内信数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopMessagezn> findShopMessageznPagerList(Pager pager){
		return shopMessageznMapper.findShopMessageznPagerList(pager);
	}

	/**
	 * 通过msId获取单条站内信数据
	 * 
	 * @param msId
	 * @return
	 */
	@Override 
	public ShopMessagezn findShopMessageznByMsId(String msId){
		return shopMessageznMapper.findShopMessageznByMsId(msId);
	}

	/**
	 * 通过msId删除站内信数据
	 * 
	 * @param msId
	 */
	@Override
	public void deleteShopMessageznByMsId(String msId){
		shopMessageznMapper.deleteShopMessageznByMsId(msId);
	}
	
	/**
	 * 通过Message_id删除站内信数据
	 * 
	 * @param message_id
	 */
	@Override
	public void deleteShopMessageznByMessage_id(String message_id){
		shopMessageznMapper.deleteShopMessageznByMessage_id(message_id);
	}

	/**
	 * 修改站内信数据
	 * 
	 * @param shopMessagezn
	 */
	@Override
	public void updateShopMessagezn(ShopMessagezn shopMessagezn){
		shopMessageznMapper.updateShopMessagezn(shopMessagezn);
	}
	/**
	 * 保存站内信数据
	 * 
	 * @param shopMessagezn
	 */
	@Override
	public void saveShopMessagezn(ShopMessagezn shopMessagezn){
		shopMessagezn.setMsId(IdGen.uuid());
		shopMessageznMapper.saveShopMessagezn(shopMessagezn);
	}
	/**
	 * 获取所有站内信数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopMessagezn> findShopMessageznAllList(){
		return shopMessageznMapper.findShopMessageznAllList();
	}

	/**
	 * 查询分页站内信数据
	 *
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopMessagezn> findShopMessageznPagerList2(Pager pager) {
		return shopMessageznMapper.findShopMessageznPagerList2(pager);
	}

	/**
	 *
	 * 查看相应的条数
	 */
	@Override
	public Integer findcount(ShopMessagezn shopMessagezn) {
		return shopMessageznMapper.findcount(shopMessagezn);
	}

}