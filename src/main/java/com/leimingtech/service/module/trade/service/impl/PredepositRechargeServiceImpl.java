package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.PredepositRecharge;
import com.leimingtech.service.module.trade.dao.PredepositRechargeDao;
import com.leimingtech.service.module.trade.service.PredepositRechargeService;

/**
 * 预存款充值表
 * @author liukai
 */
@Service
public class PredepositRechargeServiceImpl implements PredepositRechargeService{
	
	@Resource
	private PredepositRechargeDao predepositRechargeDao;
	
	/**
	 * 保存预存款充值表
	 * @param predepositRecharge
	 */
	public void savePdr(PredepositRecharge predepositRecharge){
		predepositRechargeDao.savePdr(predepositRecharge);
	}
	
	/**
	 * 根据id删除预存款充值表
	 * @param pdrId
	 */
	public void deletePdr(String pdrId){
		predepositRechargeDao.deletePdr(pdrId);
	}
	
	/**
	 * 修改预存款充值表
	 * @param predepositRecharge
	 */
	public void updatePdr(PredepositRecharge predepositRecharge){
		predepositRechargeDao.updatePdr(predepositRecharge);
	}
	
	/**
	 * 根据id查询预存款充值表
	 * @param pdrId
	 * @return
	 */
	public PredepositRecharge findById(String pdrId){
		return predepositRechargeDao.findById(pdrId);
	}
	
	/**
	 * 根据用户id查询预存款充值表
	 * @param pdrMemberId
	 * @return
	 */
	public List<PredepositRecharge> findByMemberId(String pdrMemberId){
		return predepositRechargeDao.findByMemberId(pdrMemberId);
	}
	
	/**
	 * 根据充值表编号查询预存款充值表信息
	 * @param pdrSn
	 * @return
	 */
	public PredepositRecharge findByPdrSn(String pdrSn){
		return predepositRechargeDao.findByPdrSn(pdrSn);
	}
}
