package com.leimingtech.service.module.doc.dao;

import com.leimingtech.core.entity.base.DocParamEntity;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 项目名称：leimingtech-admin
 * 类名称：DocParamDao
 * 类描述：API文档参数管理
 * 创建人：lkang
 * 创建时间：2015年5月07日 02:00:00
 */
public interface DocParamDao {
    /**
     * 获取参数的总条数
     *
     * @param param
     * @return
     */
    int getParamTotal(DocParamEntity param);

    /**
     * 获取参数的分页数据
     *
     * @param pager
     * @return
     */
    List<DocParamEntity> getParamList(Pager pager);

    /**
     * 保存参数
     *
     * @param param
     */
    void save(DocParamEntity param);

    /**
     * 更新参数
     *
     * @param param
     */
    void update(DocParamEntity param);

    /**
     * 根据id删除参数
     *
     * @param id
     */
    void delete(String id);

    /**
     * 根据id获取参数
     *
     * @param id
     * @return
     */
    DocParamEntity getParamById(String id);

    /**
     * 根据docid获取所有参数
     *
     * @param docid
     * @return
     */
    List<DocParamEntity> getAllParamList(String docid);
}
