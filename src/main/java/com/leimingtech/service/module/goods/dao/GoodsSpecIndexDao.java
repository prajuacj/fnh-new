package com.leimingtech.service.module.goods.dao;

import com.leimingtech.core.entity.base.GoodsSpecIndex;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

public interface GoodsSpecIndexDao {

	/**
	 * 保存
	 */
	void save(GoodsSpecIndex goodsSpecIndex);
	
	/**
	 * 查询通过goodsId
	 */
	GoodsSpecIndex findByGoodsId(String goodsId);
	
	/**
	 * 根据goodsId删除
	 */
	void deleteByGoodsId(String goodsId);
	
	/**
	 * 分页查找总数
	 */
	int findPagerListCount(Pager pager);
	
	/**
	 * 分页查询
	 */
	List<GoodsSpecIndex> findPagerList(Pager pager);
}
