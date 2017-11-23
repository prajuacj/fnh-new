package com.leimingtech.service.module.store.dao.mapper;

import com.leimingtech.core.entity.base.StoreGrade;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;
/**
 * 店铺等级
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：StoreGradeMapper   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月12日 上午11:36:31   
 * 修改人：yanghui   
 * 修改时间：2014年11月12日 上午11:36:31   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface StoreGradeMapper{
	 
	List<StoreGrade> queryStoreGradeList(Pager pager);
    void save(StoreGrade grade);
    void delete(String id);
    StoreGrade queryById(String id);
    void update(StoreGrade grade);

    /**
     * 校验查重
     * @param storeGrade
     * @return
     */
    int queryCount(StoreGrade storeGrade);

    /**
     * 查找店铺等级
     * @param storeGrade
     * @return
     */
    List<StoreGrade> findStoreGradeList(StoreGrade storeGrade);
}
