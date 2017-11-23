package com.leimingtech.service.module.shopappserver.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.module.shopappserver.dao.ShopServerDao;
import com.leimingtech.core.entity.base.ShopServer;
import com.leimingtech.service.module.shopappserver.dao.mapper.ShopServerMapper;

/**
 * 网站静态服务daoImpl
 *
 * @author luohm
 * @version 2015-12-24
 */
@Repository
public class ShopServerDaoImpl implements ShopServerDao {
	
	/** 网站静态服务mapper接口*/
	@Resource
	private ShopServerMapper shopServerMapper;
	
	/**
	 * 查询分页网站静态服务数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopServer> findShopServerPagerList(Pager pager){
		return shopServerMapper.findShopServerPagerList(pager);
	}

	/**
	 * 通过id获取单条网站静态服务数据
	 * 
	 * @param id
	 * @return
	 */
	@Override 
	public ShopServer findShopServerById(String id){
		return shopServerMapper.findShopServerById(id);
	}

	/**
	 * 通过id删除网站静态服务数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopServerById(String id){
		shopServerMapper.deleteShopServerById(id);
	}

	/**
	 * 修改网站静态服务数据
	 * 
	 * @param shopServer
	 */
	@Override
	public void updateShopServer(ShopServer shopServer){
		shopServerMapper.updateShopServer(shopServer);
	}
	/**
	 * 保存网站静态服务数据
	 * 
	 * @param shopServer
	 */
	@Override
	public void saveShopServer(ShopServer shopServer){
		shopServerMapper.saveShopServer(shopServer);
	}
	/**
	 * 获取所有网站静态服务数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopServer> findShopServerAllList(){
		return shopServerMapper.findShopServerAllList();
	}
	
	/**
	 * 根据类型获取所有网站静态服务数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopServer> findShopServerByType(String type) {
		return shopServerMapper.findShopServerByType(type);
	}
	
}