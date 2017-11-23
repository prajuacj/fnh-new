package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.entity.base.StoreBindClass;
import com.leimingtech.service.module.store.dao.StoreBindClassDao;
import com.leimingtech.service.module.store.dao.mapper.StoreBindClassMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺可发布商品类目表daoImpl
 *
 * @author gyh
 * @version 2016-01-05
 */
@Repository
public class StoreBindClassDaoImpl implements StoreBindClassDao {

	/** 店铺可发布商品类目表mapper接口*/
	@Resource
	private StoreBindClassMapper storeBindClassMapper;

	/**
	 * 查询分页店铺可发布商品类目表数据
	 *
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<StoreBindClass> findStoreBindClassPagerList(Pager pager){
		return storeBindClassMapper.findStoreBindClassPagerList(pager);
	}

	/**
	 * 通过bid获取单条店铺可发布商品类目表数据
	 *
	 * @param bid
	 * @return
	 */
	@Override
	public StoreBindClass findStoreBindClassByBid(int bid){
		return storeBindClassMapper.findStoreBindClassByBid(bid);
	}

	/**
	 * 通过bid删除店铺可发布商品类目表数据
	 *
	 * @param bid
	 */
	@Override
	public void deleteStoreBindClassByBid(int bid){
		storeBindClassMapper.deleteStoreBindClassByBid(bid);
	}

	/**
	 * 修改店铺可发布商品类目表数据
	 *
	 * @param storeBindClass
	 */
	@Override
	public void updateStoreBindClass(StoreBindClass storeBindClass){
		storeBindClassMapper.updateStoreBindClass(storeBindClass);
	}
	/**
	 * 保存店铺可发布商品类目表数据
	 *
	 * @param storeBindClass
	 */
	@Override
	public void saveStoreBindClass(StoreBindClass storeBindClass){
		storeBindClassMapper.saveStoreBindClass(storeBindClass);
	}
	/**
	 * 获取所有店铺可发布商品类目表数据
	 *
	 * @return
	 */
	@Override
	public List<StoreBindClass> findStoreBindClassAllList(){
		return storeBindClassMapper.findStoreBindClassAllList();
	}

}
