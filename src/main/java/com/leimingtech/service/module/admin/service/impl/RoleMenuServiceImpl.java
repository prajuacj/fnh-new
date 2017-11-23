package com.leimingtech.service.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.RoleMenu;
import com.leimingtech.core.entity.vo.AdminVo;
import com.leimingtech.service.module.admin.dao.RoleMenuDao;
import com.leimingtech.service.module.admin.service.RoleMenuService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class RoleMenuServiceImpl implements RoleMenuService{
	
	@Resource
	private RoleMenuDao roleMenuDao;

	@Override
	public Integer countRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return roleMenuDao.countRoleMenu(roleMenu);
	}

	@Override
	public Pager findRoleMenuList(Pager pager) {
		// TODO Auto-generated method stub
		List<RoleMenu> list=roleMenuDao.findRoleMenuList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public int deleteRoleMenu(String id) {
		// TODO Auto-generated method stub
		return roleMenuDao.deleteRoleMenu(id);
	}

	@Override
	public int saveRoleMenu(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return roleMenuDao.saveRoleMenu(roleMenu);
	}

	@Override
	public void updateState(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		roleMenuDao.updateState(roleMenu);
		
	}

	@Override
	public RoleMenu findRoleMenuById(String id) {
		// TODO Auto-generated method stub
		return roleMenuDao.findRoleMenuById(id);
	}

	@Override
	public int findCount(RoleMenu roleMenu) {
		// TODO Auto-generated method stub
		return roleMenuDao.findCount(roleMenu);
	}

	@Override
	public List<RoleMenu> findList(String roleid) {
		return roleMenuDao.findList(roleid);
	}

}
