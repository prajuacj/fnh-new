package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.GoodsSpecIndex;
import com.leimingtech.service.module.goods.dao.GoodsSpecIndexDao;
import com.leimingtech.service.module.goods.service.GoodsSpecIndexService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class GoodsSpecIndexServiceImpl implements GoodsSpecIndexService {

	@Autowired
	GoodsSpecIndexDao goodsSpecIndexDao;

	@Override
	public void save(GoodsSpecIndex goodsSpecIndex) {
		// TODO Auto-generated method stub
		goodsSpecIndexDao.save(goodsSpecIndex);
	}

	@Override
	public GoodsSpecIndex findByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return goodsSpecIndexDao.findByGoodsId(goodsId);
	}

	@Override
	public void deleteByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		goodsSpecIndexDao.deleteByGoodsId(goodsId);
	}

	@Override
	public int findPagerListCount(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecIndexDao.findPagerListCount(pager);
	}

	@Override
	public Pager findPagerList(Pager pager) {
		// TODO Auto-generated method stub
		List<GoodsSpecIndex> list = goodsSpecIndexDao.findPagerList(pager);
		pager.setResult(list);
		return pager;
	}

}
