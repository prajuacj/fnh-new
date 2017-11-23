package com.leimingtech.service.module.goods.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.goods.service.GoodsUrlService;

@Service
public class GoodsUrlServiceImpl implements GoodsUrlService{
    
    @Resource
    private GoodsDao goodsDao;
    
	public Goods findGoodById(String goodsId) {
		return goodsDao.findGoodById(goodsId);
	}

	@Override
	public void updateUrl(Goods goods) {
		goodsDao.updateUrl(goods);
	}
	
	
}
