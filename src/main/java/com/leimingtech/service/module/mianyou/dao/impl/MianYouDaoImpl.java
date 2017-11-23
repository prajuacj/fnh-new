package com.leimingtech.service.module.mianyou.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.MianYou;
import com.leimingtech.service.module.mianyou.dao.MianYouDao;
import com.leimingtech.service.module.mianyou.dao.mapper.MianYouMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 满免邮daoImpl
 *
 * @author admin
 * @version 2015-12-22
 */
@Repository
public class MianYouDaoImpl implements MianYouDao {
	
	/** 满免邮mapper接口*/
	@Resource
	private MianYouMapper mianYouMapper;
	
	/**
	 * 查询分页满免邮数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	@Override
	public List<MianYou> findMianYouPagerList(Pager pager){
		return mianYouMapper.findMianYouPagerList(pager);
	}

	/**
	 * 通过mianyouId获取单条满免邮数据
	 * 
	 * @param mianyouId
	 * @return
	 */
	@Override 
	public MianYou findMianYouByMianyouId(String mianyouId){
		return mianYouMapper.findMianYouByMianyouId(mianyouId);
	}

	/**
	 * 通过mianyouId删除满免邮数据
	 * 
	 * @param mianyouId
	 */
	@Override
	public void deleteMianYouByMianyouId(String mianyouId){
		mianYouMapper.deleteMianYouByMianyouId(mianyouId);
	}

	/**
	 * 修改满免邮数据
	 * 
	 * @param mianYou
	 */
	@Override
	public void updateMianYou(MianYou mianYou){
		mianYouMapper.updateMianYou(mianYou);
	}
	/**
	 * 保存满免邮数据
	 * 
	 * @param mianYou
	 */
	@Override
	public void saveMianYou(MianYou mianYou){
		mianYou.setMianyouId(IdGen.uuid());
		mianYouMapper.saveMianYou(mianYou);
	}

	/**
	 * 获取所有满免邮数据
	 * 
	 * @return
	 */
	@Override
	public List<MianYou> findMianYouAllList(){
		return mianYouMapper.findMianYouAllList();
	}

	@Override
	public int findByEndTimeCount(Map paramMap) {
		return mianYouMapper.findByEndTimeCount(paramMap);
	}

	/**
	 * 获取一条店铺当前可以使用的满免邮(需要店铺id和活动结束时间)
	 * @param mianYou
	 * @return Mianyou
	 */
	@Override
	public MianYou findStoreCurrentMianyou(MianYou mianYou) {
		return mianYouMapper.findStoreCurrentMianyou(mianYou);
	}


}