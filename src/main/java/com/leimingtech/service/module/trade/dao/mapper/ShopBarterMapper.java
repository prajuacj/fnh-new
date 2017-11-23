package com.leimingtech.service.module.trade.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.core.entity.base.RefundReturn;
import com.leimingtech.core.entity.base.ShopBarter;
import com.leimingtech.core.entity.vo.BarterDetailVo;
import com.leimingtech.core.entity.vo.ReturnDetailVo;

/**
 * 换货表的增删改查mapper接口
 * 
 * @author admin
 * @version 2015-12-22
 */
@SqlMapper
public interface ShopBarterMapper {

	/**
	 * 查询分页换货表的增删改查数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<ShopBarter> findShopBarterPagerList(Pager pager);

	/**
	 * 通过barterId获取单条换货表的增删改查数据
	 * 
	 * @param barterId
	 * @return
	 */
	public ShopBarter findShopBarterByBarterId(String barterId);

	/**
	 * 通过barterId删除换货表的增删改查数据
	 * 
	 * @param barterId
	 */
	public void deleteShopBarterByBarterId(String barterId);

	/**
	 * 修改换货表的增删改查数据
	 * 
	 * @param shopBarter
	 */
	public void updateShopBarter(ShopBarter shopBarter);

	/**
	 * 保存换货表的增删改查数据
	 * 
	 * @param shopBarter
	 */
	public void saveShopBarter(ShopBarter shopBarter);

	/**
	 * 获取所有换货表的增删改查数据
	 * 
	 * @return
	 */
	public List<ShopBarter> findShopBarterAllList();
	
	/**
	 * 查询货详情,必传换货id,可传用户id和店铺id,不需要传null
	 * @param refundReturn
	 * @return
	 */
	public BarterDetailVo findBarterDetail(ShopBarter shopBarter);

}