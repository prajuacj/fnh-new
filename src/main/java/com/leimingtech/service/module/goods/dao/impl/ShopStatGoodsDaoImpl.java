package com.leimingtech.service.module.goods.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.GoodsGeneralCount;
import com.leimingtech.core.entity.GoodsStatCount;
import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.entity.base.ShopStatGoods;
import com.leimingtech.service.module.goods.dao.ShopStatGoodsDao;
import com.leimingtech.service.module.goods.dao.mapper.ShopStatGoodsMapper;
import com.leimingtech.service.utils.page.Pager;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * @项目名称：leimingtech-seller
 * @类名称：ShopStatGoodsDaoImpl
 * @类描述：
 * @修改备注：
 */
@Repository
public class ShopStatGoodsDaoImpl implements ShopStatGoodsDao {
    @Resource
    private ShopStatGoodsMapper shopStatGoodsMapper;

    @Override
    public void save(ShopStatGoods shopStatGoods) {
        shopStatGoods.setSId(IdGen.uuid());
        shopStatGoodsMapper.save(shopStatGoods);
    }

    @Override
    public void delete(String sId) {
        shopStatGoodsMapper.delete(sId);
    }

    @Override
    public int findCount(Pager pager) {
        return shopStatGoodsMapper.findCount(pager);
    }

    @Override
    public List<ShopStatGoods> findPageList(Pager pager) {
        return shopStatGoodsMapper.findPageList(pager);
    }

    @Override
    public Brand findbyIds(ShopStatGoods shopStatGoods) {
        return shopStatGoodsMapper.findbyIds(shopStatGoods);
    }

    @Override
    public List<ShopStatGoods> findList() {
        return shopStatGoodsMapper.findList();
    }

    /**
     * 时间段，店铺id查找内容
     *
     * @param map
     * @return
     */
    @Override
    public List<GoodsGeneralCount> findStatbytime(Map<String, Object> map) {
        return shopStatGoodsMapper.findStatbytime(map);
    }

}
