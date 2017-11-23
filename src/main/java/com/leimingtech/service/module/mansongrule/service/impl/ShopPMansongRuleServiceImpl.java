package com.leimingtech.service.module.mansongrule.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.ShopPMansong;
import com.leimingtech.core.entity.base.ShopPMansongRule;
import com.leimingtech.service.module.mansong.dao.ShopPMansongDao;
import com.leimingtech.service.module.mansongrule.dao.ShopPMansongRuleDao;
import com.leimingtech.service.module.mansongrule.service.ShopPMansongRuleService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 满就送活动规则ServiceImpl
 *
 * @author linjm
 * @version 2015-11-19
 */
@Service
public class ShopPMansongRuleServiceImpl implements ShopPMansongRuleService {

	/** 满就送活动规则DAO接口 */
	@Resource
	private ShopPMansongRuleDao shopPMansongRuleDao;

	/** 满就送DAO接口 */
	@Resource
	private ShopPMansongDao shopPMansongDao;

	/**
	 * 查询分页满就送活动规则数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopPMansongRulePagerList(Pager pager) {
		List<ShopPMansongRule> list = shopPMansongRuleDao.findShopPMansongRulePagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过id获取单条满就送活动规则数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ShopPMansongRule findShopPMansongRuleById(String id) {
		return shopPMansongRuleDao.findShopPMansongRuleById(id);
	}

	/**
	 * 通过id删除满就送活动规则数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopPMansongRuleById(String id) {
		shopPMansongRuleDao.deleteShopPMansongRuleById(id);
	}

	/**
	 * 修改满就送活动规则数据
	 * 
	 * @param shopPMansongRule
	 */
	@Override
	public void updateShopPMansongRule(ShopPMansongRule shopPMansongRule) {
		shopPMansongRuleDao.updateShopPMansongRule(shopPMansongRule);
	}

	/**
	 * 保存满就送活动规则数据
	 * 
	 * @param shopPMansongRule
	 */
	@Override
	public void saveShopPMansongRule(ShopPMansongRule shopPMansongRule) {
		shopPMansongRuleDao.saveShopPMansongRule(shopPMansongRule);
	}

	/**
	 * 获取所有满就送活动规则数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopPMansongRule> findShopPMansongRuleAllList() {
		return shopPMansongRuleDao.findShopPMansongRuleAllList();
	}

	@Override
	public List<ShopPMansongRule> findShopPMansongRuleByMansongid(String mansongId) {
		return shopPMansongRuleDao.findShopPMansongRuleByMansongid(mansongId);
	}

	/**
	 * 通过满送id删除对应的规则
	 */
	@Override
	public void deleteShopPMansongRuleByMansongid(String id) {
		shopPMansongRuleDao.deleteShopPMansongRuleByMansongid(id);
	}

	@Override
	public ShopPMansongRule findCurrSingleRule(String storeId, BigDecimal price) {
		ShopPMansong mansong = shopPMansongDao.findStoreCurrentMansong(storeId, System.currentTimeMillis());
		if (mansong == null) {
			return null;
		}
		List<ShopPMansongRule> mansongRuleList = shopPMansongRuleDao
				.findShopPMansongRuleByMansongid(mansong.getMansongId());
		ShopPMansongRule mansongRule = null;
		for (int i = 0; i < mansongRuleList.size(); i++) {
			ShopPMansongRule msRule = mansongRuleList.get(i);
			if (price.compareTo(msRule.getPrice()) == -1) {
				break;
			}
			mansongRule = mansongRuleList.get(i);
		}
		return mansongRule;
	}

}