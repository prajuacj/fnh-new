package com.leimingtech.service.module.promote.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.common.ParamsUtils;
import com.leimingtech.core.entity.base.MianYou;
import com.leimingtech.core.entity.base.MianyouRule;
import com.leimingtech.core.entity.base.ShopPMansong;
import com.leimingtech.core.entity.base.ShopPMansongRule;
import com.leimingtech.core.entity.vo.OrderVo;
import com.leimingtech.service.module.mansong.service.ShopPMansongService;
import com.leimingtech.service.module.mansongrule.service.ShopPMansongRuleService;
import com.leimingtech.service.module.mianyou.service.MianYouService;
import com.leimingtech.service.module.mianyourule.service.MianyouRuleService;
import com.leimingtech.service.module.promote.context.PromoteContext;
import com.leimingtech.service.module.promote.impl.ManJian;
import com.leimingtech.service.module.promote.impl.ManYou;
import com.leimingtech.service.module.promote.service.PromoteService;
import com.leimingtech.service.module.strategy.common.StrategyTypes;

@Service
public class PromoteServiceImpl implements PromoteService{

	/** 免邮Service **/
	@Resource
	private MianYouService mianYouService;
	
	/** 免邮Service **/
	@Resource
	private MianyouRuleService mianyouRuleService;

	/** 满就送Service接口 **/
	@Resource
	private ShopPMansongService shopPMansongService;
	
	/** 满就送规则 **/
	@Resource
	private ShopPMansongRuleService shopPMansongRuleService;


	@Override
	public void calcuPrice(OrderVo orderVo, Object obj) {
		//实例化促销上下文
		PromoteContext context = new PromoteContext();
		boolean flag = true;
		//满即减
		ShopPMansong mansong = shopPMansongService.findStoreCurrentMansong(orderVo.getStoreId(), System.currentTimeMillis());
		if(mansong != null ){
			context.setPromote(new ManJian());
			orderVo.setPromotType(StrategyTypes.REDUCE_STRATEGY);
			flag = false;
		}
		if(flag){
			//满免邮
			MianYou mianyou = mianYouService.findStoreCurrentMianyou(orderVo.getStoreId(), System.currentTimeMillis());
			if(mianyou != null){
				context.setPromote(new ManYou());
				orderVo.setPromotType(StrategyTypes.PROMOTIONAL_STRATEGY);
				flag = false;
			}
		}
		if(!flag)
			context.calculate(orderVo, obj);
	}


	@Override
	public String promoteMessage(String storeId, String totalPrice) {
			String message = "";
			boolean flag = true;
			//满即减
			ShopPMansong mansong = shopPMansongService.findStoreCurrentMansong(storeId, System.currentTimeMillis());
			if(mansong != null ){
				message = manSong(storeId , totalPrice , mansong);
				flag = false;
			}
			if(flag){
				//满免邮
				MianYou mianyou = mianYouService.findStoreCurrentMianyou(storeId, System.currentTimeMillis());
				if(mianyou != null){
					message = mianYou(storeId ,totalPrice ,  mianyou);
					flag = false;
				}
			}
		return message;
	}
	
	/**
	 * 返回满送信息
	 * @param storeId
	 * @param totalPrice
	 * @return
	 */
	private String manSong(String storeId , String totalPrice ,ShopPMansong mansong){
			double price = ParamsUtils.getDouble(totalPrice);
			String ms="";
			if(mansong ==null){
				return ms;
			};
			List<ShopPMansongRule> mansongRuleList = null;
			if(!storeId.equals("-1")){
				mansongRuleList = shopPMansongRuleService.findShopPMansongRuleByMansongid(mansong.getMansongId());
					for(int i=0 ; i<mansongRuleList.size(); i++){
						ShopPMansongRule mansongRule = mansongRuleList.get(i);
						if(mansongRuleList.size()>i+1){
							ms += "满"+mansongRule.getPrice()+"减"+mansongRule.getDiscount()+"，";
						}else{
							ms += "满"+mansongRule.getPrice()+"减"+mansongRule.getDiscount();
						}
					}
			}
			//具体的优惠信息  如：totalPrice ＝ 100 返回 满100减10块
			if(mansongRuleList.size() > 0 && price!=0){
				BigDecimal p = BigDecimal.valueOf(price);
				ShopPMansongRule mansongRule = null;
				for(int i=0 ; i< mansongRuleList.size(); i++){
					ShopPMansongRule msRule = mansongRuleList.get(i);
					if(p.compareTo(msRule.getPrice()) == -1){
						break;
					}
					mansongRule = mansongRuleList.get(i);
				}
				if(mansongRule!=null)
				ms = "满"+mansongRule.getPrice()+"减"+mansongRule.getDiscount();
			}
		return ms;
	}
	
	/**
	 * 返回免邮信息
	 * @param storeId
	 * @param totalPrice
	 * @param mianyou
	 * @return String message 
	 */
	private String mianYou(String storeId , String totalPrice , MianYou mianyou){
		//double price = ParamsUtils.getDouble(totalPrice);
		//BigDecimal p = BigDecimal.valueOf(price);
		List<MianyouRule> mianyouRuleList = mianyouRuleService.findMianyouRuleByMianyouId(mianyou.getMianyouId());//findCurrSingleRule(storeId , p);
		String ms  = "";
		if(mianyouRuleList.size() > 0){
			//免邮只有一条规则
			MianyouRule m = mianyouRuleList.get(0);
			ms = "满"+m.getPrice()+"免邮费";
		}
		return ms;
	}
	
}
