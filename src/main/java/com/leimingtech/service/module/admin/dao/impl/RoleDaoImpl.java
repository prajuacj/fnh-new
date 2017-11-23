package com.leimingtech.service.module.admin.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.Roles;
import com.leimingtech.service.module.admin.dao.RoleDao;
import com.leimingtech.service.module.admin.dao.mapper.RoleMapper;
import com.leimingtech.service.utils.page.Pager;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Resource
	private RoleMapper roleMapper;

	@Override
	public Integer countShopRole(Roles shopRole) {
		return roleMapper.countShopRole(shopRole);
	}

	@Override
	public List<Roles> findShopRoleList(Pager pager) {
		List<Roles> list = roleMapper.findShopRoleList(pager);
		return list;
	}

	@Override
	public int deleteShopRole(String id) {
		return roleMapper.deleteShopRole(id);
	}

	@Override
	public int saveShopRole(Roles shopRole) {
		shopRole.setId(IdGen.uuid());
		return roleMapper.saveShopRole(shopRole);
	}

	@Override
	public void updateState(Roles shopRole) {
		roleMapper.updateState(shopRole);
	}

	@Override
	public Roles findShopRoleById(String id) {
		return roleMapper.findShopRoleById(id);
	}

	@Override
	public int findCount(Roles shopRole) {
		return roleMapper.findCount(shopRole);
	}

	@Override
	public List<Roles> findList() {
		return roleMapper.findList();
	}

}
