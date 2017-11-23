package com.leimingtech.service.module.goods.dao.impl;


import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.GoodsClass;
import com.leimingtech.core.entity.GoodsClassh5;
import com.leimingtech.service.module.goods.dao.GoodsClassDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsClassMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品分类dao层实现类
 */
@Repository
public class GoodsClassDaoImpl implements GoodsClassDao {

    @Resource
    private GoodsClassMapper goodsClassMapper;

    @Override
    public void save(GoodsClass goodsClass) {
        goodsClass.setGcId(IdGen.uuid());
        goodsClassMapper.save(goodsClass);
    }

    @Override
    public void update(GoodsClass goodsClass) {
        goodsClassMapper.update(goodsClass);
    }

    @Override
    public void delete(String id) {
        goodsClassMapper.delete(id);
    }

    @Override
    public int findCount(GoodsClass goodsClass) {
        return goodsClassMapper.findCount(goodsClass);
    }

    @Override
    public List<GoodsClass> findAll() {
        return goodsClassMapper.findAll();
    }

    @Override
    public GoodsClass findById(String gcId) {
        return goodsClassMapper.findById(gcId);
    }

    @Override
    public List<GoodsClass> findList(String parentid) {
        return goodsClassMapper.findList(parentid);
    }

    @Override
    public List<GoodsClass> findListbyishow(GoodsClass goodsClass) {
        return goodsClassMapper.findListbyishow(goodsClass);
    }

    @Override
    public List<GoodsClass> findAllbyisshow(GoodsClass goodsClass) {
        return goodsClassMapper.findAllbyisshow(goodsClass);
    }

    /**
     * 通过父id查询子分类
     *
     * @param gcParentId
     * @return
     */
    public List<GoodsClass> findChild(String gcParentId) {
        return goodsClassMapper.findChild(gcParentId);
    }


    /**
     * 修改分类
     *
     * @param goodsClass
     */
    @Override
    public void updatebyparentid(GoodsClass goodsClass) {
        goodsClassMapper.updatebyparentid(goodsClass);
    }

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<GoodsClassh5> queryGoodsClass() {
        return goodsClassMapper.queryGoodsClass();
    }
}
