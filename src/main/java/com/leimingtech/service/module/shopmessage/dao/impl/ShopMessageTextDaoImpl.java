package com.leimingtech.service.module.shopmessage.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.shopmessage.dao.ShopMessageTextDao;
import com.leimingtech.core.entity.base.ShopMessageText;
import com.leimingtech.service.module.shopmessage.dao.mapper.ShopMessageTextMapper;

/**
 * 站内信daoImpl
 *
 * @author gyh
 * @version 2016-03-16
 */
@Repository
public class ShopMessageTextDaoImpl implements ShopMessageTextDao {
	
	/** 站内信mapper接口*/
	@Resource
	private ShopMessageTextMapper shopMessageTextMapper;
	
	/**
	 * 查询分页站内信数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopMessageText> findShopMessageTextPagerList(Pager pager){
		return shopMessageTextMapper.findShopMessageTextPagerList(pager);
	}

	/**
	 * 通过textId获取单条站内信数据
	 * 
	 * @param textId
	 * @return
	 */
	@Override 
	public ShopMessageText findShopMessageTextByTextId(String textId){
		return shopMessageTextMapper.findShopMessageTextByTextId(textId);
	}

	/**
	 * 通过textId删除站内信数据
	 * 
	 * @param textId
	 */
	@Override
	public void deleteShopMessageTextByTextId(String textId){
		shopMessageTextMapper.deleteShopMessageTextByTextId(textId);
	}

	/**
	 * 修改站内信数据
	 * 
	 * @param shopMessageText
	 */
	@Override
	public void updateShopMessageText(ShopMessageText shopMessageText){
		shopMessageTextMapper.updateShopMessageText(shopMessageText);
	}
	/**
	 * 保存站内信数据
	 * 
	 * @param shopMessageText
	 */
	@Override
	public void saveShopMessageText(ShopMessageText shopMessageText){
		shopMessageText.setTextId(IdGen.uuid());
		shopMessageTextMapper.saveShopMessageText(shopMessageText);
	}
	/**
	 * 获取所有站内信数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopMessageText> findShopMessageTextAllList(){
		return shopMessageTextMapper.findShopMessageTextAllList();
	}
	
}