package com.leimingtech.service.module.menu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.FrontMenu;
import com.leimingtech.core.orm.mybatis.SqlMapper;

@SqlMapper
public interface FrontMenuMapper {
	
	/**
	 * 查询所有父级菜单
	 * @return
	 */
	public List<FrontMenu> findParentMenu();
	
	/**
	 * 根据菜单id查询菜单
	 * @param id
	 * @return
	 */
	public FrontMenu findFrontMenuById(String id);
	
	/**
	 * 根据父id查询子级菜单
	 * @param id
	 * @return
	 */
	public List<FrontMenu> findChildMenu(String id);
	
	/**
	 *查询所有菜单 
	 * @return
	 */
	public List<FrontMenu> selectAllFm();
	
	/**
	 * 添加菜单
	 * @param frontMenu
	 */
	public void save(FrontMenu frontMenu);
	
	/**
	 * 删除菜单
	 * @param id
	 */
	public void delete(@Param("id") String id);
	
	/**
	 * 修改菜单
	 * @param frontMenu
	 */
	public void update(FrontMenu frontMenu);
	
	/**
	 * 查询所有一级父节点
	 * @return
	 */
	public List<FrontMenu> selectParentFrontMenu();
	
	/**
	 * 查询父节点下的子节点
	 * @param frontMenu
	 * @return
	 */
	public List<FrontMenu> selectChildrenFrontMenu(FrontMenu frontMenu);
	
	/**
	 * 根据父级id查询所有父级id下的数量
	 * @param id
	 * @return
	 */
	public int findParentIdCount(String id);
}
