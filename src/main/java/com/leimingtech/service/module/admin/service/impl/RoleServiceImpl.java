package com.leimingtech.service.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.RoleMenu;
import com.leimingtech.core.entity.base.Roles;
import com.leimingtech.service.module.admin.dao.RoleDao;
import com.leimingtech.service.module.admin.service.RoleService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Resource
	private RoleDao roleDao;

	@Override
	public Integer countShopRole(Roles shopRole) {
		// TODO Auto-generated method stub
		return roleDao.countShopRole(shopRole);
	}

	@Override
	public Pager findShopRoleList(Pager pager) {
		// TODO Auto-generated method stub
		List<Roles> list=roleDao.findShopRoleList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public int deleteShopRole(String id) {
		// TODO Auto-generated method stub
		return roleDao.deleteShopRole(id);
	}

	@Override
	public int saveShopRole(Roles shopRole) {
		// TODO Auto-generated method stub
		shopRole.setCreateTime(System.currentTimeMillis());
		return roleDao.saveShopRole(shopRole);
	}

	@Override
	public void updateState(Roles shopRole) {
		roleDao.updateState(shopRole);	
	}

	@Override
	public Roles findShopRoleById(String id) {
		// TODO Auto-generated method stub
		return roleDao.findShopRoleById(id);
	}

	@Override
	public int findCount(Roles shopRole) {
		// TODO Auto-generated method stub
		return roleDao.findCount(shopRole);
	}

	@Override
	public List<Roles> findList() {
		return roleDao.findList();
	}
}
