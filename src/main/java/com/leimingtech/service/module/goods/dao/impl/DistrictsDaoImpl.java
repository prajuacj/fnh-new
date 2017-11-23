package com.leimingtech.service.module.goods.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.Districts;
import com.leimingtech.core.entity.GoodsClassh5;
import com.leimingtech.service.module.goods.dao.DistrictsDao;
import com.leimingtech.service.module.goods.dao.mapper.DistrictsMapper;

/**
 * 商品分类dao层实现类
 */
@Repository
public class DistrictsDaoImpl implements DistrictsDao {

    @Resource
    private DistrictsMapper districtsMapper;

    @Override
    public void save(Districts districts) {
        districts.setGcId(IdGen.uuid());
        districtsMapper.save(districts);
    }

    @Override
    public void update(Districts districts) {
        districtsMapper.update(districts);
    }

    @Override
    public void delete(String id) {
        districtsMapper.delete(id);
    }

    @Override
    public int findCount(Districts districts) {
        return districtsMapper.findCount(districts);
    }

    @Override
    public List<Districts> findAll() {
        return districtsMapper.findAll();
    }

    @Override
    public Districts findById(String gcId) {
        return districtsMapper.findById(gcId);
    }

    @Override
    public List<Districts> findList(String parentid) {
        return districtsMapper.findList(parentid);
    }

    @Override
    public List<Districts> findListbyishow(Districts districts) {
        return districtsMapper.findListbyishow(districts);
    }

    @Override
    public List<Districts> findAllbyisshow(Districts districts) {
        return districtsMapper.findAllbyisshow(districts);
    }

    /**
     * 通过父id查询子分类
     *
     * @param gcParentId
     * @return
     */
    public List<Districts> findChild(String gcParentId) {
        return districtsMapper.findChild(gcParentId);
    }


    /**
     * 修改分类
     *
     * @param districts
     */
    @Override
    public void updatebyparentid(Districts districts) {
        districtsMapper.updatebyparentid(districts);
    }

    /**
     * 查询所有分类
     * @return
     */
    @Override
    public List<GoodsClassh5> queryDistricts() {
        return districtsMapper.queryDistricts();
    }
    
    @Override
    public List<Districts> queryBottomChildList(Districts districts) {
        return districtsMapper.queryBottomChildList(districts);
    }
}
