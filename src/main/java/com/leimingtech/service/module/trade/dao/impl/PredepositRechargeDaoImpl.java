package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.PredepositRecharge;
import com.leimingtech.service.module.trade.dao.PredepositRechargeDao;
import com.leimingtech.service.module.trade.dao.mapper.PredepositRechargeMapper;

/**
 * 预存款充值表
 * @author liukai
 */
@Repository
public class PredepositRechargeDaoImpl implements PredepositRechargeDao{
	
	@Resource
	private PredepositRechargeMapper predepositRechargeMapper;
	
	/**
	 * 保存预存款充值表
	 * @param predepositRecharge
	 */
	public void savePdr(PredepositRecharge predepositRecharge){
		predepositRecharge.setPdrId(IdGen.uuid());
		predepositRechargeMapper.savePdr(predepositRecharge);
	}
	
	/**
	 * 根据id删除预存款充值表
	 * @param pdrId
	 */
	public void deletePdr(String pdrId){
		predepositRechargeMapper.deletePdr(pdrId);
	}
	
	/**
	 * 修改预存款充值表
	 * @param predepositRecharge
	 */
	public void updatePdr(PredepositRecharge predepositRecharge){
		predepositRechargeMapper.updatePdr(predepositRecharge);
	}
	
	/**
	 * 根据id查询预存款充值表
	 * @param pdrId
	 * @return
	 */
	public PredepositRecharge findById(String pdrId){
		return predepositRechargeMapper.findById(pdrId);
	}
	
	/**
	 * 根据用户id查询预存款充值表
	 * @param pdrMemberId
	 * @return
	 */
	public List<PredepositRecharge> findByMemberId(String pdrMemberId){
		return predepositRechargeMapper.findByMemberId(pdrMemberId);
	}
	
	/**
	 * 根据充值表编号查询预存款充值表信息
	 * @param pdrSn
	 * @return
	 */
	public PredepositRecharge findByPdrSn(String pdrSn){
		return predepositRechargeMapper.findByPdrSn(pdrSn);
	}

}
