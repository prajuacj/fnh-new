package com.leimingtech.service.module.doc.dao;

import com.leimingtech.core.entity.base.DocReturnValueEntity;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 项目名称：leimingtech-admin
 * 类名称：DocReturnValueDao
 * 类描述：API文档返回值管理
 * 创建人：lkang
 * 创建时间：2015年5月08日 10:00:00
 */
public interface DocReturnValueDao {

    /**
     * 获取返回值总条数
     *
     * @param returnValue
     * @return
     */
    int getReturnValueTotal(DocReturnValueEntity returnValue);

    /**
     * 获取返回值分页数据
     *
     * @param pager
     * @return
     */
    List<DocReturnValueEntity> getReturnValueList(Pager pager);

    /**
     * 保存返回值数据
     *
     * @param returnValue
     */
    void save(DocReturnValueEntity returnValue);

    /**
     * 根据id删除返回值数据
     *
     * @param id
     */
    void delete(String id);

    /**
     * 修改返回值数据
     *
     * @param returnValue
     */
    void update(DocReturnValueEntity returnValue);

    /**
     * 根据id获取返回值数据
     *
     * @param id
     * @return
     */
    DocReturnValueEntity getReturnValueById(String id);

    /**
     * 根据文档id获取返回值信息
     *
     * @param docid
     * @return
     */
    DocReturnValueEntity getReturnValueByDocId(String docid);

    /**
     * 根据docid获取返回值列表
     *
     * @param docId
     * @return
     */
    List<DocReturnValueEntity> getReturnValueListByDocId(String docId);

}