package com.leimingtech.service.module.shopmessage.dao;

import java.util.List;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopMessagezn;

/**
 * 站内信DAO接口
 *
 * @author gyh
 * @version 2016-03-16
 */
public interface ShopMessageznDao {

	/**
	 * 查询分页站内信数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	List<ShopMessagezn> findShopMessageznPagerList(Pager pager);

	/**
	 * 通过msId获取单条站内信数据
	 * 
	 * @param msId
	 * @return
	 */
	ShopMessagezn findShopMessageznByMsId(String msId);

	/**
	 * 通过msId删除站内信数据
	 * 
	 * @param msId
	 */
	void deleteShopMessageznByMsId(String msId);
	
	/**
	 * 通过Message_id删除站内信数据
	 * 
	 * @param message_id
	 */
	void deleteShopMessageznByMessage_id(String message_id);

	/**
	 * 修改站内信数据
	 * 
	 * @param shopMessagezn
	 */
	void updateShopMessagezn(ShopMessagezn shopMessagezn);

	/**
	 * 保存站内信数据
	 * 
	 * @param shopMessagezn
	 */
	void saveShopMessagezn(ShopMessagezn shopMessagezn);

	/**
	 * 获取所有站内信数据
	 * 
	 * @return
	 */
	List<ShopMessagezn> findShopMessageznAllList();
	/**
	 * 查询分页站内信数据
	 *
	 * @param pager 分页对象
	 * @return
	 */
	List<ShopMessagezn> findShopMessageznPagerList2(Pager pager);
	/**
	 *
	 * 查看相应的条数
	 */
	Integer findcount(ShopMessagezn shopMessagezn);
}