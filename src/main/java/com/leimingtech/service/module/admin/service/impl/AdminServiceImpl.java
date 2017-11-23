package com.leimingtech.service.module.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Admin;
import com.leimingtech.core.entity.vo.AdminVo;
import com.leimingtech.service.module.admin.dao.AdminDao;
import com.leimingtech.service.module.admin.service.AdminService;
import com.leimingtech.service.utils.page.Pager;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminDao adminDao;
	
	public Admin findByAdminName(String adminName){

		return adminDao.findByAdminName(adminName);
	}

	public int findAdminCount(Admin admin) {
		return adminDao.findAdminCount(admin);
	}

	public Pager findAdminList(Pager pager) {
		List<AdminVo> list=adminDao.findAdminList(pager);
		pager.setResult(list);
		return pager;
	}


	@Override
	public void update(Admin admin) {
		adminDao.update(admin);
	}
	
	@Override
	public void save(Admin admin) {
		// TODO Auto-generated method stub
		adminDao.save(admin);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		adminDao.delete(id);
	}

	@Override
	public Admin findAdminById(String id) {
		// TODO Auto-generated method stub
		Admin admin = adminDao.findAdminById(id);
		return admin;
	}

}
