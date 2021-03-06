package com.leimingtech.service.module.setting.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Express;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.setting.dao.ExpressDao;
import com.leimingtech.service.module.setting.dao.mapper.ExpressMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：MemberDaoImpl   
 * 类描述：   DAO 实现类
 * 修改备注：   
 * @version    
 *
 */
@Service("expressDao")
public class ExpressDaoImpl extends BaseDao implements ExpressDao {
    @Autowired
    private ExpressMapper expressMapper;
   
    /**
     * 查询总数
     */
    public int findExpressCount(Express express) {
        return expressMapper.findExpressCount(express);
    }

    /**
     * 获取结果集
     */
    public List<Express> findExpressList(Pager pager) {
        return expressMapper.findExpressList(pager);
    }


	@Override
	public void updateOrder(Express express) {
		// TODO Auto-generated method stub
		expressMapper.updateOrder(express);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		expressMapper.delete(id);
	}

	@Override
	public void updateState(Express express) {
		// TODO Auto-generated method stub
		expressMapper.updateState(express);
	}

	@Override
	public List<Express> findList() {
		return expressMapper.findList();
	}
	
	@Override
	public Express findById(String id){
		return expressMapper.findById(id);
	}
	
	/**
	 * 根据显示状态和是否常用状态查询物流公司信息
	 * @param eState
	 * @param eOrder
	 * @return
	 */
	@Override
	public List<Express> findExpressListByState(Integer eState,Integer eOrder){
		return expressMapper.findExpressListByState(eState, eOrder);
	}

}
