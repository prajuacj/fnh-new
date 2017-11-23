package com.leimingtech.service.module.admin.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.Admin;
import com.leimingtech.core.entity.vo.AdminVo;
import com.leimingtech.service.module.admin.dao.AdminDao;
import com.leimingtech.service.module.admin.dao.mapper.AdminMapper;
import com.leimingtech.service.utils.page.Pager;


@Repository
public class AdminDaoImpl implements AdminDao {
	
    @Resource
    private AdminMapper adminMapper;

    /**
     *获取总条数
     */
    public int findAdminCount(Admin admin) {
        return adminMapper.findAdminCount(admin);
    }

    /**
     * 分页
     */
    public List<AdminVo> findAdminList(Pager pager) {
        return adminMapper.findAdminList(pager);
    }

    @Override
    public void save(Admin admin) {
        // TODO Auto-generated method stub
        admin.setAdminId(IdGen.uuid());
        adminMapper.save(admin);
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        adminMapper.delete(id);
    }

    @Override
    public void update(Admin admin) {
        // TODO Auto-generated method stub
        adminMapper.update(admin);
    }

    @Override
    public Admin findAdminById(String id) {
        // TODO Auto-generated method stub
        return adminMapper.findAdminById(id);
    }

    @Override
    public Admin findByAdminName(String adminName) {
        return adminMapper.findByAdminName(adminName);
    }

}
