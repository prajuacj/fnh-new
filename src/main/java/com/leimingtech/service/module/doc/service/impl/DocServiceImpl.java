package com.leimingtech.service.module.doc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.DocEntity;
import com.leimingtech.service.module.doc.dao.DocDao;
import com.leimingtech.service.module.doc.service.DocService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 项目名称：leimingtech-admin
 * 类名称：DocServiceImpl
 * 类描述：DocService实现类
 * 创建人：lkang
 * 创建时间：2015年4月22日 10:52:19
 */
@Service("docService")
public class DocServiceImpl implements DocService {

    @Autowired
    private DocDao docDao;

    /**
     * 获取文档总数
     *
     * @param pager
     * @return
     */
    @Override
    public Pager getDocList(Pager pager) {
    	List<DocEntity> list=docDao.getDocList(pager);
		pager.setResult(list);
		return pager;
    }

    /**
     * 获取文档分页列表
     *
     * @param id
     * @return
     */
    @Override
    public DocEntity getDocById(String id) {
        return docDao.getDocById(id);
    }

    /**
     * 根据id获取文档详细
     *
     * @param docEntity
     * @return
     */
    @Override
    public int getDocCount(DocEntity docEntity) {
        return docDao.getDocCount(docEntity);
    }

    /**
     * 添加文档数据
     *
     * @param doc
     */
    @Override
    public void save(DocEntity doc) {
        doc.setCreateTime(System.currentTimeMillis());
        docDao.save(doc);
    }

    /**
     * 更新文档数据
     *
     * @param doc
     */
    @Override
    public void update(DocEntity doc) {
        doc.setUpdateTime(System.currentTimeMillis());
        docDao.update(doc);
    }

    /**
     * 删除文档数据
     *
     * @param id
     */
    @Override
    public void delete(String id) {
        docDao.delete(id);
    }

    /**
     * 获取所有的文档
     *
     * @return
     */
    public List<DocEntity> getAllDocList(DocEntity doc) {
        return docDao.getAllDocList(doc);
    }

}