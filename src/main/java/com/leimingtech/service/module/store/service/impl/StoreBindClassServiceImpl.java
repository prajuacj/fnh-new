package com.leimingtech.service.module.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.StoreBindClass;
import com.leimingtech.service.module.store.dao.StoreBindClassDao;
import com.leimingtech.service.module.store.service.StoreBindClassService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 店铺可发布商品类目表ServiceImpl
 *
 * @author gyh
 * @version 2016-01-05
 */
@Service
public class StoreBindClassServiceImpl implements StoreBindClassService {

	/** 店铺可发布商品类目表DAO接口 */
	@Resource
	private StoreBindClassDao storeBindClassDao;

	/**
	 * 查询分页店铺可发布商品类目表数据
	 *
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findStoreBindClassPagerList(Pager pager) {
		List<StoreBindClass> list = storeBindClassDao.findStoreBindClassPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过bid获取单条店铺可发布商品类目表数据
	 *
	 * @param bid
	 * @return
	 */
	@Override
	public StoreBindClass findStoreBindClassByBid(int bid) {
		return storeBindClassDao.findStoreBindClassByBid(bid);
	}

	/**
	 * 通过bid删除店铺可发布商品类目表数据
	 *
	 * @param bid
	 */
	@Override
	public void deleteStoreBindClassByBid(int bid) {
		storeBindClassDao.deleteStoreBindClassByBid(bid);
	}

	/**
	 * 修改店铺可发布商品类目表数据
	 *
	 * @param storeBindClass
	 */
	@Override
	public void updateStoreBindClass(StoreBindClass storeBindClass) {
		storeBindClassDao.updateStoreBindClass(storeBindClass);
	}

	/**
	 * 保存店铺可发布商品类目表数据
	 *
	 * @param storeBindClass
	 */
	@Override
	public void saveStoreBindClass(StoreBindClass storeBindClass) {
		storeBindClassDao.saveStoreBindClass(storeBindClass);
	}

	/**
	 * 获取所有店铺可发布商品类目表数据
	 *
	 * @return
	 */
	@Override
	public List<StoreBindClass> findStoreBindClassAllList() {
		return storeBindClassDao.findStoreBindClassAllList();
	}

}
