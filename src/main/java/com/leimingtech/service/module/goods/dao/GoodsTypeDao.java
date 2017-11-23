package com.leimingtech.service.module.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.GoodsType;
import com.leimingtech.core.entity.vo.GoodsTypeVO;
import com.leimingtech.service.module.goods.vo.GoodsTypeVo;
import com.leimingtech.service.utils.page.Pager;

public interface GoodsTypeDao {
    /**
     * 保存商品类型
     * @param goodsType
     */
	void save(GoodsType goodsType);

    /**
     * 修改商品类型
     * @param goodsType
     */
    void update(GoodsType goodsType);

    /**
     * 删除
     * @param
     */
    void delete(@Param("typeId") String typeId);

    /**
     * 获取id商品类型
     * @param typeId
     * @return
     */
    GoodsType findById(@Param("typeId") String typeId);
    
    /**
     * 查询所有的商品类型
     * @return
     */
    List<GoodsType> findList();
	
	/**
	 * 关联查询
	 * @param typeId
	 * @return
	 */
    GoodsTypeVO selectTypeFetchOther(@Param("typeId") String typeId);
	
	 /**
     * 查询条数
     * @param pager
     * @return
     */
    int findCount(Pager pager);

    /**
     * 列表
     * @param pager
     * @return
     */
    List<GoodsType> findPageList(Pager pager);
    
    /**
     * 保存商品类型
     * @param vo
     */
    void saveGoodsType(GoodsTypeVo vo);
    /**
     * 修改商品类型
     * @param vo
     */
    void updateGoodsType(GoodsTypeVo vo);
    
    /**
     * 只修改type
     * @param type
     */
    void updateType(GoodsType type);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<GoodsType> findList2(String parentid);

    /**
     * 通过父id查询子分类
     * @param gtParentId
     * @return
     */
    List<GoodsType> findChild(String gtParentId);

    /**
     * 根据类型名字获取数量
     * @param typeName
     * @return
     */
    int findCountByName(String typeName);
}
