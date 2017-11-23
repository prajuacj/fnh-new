package com.leimingtech.service.module.mianyou.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.MianYou;
import com.leimingtech.service.module.mianyou.dao.MianYouDao;
import com.leimingtech.service.module.mianyou.service.MianYouService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 满免邮ServiceImpl
 *
 * @author linjm
 * @version 2015-12-22
 */
@Service
public class MianYouServiceImpl implements MianYouService {

	/** 满免邮DAO接口 */
	@Resource
	private MianYouDao mianYouDao;

	/**
	 * 查询分页满免邮数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findMianYouPagerList(Pager pager) {
		List<MianYou> list = mianYouDao.findMianYouPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过mianyouId获取单条满免邮数据
	 * 
	 * @param mianyouId
	 * @return
	 */
	@Override
	public MianYou findMianYouByMianyouId(String mianyouId) {
		return mianYouDao.findMianYouByMianyouId(mianyouId);
	}

	/**
	 * 通过mianyouId删除满免邮数据
	 * 
	 * @param mianyouId
	 */
	@Override
	public void deleteMianYouByMianyouId(String mianyouId) {
		mianYouDao.deleteMianYouByMianyouId(mianyouId);
	}

	/**
	 * 修改满免邮数据
	 * 
	 * @param mianYou
	 */
	@Override
	public void updateMianYou(MianYou mianYou) {
		mianYouDao.updateMianYou(mianYou);
	}

	/**
	 * 保存满免邮数据
	 * 
	 * @param mianYou
	 */
	@Override
	public void saveMianYou(MianYou mianYou) {
		mianYouDao.saveMianYou(mianYou);
	}

	/**
	 * 获取所有满免邮数据
	 * 
	 * @return
	 */
	@Override
	public List<MianYou> findMianYouAllList() {
		return mianYouDao.findMianYouAllList();
	}

	@Override
	public int findByEndTimeCount(Map paramMap) {
		return mianYouDao.findByEndTimeCount(paramMap);
	}

	/**
	 * 获取一条店铺当前可以使用的满免邮
	 * 
	 * @param storeId
	 * @param endTime
	 * @return Mianyou
	 */
	@Override
	public MianYou findStoreCurrentMianyou(String storeId, long endTime) {
		MianYou mianYou = new MianYou();
		mianYou.setStoreId(storeId);
		mianYou.setEndTime(endTime);
		return mianYouDao.findStoreCurrentMianyou(mianYou);
	}

}