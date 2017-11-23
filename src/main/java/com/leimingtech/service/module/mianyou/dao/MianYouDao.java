package com.leimingtech.service.module.mianyou.dao;

import com.leimingtech.core.entity.base.MianYou;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;
import java.util.Map;

/**
 * 满免邮DAO接口
 *
 * @author admin
 * @version 2015-12-22
 */
public interface MianYouDao {

	/**
	 * 查询分页满免邮数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	public List<MianYou> findMianYouPagerList(Pager pager);

	/**
	 * 通过mianyouId获取单条满免邮数据
	 * 
	 * @param mianyouId
	 * @return
	 */
	public MianYou findMianYouByMianyouId(String mianyouId);

	/**
	 * 通过mianyouId删除满免邮数据
	 * 
	 * @param mianyouId
	 */
	public void deleteMianYouByMianyouId(String mianyouId);

	/**
	 * 修改满免邮数据
	 * 
	 * @param mianYou
	 */
	public void updateMianYou(MianYou mianYou);

	/**
	 * 保存满免邮数据
	 * 
	 * @param mianYou
	 */
	public void saveMianYou(MianYou mianYou);

	/**
	 * 获取所有满免邮数据
	 * 
	 * @return
	 */
	public List<MianYou> findMianYouAllList();

	/**
	 * 获取店铺下面免邮条数
	 *
	 * @param paramMap
	 * @return
	 */
	public int findByEndTimeCount(Map paramMap);

	/**
	 * 获取一条店铺当前可以使用的满免邮(需要店铺id和活动结束时间)
	 * @param mianYou
	 * @return Mianyou
	 */
	public MianYou findStoreCurrentMianyou(MianYou mianYou);
	
}