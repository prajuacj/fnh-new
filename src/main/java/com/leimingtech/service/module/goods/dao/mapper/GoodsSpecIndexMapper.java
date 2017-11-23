package com.leimingtech.service.module.goods.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.GoodsSpecIndex;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

@SqlMapper
public interface GoodsSpecIndexMapper {

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
