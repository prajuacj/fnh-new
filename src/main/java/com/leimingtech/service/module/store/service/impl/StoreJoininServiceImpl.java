package com.leimingtech.service.module.store.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.JoininLog;
import com.leimingtech.core.entity.base.ShopSeller;
import com.leimingtech.core.entity.base.Store;
import com.leimingtech.core.entity.base.StoreJoinin;
import com.leimingtech.service.module.joininlog.dao.JoininLogDao;
import com.leimingtech.service.module.lucence.service.LucenceService;
import com.leimingtech.service.module.seller.dao.ShopSellerDao;
import com.leimingtech.service.module.store.common.JoininState;
import com.leimingtech.service.module.store.dao.StoreJoininDao;
import com.leimingtech.service.module.store.service.StoreJoininService;
import com.leimingtech.service.module.store.service.StoreService;
import com.leimingtech.service.utils.page.Pager;
import com.leimingtech.service.utils.sessionkey.seller.CacheUser;
import com.leimingtech.service.utils.sessionkey.seller.CacheUtils;

/**
 * 店铺入驻ServiceImpl
 *
 * @author gyh
 * @version 2015-12-30
 */
@Service
public class StoreJoininServiceImpl implements StoreJoininService {

	/** 店铺入驻DAO接口 */
	@Resource
	private StoreJoininDao shopStoreJoininDao;

	/** 商家入驻流程日志DAO接口 */
	@Resource
	private JoininLogDao joininLogDao;

	@Resource
	private StoreService storeService;

	/** 店铺用户列表DAO接口 */
	@Resource
	private ShopSellerDao shopSellerDao;

	@Resource
	private LucenceService lucenceService;

	/**
	 * 查询分页店铺入驻数据
	 *
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopStoreJoininPagerList(Pager pager) {
		List<StoreJoinin> list = shopStoreJoininDao.findShopStoreJoininPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过memberId获取单条店铺入驻数据
	 *
	 * @param memberId
	 * @return
	 */
	@Override
	public StoreJoinin findShopStoreJoininByMemberId(String memberId) {
		return shopStoreJoininDao.findShopStoreJoininByMemberId(memberId);
	}

	/**
	 * 通过memberId删除店铺入驻数据
	 *
	 * @param memberId
	 */
	@Override
	public void deleteShopStoreJoininByMemberId(String memberId) {
		shopStoreJoininDao.deleteShopStoreJoininByMemberId(memberId);
	}

	/**
	 * 修改店铺入驻数据
	 *
	 * @param shopStoreJoinin
	 */
	@Override
	public void updateShopStoreJoinin(StoreJoinin shopStoreJoinin) {
		shopStoreJoininDao.updateShopStoreJoinin(shopStoreJoinin);
	}

	/**
	 * 保存店铺入驻数据
	 *
	 * @param shopStoreJoinin
	 */
	@Override
	public void saveShopStoreJoinin(StoreJoinin shopStoreJoinin) {
		shopStoreJoininDao.saveShopStoreJoinin(shopStoreJoinin);
	}

	/**
	 * 获取所有店铺入驻数据
	 *
	 * @return
	 */
	@Override
	public List<StoreJoinin> findShopStoreJoininAllList() {
		return shopStoreJoininDao.findShopStoreJoininAllList();
	}

	/**
	 *
	 * @param shopStoreJoinin
	 */
	@Override
	public void updateShopStoreJoininIn(StoreJoinin shopStoreJoinin, String memberId) {
		shopStoreJoininDao.updateShopStoreJoinin(shopStoreJoinin);
		// 保存店铺信息
		shopStoreJoinin = shopStoreJoininDao.findShopStoreJoininByMemberId(memberId);
		savestore(shopStoreJoinin);
		/*
		 * JoininLog joininLog = new JoininLog(); joininLog.setTitle("入驻审核");
		 * joininLog.setMemberId(memberId);
		 * joininLog.setLogContent(shopStoreJoinin.getJoininMessage());
		 * joininLog.setLogStatus(shopStoreJoinin.getJoininState());
		 * joininLog.setOptId(CacheUtil.getCacheUser().getAdmin().getAdminId());
		 * joininLog.setOptName(CacheUtil.getCacheUser().getAdmin().getAdminName
		 * ()); joininLog.setCreateTime((new Date()).getTime());
		 * joininLog.setDel(1); joininLogDao.saveJoininLog(joininLog);
		 */
	}

	/**
	 * 将入住状态调到填写公司资质步骤
	 *
	 * @param shopStoreJoinin
	 */
	@Override
	public void updateShopStoreJoininToStep4(StoreJoinin shopStoreJoinin, String memberId) {
		// 将入住状态调到填写公司资质步骤
		shopStoreJoinin.setSetupStoreState(JoininState.JOININ_SETUP_FINSH);
		shopStoreJoininDao.updateShopStoreJoinin(shopStoreJoinin);

		/*
		 * JoininLog joininLog = new JoininLog(); joininLog.setTitle("入驻审核");
		 * joininLog.setMemberId(memberId);
		 * joininLog.setLogContent(shopStoreJoinin.getJoininMessage());
		 * joininLog.setLogStatus(shopStoreJoinin.getJoininState());
		 * joininLog.setOptId(CacheUtil.getCacheUser().getAdmin().getAdminId());
		 * joininLog.setOptName(CacheUtil.getCacheUser().getAdmin().getAdminName
		 * ()); joininLog.setCreateTime((new Date()).getTime());
		 * joininLog.setDel(1); joininLogDao.saveJoininLog(joininLog);
		 */
	}

	/**
	 * 审核通过后保存入驻店铺的信息，及seller信息
	 * 
	 * @param storeJoinin
	 */
	public void savestore(StoreJoinin storeJoinin) {
		storeJoinin = shopStoreJoininDao.findShopStoreJoininByMemberId(storeJoinin.getMemberId());
		Store store = new Store();
		store.setMemberId(storeJoinin.getMemberId());
		store.setMemberName(storeJoinin.getMemberName());
		// 店铺名称
		store.setStoreName(storeJoinin.getStoreName());
		// 店铺分类
		store.setScId(storeJoinin.getScId());
		// 主营范围
		store.setStoreZy(storeJoinin.getBusinessSphere());
		// 详情
		store.setStoreAddress(storeJoinin.getCompanyAddressDetail());
		// 邮编 默认背景邮编 入驻表中没有该邮编
		// store.setStoreZip("100000");
		// 地址
		store.setAreaInfo(storeJoinin.getCompanyAddress());
		// 省id
		store.setProvinceId(storeJoinin.getCompanyProvinceId());
		// 城市id
		store.setCityId(storeJoinin.getCompanyCityId());
		// 县，区id
		store.setAreaId(storeJoinin.getCompanyAreaId());
		// 公司电话
		store.setStoreTel(storeJoinin.getCompanyPhone());
		// 证件上传
		// 执照上传
		store.setStoreImage1(storeJoinin.getBusinessLicenceNumberElectronic());
		// 银行开户名
		store.setBankAccountName(storeJoinin.getBankAccountName());
		// 公司银行账号
		store.setBankAccountNumber(storeJoinin.getBankAccountNumber());
		// 开户银行支行名称
		store.setBankName(storeJoinin.getBankName());
		// 支行联行号
		store.setBankCode(storeJoinin.getBankCode());
		// 支付宝姓名
		store.setAlipayName(storeJoinin.getAlipayName());
		// 支付宝账号
		store.setAlipayAccountNumber(storeJoinin.getAlipayAccountNumber());
		// 微信姓名
		store.setWeichatName(storeJoinin.getWeichatName());
		// 微信账号
		store.setWeichatAccountNumber(storeJoinin.getWeichatAccountNumber());

		store.setStoreState(1);// 店铺状态，0关闭，1开启，2审核中
		store.setStoreRecommend(0);// 推荐，0为否，1为是，默认为0
		store.setNameAuth(0);
		if (storeJoinin.getSgId() != null) {
			store.setGradeId(storeJoinin.getSgId());// 等级 默认为白金等级
		} else {
			store.setGradeId("1");// 等级 默认为白金等级
		}
		store.setStoreTheme("default");// 默认主题
		store.setStoreGoodsCount(0);// 商品数量
		store.setStoreSales(0);// 店铺销量
		store.setStoreCollect(0);// 店铺收藏数量
		store.setStoreClick(0);// 店铺点击量
		store.setStoreCredit(0);// 店铺信用
		store.setPraiseRate(0f);// 店铺好评率
		storeService.save(store);

		// 同时，还将申请的用户添加到店铺账户表中
		String storeId = store.getStoreId();
		ShopSeller shopSeller = new ShopSeller();
		shopSeller.setSellerName(storeJoinin.getMemberName());// 账户名
		shopSeller.setMemberId(storeJoinin.getMemberId());// 会员id
		shopSeller.setSellerGroupId("0");// 组名
		shopSeller.setStoreId(storeId);// 申请的店铺id
		shopSeller.setIsAdmin(1); // is_admin 是否是管理员0-不是，1-是
		shopSeller.setCreateTime(System.currentTimeMillis());// 创建时间
		shopSellerDao.saveShopSeller(shopSeller);

		// 生成店铺索引
		lucenceService.creatStoreOneIndex(store);
	}

	/**
	 * 审核等待页面
	 *
	 * @param storeJoinin
	 */
	public void updateShopStoreJoininTojoininfjlist(StoreJoinin storeJoinin) {
		// 开店状态 待填写合同签订及缴费
		storeJoinin.setSetupStoreState(JoininState.JOININ_SETUP_STATEHTJF);
		CacheUser member = CacheUtils.getCacheUser();
		storeJoinin.setSellerName(member.getMember().getMemberName());// 商家账号默认为会员账号
		storeJoinin.setMemberId(member.getMember().getMemberId());
		storeJoinin.setUpdateTime(System.currentTimeMillis());
		this.updateShopStoreJoinin(storeJoinin);
		/* 商家入驻日志信息录入 */
		JoininLog joininLog = new JoininLog();
		joininLog.setTitle("店铺入驻申请");
		joininLog.setMemberId(member.getMember().getMemberId());
		joininLog.setOptId(member.getMember().getMemberId());
		joininLog.setOptName(member.getMember().getMemberName());
		joininLog.setDel(1);
		joininLog.setCreateTime((new Date()).getTime());
		joininLogDao.saveJoininLog(joininLog);
	}

	/**
	 * 根据会员名字获取入驻信息
	 * 
	 * @param memberName
	 * @return
	 */
	public StoreJoinin findShopStoreJoininByMemberName(String memberName) {
		return shopStoreJoininDao.findShopStoreJoininByMemberName(memberName);
	}

}