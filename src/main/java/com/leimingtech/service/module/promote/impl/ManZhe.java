package com.leimingtech.service.module.promote.impl;

import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.promote.IPromote;
import com.leimingtech.service.module.promote.common.PromoteCondition;

/**
 * 满打折
 * @author linjianmao
 */
public class ManZhe implements IPromote {
	

	@Override
	public void calculate(OrderVo orderVo, Object obj) {
		//计算自己优惠的逻辑
	}

}
