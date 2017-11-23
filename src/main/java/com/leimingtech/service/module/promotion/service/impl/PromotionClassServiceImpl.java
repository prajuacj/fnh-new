/**
 * 
 */
package com.leimingtech.service.module.promotion.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Promotion;
import com.leimingtech.core.entity.base.PromotionClass;
import com.leimingtech.core.entity.vo.PromotionClassVo;
import com.leimingtech.service.module.promotion.dao.PromotionClassDao;
import com.leimingtech.service.module.promotion.service.PromotionClassService;
import com.leimingtech.service.utils.page.Pager;

/**
 * <p>
 * Title: PromotionClassServiceImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014-2018
 * </p>
 * <p>
 * Company: leimingtech.com
 * </p>
 * 
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@Service
public class PromotionClassServiceImpl implements PromotionClassService {

	@Resource
	private PromotionClassDao promotionClassDao;

	@Override
	public void save(PromotionClass promotionClass) {
		promotionClassDao.save(promotionClass);

	}

	@Override
	public void delete(String id) {
		promotionClassDao.delete(id);

	}

	@Override
	public void update(PromotionClass promotionClass) {
		promotionClassDao.update(promotionClass);

	}

	@Override
	public Pager findList(Pager pager) {
		List<PromotionClass> l = promotionClassDao.findList(pager);
		System.out.println("11" + l);
		pager.setResult(l);
		return pager;
	}

	@Override
	public int findCount(Pager pager) {

		return promotionClassDao.findCount(pager);
	}

	@Override
	public PromotionClass findById(String pcId) {

		return promotionClassDao.findById(pcId);
	}

	@Override
	public PromotionClassVo findVoByUse() {

		return promotionClassDao.findVoByUse();
	}

	/**
	 * 查询具体的促销方式描述,当前促销方式为全局,暂时查询全局,切有满减,包邮,打折三种方式
	 * 
	 * @return
	 */
	public String findMessage() {
		// 新建一个促销规则字段
		String promotionClass = "";
		PromotionClassVo promotionVo = promotionClassDao.findVoByUse();
		if (promotionVo != null) {
			List<Promotion> promotionList = promotionVo.getPromotionList();
			// switch (Integer.valueOf(promotionVo.getPcId())) {
			// case StrategyTypes.PROMOTIONAL_STRATEGY:
			// for(Promotion promotion:promotionList){
			// promotionClass = "满"+promotion.getStartValue()+"免运费";
			// }
			// break ;
			// case StrategyTypes.REDUCE_STRATEGY:
			// for(Promotion promotion:promotionList){
			// promotionClass =
			// "满"+promotion.getStartValue()+"减"+promotion.getPromoteValue();
			// }
			// break ;
			// case StrategyTypes.REBATE_STRATEGY:
			// for(Promotion promotion:promotionList){
			// promotionClass =
			// "满"+promotion.getStartValue()+"打"+promotion.getPromoteValue()*10+"折";
			// }
			// break;
			// default :
			// promotionClass="";
			// }
		}
		return promotionClass;
	}

}
