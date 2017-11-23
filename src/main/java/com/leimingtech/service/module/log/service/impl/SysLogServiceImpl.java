package com.leimingtech.service.module.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.SysLog;
import com.leimingtech.service.module.log.dao.SysLogDao;
import com.leimingtech.service.module.log.service.SysLogService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目名称：leimingtech-admin 类名称：SysLogServiceImpl 创建人：linjm 创建时间：2014年11月14日
 * 上午12:10:28
 * 
 * @version
 */
@Slf4j
@Service
public class SysLogServiceImpl implements SysLogService {

	@Resource
	private SysLogDao SysLogDao;

	/**
	 * @Title: countSysLog @Description: TODO(count总数查询) @param @param
	 * pager @param @return 设定文件 @return int 返回类型 @throws
	 */
	public int countSysLog(SysLog sysLog) {
		log.info("获取log列表记录数");
		return SysLogDao.countSysLog(sysLog);
	}

	/**
	 * @Title: querySysLogList @Description: TODO(带分页list 查询) @param @param
	 * pager @param @return 设定文件 @return List<SysLog> 返回类型 @throws
	 */
	public Pager querySysLogList(Pager pager) {
		log.info("获取log列表List");
		List<SysLog> list = SysLogDao.querySysLogList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * @Title: delete @Description: TODO(根据id删除数据) @param @param id 设定文件 @return
	 * void 返回类型 @throws
	 */
	public void delete(String id) {
		SysLogDao.delete(id);
	}

	/**
	 * @Title: findLogById @Description: TODO(根据ID 查询明细) @param @param
	 * id @param @return 设定文件 @return SysLog 返回类型 @throws
	 */
	public SysLog findLogById(String id) {
		return SysLogDao.findSysLogById(id);
	}

	/**
	 * 保存日志
	 * 
	 * @param SysLog
	 */
	@Override
	public void save(SysLog SysLog) {
		SysLogDao.save(SysLog);
	}

}
