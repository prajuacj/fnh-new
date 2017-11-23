package com.leimingtech.service.module.trade.dao;

import com.leimingtech.core.entity.base.ShopBarterLog;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 换货处理记录DAO接口
 *
 * @author gyh
 * @version 2015-12-22
 */
public interface ShopBarterLogDao {

    /**
     * 查询分页换货处理记录数据
     *
     * @param pager 分页对象
     * @return
     */
    List<ShopBarterLog> findShopBarterLogPagerList(Pager pager);

    /**
     * 通过id获取单条换货处理记录数据
     *
     * @param id
     * @return
     */
    ShopBarterLog findShopBarterLogById(String id);

    /**
     * 通过id删除换货处理记录数据
     *
     * @param id
     */
    void deleteShopBarterLogById(String id);

    /**
     * 修改换货处理记录数据
     *
     * @param shopBarterLog
     */
    void updateShopBarterLog(ShopBarterLog shopBarterLog);

    /**
     * 保存换货处理记录数据
     *
     * @param shopBarterLog
     */
    void saveShopBarterLog(ShopBarterLog shopBarterLog);

    /**
     * 获取所有换货处理记录数据
     *
     * @return
     */
    List<ShopBarterLog> findShopBarterLogAllList();

    /**
     * 通过换货id获取单条换货处理记录数据
     *
     * @param id
     * @return
     */
    List<ShopBarterLog> findListByBatterId(String id);

}