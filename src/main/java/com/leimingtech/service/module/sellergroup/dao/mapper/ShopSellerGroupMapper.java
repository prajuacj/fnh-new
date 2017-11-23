package com.leimingtech.service.module.sellergroup.dao.mapper;

import com.leimingtech.core.entity.base.ShopSellerGroup;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺角色(组)管理mapper接口
 *
 * @author admin
 * @version 2015-12-10
 */
@SqlMapper
public interface ShopSellerGroupMapper {

    /**
     * 查询分页店铺角色(组)管理数据
     *
     * @param pager 分页对象
     * @return
     */
    List<ShopSellerGroup> findShopSellerGroupPagerList(Pager pager);

    /**
     * 通过groupId获取单条店铺角色(组)管理数据
     *
     * @param groupId
     * @return
     */
    ShopSellerGroup findShopSellerGroupByGroupId(String groupId);

    /**
     * 通过groupId删除店铺角色(组)管理数据
     *
     * @param groupId
     */
    void deleteShopSellerGroupByGroupId(String groupId);

    /**
     * 修改店铺角色(组)管理数据
     *
     * @param shopSellerGroup
     */
    void updateShopSellerGroup(ShopSellerGroup shopSellerGroup);

    /**
     * 保存店铺角色(组)管理数据
     *
     * @param shopSellerGroup
     */
    void saveShopSellerGroup(ShopSellerGroup shopSellerGroup);

    /**
     * 获取所有店铺角色(组)管理数据
     *
     * @return
     */
    List<ShopSellerGroup> findShopSellerGroupAllList();

    /**
     * 通过店铺id，获取角色菜单
     *
     * @param storeId
     * @return
     */
    List<ShopSellerGroup> findShopSellerGroupListByStoreId(@Param("storeId") String storeId);

}