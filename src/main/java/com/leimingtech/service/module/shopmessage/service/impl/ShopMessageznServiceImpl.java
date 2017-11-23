package com.leimingtech.service.module.shopmessage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.ShopMessagezn;
import com.leimingtech.service.module.shopmessage.dao.ShopMessageznDao;
import com.leimingtech.service.module.shopmessage.service.ShopMessageznService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 站内信ServiceImpl
 *
 * @author gyh
 * @version 2016-03-16
 */
@Service
public class ShopMessageznServiceImpl implements ShopMessageznService {

	/** 站内信DAO接口 */
	@Resource
	private ShopMessageznDao shopMessageznDao;

	/**
	 * 查询分页站内信数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopMessageznPagerList(Pager pager) {
		List<ShopMessagezn> list = shopMessageznDao.findShopMessageznPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过msId获取单条站内信数据
	 * 
	 * @param msId
	 * @return
	 */
	@Override
	public ShopMessagezn findShopMessageznByMsId(String msId) {
		return shopMessageznDao.findShopMessageznByMsId(msId);
	}

	/**
	 * 通过msId删除站内信数据
	 * 
	 * @param msId
	 */
	@Override
	public void deleteShopMessageznByMsId(String msId) {
		shopMessageznDao.deleteShopMessageznByMsId(msId);
	}

	/**
	 * 通过Message_id删除站内信数据
	 * 
	 * @param message_id
	 */
	@Override
	public void deleteShopMessageznByMessage_id(String message_id) {
		shopMessageznDao.deleteShopMessageznByMessage_id(message_id);
	}

	/**
	 * 修改站内信数据
	 * 
	 * @param shopMessagezn
	 */
	@Override
	public void updateShopMessagezn(ShopMessagezn shopMessagezn) {
		shopMessageznDao.updateShopMessagezn(shopMessagezn);
	}

	/**
	 * 保存站内信数据
	 * 
	 * @param shopMessagezn
	 */
	@Override
	public void saveShopMessagezn(ShopMessagezn shopMessagezn) {
		shopMessageznDao.saveShopMessagezn(shopMessagezn);
	}

	/**
	 * 获取所有站内信数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopMessagezn> findShopMessageznAllList() {
		return shopMessageznDao.findShopMessageznAllList();
	}

	/**
	 * 查询分页站内信数据
	 *
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopMessageznPagerList2(Pager pager) {
		List<ShopMessagezn> list = shopMessageznDao.findShopMessageznPagerList2(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 *
	 * 查看相应的条数
	 */
	@Override
	public Integer findcount(ShopMessagezn shopMessagezn) {
		return shopMessageznDao.findcount(shopMessagezn);
	}

}