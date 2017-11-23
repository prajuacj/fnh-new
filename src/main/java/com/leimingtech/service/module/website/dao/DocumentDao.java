package com.leimingtech.service.module.website.dao;

import java.util.List;

import com.leimingtech.core.entity.base.Document;
import com.leimingtech.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.leimingtech.service.module.website.dao
 * @Description:
 * @date 2014/11/11 11:27
 */
public interface DocumentDao {

    /**
     * 保存
     * @param document
     */
    public void save(Document document);

    /**
     * 修改
     * @param document
     */
    public void update(Document document);

    /**
     * 删除
     * @param id
     */
    public void delete(String id);

    /**
     * 查询单条记录
     * @param id
     * @return
     */
    public Document findById(String id);

    /**
     * 查询条数
     * @return
     */
    public int findCount();

    /**
     * 分页列表
     * @param pager
     * @return
     */
    public List<Document> findPageList(Pager pager);
}
