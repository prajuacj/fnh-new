package com.leimingtech.service.module.coupon.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.Coupon;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 优惠券mapper
 * @author kviuff
 * @date 2015-07-27 17:00:00
 */

@SqlMapper
public interface CouponMapper {
	/**
	 * 保存优惠券
	 * @param coupon
	 */
	void saveCoupon(Coupon coupon);
	
	/**
	 * 删除优惠券
	 * @param id
	 */
	void deleteCoupon(String id);
	
	/**
	 * 修改优惠券
	 * @param coupon
	 */
	void updateCoupon(Coupon coupon);
	
	/**
	 * 获取优惠券数量
	 * @param coupon
	 * @return
	 */
	int findCouponCount(Coupon coupon);
	
	/**
	 * 获取优惠券分页列表
	 * @param pager
	 * @return
	 */
	List<Coupon> findCouponPagerList(Pager pager);
	
	/**
	 * 根据id获取优惠券
	 * @param id
	 * @return
	 */
	Coupon getCouponById(String id);
	
	/**
	 * 获取所有的优惠券列表
	 * @return
	 */
	List<Coupon> findCouponAllList(String storeId);
	
	/**
	 * 通过条件获取优惠券列表
	 * @return
	 */
	List<Coupon>  findCouponbycolumList(Coupon coupon);
	/**
	 * 通过couponClassId获取优惠券
	 * @return
	 */
	List<Coupon> findCouponByClassId(String couponClassId);
	/**
	 * 获取优惠券列表
	 * @param coupon
	 * @return
	 */
	List<Coupon> findCoupons(Coupon coupon);
	/**
	 * 通过店铺和商品的列表获取优惠券列表
	 * @param storeIds 店铺id,以逗号分隔的字符串
	 * @param goodsClassIds 商品分类ids
	 * @return
	 */
	List<Coupon> findCouponsByCondition(@Param("storeIds")String storeIds,@Param("goodsClassIds")String goodsClassIds);
}
