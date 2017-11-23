package com.leimingtech.service.module.goods.dao.mapper;

import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

@SqlMapper
public interface GoodsSpecMapper {
    /**
     * 保存goodsspec
     *
     * @param goodsSpec
     */
    void saveGoodsSpec(GoodsSpec goodsSpec);

    /**
     * 根据goodsid批量删除
     *
     * @param goodsId
     */
    void deleteGoodsSpecByGoodsId(String goodsId);

    /**
     * 通过goodsId查找
     *
     * @param goodsId
     * @return
     */
    List<GoodsSpec> findListByGoodsId(String goodsId);

    /**
     * 通过主键查找
     *
     * @param goodsSpecId
     * @return
     */
    GoodsSpec findByGoodsSpecId(String goodsSpecId);

    /**
     * 查找所有的
     */
    List<GoodsSpec> findAllList();

    /**
     * 分页并且条件查询
     *
     * @param pager
     * @return
     */
    List<GoodsSpec> findPageList(Pager pager);

    /**
     * 查询条数
     */
    Integer findPageListCount(Pager pager);

    /**
     * 修改库存
     */
    void updateGoodsSpecStorage(GoodsSpec goodsSpec);

    /**
     * 修改商品规格
     * @param goodsSpec
     */
    void updateGoodsSpec(GoodsSpec goodsSpec);
}
