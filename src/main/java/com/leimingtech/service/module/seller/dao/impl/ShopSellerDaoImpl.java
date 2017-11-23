package com.leimingtech.service.module.seller.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.ShopSeller;
import com.leimingtech.service.module.seller.dao.ShopSellerDao;
import com.leimingtech.service.module.seller.dao.mapper.ShopSellerMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺用户列表daoImpl
 *
 * @author yangxp
 * @version 2015-12-10
 */
@Repository
public class ShopSellerDaoImpl implements ShopSellerDao {
	
	/** 店铺用户列表mapper接口*/
	@Resource
	private ShopSellerMapper shopSellerMapper;
	
	/**
	 * 查询分页店铺用户列表数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopSeller> findShopSellerPagerList(Pager pager){
		return shopSellerMapper.findShopSellerPagerList(pager);
	}

	/**
	 * 通过sellerId获取单条店铺用户列表数据
	 * 
	 * @param sellerId
	 * @return
	 */
	@Override 
	public ShopSeller findShopSellerBySellerId(String sellerId){
		return shopSellerMapper.findShopSellerBySellerId(sellerId);
	}

	/**
	 * 通过sellerId删除店铺用户列表数据
	 * 
	 * @param sellerId
	 */
	@Override
	public void deleteShopSellerBySellerId(String sellerId){
		shopSellerMapper.deleteShopSellerBySellerId(sellerId);
	}

	/**
	 * 修改店铺用户列表数据
	 * 
	 * @param shopSeller
	 */
	@Override
	public void updateShopSeller(ShopSeller shopSeller){
		shopSellerMapper.updateShopSeller(shopSeller);
	}
	/**
	 * 保存店铺用户列表数据
	 * 
	 * @param shopSeller
	 */
	@Override
	public void saveShopSeller(ShopSeller shopSeller){
		shopSeller.setSellerId(IdGen.uuid());
		shopSellerMapper.saveShopSeller(shopSeller);
	}
	/**
	 * 获取所有店铺用户列表数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopSeller> findShopSellerAllList(){
		return shopSellerMapper.findShopSellerAllList();
	}
	
	/**
	 * 获取店铺的会员列表
	 * @param storeId
	 * @return
	 */
	public List<ShopSeller> findSellerListByStoreId(String storeId){
		return shopSellerMapper.findSellerListByStoreId(storeId);
	}
	
	/**
	 * 通过账户名获取会员信息
	 * @param sellerName
	 * @return
	 */
	public ShopSeller findShopSellerBySellerName(String sellerName){
		return shopSellerMapper.findShopSellerBySellerName(sellerName);
	}
	
	/**
	 * 通过memberId获取店铺账户信息
	 * @param memberId
	 * @return
	 */
	public ShopSeller findShopSellerByMemberId(String memberId){
		return shopSellerMapper.findShopSellerByMemberId(memberId);
	}
}