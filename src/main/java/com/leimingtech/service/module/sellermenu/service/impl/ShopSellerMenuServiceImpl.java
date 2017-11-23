package com.leimingtech.service.module.sellermenu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.ShopSellerMenu;
import com.leimingtech.core.entity.vo.ShopSellerMenuVo;
import com.leimingtech.service.module.sellermenu.dao.ShopSellerMenuDao;
import com.leimingtech.service.module.sellermenu.service.ShopSellerMenuService;
import com.leimingtech.service.utils.page.Pager;

/**
 * sellerMenuServiceImpl
 *
 * @author yangxp
 * @version 2015-12-08
 */
@Service
public class ShopSellerMenuServiceImpl implements ShopSellerMenuService {

	/**
	 * sellerMenuDAO接口
	 */
	@Resource
	private ShopSellerMenuDao shopSellerMenuDao;

	/**
	 * 查询分页sellerMenu数据
	 *
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopSellerMenuPagerList(Pager pager) {
		List<ShopSellerMenu> list = shopSellerMenuDao.findShopSellerMenuPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过mId获取单条sellerMenu数据
	 *
	 * @param mId
	 * @return
	 */
	@Override
	public ShopSellerMenu findShopSellerMenuByMId(String mId) {
		return shopSellerMenuDao.findShopSellerMenuByMId(mId);
	}

	/**
	 * 根据父mId查询sellerMenu列表
	 *
	 * @param pid
	 * @return
	 */
	@Override
	public List<ShopSellerMenu> findShopSellerMenuListByPid(String pid) {
		return shopSellerMenuDao.findShopSellerMenuListByPid(pid);
	}

	/**
	 * 通过mId删除sellerMenu数据
	 *
	 * @param mId
	 */
	@Override
	public void deleteShopSellerMenuByMId(String mId) {
		List<ShopSellerMenu> shopSellerMenuChild = findShopSellerMenuListByPid(mId);
		if (shopSellerMenuChild != null && shopSellerMenuChild.size() > 0) {
			for (int i = 0; i < shopSellerMenuChild.size(); i++) {
				deleteShopSellerMenuByMId(shopSellerMenuChild.get(i).getMenuId());
			}
		}
		shopSellerMenuDao.deleteShopSellerMenuByMId(mId);
	}

	/**
	 * 修改sellerMenu数据
	 *
	 * @param shopSellerMenu
	 */
	@Override
	public void updateShopSellerMenu(ShopSellerMenu shopSellerMenu) {
		/*
		 * ShopSellerMenu old =
		 * findShopSellerMenuByMId(shopSellerMenu.getMenuId()); if
		 * (old.getMenuParentId() != shopSellerMenu.getMenuParentId()) {
		 * ShopSellerMenu p = null; // 重置levels和idpaths字段 if
		 * (shopSellerMenu.getMenuParentId() == 0) {
		 * shopSellerMenu.setMenuLevel(1);
		 * shopSellerMenu.setMenuParentId(shopSellerMenu.getMenuId()); } else {
		 * p =
		 * shopSellerMenuDao.findShopSellerMenuByMId(shopSellerMenu.getMenuId())
		 * ; shopSellerMenu.setMenuLevel(p.getMenuLevel() + 1);
		 * shopSellerMenu.setMenuParentId(p.getMenuParentId() +
		 * shopSellerMenu.getMenuId()); } }
		 * 
		 * shopSellerMenuDao.updateShopSellerMenu(shopSellerMenu); if
		 * (old.getMenuParentId() != shopSellerMenu.getMenuParentId()) {
		 * updateChildLevelAndPids(shopSellerMenu); }
		 */

		shopSellerMenuDao.updateShopSellerMenu(shopSellerMenu);
	}

	/**
	 * 更新子levels和pids字段
	 *
	 * @param shopSellerMenu
	 */
	private void updateChildLevelAndPids(ShopSellerMenu shopSellerMenu) {
		List<ShopSellerMenu> shopSellerMenuList = findShopSellerMenuListByPid(shopSellerMenu.getMenuId());
		if (shopSellerMenuList != null && shopSellerMenuList.size() > 0) {
			for (int i = 0; i < shopSellerMenuList.size(); i++) {
				ShopSellerMenu child = shopSellerMenuList.get(i);
				child.setMenuLevel(shopSellerMenu.getMenuLevel() + 1);
				child.setMenuParentId(shopSellerMenu.getMenuParentId() + child.getMenuId());

				shopSellerMenuDao.updateShopSellerMenu(child);
				updateChildLevelAndPids(child);
			}
		}
	}

	/**
	 * 保存sellerMenu数据
	 *
	 * @param shopSellerMenu
	 */
	@Override
	public void saveShopSellerMenu(ShopSellerMenu shopSellerMenu) {
		shopSellerMenuDao.saveShopSellerMenu(shopSellerMenu);
	}

	/**
	 * 获取所有sellerMenu数据
	 *
	 * @return
	 */
	@Override
	public List<ShopSellerMenu> findShopSellerMenuAllList() {
		List<ShopSellerMenu> shopSellerMenuList = shopSellerMenuDao.findShopSellerMenuAllList();
		for (ShopSellerMenu shopSellerMenu : shopSellerMenuList) {
			shopSellerMenu.setShopSellerMenuList(shopSellerMenuDao.findChild(shopSellerMenu.getMenuId()));
		}
		return shopSellerMenuList;
	}

	@Override
	public String findbyparentid(String mparentid) {
		return shopSellerMenuDao.findbyparentid(mparentid);
	}

	@Override
	public List<ShopSellerMenuVo> findListForPage() {
		return shopSellerMenuDao.findPageList();
	}

	@Override
	public List<ShopSellerMenuVo> findChildList(String id) {
		return shopSellerMenuDao.findChildList(id);
	}

	@Override
	public Integer findparentidCount(String id) {
		return shopSellerMenuDao.findparentidCount(id);
	}

	/**
	 * 获取账户可见的菜单列表
	 *
	 * @return
	 */
	public List<ShopSellerMenu> findShowMenuList() {
		return shopSellerMenuDao.findShowMenuList();
	}

	/**
	 * 通过权限列表获取菜单列表
	 *
	 * @param limits
	 *            以逗号分隔的字符串，存的是menuId
	 * @return
	 */
	public List<ShopSellerMenu> findShopSellerMenuListByMenuIds(String limits, int menuIsshow) {
		limits = limits.replaceAll("'", "");
		limits = "'" + limits.replaceAll(",", "','") + "'"; // .replaceAll("'","")
															// oracle 不带单引号报错
		return shopSellerMenuDao.findShopSellerMenuListByMenuIds(limits, menuIsshow);
	}

	/**
	 * 通过权限列表获取父菜单列表
	 *
	 * @param limits
	 *            以逗号分隔的字符串，存的是menuId
	 * @return
	 */
	public List<ShopSellerMenu> findShopSellerParentMenu(String limits) {
		limits = limits.replaceAll("'", "");
		limits = "'" + limits.replaceAll(",", "','") + "'";
		return shopSellerMenuDao.findShopSellerParentMenu(limits);
	}
}