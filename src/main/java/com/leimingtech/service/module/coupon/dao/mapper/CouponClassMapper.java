package com.leimingtech.service.module.coupon.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.CouponClass;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 优惠券类型mapper
 * @author kviuff
 * @date 2015-07-23 10:00:00
 */

@SqlMapper
public interface CouponClassMapper {

	/**
	 * 保存优惠券类型
	 * @param conpon
	 */
	void saveCoupon(CouponClass conpon);
	
	/**
	 * 删除优惠券类型
	 * @param classId
	 */
	void deleteCoupon(String classId);
	
	/**
	 * 修改优惠券类型
	 * @param conpon
	 */
	void updateCoupon(CouponClass conpon);
	
	/**
	 * 获取优惠分类总数
	 * @param conpon
	 * @return
	 */
	int findCouponCount(CouponClass conpon);
	
	/**
	 * 获取优惠券分类列表
	 * @param pager
	 * @return
	 */
	List<CouponClass> findCouponPageList(Pager pager);
	
	/**
	 * 获取所有的优惠券列表
	 * @return
	 */
	List<CouponClass> findAllCouponList();
	
	/**
	 * 根据id获取优惠券分类
	 * @param id
	 * @return
	 */
	CouponClass getCouponById(String id);
	
	List<CouponClass> findAllCouponListByCoupon(CouponClass conpon);
}
