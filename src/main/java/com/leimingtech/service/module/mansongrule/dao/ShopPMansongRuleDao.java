package com.leimingtech.service.module.mansongrule.dao;

import java.util.List;

import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.ShopPMansongRule;

/**
 * 满就送活动规则DAO接口
 *
 * @author linmj
 * @version 2015-11-19
 */
public interface ShopPMansongRuleDao {

	/**
	 * 查询分页满就送活动规则数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	List<ShopPMansongRule> findShopPMansongRulePagerList(Pager pager);

	/**
	 * 通过id获取单条满就送活动规则数据
	 * 
	 * @param id
	 * @return
	 */
	ShopPMansongRule findShopPMansongRuleById(String id);

	/**
	 * 通过id删除满就送活动规则数据
	 * 
	 * @param id
	 */
	void deleteShopPMansongRuleById(String id);

	/**
	 * 通过满就送的id删除该满送的对应规则
	 * @param id
	 */
	void deleteShopPMansongRuleByMansongid(String id);
	
	/**
	 * 修改满就送活动规则数据
	 * 
	 * @param shopPMansongRule
	 */
	void updateShopPMansongRule(ShopPMansongRule shopPMansongRule);

	/**
	 * 保存满就送活动规则数据
	 * 
	 * @param shopPMansongRule
	 */
	void saveShopPMansongRule(ShopPMansongRule shopPMansongRule);

	/**
	 * 获取所有满就送活动规则数据
	 * 
	 * @return
	 */
	List<ShopPMansongRule> findShopPMansongRuleAllList();

	/**
	 * 获取满送规则，通过满送的id
	 * @param mansongId
	 * @return
	 */
	List<ShopPMansongRule> findShopPMansongRuleByMansongid(String mansongId);
	
	/**
	 *  获取当前店铺满即送下的符合条件一条规则
	 * @return mansongRule
	 */
	ShopPMansongRule findManSongRule(ShopPMansongRule mansongRule);
	
}