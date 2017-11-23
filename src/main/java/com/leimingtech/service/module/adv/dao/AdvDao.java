/**
 * 
 */
package com.leimingtech.service.module.adv.dao;

import java.util.List;

import com.leimingtech.core.entity.base.Adv;
import com.leimingtech.core.entity.base.AdvPosition;
import com.leimingtech.service.utils.page.Pager;

/**
 * <p>Title: AdvDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
public interface AdvDao {
	
	/**
	 * 保存
	 * @param adv
	 */
	void save(Adv adv);
	
	/**
	 * 更新
	 * @param adv
	 */
	void update(Adv adv);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 总条数
	 * @param adv
	 * @return int
	 */
	int findAdvCount(Adv adv);
	
	/**
	 * 查询集合
	 * @param adv
	 * @return
	 */
	List<Adv> findAllAdv(Adv adv);
	
	/**
	 * 分页集合
	 * @param pager
	 * @return
	 */
	List<Adv> findAdvPagerList(Pager pager);
	
	/**
	 * 根据id 查询
	 * @param id
	 * @return
	 */
	Adv findAdvById(String id);
	
	/**
	 * 根据apId 查询
	 * @param apId
	 * @return
	 */
	List<Adv> findAdvByPositionId(String apId, long nowTime);

}
