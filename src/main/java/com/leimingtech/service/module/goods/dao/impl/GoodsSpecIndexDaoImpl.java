package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leimingtech.core.entity.base.GoodsSpecIndex;
import com.leimingtech.service.module.goods.dao.GoodsSpecIndexDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsSpecIndexMapper;
import com.leimingtech.service.utils.page.Pager;

@Component
public class GoodsSpecIndexDaoImpl implements GoodsSpecIndexDao{

	@Autowired
	GoodsSpecIndexMapper goodsSpecIndexMapper;
	
	@Override
	public void save(GoodsSpecIndex goodsSpecIndex) {
		// TODO Auto-generated method stub
		goodsSpecIndexMapper.save(goodsSpecIndex);
	}

	@Override
	public GoodsSpecIndex findByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return goodsSpecIndexMapper.findByGoodsId(goodsId);
	}

	@Override
	public void deleteByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		goodsSpecIndexMapper.deleteByGoodsId(goodsId);
	}

	@Override
	public int findPagerListCount(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecIndexMapper.findPagerListCount(pager);
	}

	@Override
	public List<GoodsSpecIndex> findPagerList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecIndexMapper.findPagerList(pager);
	}

}
