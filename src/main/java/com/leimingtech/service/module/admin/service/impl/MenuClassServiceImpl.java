package com.leimingtech.service.module.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.leimingtech.core.entity.MenuLanguage;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.MenuClass;
import com.leimingtech.core.entity.vo.MenuClassVo;
import com.leimingtech.service.module.admin.dao.MenuClassDao;
import com.leimingtech.service.module.admin.service.MenuClassService;

@Service
public class MenuClassServiceImpl implements MenuClassService{

    @Resource
    private MenuClassDao menuClassDao;

    /**
     * 保存分类
     * @param menuClass
     */
    @Override
    public void save(MenuClass menuClass) {
    	menuClassDao.save(menuClass);
    }

    /**
     * 修改分类
     * @param menuClass
     */
    @Override
    public void update(MenuClass menuClass) {
    	menuClassDao.update(menuClass);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id) {
    	menuClassDao.delete(id);
    }

    /**
     * 通过id查询分类
     * @param id
     * @return
     */
    @Override
    public MenuClass findById(String id,Integer isshow) {
    	Map<String,Object> pramap = new HashMap<String, Object>(); 
    	pramap.put("mid", id);
    	pramap.put("isshow", isshow);
        return menuClassDao.findById(pramap);
    }

    /**
     * 查询一级分类
     * @return
     */
    @Override
    public List<MenuClassVo> findListForPage() {
        return menuClassDao.findPageList();
    }

    /**
     * 查询子列表
     * @param id
     * @return
     */
    @Override
    public List<MenuClassVo> findChildList(String id) {
        return menuClassDao.findChildList(id);
    }

    /**
     * 查询出所有级别的分类
     * @return
     */
    @Override
    public List<MenuClass> findList(Integer isshow) {
        return menuClassDao.findList(isshow);
    }

    /**
     * 根据不同条件查询条数，页面验证用
     * @param menuClass
     * @return
     */
    @Override
    public int findCount(MenuClass menuClass) {
        return menuClassDao.findCount(menuClass);
    }

    /**
     * 递归查询所有
     *
     * @return
     */
    @Override
    public List<MenuClass> findAll() {

        List<MenuClass> meunClassList = menuClassDao.findList(null);
        for(MenuClass menuClass : meunClassList){
        	menuClass.setMenuClassList(menuClassDao.findChild(menuClass.getMid()));
        }
        return meunClassList;
    }
    
   

    
	@Override
	public String findbyparentid(String mparentid) {
		return menuClassDao.findbyparentid(mparentid);
	}

	@Override
	public int findparentidCount(String mid) {
		return menuClassDao.findparentidCount(mid);
	}

	@Override
	public List<MenuClassVo> findChildListmap(String id,Integer isshow,String roleids) {
		Map<String,Object> params = new HashMap<String, Object>(); 
    	params.put("mparentid", id);
    	params.put("isshow", isshow);
    	params.put("roleids", roleids);
		return menuClassDao.findChildListmap(params);
	}

	@Override
	public List<MenuClass> findByRoleids(String roleids,Integer isshow) {
		return menuClassDao.findByRoleids(roleids,isshow);
	}

    /**
     * 保存菜单的多语言
     * @param menuLanguage
     */
    public void saveMenuLanguage(MenuLanguage menuLanguage) {
        menuClassDao.saveMenuLanguage(menuLanguage);
    }

    /**
     * 根据id获取多语言菜单
     * @param menuId
     * @return
     */
    public MenuLanguage getMenuLanguage(String menuId) {
        return menuClassDao.getMenuLanguage(menuId);
    }

    /**
     * 更新菜单的多语言
     * @param menuLanguage
     */
    public void updateMenuLanguage(MenuLanguage menuLanguage) {
        menuClassDao.updateMenuLanguage(menuLanguage);
    }

}
