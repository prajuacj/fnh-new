package com.leimingtech.service.module.joininlog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.JoininLog;
import com.leimingtech.service.module.joininlog.dao.JoininLogDao;
import com.leimingtech.service.module.joininlog.service.JoininLogService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 商家入驻流程日志ServiceImpl
 *
 * @author zhubo
 * @version 2016-07-14
 */
@Service
public class JoininLogServiceImpl implements JoininLogService {

	/** 商家入驻流程日志DAO接口 */
	@Resource
	private JoininLogDao joininLogDao;

	/**
	 * 查询分页商家入驻流程日志数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findJoininLogPagerList(Pager pager) {
		List<JoininLog> list = joininLogDao.findJoininLogPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过joininLogId获取单条商家入驻流程日志数据
	 * 
	 * @param joininLogId
	 * @return
	 */
	@Override
	public JoininLog findJoininLogByJoininLogId(String joininLogId) {
		return joininLogDao.findJoininLogByJoininLogId(joininLogId);
	}

	/**
	 * 通过joininLogId删除商家入驻流程日志数据
	 * 
	 * @param joininLogId
	 */
	@Override
	public void deleteJoininLogByJoininLogId(String joininLogId) {
		joininLogDao.deleteJoininLogByJoininLogId(joininLogId);
	}

	/**
	 * 修改商家入驻流程日志数据
	 * 
	 * @param JoininLog
	 */
	@Override
	public void updateJoininLog(JoininLog JoininLog) {
		joininLogDao.updateJoininLog(JoininLog);
	}

	/**
	 * 保存商家入驻流程日志数据
	 * 
	 * @param JoininLog
	 */
	@Override
	public void saveJoininLog(JoininLog JoininLog) {
		joininLogDao.saveJoininLog(JoininLog);
	}

	/**
	 * 获取所有商家入驻流程日志数据
	 * 
	 * @return
	 */
	@Override
	public List<JoininLog> findJoininLogAllList() {
		return joininLogDao.findJoininLogAllList();
	}

	/**
	 * 根据实体类来查找日志列表
	 * 
	 * @param JoininLog
	 * @return
	 */
	@Override
	public List<JoininLog> findJoininLogsByEntity(JoininLog JoininLog) {
		return joininLogDao.findJoininLogsByEntity(JoininLog);
	}

}