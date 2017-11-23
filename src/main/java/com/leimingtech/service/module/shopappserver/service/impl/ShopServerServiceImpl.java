package com.leimingtech.service.module.shopappserver.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.ShopServer;
import com.leimingtech.service.module.shopappserver.dao.ShopServerDao;
import com.leimingtech.service.module.shopappserver.service.ShopServerService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 网站静态服务ServiceImpl
 *
 * @author luohm
 * @version 2015-12-24
 */
@Service
public class ShopServerServiceImpl implements ShopServerService {

	/** 网站静态服务DAO接口 */
	@Resource
	private ShopServerDao shopServerDao;

	/**
	 * 查询分页网站静态服务数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopServerPagerList(Pager pager) {
		List<ShopServer> list = shopServerDao.findShopServerPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过id获取单条网站静态服务数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ShopServer findShopServerById(String id) {
		return shopServerDao.findShopServerById(id);
	}

	/**
	 * 通过id删除网站静态服务数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopServerById(String id) {
		shopServerDao.deleteShopServerById(id);
	}

	/**
	 * 修改网站静态服务数据
	 * 
	 * @param shopServer
	 */
	@Override
	public void updateShopServer(ShopServer shopServer) {
		shopServerDao.updateShopServer(shopServer);
	}

	/**
	 * 保存网站静态服务数据
	 * 
	 * @param shopServer
	 */
	@Override
	public void saveShopServer(ShopServer shopServer) {
		shopServerDao.saveShopServer(shopServer);
	}

	/**
	 * 获取所有网站静态服务数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopServer> findShopServerAllList() {
		return shopServerDao.findShopServerAllList();
	}

	/**
	 * 根据类型获取所有网站静态服务数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopServer> findShopServerByType(String type) {
		return shopServerDao.findShopServerByType(type);
	}
}