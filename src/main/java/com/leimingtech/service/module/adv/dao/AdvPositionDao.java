/**
 * 
 */
package com.leimingtech.service.module.adv.dao;

import java.util.List;

import com.leimingtech.core.entity.base.Adv;
import com.leimingtech.core.entity.base.AdvPosition;
import com.leimingtech.core.entity.vo.AdvPositionVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * <p>Title: AdvPositionDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014-2018</p>
 * <p>Company: leimingtech.com</p>
 * @author linjm
 * @date 2015年7月7日
 * @version 1.0
 */
public interface AdvPositionDao {
	
	/**
	 * 保存
	 * @param advPosition
	 */
	void save(AdvPosition advPosition);
	
	/**
	 * 更新
	 * @param advPosition
	 */
	void update(AdvPosition advPosition);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 总条数
	 * @param advPosition
	 * @return int
	 */
	int findAdvPositionCount(AdvPosition advPosition);
	
	/**
	 * 查询集合
	 * @param advPosition
	 * @return
	 */
	List<AdvPosition> findAllAdvPosition(AdvPosition advPosition);
	
	/**
	 * 分页集合
	 * @param pager
	 * @return
	 */
	List<AdvPosition> findAdvPositionPagerList(Pager pager);
	
	/**
	 * 根据id 查询
	 * @param id
	 * @return
	 */
	AdvPosition findAdvPositionById(String id);
	
	/**
	 * 根据advPosition 获取广告
	 * @param advPosition
	 * @return List<AdvPosition>
	 */
	AdvPositionVo findAdvPositionVoList(AdvPosition advPosition);
	

}
