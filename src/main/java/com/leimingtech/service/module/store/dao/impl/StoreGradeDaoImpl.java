package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.entity.base.StoreGrade;
import com.leimingtech.service.module.store.dao.StoreGradeDao;
import com.leimingtech.service.module.store.dao.mapper.StoreGradeMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称：leimingtech-admin
 * 类名称：StoreGradeDaoImpl
 * 类描述：
 * 创建人：yanghui
 * 创建时间：2014年11月13日 下午3:23:55
 * 修改人：yanghui
 * 修改时间：2014年11月13日 下午3:23:55
 * 修改备注：
 */
@Repository
public class StoreGradeDaoImpl implements StoreGradeDao {

    @Resource
    private StoreGradeMapper storeGradeMapper;

    @Override
    public List<StoreGrade> queryStoreGradeList(Pager pager) {
        // TODO Auto-generated method stub
        return storeGradeMapper.queryStoreGradeList(pager);
    }

    @Override
    public void save(StoreGrade grade) {
        storeGradeMapper.save(grade);
    }

    @Override
    public void delete(String id) {
        storeGradeMapper.delete(id);
    }

    @Override
    public StoreGrade queryById(String id) {
        return storeGradeMapper.queryById(id);
    }

    @Override
    public void update(StoreGrade grade) {
        storeGradeMapper.update(grade);

    }

    /**
     * 校验查重
     *
     * @param storeGrade
     * @return
     */
    @Override
    public int queryCount(StoreGrade storeGrade) {
        return storeGradeMapper.queryCount(storeGrade);
    }

    /**
     * 查找店铺等级
     * @param storeGrade
     * @return
     */
    @Override
    public List<StoreGrade> findStoreGradeList(StoreGrade storeGrade) {
        return storeGradeMapper.findStoreGradeList(storeGrade);
    }
}
