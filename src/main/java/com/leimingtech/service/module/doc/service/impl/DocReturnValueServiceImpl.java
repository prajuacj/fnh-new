package com.leimingtech.service.module.doc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.DocReturnValueEntity;
import com.leimingtech.service.module.doc.dao.DocReturnValueDao;
import com.leimingtech.service.module.doc.service.DocReturnValueService;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：DocReturnValueDaoImpl
 * 类描述：API文档返回值管理
 * 创建人：lkang   
 * 创建时间：2015年5月08日 10:00:00   
 */
@Service("docReturnValueServiceImpl")
public class DocReturnValueServiceImpl implements DocReturnValueService {

	@Autowired
	private DocReturnValueDao docReturnValueDaoImpl;
	
	/**
	 * 获取返回值总条数
	 * @param returnValue
	 * @return
	 */
	public int getReturnValueTotal(DocReturnValueEntity returnValue) {
		return docReturnValueDaoImpl.getReturnValueTotal(returnValue);
	}

	/**
	 * 获取返回值分页数据
	 * @param pager
	 * @return
	 */
	public Pager getReturnValueList(Pager pager) {
		List<DocReturnValueEntity> list=docReturnValueDaoImpl.getReturnValueList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 保存返回值数据
	 * @param returnValue
	 */
	public void save(DocReturnValueEntity returnValue) {
		returnValue.setCreateTime(System.currentTimeMillis());
		docReturnValueDaoImpl.save(returnValue);
	}

	/**
	 * 根据id删除返回值数据
	 * @param id
	 */
	public void delete(String id) {
		docReturnValueDaoImpl.delete(id);
	}

	/**
	 * 修改返回值数据
	 * @param returnValue
	 */
	public void update(DocReturnValueEntity returnValue) {
		returnValue.setUpdateTime(System.currentTimeMillis());
		docReturnValueDaoImpl.update(returnValue);
	}

	/**
	 * 根据id获取返回值数据
	 * @param id
	 * @return
	 */
	public DocReturnValueEntity getReturnValueById(String id) {
		return docReturnValueDaoImpl.getReturnValueById(id);
	}

	/**
	 * 根据文档id获取返回值信息
	 * @param docid
	 * @return
	 */
	public DocReturnValueEntity getReturnValueByDocId(String docid) {
		return docReturnValueDaoImpl.getReturnValueByDocId(docid);
	}

	/**
	 * 根据docid获取返回值列表
	 * @param docId
	 * @return
	 */
	public List<DocReturnValueEntity> getReturnValueListByDocId(String docId) {
		return docReturnValueDaoImpl.getReturnValueListByDocId(docId);
	}
	
	

}
