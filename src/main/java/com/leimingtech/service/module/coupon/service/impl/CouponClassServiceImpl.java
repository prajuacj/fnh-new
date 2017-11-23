package com.leimingtech.service.module.coupon.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.CouponClass;
import com.leimingtech.service.module.coupon.dao.CouponClassDao;
import com.leimingtech.service.module.coupon.service.CouponClassService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 优惠券类型接口实现
 * @author kviuff
 * @date 2015-07-23 10:00:00
 */

@Service
public class CouponClassServiceImpl implements CouponClassService {
	
	@Resource
	private CouponClassDao couponClassDao;

	/**
	 * 保存优惠券类型
	 * @param conpon
	 */
	public void saveCoupon(CouponClass conpon) {
		couponClassDao.saveCoupon(conpon);
	}

	/**
	 * 删除优惠券类型
	 * @param classId
	 */
	public void deleteCoupon(String classId) {
		couponClassDao.deleteCoupon(classId);
	}

	/**
	 * 修改优惠券类型
	 * @param conpon
	 */
	public void updateCoupon(CouponClass conpon) {
		couponClassDao.updateCoupon(conpon);
	}

	/**
	 * 获取优惠分类总数
	 * @param conpon
	 * @return
	 */
	public int findCouponCount(CouponClass conpon) {
		return couponClassDao.findCouponCount(conpon);
	}

	/**
	 * 获取优惠券分类列表
	 * @param pager
	 * @return
	 */
	public Pager findCouponPageList(Pager pager) {
		List<CouponClass> list=couponClassDao.findCouponPageList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 获取所有的优惠券类型列表
	 */
	public List<CouponClass> findAllCouponList() {
		return couponClassDao.findAllCouponList();
	}

	/**
	 * 根据id获取优惠券分类
	 * @param id
	 * @return
	 */
	public CouponClass getCouponById(String id) {
		return couponClassDao.getCouponById(id);
	}

	@Override
	public List<CouponClass> findAllCouponListByCoupon(CouponClass conpon) {
		
		return couponClassDao.findAllCouponListByCoupon(conpon);
	}
	
	
	
}
