package com.leimingtech.service.module.store.dao;

import com.leimingtech.core.entity.base.StoreBindClass;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 店铺可发布商品类目表DAO接口
 *
 * @author gyh
 * @version 2016-01-05
 */
public interface StoreBindClassDao {

	/**
	 * 查询分页店铺可发布商品类目表数据
	 *
	 * @param pager 分页对象
	 * @return
	 */
	public List<StoreBindClass> findStoreBindClassPagerList(Pager pager);

	/**
	 * 通过bid获取单条店铺可发布商品类目表数据
	 *
	 * @param bid
	 * @return
	 */
	public StoreBindClass findStoreBindClassByBid(int bid);

	/**
	 * 通过bid删除店铺可发布商品类目表数据
	 *
	 * @param bid
	 */
	public void deleteStoreBindClassByBid(int bid);

	/**
	 * 修改店铺可发布商品类目表数据
	 *
	 * @param storeBindClass
	 */
	public void updateStoreBindClass(StoreBindClass storeBindClass);

	/**
	 * 保存店铺可发布商品类目表数据
	 *
	 * @param storeBindClass
	 */
	public void saveStoreBindClass(StoreBindClass storeBindClass);

	/**
	 * 获取所有店铺可发布商品类目表数据
	 *
	 * @return
	 */
	public List<StoreBindClass> findStoreBindClassAllList();

}
