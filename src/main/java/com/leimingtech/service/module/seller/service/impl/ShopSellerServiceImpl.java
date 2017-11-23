package com.leimingtech.service.module.seller.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.ShopSeller;
import com.leimingtech.service.module.member.service.MemberService;
import com.leimingtech.service.module.seller.dao.ShopSellerDao;
import com.leimingtech.service.module.seller.service.ShopSellerService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 店铺用户列表ServiceImpl
 *
 * @author yangxp
 * @version 2015-12-10
 */
@Service
public class ShopSellerServiceImpl implements ShopSellerService {

	/** 店铺用户列表DAO接口 */
	@Resource
	private ShopSellerDao shopSellerDao;

	@Resource
	private MemberService memberService;

	/**
	 * 查询分页店铺用户列表数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopSellerPagerList(Pager pager) {
		List<ShopSeller> list = shopSellerDao.findShopSellerPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过sellerId获取单条店铺用户列表数据
	 * 
	 * @param sellerId
	 * @return
	 */
	@Override
	public ShopSeller findShopSellerBySellerId(String sellerId) {
		return shopSellerDao.findShopSellerBySellerId(sellerId);
	}

	/**
	 * 通过sellerId删除店铺用户列表数据
	 * 
	 * @param sellerId
	 */
	@Override
	public void deleteShopSellerBySellerId(String sellerId) {
		shopSellerDao.deleteShopSellerBySellerId(sellerId);
	}

	/**
	 * 修改店铺用户列表数据
	 * 
	 * @param shopSeller
	 */
	@Override
	public void updateShopSeller(ShopSeller shopSeller) {
		shopSellerDao.updateShopSeller(shopSeller);
	}

	/**
	 * 保存店铺用户列表数据
	 * 
	 * @param shopSeller
	 */
	@Override
	public void saveShopSeller(ShopSeller shopSeller) {
		shopSellerDao.saveShopSeller(shopSeller);
	}

	/**
	 * 获取所有店铺用户列表数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopSeller> findShopSellerAllList() {
		return shopSellerDao.findShopSellerAllList();
	}

	@Override
	public void saveShopSeller(ShopSeller shopSeller, String memberName, String password) throws Exception {
		// 先将新用户保存到用户中
		Member member = memberService.findMemberByName(memberName);
		// 判断用户是否存在,不存在就创建个新的，并保存
		if (member == null) {
			member = new Member();
			member.setMemberName(memberName);
			member.setMemberPasswd(password);
			member.setCreateTime(System.currentTimeMillis());
			memberService.save(member);
			ShopSeller oldSeller = shopSellerDao.findShopSellerBySellerName(memberName);
			if (oldSeller == null) {
				shopSeller.setMemberId(member.getMemberId());
				shopSellerDao.saveShopSeller(shopSeller);
			} else {
				throw new Exception("用户名不能重复！");
			}
		} else {
			throw new Exception("用户名不能重复！");
		}
	}

	@Override
	public void deleteShopSeller(ShopSeller shopSeller) {
		shopSellerDao.deleteShopSellerBySellerId(shopSeller.getSellerId());
		memberService.delete(shopSeller.getMemberId());
	}

	/**
	 * 获取店铺会员列表
	 */
	@Override
	public List<ShopSeller> findSellerListByStoreId(String storeId) {
		return shopSellerDao.findSellerListByStoreId(storeId);
	}

	/**
	 * 通过账户名获取会员信息
	 * 
	 * @param sellerName
	 * @return
	 */
	public ShopSeller findShopSellerBySellerName(String sellerName) {
		return shopSellerDao.findShopSellerBySellerName(sellerName);
	}

	/**
	 * 通过memberId获取店铺账户信息
	 * 
	 * @param memberId
	 * @return
	 */
	public ShopSeller findShopSellerByMemberId(String memberId) {
		return shopSellerDao.findShopSellerByMemberId(memberId);
	}
}