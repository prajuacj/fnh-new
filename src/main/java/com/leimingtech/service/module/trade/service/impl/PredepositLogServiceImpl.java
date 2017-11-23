package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.PredepositLog;
import com.leimingtech.service.module.trade.dao.PredepositLogDao;
import com.leimingtech.service.module.trade.service.PredepositLogService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 预存款变更日志表
 * 
 * @author liukai
 */
@Service
public class PredepositLogServiceImpl implements PredepositLogService {

	@Resource
	private PredepositLogDao predepositLogDao;

	/**
	 * 保存预存款变更日志表
	 * 
	 * @param predepositLog
	 */
	public void savePdl(PredepositLog predepositLog) {
		predepositLogDao.savePdl(predepositLog);
	}

	/**
	 * 修改预存款变更日志表
	 * 
	 * @param predepositLog
	 */
	public void updatePdl(PredepositLog predepositLog) {
		predepositLogDao.updatePdl(predepositLog);
	}

	/**
	 * 通过id删除预存款变更日志表
	 * 
	 * @param lgId
	 */
	public void deletePdl(String lgId) {
		predepositLogDao.deletePdl(lgId);
	}

	/**
	 * 通过id查找预存款变更日志表
	 * 
	 * @param lgId
	 * @return
	 */
	public PredepositLog findPdlById(String lgId) {
		return predepositLogDao.findPdlById(lgId);
	}

	/**
	 * 根据用户id查询预存款变更日志表
	 * 
	 * @param lgMemberId
	 * @return
	 */
	public List<PredepositLog> findByMemberId(String lgMemberId) {
		return predepositLogDao.findByMemberId(lgMemberId);
	}

	/**
	 * 分页按条件查询预存款变更日志表
	 * 
	 * @param pager
	 * @return
	 */
	public Pager findByPager(Pager pager) {
		List<PredepositLog> list = predepositLogDao.findByPager(pager);
		pager.setResult(list);
		return pager;
	}

}
