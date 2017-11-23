package com.leimingtech.service.module.promote;

import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.promote.common.PromoteCondition;

public interface IPromote {
	
	/**
	 * 计算价格
	 * @param orderVo
	 * @param obj
	 */
	public void calculate(OrderVo orderVo , Object obj);

}
