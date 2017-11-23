/**
 * 
 */
package com.leimingtech.service.module.calculate.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Promotion;
import com.leimingtech.core.entity.vo.PromotionClassVo;
import com.leimingtech.service.module.calculate.service.CalculateService;
import com.leimingtech.service.module.promotion.service.PromotionClassService;
import com.leimingtech.service.module.strategy.StrategyService;
import com.leimingtech.service.module.strategy.vo.StrategyCondition;

/**
 * <p>Title: CalculateServiceImpl.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月27日
 * @version 1.0
 */
@Service
public class CalculateServiceImpl implements CalculateService {
	
	
	@Resource
	private PromotionClassService promotionClassService;
	
	@Resource
	private StrategyService strategyService;

	@Override
	public double Calculate(double consumPrice ,StrategyCondition condition) {
		PromotionClassVo promotionVo = promotionClassService.findVoByUse();
		double price = consumPrice;
		if(promotionVo!=null){
			for(Promotion p :promotionVo.getPromotionList()){
				condition.setStartValue(p.getStartValue());
				
				//if(condition.getPromoteValue()>0) 
					condition.setPromoteValue(p.getPromoteValue());
				
				price = strategyService.realPrice(promotionVo.getPcId(), consumPrice, condition);
			}
		}
		return price;
	}

}
