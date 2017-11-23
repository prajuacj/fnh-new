/**
 * 
 */
package com.leimingtech.service.module.strategy.impl;

import com.leimingtech.service.module.strategy.IStrategy;
import com.leimingtech.service.module.strategy.vo.StrategyCondition;

/**
 * <p>Title: ReduceStratery.java</p>
 * <p>Description: 满 xxxx减 xxx</p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
public class ReduceStrategy implements IStrategy {

	@Override
	public double realPrice(double consumPrice, StrategyCondition condition) {
		if(consumPrice>condition.getStartValue() && condition.getPromoteValue() < consumPrice){
			return consumPrice - condition.getPromoteValue() + condition.getOrderFreight();
		}else{
			return consumPrice + condition.getOrderFreight();
		}
	}

}
