package com.leimingtech.service.module.admin.dao;


import com.leimingtech.core.entity.base.Admin;
import com.leimingtech.core.entity.vo.AdminVo;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：AdminDao   
 * 类描述：接口
 * 修改备注：   
 * @version    
 *
 */
public interface AdminDao {
    
	/**
     * 根据登录名查找用户
     * @return
     */
    Admin findByAdminName(String adminName);
    
	int findAdminCount(Admin admin);
    
    List<AdminVo> findAdminList(Pager pager);
    
    Admin findAdminById(@Param("id") String id);
    
    void save(Admin admin);
    
    void update(Admin admin);
    
    void delete(String id);
    
}