package com.leimingtech.service.module.sellergroup.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.ShopSellerGroup;
import com.leimingtech.service.module.sellergroup.dao.ShopSellerGroupDao;
import com.leimingtech.service.module.sellergroup.service.ShopSellerGroupService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 店铺角色(组)管理ServiceImpl
 *
 * @author admin
 * @version 2015-12-10
 */
@Service
public class ShopSellerGroupServiceImpl implements ShopSellerGroupService {

	/** 店铺角色(组)管理DAO接口 */
	@Resource
	private ShopSellerGroupDao shopSellerGroupDao;

	/**
	 * 查询分页店铺角色(组)管理数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopSellerGroupPagerList(Pager pager) {
		List<ShopSellerGroup> list = shopSellerGroupDao.findShopSellerGroupPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过groupId获取单条店铺角色(组)管理数据
	 * 
	 * @param groupId
	 * @return
	 */
	@Override
	public ShopSellerGroup findShopSellerGroupByGroupId(String groupId) {
		return shopSellerGroupDao.findShopSellerGroupByGroupId(groupId);
	}

	/**
	 * 通过groupId删除店铺角色(组)管理数据
	 * 
	 * @param groupId
	 */
	@Override
	public void deleteShopSellerGroupByGroupId(String groupId) {
		shopSellerGroupDao.deleteShopSellerGroupByGroupId(groupId);
	}

	/**
	 * 修改店铺角色(组)管理数据
	 * 
	 * @param shopSellerGroup
	 */
	@Override
	public void updateShopSellerGroup(ShopSellerGroup shopSellerGroup) {
		shopSellerGroupDao.updateShopSellerGroup(shopSellerGroup);
	}

	/**
	 * 保存店铺角色(组)管理数据
	 * 
	 * @param shopSellerGroup
	 */
	@Override
	public void saveShopSellerGroup(ShopSellerGroup shopSellerGroup) {
		shopSellerGroupDao.saveShopSellerGroup(shopSellerGroup);
	}

	/**
	 * 获取所有店铺角色(组)管理数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopSellerGroup> findShopSellerGroupAllList() {
		return shopSellerGroupDao.findShopSellerGroupAllList();
	}

	/**
	 * 通过店铺id，获取角色菜单
	 * 
	 * @param storeId
	 * @return
	 */
	public List<ShopSellerGroup> findShopSellerGroupListByStoreId(String storeId) {
		return shopSellerGroupDao.findShopSellerGroupListByStoreId(storeId);
	}
}