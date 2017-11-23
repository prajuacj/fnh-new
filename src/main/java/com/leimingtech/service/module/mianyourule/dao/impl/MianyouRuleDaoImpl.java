package com.leimingtech.service.module.mianyourule.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.MianyouRule;
import com.leimingtech.service.module.mianyourule.dao.MianyouRuleDao;
import com.leimingtech.service.module.mianyourule.dao.mapper.MianyouRuleMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 满免邮活动规则daoImpl
 * @author admin
 * @version 2015-12-22
 */
@Repository
public class MianyouRuleDaoImpl implements MianyouRuleDao {
	
	/** 满免邮活动规则mapper接口*/
	@Resource
	private MianyouRuleMapper mianyouRuleMapper;
	
	/**
	 * 查询分页满免邮活动规则数据
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<MianyouRule> findMianyouRulePagerList(Pager pager){
		return mianyouRuleMapper.findMianyouRulePagerList(pager);
	}

	/**
	 * 通过ruleId获取单条满免邮活动规则数据
	 * @param ruleId
	 * @return
	 */
	@Override 
	public MianyouRule findMianyouRuleByRuleId(String ruleId){
		return mianyouRuleMapper.findMianyouRuleByRuleId(ruleId);
	}

	/**
	 * 通过ruleId删除满免邮活动规则数据
	 * @param ruleId
	 */
	@Override
	public void deleteMianyouRuleByRuleId(String ruleId){
		mianyouRuleMapper.deleteMianyouRuleByRuleId(ruleId);
	}

	/**
	 * 修改满免邮活动规则数据
	 * @param mianyouRule
	 */
	@Override
	public void updateMianyouRule(MianyouRule mianyouRule){
		mianyouRuleMapper.updateMianyouRule(mianyouRule);
	}
	
	/**
	 * 保存满免邮活动规则数据
	 * @param mianyouRule
	 */
	@Override
	public void saveMianyouRule(MianyouRule mianyouRule){
		mianyouRule.setRuleId(IdGen.uuid());
		mianyouRuleMapper.saveMianyouRule(mianyouRule);
	}
	
	/**
	 * 获取所有满免邮活动规则数据
	 * @return
	 */
	@Override
	public List<MianyouRule> findMianyouRuleAllList(){
		return mianyouRuleMapper.findMianyouRuleAllList();
	}

	@Override
	public List<MianyouRule> findMianyouRuleByMianyouId(String mianyouId) {
		return mianyouRuleMapper.findMianyouRuleByMianyouId(mianyouId);
	}
	
}