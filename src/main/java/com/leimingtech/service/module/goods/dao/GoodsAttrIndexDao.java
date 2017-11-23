package com.leimingtech.service.module.goods.dao;

import com.leimingtech.core.entity.base.GoodsAttrIndex;

public interface GoodsAttrIndexDao {

	/**
	 * 保存
	 */
	void save(GoodsAttrIndex goodsAttrIndex);
	
	/**
	 * 查询通过goodsId
	 */
	GoodsAttrIndex findByGoodsId(String goodsId);
	
	/**
	 * 根据goodsId删除
	 */
	void deleteByGoodsId(String goodsId);
}
