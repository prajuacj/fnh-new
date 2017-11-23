package com.leimingtech.service.module.sellergroup.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.ShopSellerGroup;
import com.leimingtech.service.module.sellergroup.dao.ShopSellerGroupDao;
import com.leimingtech.service.module.sellergroup.dao.mapper.ShopSellerGroupMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺角色(组)管理daoImpl
 *
 * @author admin
 * @version 2015-12-10
 */
@Repository
public class ShopSellerGroupDaoImpl implements ShopSellerGroupDao {
	
	/** 店铺角色(组)管理mapper接口*/
	@Resource
	private ShopSellerGroupMapper shopSellerGroupMapper;
	
	/**
	 * 查询分页店铺角色(组)管理数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopSellerGroup> findShopSellerGroupPagerList(Pager pager){
		return shopSellerGroupMapper.findShopSellerGroupPagerList(pager);
	}

	/**
	 * 通过groupId获取单条店铺角色(组)管理数据
	 * 
	 * @param groupId
	 * @return
	 */
	@Override 
	public ShopSellerGroup findShopSellerGroupByGroupId(String groupId){
		return shopSellerGroupMapper.findShopSellerGroupByGroupId(groupId);
	}

	/**
	 * 通过groupId删除店铺角色(组)管理数据
	 * 
	 * @param groupId
	 */
	@Override
	public void deleteShopSellerGroupByGroupId(String groupId){
		shopSellerGroupMapper.deleteShopSellerGroupByGroupId(groupId);
	}

	/**
	 * 修改店铺角色(组)管理数据
	 * 
	 * @param shopSellerGroup
	 */
	@Override
	public void updateShopSellerGroup(ShopSellerGroup shopSellerGroup){
		shopSellerGroupMapper.updateShopSellerGroup(shopSellerGroup);
	}
	/**
	 * 保存店铺角色(组)管理数据
	 * 
	 * @param shopSellerGroup
	 */
	@Override
	public void saveShopSellerGroup(ShopSellerGroup shopSellerGroup){
		shopSellerGroup.setGroupId(IdGen.uuid());
		shopSellerGroupMapper.saveShopSellerGroup(shopSellerGroup);
	}
	/**
	 * 获取所有店铺角色(组)管理数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopSellerGroup> findShopSellerGroupAllList(){
		return shopSellerGroupMapper.findShopSellerGroupAllList();
	}
	
	/**
	 * 通过店铺id，获取角色菜单
	 * @param storeId
	 * @return
	 */
	public List<ShopSellerGroup> findShopSellerGroupListByStoreId(String storeId){
		return shopSellerGroupMapper.findShopSellerGroupListByStoreId(storeId);
	}
}