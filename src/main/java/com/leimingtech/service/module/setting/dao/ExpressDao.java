package com.leimingtech.service.module.setting.dao;


import com.leimingtech.core.entity.base.Express;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;


/**
 * 项目名称：leimingtech-admin   
 * 类名称：ExpressDao   
 * 类描述：接口
 * 修改备注：   
 * @version    
 */
public interface ExpressDao {
    
    
    int findExpressCount(Express express);

	List<Express> findExpressList(Pager pager);
	
	void updateState(Express express);

	void updateOrder(Express express);

	void delete(String id);
	
	List<Express> findList();
	
	Express findById(String id);
	
	/**
	 * 根据显示状态和是否常用状态查询物流公司信息
	 * @param eState
	 * @param eOrder
	 * @return
	 */
	List<Express> findExpressListByState(Integer eState, Integer eOrder);
    
}
