package com.leimingtech.service.module.shopappserver.dao;

import com.leimingtech.core.entity.base.ShopServer;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 网站静态服务DAO接口
 *
 * @author luohm
 * @version 2015-12-24
 */
public interface ShopServerDao {

	/**
	 * 查询分页网站静态服务数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	List<ShopServer> findShopServerPagerList(Pager pager);

	/**
	 * 通过id获取单条网站静态服务数据
	 * 
	 * @param id
	 * @return
	 */
	ShopServer findShopServerById(String id);

	/**
	 * 通过id删除网站静态服务数据
	 * 
	 * @param id
	 */
	void deleteShopServerById(String id);

	/**
	 * 修改网站静态服务数据
	 * 
	 * @param shopServer
	 */
	void updateShopServer(ShopServer shopServer);

	/**
	 * 保存网站静态服务数据
	 * 
	 * @param shopServer
	 */
	void saveShopServer(ShopServer shopServer);

	/**
	 * 获取所有网站静态服务数据
	 * 
	 * @return
	 */
	List<ShopServer> findShopServerAllList();
	
	/**
	 * 根据类型获取所有网站静态服务数据
	 * 
	 * @return
	 */
	List<ShopServer> findShopServerByType(String type);
	
}