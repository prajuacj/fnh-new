package com.leimingtech.service.module.promote.impl;

import com.leimingtech.core.common.SpringContextUtil;
import com.leimingtech.core.entity.base.MianyouRule;
import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.mianyourule.service.MianyouRuleService;
import com.leimingtech.service.module.promote.IPromote;
import com.leimingtech.service.module.promote.common.PromoteCondition;

import java.math.BigDecimal;

/**
 * 满免邮
 * @author linjianmao
 *
 */
public class ManYou implements IPromote {

	private MianyouRuleService mianyouRuleService = SpringContextUtil.getBean(MianyouRuleService.class);

	@Override
	public void calculate(OrderVo orderVo, Object obj) {
		MianyouRule mianyouRule = mianyouRuleService.findCurrSingleRule(orderVo.getStoreId() , orderVo.getGoodsAmount());
		if(mianyouRule==null){
			orderVo.setPromoPrice(BigDecimal.valueOf(0.00));
		}else{
			orderVo.setPromoPrice(orderVo.getShippingFee());
		}
	}

}
