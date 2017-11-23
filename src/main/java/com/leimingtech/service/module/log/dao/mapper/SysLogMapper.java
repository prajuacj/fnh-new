package com.leimingtech.service.module.log.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.SysLog;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;


/**
 * 项目名称：leimingtech-admin   
 * 类名称：SysLogMapper   
 * 创建人：linjm   
 * 创建时间：2014年11月14日 上午12:10:28    
 * @version    
 */
@SqlMapper
public interface SysLogMapper{
	 
	/**
	 * @Title: countSysLog 
	 * @Description: count总数查询
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
    public int countSysLog(SysLog SysLog);
    
    /**
     * @Title: querySysLogList 
     * @Description: 带分页list 查询
     * @param @param pager
     * @param @return    设定文件 
     * @return List<SysLog>    返回类型 
     * @throws
     */
    public List<SysLog> querySysLogList(Pager pager);
    
    /**
     * @Title: delete 
     * @Description: 根据id删除数据
     * @param @param id    设定文件 
     * @return void    返回类型 
     * @throws
     */
    public void delete(@Param("id") String id);
    
    /**
     * @Title: findLogById 
     * @Description: 根据ID 查询明细
     * @param @param id
     * @param @return    设定文件 
     * @return SysLog    返回类型 
     * @throws
     */
    public SysLog findSysLogById(@Param("id") String id);

    /**
     * 保存日志
     * @param SysLog
     */
    public void save(SysLog SysLog);
}
