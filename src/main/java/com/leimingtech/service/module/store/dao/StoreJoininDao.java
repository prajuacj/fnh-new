package com.leimingtech.service.module.store.dao;

import com.leimingtech.core.entity.base.StoreJoinin;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 店铺入驻DAO接口
 *
 * @author gyh
 * @version 2015-12-30
 */
public interface StoreJoininDao {

	/**
	 * 查询分页店铺入驻数据
	 *
	 * @param pager 分页对象
	 * @return
	 */
	 List<StoreJoinin> findShopStoreJoininPagerList(Pager pager);

	/**
	 * 通过memberId获取单条店铺入驻数据
	 *
	 * @param memberId
	 * @return
	 */
	 StoreJoinin findShopStoreJoininByMemberId(String memberId);

	/**
	 * 通过memberId删除店铺入驻数据
	 *
	 * @param memberId
	 */
	 void deleteShopStoreJoininByMemberId(String memberId);

	/**
	 * 修改店铺入驻数据
	 *
	 * @param shopStoreJoinin
	 */
	 void updateShopStoreJoinin(StoreJoinin shopStoreJoinin);

	/**
	 * 保存店铺入驻数据
	 *
	 * @param shopStoreJoinin
	 */
	 void saveShopStoreJoinin(StoreJoinin shopStoreJoinin);

	/**
	 * 获取所有店铺入驻数据
	 *
	 * @return
	 */
	 List<StoreJoinin> findShopStoreJoininAllList();

	/**
	 * 根据会员名字获取入驻信息
	 * @param memberName
	 * @return
	 */
	StoreJoinin findShopStoreJoininByMemberName(String memberName);

}