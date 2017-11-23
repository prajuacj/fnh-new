package com.leimingtech.service.module.coupon.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.Coupon;
import com.leimingtech.service.module.coupon.dao.CouponDao;
import com.leimingtech.service.module.coupon.dao.mapper.CouponMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 优惠券dao
 * @author kviuff
 * @date 2015-07-27 17:50:00
 */
@Repository
public class CouponDaoImpl implements CouponDao {
	
	@Resource
	private CouponMapper couponMapper;

	/**
	 * 保存优惠券
	 * @param coupon
	 */
	public void saveCoupon(Coupon coupon) {
		coupon.setCouponId(IdGen.uuid());
		couponMapper.saveCoupon(coupon);
	}

	/**
	 * 删除优惠券
	 * @param id
	 */
	public void deleteCoupon(String id) {
		couponMapper.deleteCoupon(id);
	}

	/**
	 * 修改优惠券
	 * @param coupon
	 */
	public void updateCoupon(Coupon coupon) {
		couponMapper.updateCoupon(coupon);
	}

	/**
	 * 获取优惠券数量
	 * @param coupon
	 * @return
	 */
	public int findCouponCount(Coupon coupon) {
		return couponMapper.findCouponCount(coupon);
	}

	/**
	 * 获取优惠券分页列表
	 * @param pager
	 * @return
	 */
	public List<Coupon> findCouponPagerList(Pager pager) {
		return couponMapper.findCouponPagerList(pager);
	}

	/**
	 * 根据id获取优惠券
	 * @param id
	 * @return
	 */
	public Coupon getCouponById(String id) {
		return couponMapper.getCouponById(id);
	}

	/**
	 * 获取所有的优惠券列表
	 * @return
	 */
	public List<Coupon> findCouponAllList(String storeId) {
		return couponMapper.findCouponAllList(storeId);
	}
   
	/**
	 * 通过条件获取优惠券列表
	 * @return
	 */
	@Override
	public List<Coupon> findCouponbycolumList(Coupon coupon) {
		return couponMapper.findCouponbycolumList(coupon);
	}
	/**
	 * 通过couponClassId获取优惠券
	 * @return
	 */
	@Override
	public List<Coupon> findCouponByClassId(String couponClassId) {
		return couponMapper.findCouponByClassId(couponClassId);
	}
	/**
	 * 获取优惠券列表
	 */
	@Override
	public List<Coupon> findCoupons(Coupon coupon) {
		return couponMapper.findCoupons(coupon);
	}
	/**
	 * 通过店铺和商品的列表获取优惠券列表
	 * @param storeIds 店铺id,以逗号分隔的字符串
	 * @param goodsClassIds 商品分类id
	 * @return
	 */
	@Override
	public List<Coupon> findCouponsByCondition(String storeIds, String goodsClassIds){
		return couponMapper.findCouponsByCondition(storeIds,goodsClassIds);
	}
}
