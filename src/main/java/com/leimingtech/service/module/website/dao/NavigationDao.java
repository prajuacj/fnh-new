package com.leimingtech.service.module.website.dao;

import com.leimingtech.core.entity.base.Navigation;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * @author llf
 * @Package com.leimingtech.service.module.website.dao
 * @Description:
 * @date 2014/11/11 13:32
 */
public interface NavigationDao {

    /**
     * 保存
     *
     * @param navigation
     */
    void save(Navigation navigation);

    /**
     * 修改
     *
     * @param navigation
     */
    void update(Navigation navigation);

    /**
     * 删除
     *
     * @param id
     */
    void delete(String id);

    /**
     * 查询单条
     *
     * @param id
     * @return
     */
    Navigation findById(String id);

    /**
     * 总条数
     *
     * @param navigation
     * @return
     */
    int findCount(Navigation navigation);

    /**
     * 分页列表
     *
     * @return
     */
    List<Navigation> findPageList(Pager pager);

    /**
     * 根据参数获取 列表查询
     *
     * @param navigation
     * @return
     */
    List<Navigation> findAllList(Navigation navigation);
}
