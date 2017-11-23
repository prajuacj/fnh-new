package com.leimingtech.service.module.member.dao;


import java.util.List;

import com.leimingtech.core.entity.base.ShopPointsLog;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：ShopPointsLogDao   
 * 类描述：接口
 * 创建人：gyh   
 * 创建时间：2015年7月24日 上午9:39:20   
 * 修改备注：   
 * @version    
 *
 */
public interface ShopPointsLogDao {
    
	/**
	 * 保存会员积分记录
	 * @param shopStatGoods
	 */
	void save(ShopPointsLog shopPointsLog);
	/**
	 * 获取条数
	 * @param shopPointsLog
	 * @return
	 */
	int findCount(ShopPointsLog shopPointsLog);
	
	/**
	 * 获取分页数据
	 * @param pager
	 * @return
	 */
	List<ShopPointsLog> findPageList(Pager pager);

	/**
	 * 获取全部数据
	 * @return
	 */
	List<ShopPointsLog> findList();
	
	/**
	 * 获取分享注册用户 
	 * @author 张华
	 * @date 2017-7-6 下午3:34:58
	 * @param shopPointsLog
	 * @return
	 */
	List<ShopPointsLog> selectMemberList(ShopPointsLog shopPointsLog);
}
