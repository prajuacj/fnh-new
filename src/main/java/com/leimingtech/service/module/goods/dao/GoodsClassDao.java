package com.leimingtech.service.module.goods.dao;


import java.util.List;

import com.leimingtech.core.entity.GoodsClassh5;
import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.GoodsClass;

public interface GoodsClassDao {

	/**
     * 保存分类
     * @param goodsClass
     */
    void save(GoodsClass goodsClass);
    /**
     * 修改分类
     * @param goodsClass
     */
    void update(GoodsClass goodsClass);
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
    GoodsClass findById(String gcId);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<GoodsClass> findList(String parentid);
	

    /**
     * 查询所有的分类
     * @return
     */
    List<GoodsClass> findAll();
    
    /**
     * 根据不同条件查询条数，页面验证用
     * @param goodsClass
     * @return
     */
    int findCount(GoodsClass goodsClass);
    /**
     * 根据父goodsClass查询分类列表
     * @param goodsClass
     * @return
     */
    List<GoodsClass> findListbyishow(GoodsClass goodsClass);
	

    /**
     * 查询所有的分类
     * @return
     */
    List<GoodsClass> findAllbyisshow(GoodsClass goodsClass);
    
    /**
     * 通过父id查询子分类
     * @param gcParentId
     * @return
     */
    List<GoodsClass> findChild(String gcParentId);
    
    /**
     * 修改分类
     * @param goodsClass
     */
    void updatebyparentid(GoodsClass goodsClass);
    /**
     * 查询所有分类
     * @return
     */
    List<GoodsClassh5> queryGoodsClass();
}
