package com.leimingtech.service.module.shopappserver.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopServer;

/**
 * 网站静态服务mapper接口
 * 
 * @author luohm
 * @version 2015-12-24
 */
@SqlMapper
public interface ShopServerMapper {

	/**
	 * 查询分页网站静态服务数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<ShopServer> findShopServerPagerList(Pager pager);

	/**
	 * 通过id获取单条网站静态服务数据
	 * 
	 * @param id
	 * @return
	 */
	public ShopServer findShopServerById(String id);

	/**
	 * 通过id删除网站静态服务数据
	 * 
	 * @param id
	 */
	public void deleteShopServerById(String id);

	/**
	 * 修改网站静态服务数据
	 * 
	 * @param shopServer
	 */
	public void updateShopServer(ShopServer shopServer);

	/**
	 * 保存网站静态服务数据
	 * 
	 * @param shopServer
	 */
	public void saveShopServer(ShopServer shopServer);

	/**
	 * 获取所有网站静态服务数据
	 * 
	 * @return
	 */
	public List<ShopServer> findShopServerAllList();
	
	/**
	 * 根据类型获取所有网站静态服务数据
	 * 
	 * @return
	 */
	public List<ShopServer> findShopServerByType(String type);

}