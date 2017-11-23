package com.leimingtech.service.module.trade.dao;

import com.leimingtech.core.entity.base.ShopBarter;
import com.leimingtech.core.entity.vo.BarterDetailVo;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 换货表的增删改查DAO接口
 *
 * @author admin
 * @version 2015-12-22
 */
public interface ShopBarterDao {

    /**
     * 查询分页换货表的增删改查数据
     *
     * @param pager 分页对象
     * @return
     */
    List<ShopBarter> findShopBarterPagerList(Pager pager);

    /**
     * 通过barterId获取单条换货表的增删改查数据
     *
     * @param barterId
     * @return
     */
    ShopBarter findShopBarterByBarterId(String barterId);

    /**
     * 通过barterId删除换货表的增删改查数据
     *
     * @param barterId
     */
    void deleteShopBarterByBarterId(String barterId);

    /**
     * 修改换货表的增删改查数据
     *
     * @param shopBarter
     */
    void updateShopBarter(ShopBarter shopBarter);

    /**
     * 保存换货表的增删改查数据
     *
     * @param shopBarter
     */
    void saveShopBarter(ShopBarter shopBarter);

    /**
     * 获取所有换货表的增删改查数据
     *
     * @return
     */
    List<ShopBarter> findShopBarterAllList();

    /**
     * 查询货详情,必传换货id,可传用户id和店铺id,不需要传null
     *
     * @param shopBarter
     * @return
     */
    BarterDetailVo findBarterDetail(ShopBarter shopBarter);

}