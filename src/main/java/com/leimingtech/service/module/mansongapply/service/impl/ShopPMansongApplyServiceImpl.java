package com.leimingtech.service.module.mansongapply.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.ShopPMansongApply;
import com.leimingtech.service.module.mansongapply.dao.ShopPMansongApplyDao;
import com.leimingtech.service.module.mansongapply.service.ShopPMansongApplyService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 满就送套餐申请ServiceImpl
 *
 * @author admin
 * @version 2015-11-19
 */
@Service
public class ShopPMansongApplyServiceImpl implements ShopPMansongApplyService {

	/** 满就送套餐申请DAO接口 */
	@Resource
	private ShopPMansongApplyDao shopPMansongApplyDao;

	/**
	 * 查询分页满就送套餐申请数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopPMansongApplyPagerList(Pager pager) {
		List<ShopPMansongApply> list = shopPMansongApplyDao.findShopPMansongApplyPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过id获取单条满就送套餐申请数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ShopPMansongApply findShopPMansongApplyById(String id) {
		return shopPMansongApplyDao.findShopPMansongApplyById(id);
	}

	/**
	 * 通过id删除满就送套餐申请数据
	 * 
	 * @param id
	 */
	@Override
	public void deleteShopPMansongApplyById(String id) {
		shopPMansongApplyDao.deleteShopPMansongApplyById(id);
	}

	/**
	 * 修改满就送套餐申请数据
	 * 
	 * @param shopPMansongApply
	 */
	@Override
	public void updateShopPMansongApply(ShopPMansongApply shopPMansongApply) {
		shopPMansongApplyDao.updateShopPMansongApply(shopPMansongApply);
	}

	/**
	 * 保存满就送套餐申请数据
	 * 
	 * @param shopPMansongApply
	 */
	@Override
	public void saveShopPMansongApply(ShopPMansongApply shopPMansongApply) {
		shopPMansongApplyDao.saveShopPMansongApply(shopPMansongApply);
	}

	/**
	 * 获取所有满就送套餐申请数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopPMansongApply> findShopPMansongApplyAllList() {
		return shopPMansongApplyDao.findShopPMansongApplyAllList();
	}

}