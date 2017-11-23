package com.leimingtech.service.module.joininlog.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.JoininLog;
import com.leimingtech.service.module.joininlog.dao.JoininLogDao;
import com.leimingtech.service.module.joininlog.dao.mapper.JoininLogMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商家入驻流程日志daoImpl
 *
 * @author zhubo
 * @version 2016-07-14
 */
@Repository
public class JoininLogDaoImpl implements JoininLogDao {
	
	/** 商家入驻流程日志mapper接口*/
	@Resource
	private JoininLogMapper JoininLogMapper;
	
	/**
	 * 查询分页商家入驻流程日志数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<JoininLog> findJoininLogPagerList(Pager pager){
		return JoininLogMapper.findJoininLogPagerList(pager);
	}

	/**
	 * 通过joininLogId获取单条商家入驻流程日志数据
	 * 
	 * @param joininLogId
	 * @return
	 */
	@Override 
	public JoininLog findJoininLogByJoininLogId(String joininLogId){
		return JoininLogMapper.findJoininLogByJoininLogId(joininLogId);
	}

	/**
	 * 通过joininLogId删除商家入驻流程日志数据
	 * 
	 * @param joininLogId
	 */
	@Override
	public void deleteJoininLogByJoininLogId(String joininLogId){
		JoininLogMapper.deleteJoininLogByJoininLogId(joininLogId);
	}

	/**
	 * 修改商家入驻流程日志数据
	 * 
	 * @param JoininLog
	 */
	@Override
	public void updateJoininLog(JoininLog JoininLog){
		JoininLogMapper.updateJoininLog(JoininLog);
	}
	/**
	 * 保存商家入驻流程日志数据
	 * 
	 * @param JoininLog
	 */
	@Override
	public void saveJoininLog(JoininLog JoininLog){
		JoininLog.setJoininLogId(IdGen.uuid());
		JoininLogMapper.saveJoininLog(JoininLog);
	}
	/**
	 * 获取所有商家入驻流程日志数据
	 * 
	 * @return
	 */
	@Override
	public List<JoininLog> findJoininLogAllList(){
		return JoininLogMapper.findJoininLogAllList();
	}

	@Override
	public List<JoininLog> findJoininLogsByEntity(JoininLog JoininLog) {
		return JoininLogMapper.findJoininLogsByEntity(JoininLog);
	}

}