package com.leimingtech.service.module.admin.dao;

import java.util.List;

import com.leimingtech.core.entity.base.Roles;
import com.leimingtech.service.utils.page.Pager;

public interface RoleDao {
	/**
	 * 查询角色
	 * @param shopRole
	 * @return
	 */
	Integer countShopRole(Roles shopRole);
	
	/**
	 * 获取全部服装
	 * @return
	 */
	List<Roles> findShopRoleList(Pager pager);
	
	/**
	 * 删除Role数据
	 * @param id 对应Test id
	 * @return
	 */
	int deleteShopRole(String id);
	
	/**
	 * 保存
	 * @param shopRole
	 * @return
	 */
	int saveShopRole(Roles shopRole);
	
	/**
	 * 更新存在状态
	 * @param shopRole
	 */
	void updateState(Roles shopRole);
	
	/**
	 * 插件一条数据信息
	 */
	Roles findShopRoleById(String id);
	
    /**
     * 校验查询
     *
     * @param shopRole
     */
    public int findCount(Roles shopRole);
    
    /**
     * 查询出所有级角色
     * @return
     */
    List<Roles> findList();
}
