package com.leimingtech.service.module.user.service.impl;

import com.google.common.collect.Maps;
import com.leimingtech.core.entity.Order;
import com.leimingtech.core.entity.base.EvaluateGoods;
import com.leimingtech.core.entity.base.EvaluateStore;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.OrderGoods;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.store.dao.EvaluateStoreDao;
import com.leimingtech.service.module.trade.dao.EvaluateGoodsDao;
import com.leimingtech.service.module.trade.dao.OrderDao;
import com.leimingtech.service.module.trade.dao.OrderGoodsDao;
import com.leimingtech.service.module.trade.service.OrderService;
import com.leimingtech.service.module.user.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author llf
 * @Package com.leimingtech.front.module.user.service.impl
 * @Description:
 * @date 2015/3/16 14:17
 */
@Service
public class EvaluateServiceImpl implements EvaluateService{

    @Resource
    private EvaluateGoodsDao evaluateGoodsDao;

    @Resource
    private EvaluateStoreDao evaluateStoreDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private OrderGoodsDao orderGoodsDao;
    
    @Resource
    private OrderService orderService;
    
    @Autowired
    private GoodsDao goodsDao;
    

    @Override
    public Map<String,Object> saveEvaluate(long orderSn, String recId,
                             			   EvaluateStore evaluateStore, EvaluateGoods evaluateGoods,
                                           String memberId,String memberName,String specInfo) {

        Order order = orderDao.findByOrderSn(orderSn+"");
//    	Order order = new Order();
        OrderGoods orderGoods = orderGoodsDao.findById(recId);
        Map<String,Object> map = Maps.newHashMap();
        if(order != null && orderGoods != null){
        	long nowtime = System.currentTimeMillis();
            //保存商品评价
            evaluateGoods.setGevalOrderId(orderGoods.getOrderId());
            evaluateGoods.setGevalOrderNo(orderSn);
            evaluateGoods.setGevalOrderGoodsId(recId);
            evaluateGoods.setGevalGoodsId(orderGoods.getGoodsId());
            evaluateGoods.setGevalGoodsName(orderGoods.getGoodsName());
            evaluateGoods.setGevalGoodsPrice(orderGoods.getGoodsPrice().doubleValue());
            evaluateGoods.setGevalStoreId(orderGoods.getStoreId());
            evaluateGoods.setGevalStorename(order.getStoreName());
            evaluateGoods.setGevalFrommemberid(memberId);
            evaluateGoods.setGevalFrommembername(memberName);
            evaluateGoods.setCreateTime(nowtime);
            evaluateGoods.setGevalState(0);
            evaluateGoods.setSpecInfo(specInfo);
            evaluateGoods.setGevalAddTime(nowtime);
            if(evaluateGoods.getGevalIsAnonymous()==1){
            	 evaluateGoods.setGevalFrommembername("匿名用户");
            }
            evaluateGoodsDao.saveEvaluate(evaluateGoods);
            //保存店铺评价
            evaluateStore.setSevalOrderId(order.getOrderId());
            evaluateStore.setSevalOrderNo(orderSn+"");
            evaluateStore.setSevalStoreId(orderGoods.getStoreId());
            evaluateStore.setSevalStoreName(order.getStoreName());
            evaluateStore.setSevalMemberId(memberId);
            evaluateStore.setSevalMemberName(memberName);
            evaluateStore.setSevalAddTime(System.currentTimeMillis());
            evaluateStore.setCreateTime(System.currentTimeMillis());
            evaluateStoreDao.save(evaluateStore);
            //修改订单是否评价状态
            orderService.updateEvaluationStatus(order,recId);
            
            //修改商品评价次数
            Goods goods = new Goods();
            goods.setGoodsId(orderGoods.getGoodsId());
            goods.setCommentnum(1);
            goodsDao.updateGoods(goods);
            
            map.put("success",true);
            map.put("msg","保存成功");
        }else{
            map.put("success",false);
            map.put("msg","订单异常");
        }

        return map;
    }


	@Override
	public Map<String, Object> saveEvaluate(long orderSn, String recId,
			EvaluateStore evaluateStore, EvaluateGoods evaluateGoods,
                                            String memberId, String memberName) {
	       Order order = orderDao.findByOrderSn(orderSn+"");
//	    	Order order = new Order();
	        OrderGoods orderGoods = orderGoodsDao.findById(recId);
	        Map<String,Object> map = Maps.newHashMap();
	        if(order != null && orderGoods != null){
	            //保存商品评价
	            evaluateGoods.setGevalOrderId(orderGoods.getOrderId());
	            evaluateGoods.setGevalOrderNo(orderSn);
	            evaluateGoods.setGevalOrderGoodsId(recId);
	            evaluateGoods.setGevalGoodsId(orderGoods.getGoodsId());
	            evaluateGoods.setGevalGoodsName(orderGoods.getGoodsName());
	            evaluateGoods.setGevalGoodsPrice(orderGoods.getGoodsPrice().doubleValue());
	            evaluateGoods.setGevalStoreId(orderGoods.getStoreId());
	            evaluateGoods.setGevalStorename(order.getStoreName());
	            evaluateGoods.setGevalFrommemberid(memberId);
	            evaluateGoods.setGevalFrommembername(memberName);
	            evaluateGoods.setCreateTime(System.currentTimeMillis());
	            evaluateGoods.setGevalState(0);
	            evaluateGoodsDao.saveEvaluate(evaluateGoods);
	            //保存店铺评价
	            evaluateStore.setSevalOrderId(order.getOrderId());
	            evaluateStore.setSevalOrderNo(orderSn+"");
	            evaluateStore.setSevalStoreId(orderGoods.getStoreId());
	            evaluateStore.setSevalStoreName(order.getStoreName());
	            evaluateStore.setSevalMemberId(memberId);
	            evaluateStore.setSevalMemberName(memberName);
	            evaluateStoreDao.save(evaluateStore);
	            //修改订单是否评价状态
	            orderService.updateEvaluationStatus(order,recId);
	            
	            //修改商品评价次数
	            Goods goods = new Goods();
	            goods.setGoodsId(orderGoods.getGoodsId());
	            goods.setCommentnum(1);
	            goodsDao.updateGoods(goods);
	            
	            map.put("success",true);
	            map.put("msg","保存成功");
	        }else{
	            map.put("success",false);
	            map.put("msg","订单异常");
	        }

	        return map;
	}
}
