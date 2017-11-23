package com.leimingtech.service.module.goods.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.GoodsPlatform;
import com.leimingtech.service.module.goods.dao.GoodsPlatformDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsPlatformMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by young on 2016/1/27.
 */
@Repository
public class GoodsPlatformDaoImpl implements GoodsPlatformDao{

    @Resource
    private GoodsPlatformMapper goodsPlatformMapper;

    /**
     * 通过商品id获取商品信息
     * @param goodsId
     * @return
     */
    @Override
    public GoodsPlatform findGoodsById(String goodsId) {
        return goodsPlatformMapper.findGoodsById(goodsId);
    }

    /**
     * 获取商品列表     * @param pager
     * @return
     */
    @Override
    public List<GoodsPlatform> findGoodsPagerList(Pager pager) {
        return goodsPlatformMapper.findGoodsPagerList(pager);
    }

    /**
     * 保存
     * @param goods
     */
    @Override
    public void saveGoods(GoodsPlatform goods) {
        goods.setGoodsId(IdGen.uuid());
        goodsPlatformMapper.saveGoods(goods);
    }

    /**
     * 更新
     * @param goods
     */
    @Override
    public void updateGoods(GoodsPlatform goods) {
        goodsPlatformMapper.updateGoods(goods);
    }

    /**
     * 删除
     * @param goodsId
     */
    @Override
    public void deleteGoods(String goodsId) {
        goodsPlatformMapper.deleteGoods(goodsId);
    }

    /**
     * 根据条件查询商品信息
     * @param goods
     * @return
     */
    @Override
    public GoodsPlatform findGoodsByCondition(GoodsPlatform goods) {
        return goodsPlatformMapper.findGoodsByCondition(goods);
    }

    /**
     * 获取商品的数量
     * @param goods
     * @return
     */
    @Override
    public int countGoods(GoodsPlatform goods) {
        return goodsPlatformMapper.countGoods(goods);
    }
}
