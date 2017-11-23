package com.leimingtech.service.module.log.dao;

import java.util.List;

import com.leimingtech.core.entity.base.SysLog;
import com.leimingtech.service.utils.page.Pager;

/**
 * 日志DAO
 * 项目名称：leimingtech-admin   
 * 类名称：OrderStatisDao   
 * 创建人：linjm   
 * 创建时间：2014年11月14日 上午12:10:28   
 * 修改备注：   
 * @version    
 */
public interface SysLogDao {

	/**
	 * @Title: countSysLog 
	 * @Description: TODO(count总数查询) 
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
    int countSysLog(SysLog sysLog);
    
    /**
     * @Title: querySysLogList 
     * @Description: TODO(带分页list 查询) 
     * @param @param pager
     * @param @return    设定文件 
     * @return List<SysLog>    返回类型 
     * @throws
     */
    List<SysLog> querySysLogList(Pager pager);
    
    /**
     * @Title: delete 
     * @Description: TODO(根据id删除数据) 
     * @param @param id    设定文件 
     * @return void    返回类型 
     * @throws
     */
    void delete(String id);
    
    /**
     * @Title: findLogById 
     * @Description: TODO(根据ID 查询明细) 
     * @param @param id
     * @param @return    设定文件 
     * @return SysLog    返回类型 
     * @throws
     */
    SysLog findSysLogById(String id);

    /**
     * 保存日志
     * @param sysLog
     */
    void save(SysLog sysLog);
}
