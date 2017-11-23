package com.leimingtech.service.module.shopmessage.dao;

import java.util.List;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopMessageText;

/**
 * 站内信DAO接口
 *
 * @author gyh
 * @version 2016-03-16
 */
public interface ShopMessageTextDao {

	/**
	 * 查询分页站内信数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	List<ShopMessageText> findShopMessageTextPagerList(Pager pager);

	/**
	 * 通过textId获取单条站内信数据
	 * 
	 * @param textId
	 * @return
	 */
	ShopMessageText findShopMessageTextByTextId(String textId);

	/**
	 * 通过textId删除站内信数据
	 * 
	 * @param textId
	 */
	void deleteShopMessageTextByTextId(String textId);

	/**
	 * 修改站内信数据
	 * 
	 * @param shopMessageText
	 */
	void updateShopMessageText(ShopMessageText shopMessageText);

	/**
	 * 保存站内信数据
	 * 
	 * @param shopMessageText
	 */
	void saveShopMessageText(ShopMessageText shopMessageText);

	/**
	 * 获取所有站内信数据
	 * 
	 * @return
	 */
	List<ShopMessageText> findShopMessageTextAllList();
	
}