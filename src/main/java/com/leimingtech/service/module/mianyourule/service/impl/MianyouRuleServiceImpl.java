package com.leimingtech.service.module.mianyourule.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.MianYou;
import com.leimingtech.core.entity.base.MianyouRule;
import com.leimingtech.service.module.mianyou.dao.MianYouDao;
import com.leimingtech.service.module.mianyourule.dao.MianyouRuleDao;
import com.leimingtech.service.module.mianyourule.service.MianyouRuleService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 满免邮活动规则ServiceImpl
 * 
 * @author admin
 * @version 2015-12-22
 */
@Service
public class MianyouRuleServiceImpl implements MianyouRuleService {

	/** 满免邮活动规则DAO接口 */
	@Resource
	private MianyouRuleDao mianyouRuleDao;

	@Resource
	private MianYouDao mianYouDao;

	/**
	 * 查询分页满免邮活动规则数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findMianyouRulePagerList(Pager pager) {
		List<MianyouRule> list = mianyouRuleDao.findMianyouRulePagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过ruleId获取单条满免邮活动规则数据
	 * 
	 * @param ruleId
	 * @return
	 */
	@Override
	public MianyouRule findMianyouRuleByRuleId(String ruleId) {
		return mianyouRuleDao.findMianyouRuleByRuleId(ruleId);
	}

	/**
	 * 通过ruleId删除满免邮活动规则数据
	 * 
	 * @param ruleId
	 */
	@Override
	public void deleteMianyouRuleByRuleId(String ruleId) {
		mianyouRuleDao.deleteMianyouRuleByRuleId(ruleId);
	}

	/**
	 * 修改满免邮活动规则数据
	 * 
	 * @param mianyouRule
	 */
	@Override
	public void updateMianyouRule(MianyouRule mianyouRule) {
		mianyouRuleDao.updateMianyouRule(mianyouRule);
	}

	/**
	 * 保存满免邮活动规则数据
	 * 
	 * @param mianyouRule
	 */
	@Override
	public void saveMianyouRule(MianyouRule mianyouRule) {
		mianyouRuleDao.saveMianyouRule(mianyouRule);
	}

	/**
	 * 获取所有满免邮活动规则数据
	 * 
	 * @return
	 */
	@Override
	public List<MianyouRule> findMianyouRuleAllList() {
		return mianyouRuleDao.findMianyouRuleAllList();
	}

	@Override
	public List<MianyouRule> findMianyouRuleByMianyouId(String mianyouId) {
		return mianyouRuleDao.findMianyouRuleByMianyouId(mianyouId);
	}

	@Override
	public MianyouRule findCurrSingleRule(String storeId, BigDecimal price) {
		// 查询可以使用满免邮查询实体
		MianYou mianYou = new MianYou();
		mianYou.setStoreId(storeId);
		mianYou.setEndTime(System.currentTimeMillis());

		MianYou mianyou = mianYouDao.findStoreCurrentMianyou(mianYou);
		if (mianyou == null) {
			return null;
		}
		List<MianyouRule> mianyouRuleList = mianyouRuleDao.findMianyouRuleByMianyouId(mianyou.getMianyouId());
		MianyouRule mianyouRule = null;
		for (int i = 0; i < mianyouRuleList.size(); i++) {
			MianyouRule msRule = mianyouRuleList.get(i);
			if (price.compareTo(msRule.getPrice()) == -1) {
				break;
			}
			mianyouRule = mianyouRuleList.get(i);
		}
		return mianyouRule;
	}

}