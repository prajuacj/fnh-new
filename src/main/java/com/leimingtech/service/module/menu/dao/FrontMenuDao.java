package com.leimingtech.service.module.menu.dao;

import com.leimingtech.core.entity.base.FrontMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FrontMenuDao {
	
	/**
	 * 查询所有父级菜单
	 * @return
	 */
	List<FrontMenu> findParentMenu();
	
	/**
	 * 根据菜单id查询菜单
	 * @param id
	 * @return
	 */
	FrontMenu findFrontMenuById(String id);
	
	/**
	 * 根据父id查询子级菜单
	 * @param id
	 * @return
	 */
	List<FrontMenu> findChildMenu(String id);
	
	/**
	 *查询所有菜单 
	 * @return
	 */
	List<FrontMenu> selectAllFm();
	
	/**
	 * 添加菜单
	 * @param frontMenu
	 */
	void save(FrontMenu frontMenu);
	
	/**
	 * 删除菜单
	 * @param id
	 */
	void delete(@Param("id") String id);
	
	/**
	 * 修改菜单
	 * @param frontMenu
	 */
	void update(FrontMenu frontMenu);
	
	/**
	 * 查询所有一级父节点
	 * @return
	 */
	List<FrontMenu> selectParentFrontMenu();
	
	/**
	 * 查询父节点下的子节点
	 * @param frontMenu
	 * @return
	 */
	List<FrontMenu> selectChildrenFrontMenu(FrontMenu frontMenu);
	
	/**
	 * 根据父级id查询所有父级id下的数量
	 * @param id
	 * @return
	 */
	int findParentIdCount(String id);
}
