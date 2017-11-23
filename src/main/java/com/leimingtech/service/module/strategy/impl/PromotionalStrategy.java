/**
 * 
 */
package com.leimingtech.service.module.strategy.impl;

import com.leimingtech.service.module.strategy.IStrategy;
import com.leimingtech.service.module.strategy.vo.StrategyCondition;


/**
 * <p>Title: PromotionalStrategy.java</p>
 * <p>Description: 满 xxx 免邮费</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
public class PromotionalStrategy implements IStrategy{

	@Override
	public double realPrice(double consumPrice, StrategyCondition condition) {
		if(consumPrice > condition.getStartValue()){
			//return consumPrice - condition.getPromoteValue();
			
			return consumPrice ;
		}else{
			return consumPrice + condition.getOrderFreight();
		}
	}

}
