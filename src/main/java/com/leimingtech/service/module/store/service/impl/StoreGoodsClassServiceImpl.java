package com.leimingtech.service.module.store.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leimingtech.core.entity.StoreGoodsClass;
import com.leimingtech.core.entity.vo.StoreGoodsClassVo;
import com.leimingtech.service.module.store.dao.StoreGoodsClassDao;
import com.leimingtech.service.module.store.service.StoreGoodsClassService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class StoreGoodsClassServiceImpl implements StoreGoodsClassService {
	@Resource
	private StoreGoodsClassDao storeGoodsClassDao;

	@Override
	public List<StoreGoodsClassVo> queryClasssList(StoreGoodsClassVo storeGoodsClassVo) {
		// 对数据进行整理
		List<StoreGoodsClassVo> results = storeGoodsClassDao.queryClasssList(storeGoodsClassVo);
		List<StoreGoodsClassVo> returnResults = Lists.newArrayList();
		String pId = "0";
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getChildId() == null) {
				returnResults.add(results.get(i));
			} else {
				if (pId.equals(results.get(i).getParentId())) {
					StoreGoodsClassVo cv = new StoreGoodsClassVo();
					cv.setChildId(results.get(i).getChildId());
					cv.setChildName(results.get(i).getChildName());
					cv.setChildSort(results.get(i).getChildSort());
					cv.setChildState(results.get(i).getChildState());
					cv.setParentId(results.get(i).getParentId());
					returnResults.add(cv);
				} else {
					StoreGoodsClassVo cv = new StoreGoodsClassVo();
					cv.setParentId(results.get(i).getParentId());
					cv.setParentName(results.get(i).getParentName());
					cv.setParentSort(results.get(i).getParentSort());
					cv.setParentState(results.get(i).getParentState());
					returnResults.add(cv);
					cv = new StoreGoodsClassVo();
					cv.setChildId(results.get(i).getChildId());
					cv.setChildName(results.get(i).getChildName());
					cv.setChildSort(results.get(i).getChildSort());
					cv.setChildState(results.get(i).getChildState());
					cv.setParentId(results.get(i).getParentId());
					returnResults.add(cv);
				}
				pId = results.get(i).getParentId();
			}
		}
		return returnResults;
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		storeGoodsClassDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<StoreGoodsClass> findParentList(String id) {
		return storeGoodsClassDao.findParentList(id);
	}

	@Override
	public void save(StoreGoodsClass storeGoodsClass) {
		storeGoodsClass.setCreateTime(System.currentTimeMillis());
		storeGoodsClassDao.insertSelective(storeGoodsClass);
	}

	@Override
	public void update(StoreGoodsClass storeGoodsClass) {
		storeGoodsClass.setUpdateTime(System.currentTimeMillis());
		storeGoodsClassDao.updateByPrimaryKeySelective(storeGoodsClass);
		storeGoodsClassDao.updateState(storeGoodsClass);
	}

	@Override
	public StoreGoodsClass selectByPrimaryKey(String stcId) {
		return storeGoodsClassDao.selectByPrimaryKey(stcId);
	}

	/**
	 * 获取到分类属性
	 */
	public Map<String, List<StoreGoodsClass>> queryStoreClass(String id) {
		List<StoreGoodsClass> list = storeGoodsClassDao.findParentList(id);
		List<StoreGoodsClass> lists = storeGoodsClassDao.findAll(id);
		Map<String, List<StoreGoodsClass>> map = Maps.newLinkedHashMap();
		if (list != null && list.size() > 0) {
			for (StoreGoodsClass goodsClass : list) {
				List<StoreGoodsClass> goodsClasses = Lists.newArrayList();
				for (StoreGoodsClass storeGoodsClass : lists) {
					if (goodsClass.getStcId().equals(storeGoodsClass.getStcParentId())) {

						goodsClasses.add(storeGoodsClass);
					}
				}
				map.put(goodsClass.getStcId() + "@" + goodsClass.getStcName(), goodsClasses);
			}
			return map;
		}
		return null;
	}

	@Override
	public List<StoreGoodsClass> findChild(String id) {
		return storeGoodsClassDao.findChild(id);
	}

	@Override
	public List<StoreGoodsClass> findList(StoreGoodsClass storeGoodsClass) {
		return storeGoodsClassDao.findList(storeGoodsClass);
	}

	@Override
	public StoreGoodsClass findbystcName(String stcName) {
		return storeGoodsClassDao.findbystcName(stcName);
	}

	/**
	 * 查询父子关联通过显示状态
	 *
	 * @param storeGoodsClass
	 */
	@Override
	public List<StoreGoodsClass> findListbystate(StoreGoodsClass storeGoodsClass) {
		return storeGoodsClassDao.findListbystate(storeGoodsClass);
	}

	/**
	 * 查询条数
	 *
	 * @param storeGoodsClass
	 * @return
	 */
	@Override
	public int queryCount(StoreGoodsClass storeGoodsClass) {
		return storeGoodsClassDao.queryCount(storeGoodsClass);
	}

	/**
	 * 查询店铺自定义分类分页数据
	 *
	 * @param pager
	 * @return
	 */
	@Override
	public Pager queryList(Pager pager) {
		List<StoreGoodsClass> list = storeGoodsClassDao.queryList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public void updateByPrimaryKeySelective(StoreGoodsClass storeGoodsClass) {
		storeGoodsClassDao.updateByPrimaryKeySelective(storeGoodsClass);
	}
}
