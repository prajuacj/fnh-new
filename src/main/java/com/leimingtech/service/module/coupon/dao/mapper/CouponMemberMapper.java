package com.leimingtech.service.module.coupon.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.CouponMember;
import com.leimingtech.core.entity.searchbean.CouponMemberSearch;
import com.leimingtech.core.entity.searchbean.CouponSearch;
import com.leimingtech.core.orm.mybatis.SqlMapper;

/**
 * 会员领取优惠券接口mapper
 * @author kviuff
 * @date 2015-07-31 16:00:00
 */
@SqlMapper
public interface CouponMemberMapper {

	/**
	 * 保存领取优惠券
	 * @param couponMember
	 */
	void saveCouponMember(CouponMember couponMember);
	
	/**
	 * 根据会员id获取所领取的优惠券数量
	 * @param couponId
	 * @return
	 */
	int getCouponMemberCount(String couponId);
	
	/**
	 * 根据会员id和店铺id获取优惠券列表
	 * @param couponSearch
	 * @return
	 */
	List<CouponMember> getCouponListByMemberIdAndStoreId(CouponSearch couponSearch);
	
	/**
	 * 修改是否已使用状态
	 * @param couponMember
	 */
	void updateCouponMember(CouponMember couponMember);
	/**
	 * 通过用户id、店铺和商品的列表获取优惠券列表
	 * @param memberId
	 * @param storeIds
	 * @param goodsClassIds
	 * @return
	 */
	List<CouponMember> findCouponsByConditions(@Param("memberId")String memberId,@Param("storeIds")String storeIds,@Param("goodsClassIds")String goodsClassIds);
	/**
	 * 通过条件查询会员优惠券
	 * @param couponMemberSearch
	 * @return
	 */
	List<CouponMember> findCouponMemberByConditions(
			CouponMemberSearch couponMemberSearch);
}
