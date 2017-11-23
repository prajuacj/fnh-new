package com.leimingtech.service.module.website.dao;

import java.util.List;

import com.leimingtech.core.entity.base.WebCode;

/**
 * @author llf
 * @Package com.leimingtech.service.module.website.dao
 * @Description:
 * @date 2014/12/16 14:23
 */
public interface WebCodeDao {

    /**
     * 保存
     * @param webCode
     */
    void save(WebCode webCode);

    /**
     * 查询所有
     * @return
     */
    List<WebCode> queryAll();

    /**
     * 查询单个
     * @param id
     * @return
     */
    WebCode queryById(String id);

    /**
     * 修改
     * @param webCode
     */
    void update(WebCode webCode);

    /**
     * 删除
     * @param id
     */
    void delete(String id);
    
    
    /**
     * 获取指定类型的 webCode
     * @param type
     * @return
     */
    public List<WebCode> queryAllByType(String type);

    /**
     * 获取指定类型的 webCode
     * @param type
     * @return
     */
    public WebCode queryNewByType(String type);

    public List<WebCode> queryByVarname(String type);
}
