package com.leimingtech.service.module.trade.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.ShopBarter;
import com.leimingtech.core.entity.vo.BarterDetailVo;
import com.leimingtech.service.module.trade.dao.ShopBarterDao;
import com.leimingtech.service.module.trade.dao.mapper.ShopBarterMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 换货表的增删改查daoImpl
 *
 * @author admin
 * @version 2015-12-22
 */
@Repository
public class ShopBarterDaoImpl implements ShopBarterDao {
	
	/** 换货表的增删改查mapper接口*/
	@Resource
	private ShopBarterMapper shopBarterMapper;
	
	/**
	 * 查询分页换货表的增删改查数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<ShopBarter> findShopBarterPagerList(Pager pager){
		return shopBarterMapper.findShopBarterPagerList(pager);
	}

	/**
	 * 通过barterId获取单条换货表的增删改查数据
	 * 
	 * @param barterId
	 * @return
	 */
	@Override 
	public ShopBarter findShopBarterByBarterId(String barterId){
		return shopBarterMapper.findShopBarterByBarterId(barterId);
	}

	/**
	 * 通过barterId删除换货表的增删改查数据
	 * 
	 * @param barterId
	 */
	@Override
	public void deleteShopBarterByBarterId(String barterId){
		shopBarterMapper.deleteShopBarterByBarterId(barterId);
	}

	/**
	 * 修改换货表的增删改查数据
	 * 
	 * @param shopBarter
	 */
	@Override
	public void updateShopBarter(ShopBarter shopBarter){
		shopBarterMapper.updateShopBarter(shopBarter);
	}
	/**
	 * 保存换货表的增删改查数据
	 * 
	 * @param shopBarter
	 */
	@Override
	public void saveShopBarter(ShopBarter shopBarter){
		shopBarter.setBarterId(IdGen.uuid());
		shopBarterMapper.saveShopBarter(shopBarter);
	}
	/**
	 * 获取所有换货表的增删改查数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopBarter> findShopBarterAllList(){
		return shopBarterMapper.findShopBarterAllList();
	}
    
	/**
	 * 查询货详情,必传换货id,可传用户id和店铺id,不需要传null
	 * @param refundReturn
	 * @return
	 */
	@Override
	public BarterDetailVo findBarterDetail(ShopBarter shopBarter) {
		return shopBarterMapper.findBarterDetail(shopBarter);
	}
	
}