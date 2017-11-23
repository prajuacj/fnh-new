package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.GoodsCombination;
import com.leimingtech.service.module.goods.dao.GoodsCombinationDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsCombinationMapper;

/**
 * 组合商品
 * @author chen
 * 2015年08月14日11:00:49
 */
@Repository
public class GoodsCombinationDaoImpl implements GoodsCombinationDao {

	@Autowired
	private GoodsCombinationMapper goodsCombinationMapper;
	
	@Override
	public void saveGoodsCombination(GoodsCombination goodsCombination) {
		// TODO Auto-generated method stub
		goodsCombinationMapper.saveGoodsCombination(goodsCombination);
	}

	@Override
	public void deleteByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		goodsCombinationMapper.deleteByGoodsId(goodsId);
	}

	@Override
	public List<GoodsCombination> selectByCondition(GoodsCombination goodsCombination) {
		// TODO Auto-generated method stub
		return goodsCombinationMapper.selectByCondition(goodsCombination);
	}

	
}
