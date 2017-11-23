package com.leimingtech.service.module.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.GoodsAttrIndex;
import com.leimingtech.service.module.goods.dao.GoodsAttrIndexDao;
import com.leimingtech.service.module.goods.service.GoodsAttrIndexService;

@Service
public class GoodsAttrIndexServiceImpl implements GoodsAttrIndexService {

    @Autowired
    GoodsAttrIndexDao goodsAttrIndexDao;

    @Override
    public void save(GoodsAttrIndex goodsAttrIndex) {
        // TODO Auto-generated method stub
        goodsAttrIndexDao.save(goodsAttrIndex);
    }

    @Override
    public GoodsAttrIndex findByGoodsId(String goodsId) {
        // TODO Auto-generated method stub
        return goodsAttrIndexDao.findByGoodsId(goodsId);
    }

    @Override
    public void deleteByGoodsId(String goodsId) {
        // TODO Auto-generated method stub
        goodsAttrIndexDao.deleteByGoodsId(goodsId);
    }

}
