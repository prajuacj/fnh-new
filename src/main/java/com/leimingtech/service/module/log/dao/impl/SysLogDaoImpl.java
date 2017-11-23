package com.leimingtech.service.module.log.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.SysLog;
import com.leimingtech.service.module.log.dao.SysLogDao;
import com.leimingtech.service.module.log.dao.mapper.SysLogMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 项目名称：leimingtech-admin   
 * 类名称：OrderStatisDaoImpl   
 * 创建人：linjm   
 * 创建时间：2014年11月14日 上午12:10:28   
 * 修改备注：   
 * @version    
 */

@Repository
public class SysLogDaoImpl implements SysLogDao{

    @Resource
    private SysLogMapper SysLogMapper ;

	@Override
	public int countSysLog(SysLog sysLog) {
		return SysLogMapper.countSysLog(sysLog);
	}

	@Override
	public List<SysLog> querySysLogList(Pager pager) {
		return SysLogMapper.querySysLogList(pager);
	}

	@Override
	public void delete(String id) {
		SysLogMapper.delete(id);
	}

	@Override
	public SysLog findSysLogById(String id) {
		return SysLogMapper.findSysLogById(id);
	}

    /**
     * 保存日志
     * @param sysLog
     */
    @Override
    public void save(SysLog sysLog) {
		sysLog.setId(IdGen.uuid());
		SysLogMapper.save(sysLog);
    }

}
