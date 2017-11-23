package com.leimingtech.service.module.mianyourule.dao.mapper;

import com.leimingtech.core.entity.base.MianyouRule;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 满免邮活动规则mapper接口
 * @author admin
 * @version 2015-12-22
 */
@SqlMapper
public interface MianyouRuleMapper {

	/**
	 * 查询分页满免邮活动规则数据
	 * @param pager 分页对象
	 * @return
	 */
	public List<MianyouRule> findMianyouRulePagerList(Pager pager);

	/**
	 * 通过ruleId获取单条满免邮活动规则数据
	 * @param ruleId
	 * @return
	 */
	public MianyouRule findMianyouRuleByRuleId(String ruleId);

	/**
	 * 通过ruleId删除满免邮活动规则数据
	 * @param ruleId
	 */
	public void deleteMianyouRuleByRuleId(String ruleId);

	/**
	 * 修改满免邮活动规则数据
	 * @param mianyouRule
	 */
	public void updateMianyouRule(MianyouRule mianyouRule);

	/**
	 * 保存满免邮活动规则数据
	 * @param mianyouRule
	 */
	public void saveMianyouRule(MianyouRule mianyouRule);

	/**
	 * 获取所有满免邮活动规则数据
	 * 
	 * @return
	 */
	public List<MianyouRule> findMianyouRuleAllList();
	
	/**
	 * 根据id获取所有满免邮活动规则数据
	 * @return
	 */
	public List<MianyouRule> findMianyouRuleByMianyouId(String mianyouId);
	
}