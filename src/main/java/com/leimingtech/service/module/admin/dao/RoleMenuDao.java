package com.leimingtech.service.module.admin.dao;

import java.util.List;

import com.leimingtech.core.entity.base.RoleMenu;
import com.leimingtech.service.utils.page.Pager;

public interface RoleMenuDao {
	/**
	 * 查询角色
	 * @param roleMenu
	 * @return
	 */
	Integer countRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 获取全部服装
	 * @return
	 */
	List<RoleMenu> findRoleMenuList(Pager pager);
	
	/**
	 * 删除Role数据
	 * @param id 对应Test id
	 * @return
	 */
	int deleteRoleMenu(String id);
	
	/**
	 * 保存
	 * @param roleMenu
	 * @return
	 */
	int saveRoleMenu(RoleMenu roleMenu);
	
	/**
	 * 更新存在状态
	 * @param roleMenu
	 */
	void updateState(RoleMenu roleMenu);
	
	/**
	 * 插件一条数据信息
	 */
	RoleMenu findRoleMenuById(String id);
	
    /**
     * 校验查询
     *
     * @param roleMenu
     */
    public int findCount(RoleMenu roleMenu);
    /**
     * 查询出所有
     * @return
     */
    List<RoleMenu> findList(String roleid);
}
