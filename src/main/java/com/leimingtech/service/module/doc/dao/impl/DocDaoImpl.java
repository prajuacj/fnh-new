package com.leimingtech.service.module.doc.dao.impl;

import java.util.List;

import com.leimingtech.core.common.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.DocEntity;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.doc.dao.DocDao;
import com.leimingtech.service.module.doc.dao.mapper.DocMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：DocDaoImpl
 * 类描述：DAO 实现类
 * 创建人：lkang   
 * 创建时间：2015年4月22日 10:52:19   
 */
@Service("docDao")
public class DocDaoImpl extends BaseDao implements DocDao {

	@Autowired
	private DocMapper docMapper;
	/**
	 * 获取文档总数
	 * @param docEntity
	 * @return
	 */
	@Override
	public int getDocCount(DocEntity docEntity) {
		return docMapper.getDocCount(docEntity);
	}

	/**
	 * 获取文档分页列表
	 * @param pager
	 * @return
	 */
	@Override
	public List<DocEntity> getDocList(Pager pager) {
		return docMapper.getDocList(pager);
	}

	/**
	 * 根据id获取文档详细
	 * @param id
	 * @return
	 */
	@Override
	public DocEntity getDocById(String id) {
		return docMapper.getDocById(id);
	}

	/**
	 * 添加文档数据
	 * @param doc
	 */
	@Override
	public void save(DocEntity doc) {
		doc.setId(IdGen.uuid());
		docMapper.save(doc);
	}

	/**
	 * 更新文档数据
	 * @param doc
	 */
	@Override
	public void update(DocEntity doc) {
		docMapper.update(doc);
	}

	/**
	 * 删除文档数据
	 * @param id
	 */
	@Override
	public void delete(String id) {
		docMapper.delete(id);
	}

	/**
	 * 获取所有的文档
	 * @return
	 */
	public List<DocEntity> getAllDocList(DocEntity doc){
		return docMapper.getAllDocList(doc);
	}
}
