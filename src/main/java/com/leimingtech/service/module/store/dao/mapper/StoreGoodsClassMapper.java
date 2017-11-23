package com.leimingtech.service.module.store.dao.mapper;

import com.leimingtech.core.entity.StoreGoodsClass;
import com.leimingtech.core.entity.vo.StoreGoodsClassVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@SqlMapper
public interface StoreGoodsClassMapper {
    /**
     * 按顺序查询所有的分类属性
     *
     * @param storeGoodsClassVo
     * @return
     */
    List<StoreGoodsClassVo> queryClasssList(StoreGoodsClassVo storeGoodsClassVo);

    /**
     * 根据id号修改is_del状态，达到删除的目的
     *
     * @param id
     */
    void deleteByPrimaryKey(String id);

    List<StoreGoodsClass> findParentList(@Param("id") String id);

    void insertSelective(StoreGoodsClass storeGoodsClass);

    void updateByPrimaryKeySelective(StoreGoodsClass storeGoodsClass);

    StoreGoodsClass selectByPrimaryKey(String stcId);

    /**
     * 取出所有的分类
     *
     * @param @return 设定文件
     * @return List<StoreGoodsClass>    返回类型
     * @throws
     * @Title: findAll
     * @Description: TODO(这里用一句话描述这个方法的作用)
     */
    List<StoreGoodsClass> findAll(@Param("id") String id);

    List<StoreGoodsClass> findChild(@Param("id") String id);

    void updateState(StoreGoodsClass storeGoodsClass);
    /**
     * 查询父子关联
     * @param storeGoodsClass
     */
    List<StoreGoodsClass> findList(StoreGoodsClass storeGoodsClass);
    
    /**
     * 校验重复的分类名称
     * @param stcName
     */
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
