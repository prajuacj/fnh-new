package com.leimingtech.service.module.mansong.dao.mapper;

import com.leimingtech.core.entity.base.ShopPMansong;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 满就送mapper接口
 *
 * @author linjm
 * @version 2015-11-19
 */
@SqlMapper
public interface ShopPMansongMapper {

    /**
     * 查询分页满就送数据
     *
     * @param pager 分页对象
     * @return
     */
    List<ShopPMansong> findShopPMansongPagerList(Pager pager);

    /**
     * 通过id获取单条满就送数据
     *
     * @param id
     * @return
     */
    ShopPMansong findShopPMansongById(String id);

    /**
     * 通过id删除满就送数据
     *
     * @param id
     */
    void deleteShopPMansongById(String id);

    /**
     * 修改满就送数据
     *
     * @param shopPMansong
     */
    void updateShopPMansong(ShopPMansong shopPMansong);

    /**
     * 保存满就送数据
     *
     * @param shopPMansong
     */
    void saveShopPMansong(ShopPMansong shopPMansong);

    /**
     * 获取所有满就送数据
     *
     * @return
     */
    List<ShopPMansong> findShopPMansongAllList();

    /**
     * 通过满就送套餐id，获取该套餐对应的满就送活动
     *
     * @param id
     * @return
     */
    List<ShopPMansong> findShopPMansongByQuotaId(String id);

    /**
     * 获取一条店铺当前可以使用的满送
     *
     * @param storeId
     * @return ShopPMansong
     */
    ShopPMansong findStoreCurrentMansong(@Param("storeId") String storeId, @Param("endTime") long endTime);


    /**
     * 获取店铺下面指定条件满即送条数
     *
     * @param paramMap
     * @return
     */
    public int findByEndTimeCount(Map paramMap);
}