package com.leimingtech.service.module.sellermenu.dao.mapper;

import com.leimingtech.core.entity.base.ShopSellerMenu;
import com.leimingtech.core.entity.vo.ShopSellerMenuVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sellerMenumapper接口
 *
 * @author yangxp
 * @version 2015-12-08
 */
@SqlMapper
public interface ShopSellerMenuMapper {

    /**
     * 查询分页sellerMenu数据
     *
     * @param pager 分页对象
     * @return
     */
    List<ShopSellerMenu> findShopSellerMenuPagerList(Pager pager);

    /**
     * 通过mId获取单条sellerMenu数据
     *
     * @param mId
     * @return
     */
    ShopSellerMenu findShopSellerMenuByMId(String mId);

    /**
     * 根据父mId查询sellerMenu列表
     *
     * @param mId
     * @return
     */
    List<ShopSellerMenu> findShopSellerMenuListByPid(String mId);

    /**
     * 通过mId删除sellerMenu数据
     *
     * @param mId
     */
    void deleteShopSellerMenuByMId(String mId);

    /**
     * 修改sellerMenu数据
     *
     * @param shopSellerMenu
     */
    void updateShopSellerMenu(ShopSellerMenu shopSellerMenu);

    /**
     * 保存sellerMenu数据
     *
     * @param shopSellerMenu
     */
    void saveShopSellerMenu(ShopSellerMenu shopSellerMenu);

    /**
     * 获取所有sellerMenu数据
     *
     * @return
     */
    List<ShopSellerMenu> findShopSellerMenuAllList();

    /**
     * 根据父id获取
     *
     * @param mparentid
     * @return
     */
    String findbyparentid(String mparentid);

    /**
     * 获取一级菜单
     *
     * @return
     */
    List<ShopSellerMenuVo> findPageList();

    /**
     * 获取子级列表
     *
     * @param id
     * @return
     */
    List<ShopSellerMenuVo> findChildList(String id);

    /**
     * 获取子级id数量
     *
     * @param id
     * @return
     */
    Integer findparentidCount(String id);

    /**
     * 获取账户可见的菜单列表
     *
     * @return
     */
    List<ShopSellerMenu> findShowMenuList();

    /**
     * 获取子级列表
     *
     * @param menuId
     * @return
     */
    List<ShopSellerMenu> findChild(String menuId);

    /**
     * 通过权限列表获取菜单列表
     *
     * @param limits 以逗号分隔的字符串，存的是menuId
     * @return
     */
    List<ShopSellerMenu> findShopSellerMenuListByMenuIds(@Param("limits") String limits, @Param("menuIsshow") Integer menuIsshow);

    /**
     * 通过权限列表获取父菜单列表
     *
     * @param limits 以逗号分隔的字符串，存的是menuId
     * @return
     */
    List<ShopSellerMenu> findShopSellerParentMenu(@Param("limits") String limits);

    /**
     * 获取子菜单权限
     *
     * @param limits
     */
    List<ShopSellerMenu> findShopSellerChildMenu(@Param("limits") String limits);

}