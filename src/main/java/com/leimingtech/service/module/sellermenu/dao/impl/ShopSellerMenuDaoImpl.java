package com.leimingtech.service.module.sellermenu.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.ShopSellerMenu;
import com.leimingtech.core.entity.vo.ShopSellerMenuVo;
import com.leimingtech.service.module.sellermenu.dao.ShopSellerMenuDao;
import com.leimingtech.service.module.sellermenu.dao.mapper.ShopSellerMenuMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * sellerMenudaoImpl
 *
 * @author yangxp
 * @version 2015-12-08
 */
@Repository
public class ShopSellerMenuDaoImpl implements ShopSellerMenuDao {

    /**
     * sellerMenumapper接口
     */
    @Resource
    private ShopSellerMenuMapper shopSellerMenuMapper;

    /**
     * 查询分页sellerMenu数据
     *
     * @param pager 分页对象
     * @return
     */
    @Override
    public List<ShopSellerMenu> findShopSellerMenuPagerList(Pager pager) {
        if (pager == null) {
            pager = new Pager();
        }
        if (pager.getCondition() == null) {
            pager.setCondition(new ShopSellerMenu());
        }
        return shopSellerMenuMapper.findShopSellerMenuPagerList(pager);
    }

    /**
     * 通过mId获取单条sellerMenu数据
     *
     * @param mId
     * @return
     */
    @Override
    public ShopSellerMenu findShopSellerMenuByMId(String mId) {
        return shopSellerMenuMapper.findShopSellerMenuByMId(mId);
    }

    /**
     * 通过mId删除sellerMenu数据
     *
     * @param mId
     */
    @Override
    public void deleteShopSellerMenuByMId(String mId) {
        shopSellerMenuMapper.deleteShopSellerMenuByMId(mId);
    }

    /**
     * 根据父mId查询sellerMenu列表
     *
     * @param pid
     * @return
     */
    @Override
    public List<ShopSellerMenu> findShopSellerMenuListByPid(String pid) {
        return shopSellerMenuMapper.findShopSellerMenuListByPid(pid);
    }

    /**
     * 修改sellerMenu数据
     *
     * @param shopSellerMenu
     */
    @Override
    public void updateShopSellerMenu(ShopSellerMenu shopSellerMenu) {
        shopSellerMenuMapper.updateShopSellerMenu(shopSellerMenu);
    }

    /**
     * 保存sellerMenu数据
     *
     * @param shopSellerMenu
     */
    @Override
    public void saveShopSellerMenu(ShopSellerMenu shopSellerMenu) {
        shopSellerMenu.setMenuId(IdGen.uuid());
        shopSellerMenuMapper.saveShopSellerMenu(shopSellerMenu);
    }

    /**
     * 获取所有sellerMenu数据
     *
     * @return
     */
    @Override
    public List<ShopSellerMenu> findShopSellerMenuAllList() {
        return shopSellerMenuMapper.findShopSellerMenuAllList();
    }

    @Override
    public String findbyparentid(String mparentid) {
        return shopSellerMenuMapper.findbyparentid(mparentid);
    }

    @Override
    public List<ShopSellerMenuVo> findPageList() {
        return shopSellerMenuMapper.findPageList();
    }

    @Override
    public List<ShopSellerMenuVo> findChildList(String id) {
        return shopSellerMenuMapper.findChildList(id);
    }

    @Override
    public Integer findparentidCount(String id) {
        return shopSellerMenuMapper.findparentidCount(id);
    }

    /**
     * 获取账户可见的菜单列表
     *
     * @return
     */
    public List<ShopSellerMenu> findShowMenuList() {
        return shopSellerMenuMapper.findShowMenuList();
    }

    /**
     * 获取子级列表
     *
     * @param menuId
     * @return
     */
    public List<ShopSellerMenu> findChild(String menuId) {
        return shopSellerMenuMapper.findChild(menuId);
    }

    /**
     * 通过权限列表获取菜单列表
     *
     * @param limits 以逗号分隔的字符串，存的是menuId
     * @return
     */
    public List<ShopSellerMenu> findShopSellerMenuListByMenuIds(String limits, int menuIsshow) {
        return shopSellerMenuMapper.findShopSellerMenuListByMenuIds(limits, menuIsshow);
    }

    /**
     * 通过权限列表获取父菜单列表
     *
     * @param limits 以逗号分隔的字符串，存的是menuId
     * @return
     */
    public List<ShopSellerMenu> findShopSellerParentMenu(String limits) {
        return shopSellerMenuMapper.findShopSellerParentMenu(limits);
    }

    /**
     * 获取子菜单权限
     *
     * @param limits
     */
    public List<ShopSellerMenu> findShopSellerChildMenu(String limits) {
        return shopSellerMenuMapper.findShopSellerChildMenu(limits);
    }

}