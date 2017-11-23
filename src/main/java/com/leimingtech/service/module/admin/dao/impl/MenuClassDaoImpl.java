package com.leimingtech.service.module.admin.dao.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.MenuLanguage;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.MenuClass;
import com.leimingtech.core.entity.vo.MenuClassVo;
import com.leimingtech.service.module.admin.dao.MenuClassDao;
import com.leimingtech.service.module.admin.dao.mapper.MenuClassMapper;

@Repository
public class MenuClassDaoImpl implements MenuClassDao{

    @Resource
    private MenuClassMapper menuClassMapper;

    /**
     * 查询下级分类
     *
     * @param id
     * @return
     */
    @Override
    public List<MenuClass> findChild(String id) {
        return menuClassMapper.findChild(id);
    }

    
    /**
     * 保存分类
     * @param menuClass
     */
	@Override
	public void save(MenuClass menuClass) {
        menuClass.setMid(IdGen.uuid());
		menuClassMapper.save(menuClass);
	}
    

    /**
     * 修改分类
     * @param menuClass
     */
	@Override
	public void update(MenuClass menuClass) {
		menuClassMapper.update(menuClass);
	}

	@Override
	public int findCount(MenuClass menuClass) {
		
		return menuClassMapper.findCount(menuClass);
	}
	 /**
     * 删除
     * @param id
     */
	@Override
	public void delete(String id) {
		menuClassMapper.delete(id);
	}
    
	/**
     * 通过id查询分类
     * @param parmap
     * @return
     */
    @Override
    public MenuClass findById(Map parmap) {
        return menuClassMapper.findById(parmap);
    }
    
    /**
     * 查询一级分类
     * @return
     */
    @Override
    public List<MenuClassVo> findPageList() {
        return menuClassMapper.findPageList();
    }

    /**
     * 查询出所有级别的分类
     * @return
     */
    @Override
    public List<MenuClass> findList(Integer isshow) {
        return menuClassMapper.findList(isshow);
    }

  

    /**
     * 查询子列表
     * @param parmap
     * @return
     */
    @Override
    public List<MenuClassVo> findChildListmap(Map parmap) {
        return menuClassMapper.findChildListmap(parmap);
    }

    /**
     * 修改子类分类
     *
     * @param menuClass
     */
    @Override
    public void updateChildType(MenuClass menuClass) {
    	menuClassMapper.updateChildType(menuClass);
    }

    /**
     * 递归查询所有
     *
     * @return
     */
    @Override
    public List<MenuClass> findAll() {
        return menuClassMapper.findAll();
    }


	@Override
	public String findbyparentid(String mparentid) {
		return menuClassMapper.findbyparentid(mparentid);
	}


	@Override
	public int findparentidCount(String mid) {
		return menuClassMapper.findparentidCount(mid);
	}


	@Override
	public List<MenuClassVo> findChildList(String id) {
		// TODO Auto-generated method stub
		return menuClassMapper.findChildList(id);
	}


	@Override
	public List<MenuClass> findByRoleids(String roleids,Integer isshow) {
		return menuClassMapper.findByRoleids(roleids,isshow);
	}

    /**
     * 保存菜单的多语言
     * @param menuLanguage
     */
    public void saveMenuLanguage(MenuLanguage menuLanguage) {
        menuClassMapper.saveMenuLanguage(menuLanguage);
    }

    /**
     * 根据id获取多语言菜单
     * @param menuId
     * @return
     */
    public MenuLanguage getMenuLanguage(String menuId) {
        return menuClassMapper.getMenuLanguage(menuId);
    }

    /**
     * 更新菜单的多语言
     * @param menuLanguage
     */
    public void updateMenuLanguage(MenuLanguage menuLanguage) {
        menuClassMapper.updateMenuLanguage(menuLanguage);
    }


}
