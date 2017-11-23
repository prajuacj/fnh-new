package com.leimingtech.service.module.goods.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.GoodsCombination;

/**
 * 组合商品
 * @author chen
 * 2015年08月14日11:00:49
 */
@Repository
public interface GoodsCombinationDao {

	/**
	 * @author chen
	 * 保存组合商品
	 */
	void saveGoodsCombination(GoodsCombination goodsCombination);
	
	/**
	 * @author chen
	 * 通过goodsId删除组合商品
	 */
	void deleteByGoodsId(String goodsId);
	
	/**
	 * @author chen
	 * 通过条件获取组合商品
	 * 参数说明:在GoodsCombination实体类中设置条件,则返回相应的list
	 * 比如:设置goodsid=1,则返回goodsid=1的组合商品
	 */
	List<GoodsCombination> selectByCondition(GoodsCombination goodsCombination);
}
