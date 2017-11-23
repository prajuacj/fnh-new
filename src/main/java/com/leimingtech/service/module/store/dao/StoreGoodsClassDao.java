package com.leimingtech.service.module.store.dao;

import com.leimingtech.core.entity.StoreGoodsClass;
import com.leimingtech.core.entity.vo.StoreGoodsClassVo;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

public interface StoreGoodsClassDao {
    List<StoreGoodsClassVo> queryClasssList(StoreGoodsClassVo storeGoodsClassVo);

    void deleteByPrimaryKey(String id);

    List<StoreGoodsClass> findParentList(String id);

    void insertSelective(StoreGoodsClass storeGoodsClass);

    void updateByPrimaryKeySelective(StoreGoodsClass storeGoodsClass);

    StoreGoodsClass selectByPrimaryKey(String stcId);

    List<StoreGoodsClass> findAll(String id);

    List<StoreGoodsClass> findChild(String id);

    void updateState(StoreGoodsClass storeGoodsClass);
    
    List<StoreGoodsClass> findList(StoreGoodsClass storeGoodsClass);
    
    StoreGoodsClass findbystcName(String stcName);
    
    /**
     * 查询父子关联通过显示状态
     * @param storeGoodsClass
     */
    List<StoreGoodsClass> findListbystate(StoreGoodsClass storeGoodsClass);
    
    /**
     * 查询条数
     * @param storeGoodsClass
     * @return
     */
    int queryCount(StoreGoodsClass storeGoodsClass);
    
    /**
     * 查询店铺自定义分类分页数据
     * @param pager
     * @return
     */
    List<StoreGoodsClass> queryList(Pager pager);
}
