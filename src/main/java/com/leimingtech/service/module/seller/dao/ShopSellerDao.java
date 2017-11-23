package com.leimingtech.service.module.seller.dao;

import com.leimingtech.core.entity.base.ShopSeller;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 店铺用户列表DAO接口
 *
 * @author yangxp
 * @version 2015-12-10
 */
public interface ShopSellerDao {

    /**
     * 查询分页店铺用户列表数据
     *
     * @param pager 分页对象
     * @return
     */
    List<ShopSeller> findShopSellerPagerList(Pager pager);

    /**
     * 通过sellerId获取单条店铺用户列表数据
     *
     * @param sellerId
     * @return
     */
    ShopSeller findShopSellerBySellerId(String sellerId);

    /**
     * 通过sellerId删除店铺用户列表数据
     *
     * @param sellerId
     */
    void deleteShopSellerBySellerId(String sellerId);

    /**
     * 修改店铺用户列表数据
     *
     * @param shopSeller
     */
    void updateShopSeller(ShopSeller shopSeller);

    /**
     * 保存店铺用户列表数据
     *
     * @param shopSeller
     */
    void saveShopSeller(ShopSeller shopSeller);

    /**
     * 获取所有店铺用户列表数据
     *
     * @return
     */
    List<ShopSeller> findShopSellerAllList();

    /**
     * 获取店铺的会员列表
     *
     * @param storeId
     * @return
     */
    List<ShopSeller> findSellerListByStoreId(String storeId);

    /**
     * 通过账户名获取会员信息
     *
     * @param sellerName
     * @return
     */
    ShopSeller findShopSellerBySellerName(String sellerName);

    /**
     * 通过memberId获取店铺账户信息
     *
     * @param memberId
     * @return
     */
    ShopSeller findShopSellerByMemberId(String memberId);

}