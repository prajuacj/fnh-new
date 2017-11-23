package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.PredepositLog;
import com.leimingtech.service.module.trade.dao.PredepositLogDao;
import com.leimingtech.service.module.trade.dao.mapper.PredepositLogMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 预存款变更日志表
 * @author liukai
 */
@Repository
public class PredepositLogDaoImpl implements PredepositLogDao{
	
	@Resource
	private PredepositLogMapper predepositLogMapper;
	/**
	 * 保存预存款变更日志表
	 * @param predepositLog
	 */
	public void savePdl(PredepositLog predepositLog){
		predepositLog.setLgId(IdGen.uuid());
		predepositLogMapper.savePdl(predepositLog);
	}
	
	/**
	 * 修改预存款变更日志表
	 * @param predepositLog
	 */
	public void updatePdl(PredepositLog predepositLog){
		predepositLogMapper.updatePdl(predepositLog);
	}
	
	/**
	 * 通过id删除预存款变更日志表
	 * @param lgId
	 */
	public void deletePdl(String lgId){
		predepositLogMapper.deletePdl(lgId);
	}
	
	/**
	 * 通过id查找预存款变更日志表
	 * @param lgId
	 * @return
	 */
	public PredepositLog findPdlById(String lgId){
		return predepositLogMapper.findPdlById(lgId);
	}
	
	/**
	 * 根据用户id查询预存款变更日志表
	 * @param lgMemberId
	 * @return
	 */
	public List<PredepositLog> findByMemberId(String lgMemberId){
		return predepositLogMapper.findByMemberId(lgMemberId);
	}
	
	/**
	 * 分页按条件查询预存款变更日志表
	 * @param pager
	 * @return
	 */
	public List<PredepositLog> findByPager(Pager pager){
		return predepositLogMapper.findByPager(pager);
	}
}
