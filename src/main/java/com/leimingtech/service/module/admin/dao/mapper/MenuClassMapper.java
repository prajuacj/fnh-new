package com.leimingtech.service.module.admin.dao.mapper;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.entity.MenuLanguage;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.leimingtech.core.entity.MenuClass;
import com.leimingtech.core.entity.vo.MenuClassVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * Created by rabook on 2014/11/4.
 */

@SqlMapper
public interface MenuClassMapper {

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
    void delete(@Param("mid") String mid);

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
     * @return ,
     */
    List<MenuClass> findList(@Param("isshow")Integer isshow);
    /**
     * 根据不同条件查询条数，页面验证用
     * @param menuClass
     * @return
     */
    int findCount(MenuClass menuClass);

    /**
     * 查询子列表
     * @param mid
     * @return
     */
    List<MenuClassVo> findChildList(@Param("mparentid") String mid);
    
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
     * @param mid
     * @return
     */
    List<MenuClass> findChild(@Param("mparentid") String mid);
    
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
    List<MenuClass> findByRoleids(@Param("roleids")String roleids,@Param("isshow")Integer isshow);

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
