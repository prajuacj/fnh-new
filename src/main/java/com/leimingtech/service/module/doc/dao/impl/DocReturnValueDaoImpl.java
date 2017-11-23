package com.leimingtech.service.module.doc.dao.impl;

import java.util.List;

import com.leimingtech.core.common.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.DocReturnValueEntity;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.doc.dao.DocReturnValueDao;
import com.leimingtech.service.module.doc.dao.mapper.DocReturnValueMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：DocReturnValueDaoImpl
 * 类描述：API文档返回值管理
 * 创建人：lkang   
 * 创建时间：2015年5月08日 10:00:00   
 */
@Service("docReturnValueDaoImpl")
public class DocReturnValueDaoImpl extends BaseDao implements DocReturnValueDao {

	@Autowired
	private DocReturnValueMapper docReturnValueMapper;
	
	/**
	 * 获取返回值总条数
	 * @param returnValue
	 * @return
	 */
	public int getReturnValueTotal(DocReturnValueEntity returnValue) {
		return docReturnValueMapper.getReturnValueTotal(returnValue);
	}

	/**
	 * 获取返回值分页数据
	 * @param pager
	 * @return
	 */
	public List<DocReturnValueEntity> getReturnValueList(Pager pager) {
		return docReturnValueMapper.getReturnValueList(pager);
	}

	/**
	 * 保存返回值数据
	 * @param returnValue
	 */
	public void save(DocReturnValueEntity returnValue) {
		returnValue.setId(IdGen.uuid());
		docReturnValueMapper.save(returnValue);
	}

	/**
	 * 根据id删除返回值数据
	 * @param id
	 */
	public void delete(String id) {
		docReturnValueMapper.delete(id);
	}

	/**
	 * 修改返回值数据
	 * @param returnValue
	 */
	public void update(DocReturnValueEntity returnValue) {
		docReturnValueMapper.update(returnValue);
	}

	/**
	 * 根据id获取返回值数据
	 * @param id
	 * @return
	 */
	public DocReturnValueEntity getReturnValueById(String id) {
		return docReturnValueMapper.getReturnValueById(id);
	}

	/**
	 * 根据文档id获取返回值信息
	 * @param docid
	 * @return
	 */
	public DocReturnValueEntity getReturnValueByDocId(String docid) {
		return docReturnValueMapper.getReturnValueByDocId(docid);
	}

	/**
	 * 根据docid获取返回值列表
	 * @param docId
	 * @return
	 */
	public List<DocReturnValueEntity> getReturnValueListByDocId(String docId) {
		return docReturnValueMapper.getReturnValueListByDocId(docId);
	}
	
	
}
