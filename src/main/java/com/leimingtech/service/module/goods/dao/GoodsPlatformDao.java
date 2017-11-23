package com.leimingtech.service.module.goods.dao;

import com.leimingtech.core.entity.base.GoodsPlatform;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * Created by young on 2016/1/27.
 */
public interface GoodsPlatformDao {

    /**
     * 通过goodsid查找goods
     * @param goodsId
     * @return
     */
    GoodsPlatform findGoodsById(String goodsId);

    /**
     * 分页查询获得list
     * @param pager
     * @return
     */
    List<GoodsPlatform> findGoodsPagerList(Pager pager);

    /**
     * 保存
     * @param goods
     */
    void saveGoods(GoodsPlatform goods);

    /**
     * 修改商品
     * @param goods
     */
    void updateGoods(GoodsPlatform goods);

    /**
     * 删除商品
     * @param goodsId
     */
    void deleteGoods(String goodsId);

    /**
     * 通过一定条件的条件,查找某个商品,
     * 这个方法只会返回一个商品,
     * 使用方法:
     * 新建一个goods对象,在这个对象中
     * 一定要设置goodsid这个属性
     * 可以选择set属性:storeId,goodsState
     * 使用这个方法会根据你所设置的条件去查询,
     * 如果没有返回null
     */
    GoodsPlatform findGoodsByCondition(GoodsPlatform goods);

    /**
     * 根据商品字段获取商品的数量
     * @param goods
     * @return
     */
    int countGoods(GoodsPlatform goods);
}
