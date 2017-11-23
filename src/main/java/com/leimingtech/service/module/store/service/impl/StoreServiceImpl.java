package com.leimingtech.service.module.store.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Store;
import com.leimingtech.core.entity.vo.StoreVo;
import com.leimingtech.service.module.goods.service.GoodsService;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.search.service.StoreSearchService;
import com.leimingtech.service.module.store.dao.StoreDao;
import com.leimingtech.service.module.store.service.StoreService;
import com.leimingtech.service.utils.page.Pager;

/**
 * @项目名称：leimingtech-seller
 * @类名称：StoreServiceImpl @类描述： @修改备注：
 */
@Service
public class StoreServiceImpl implements StoreService {
	@Resource
	private StoreDao storeDao;
	@Resource
	private GoodsService goodsService;
	@Resource
	private StoreSearchService storeSearchService;
	@Resource
	private LucenceService lucenceService;

	public Store findById(String id) {
		return storeDao.findById(id);
	}

	public StoreVo findVoById(String id) {
		return storeDao.findVoById(id);
	}

	public void updateStore(Store store) {
		storeDao.updateStore(store);
		Store dbStore = storeDao.findById(store.getStoreId());
		// 当开启时才建立索引
		if (dbStore.getStoreState() != null && dbStore.getStoreState() == 1) {
			lucenceService.updateStoreOneIndex(storeDao.findById(store.getStoreId()));
		}
		if ((dbStore.getStoreState() != null && dbStore.getStoreState() == 0) || dbStore.getIsDel() == 1) {
			storeSearchService.deleteStoreIndex(store.getStoreId());// 关闭店铺时删除店铺索引
			goodsService.delserchgoods(store.getStoreId());// 店铺关闭后下架商品和删除店铺下的商品索引
		}
	}

	public void updateStoreCus(Store store) {
		storeDao.updateStoreCus(store);
	}

	public Store findByMemberId(String id) {
		return storeDao.findByMemberId(id);
	}

	public void save(Store store) {
		store.setStoreId(IdGen.uuid());
		store.setCreateTime(System.currentTimeMillis());
		store.setIsDel(0);// 0没有删除，1删除
		storeDao.save(store);
	}

	public Store findByStorename(String storename) {
		return storeDao.findByStorename(storename);
	}

	public int queryCount(Store store) {
		return storeDao.queryCount(store);
	}

	;

	public Pager queryList(Pager pager) {
		List<Store> list = storeDao.queryList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public void updateVerifyPass(Store store, String verifyType, Map<String, Object> mailmap) {

	}

	@Override
	public void updateStoreCount(Map<String, String> map) {
		storeDao.updateStoreCount(map);
	}

	@Override
	public Store findByIds(Store store) {
		return storeDao.findByIds(store);
	}

	@Override
	public void updateStore(String storeId) {
		Store store = storeDao.findById(storeId);// 获取店铺信息
		Store store2 = new Store();
		if (store != null) {
			store2.setStoreId(storeId);
			store2.setStoreLastLogintime(store.getStoreLogintime());
			store2.setStoreLogintime(System.currentTimeMillis());
			storeDao.updateStore(store2);
		}
	}

	/**
	 * @param @param
	 *            id 设定文件
	 * @return void 返回类型
	 * @Title: delete
	 * @Description: 根据ID 删除
	 */
	@Override
	public void delete(String id) {
		storeDao.delete(id);
	}

	/**
	 * 根据会员名字获取店铺信息
	 * 
	 * @param memberName
	 * @return
	 */
	public Store findByMemberName(String memberName) {
		return storeDao.findByMemberName(memberName);
	}

	@Override
	public List<Store> findByAreaIdAndName(String areaId) {
		return storeDao.findByAreaIdAndName(areaId);
	}

	@Override
	public List<Store> listStoreByCondition(Store store) {
		// TODO Auto-generated method stub
		return storeDao.listStoreByCondition(store);
	}

	@Override
	public List<Store> listStoreByCooperative(String provinceId) {
		// TODO Auto-generated method stub
		return storeDao.listStoreByCooperative(provinceId);
	}

	public Store findStoreByOne(String storeId) {
		return storeDao.findStoreByOne(storeId);
	}

	@Override
	public List<Store> selectList(Store store) {
		return storeDao.selectList(store);
	}

	@Override
	public List<Store> selectListByLeaderId(String leaderId) {
		return storeDao.selectListByLeaderId(leaderId);
	}

}
