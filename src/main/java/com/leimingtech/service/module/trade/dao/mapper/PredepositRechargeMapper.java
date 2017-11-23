package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.PredepositRecharge;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * 预存款充值表
 * @author liukai
 */
@SqlMapper
public interface PredepositRechargeMapper {
	
	/**
	 * 保存预存款充值表
	 * @param predepositRecharge
	 */
	void savePdr(PredepositRecharge predepositRecharge);
	
	/**
	 * 根据id删除预存款充值表
	 * @param pdrId
	 */
	void deletePdr(String pdrId);
	
	/**
	 * 修改预存款充值表
	 * @param predepositRecharge
	 */
	void updatePdr(PredepositRecharge predepositRecharge);
	
	/**
	 * 根据id查询预存款充值表
	 * @param pdrId
	 * @return
	 */
	PredepositRecharge findById(String pdrId);
	
	/**
	 * 根据用户id查询预存款充值表
	 * @param pdrMemberId
	 * @return
	 */
	List<PredepositRecharge> findByMemberId(String pdrMemberId);
	
	/**
	 * 根据充值表编号查询预存款充值表信息
	 * @param pdrSn
	 * @return
	 */
	PredepositRecharge findByPdrSn(String pdrSn);
}
