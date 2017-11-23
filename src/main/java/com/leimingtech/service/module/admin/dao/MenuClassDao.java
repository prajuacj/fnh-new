package com.leimingtech.service.module.admin.dao;


import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.MenuClass;
import com.leimingtech.core.entity.MenuLanguage;
import com.leimingtech.core.entity.vo.MenuClassVo;

public interface MenuClassDao {

    /**
     * 保存分类
     * @param menuClass
     */
    void save(MenuClass menuClass);
    /**
     * 修改分类
     * @param menuClass
     */
    void update(MenuClass menuClass);
    /**
     * 删除
     * @param mid
     */
    void delete(String mid);
    /**
     * 通过id查询分类
     * @param pramap
     * @return
     */
    MenuClass findById(Map pramap);
    /**
     * 查询一级分类
     * @return
     */
    List<MenuClassVo> findPageList();
    /**
     * 查询出所有级别的分类
     * @return
     */
    List<MenuClass> findList(Integer isshow);
    /**
     * 根据不同条件查询条数，页面验证用
     * @param menuClass
     * @return
     */
    int findCount(MenuClass menuClass);
    /**
     * 查询子列表
     * @param id
     * @return
     */
    List<MenuClassVo> findChildList(String id);
    
    List<MenuClassVo> findChildListmap(Map pramap);

    /**
     * 修改子类分类
     * @param menuClass
     */
    void updateChildType(MenuClass menuClass);

    /**
     * 递归查询所有
     * @return
     */
    List<MenuClass> findAll();

    /**
     * 查询下级分类
     * @param id
     * @return
     */
    List<MenuClass> findChild(String id);
    
    /**
     * 根据mparentid查询
     * @param mparentid
     * @return
     */
    String findbyparentid(String mparentid);
    /**
     * 根据mid查询
     * @param mid
     * @return
     */
    int findparentidCount(String mid);

    /**
     * 根据角色ID获取菜单
     * @param roleids
     * @param isshow
     * @return
     */
    List<MenuClass> findByRoleids(String roleids,Integer isshow);

    /**
     * 保存菜单的多语言
     * @param menuLanguage
     */
    void saveMenuLanguage(MenuLanguage menuLanguage);

    /**
     * 根据id获取多语言菜单
     * @param menuId
     * @return
     */
    MenuLanguage getMenuLanguage(String menuId);

    /**
     * 更新菜单的多语言
     * @param menuLanguage
     */
    void updateMenuLanguage(MenuLanguage menuLanguage);
}
