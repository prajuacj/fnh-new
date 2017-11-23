package com.leimingtech.service.module.goods.dao;


import java.util.List;

import com.leimingtech.core.entity.Districts;
import com.leimingtech.core.entity.GoodsClassh5;

public interface DistrictsDao {

	/**
     * 保存分类
     * @param districts
     */
    void save(Districts districts);
    /**
     * 修改分类
     * @param districts
     */
    void update(Districts districts);
    /**
     * 删除
     * @param id
     */
    void delete(String id);
    
    /**
     * 通过id查询分类
     * @param gcId
     * @return
     */
    Districts findById(String gcId);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<Districts> findList(String parentid);
	

    /**
     * 查询所有的分类
     * @return
     */
    List<Districts> findAll();
    
    /**
     * 根据不同条件查询条数，页面验证用
     * @param districts
     * @return
     */
    int findCount(Districts districts);
    /**
     * 根据父districts查询分类列表
     * @param districts
     * @return
     */
    List<Districts> findListbyishow(Districts districts);
	

    /**
     * 查询所有的分类
     * @return
     */
    List<Districts> findAllbyisshow(Districts districts);
    
    /**
     * 通过父id查询子分类
     * @param gcParentId
     * @return
     */
    List<Districts> findChild(String gcParentId);
    
    /**
     * 修改分类
     * @param districts
     */
    void updatebyparentid(Districts districts);
    /**
     * 查询所有分类
     * @return
     */
    List<GoodsClassh5> queryDistricts();
    
    /**
     * 查询所有最下层（村落）分类
     * @author 张华
     * @date 2016-8-8 下午6:48:57
     * @param districts
     * @return
     */
    List<Districts> queryBottomChildList(Districts districts);
    
}
