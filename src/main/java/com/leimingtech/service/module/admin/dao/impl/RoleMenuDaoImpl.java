package com.leimingtech.service.module.admin.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.RoleMenu;
import com.leimingtech.service.module.admin.dao.RoleMenuDao;
import com.leimingtech.service.module.admin.dao.mapper.RoleMenuMapper;
import com.leimingtech.service.utils.page.Pager;

@Repository
public class RoleMenuDaoImpl implements RoleMenuDao {
	
	@Resource
	private RoleMenuMapper roleMenuMapper;

	@Override
	public Integer countRoleMenu(RoleMenu roleMenu) {
		return roleMenuMapper.countRoleMenu(roleMenu);
	}

	@Override
	public List<RoleMenu> findRoleMenuList(Pager pager) {
		return roleMenuMapper.findRoleMenuList(pager);
	}

	@Override
	public int deleteRoleMenu(String id) {
		return roleMenuMapper.deleteRoleMenu(id);
	}

	@Override
	public int saveRoleMenu(RoleMenu roleMenu) {
		return roleMenuMapper.saveRoleMenu(roleMenu);
	}

	@Override
	public void updateState(RoleMenu roleMenu) {
		roleMenuMapper.updateState(roleMenu);
		
	}

	@Override
	public RoleMenu findRoleMenuById(String id) {
		return roleMenuMapper.findRoleMenuById(id);
	}

	@Override
	public int findCount(RoleMenu roleMenu) {
		return roleMenuMapper.findCount(roleMenu);
	}

	@Override
	public List<RoleMenu> findList(String roleid) {
		return roleMenuMapper.findList(roleid);
	}
	
}
