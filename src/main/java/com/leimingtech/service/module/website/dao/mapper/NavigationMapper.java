package com.leimingtech.service.module.website.dao.mapper;

import java.util.List;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.base.Navigation;
import com.leimingtech.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.leimingtech.service.module.website.dao.mapper
 * @Description:
 * @date 2014/11/11 13:11
 */

@SqlMapper
public interface NavigationMapper {

	/**
     * 保存
     * @param navigation
     */
    public void save(Navigation navigation);
    /**
     * 修改
     * @param navigation
     */
    public void update(Navigation navigation);
    
    /**
     * 修改
     * @param navigation
     */
    public void delete(String id);
    
    public Navigation findById(String id);
    /**
     * 查询总条数
     * @return
     */
    public int findCount(Navigation navigation);
    public List<Navigation> findPageList(Pager pager);
    /**
     * 根据参数获取 列表查询
     * @param navigation
     * @return
     */
    public List<Navigation> findAllList(Navigation navigation);
}
