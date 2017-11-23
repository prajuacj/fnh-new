package com.leimingtech.service.module.doc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.DocEnProEntity;
import com.leimingtech.service.module.doc.dao.DocEntityProDao;
import com.leimingtech.service.module.doc.service.DocEntityProService;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：DocEntityProServiceImpl   
 * 类描述：文档实体属性管理
 * 创建人：lkang   
 * 创建时间：2015年5月04日 02:00:00   
 */
@Service("docEntityProService")
public class DocEntityProServiceImpl implements DocEntityProService {

	@Autowired
	private DocEntityProDao docEntityProDao;
	/**
	 * 获取属性的总数
	 * @param pro
	 * @return
	 */
	public int getProTotal(DocEnProEntity pro) {
		return docEntityProDao.getProTotal(pro);
	}

	/**
	 * 获取属性的分页数据
	 * @param pager
	 * @return
	 */
	public Pager getProList(Pager pager) {
		List<DocEnProEntity> list=docEntityProDao.getProList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 根据id获取属性
	 * @param id
	 * @return
	 */
	public DocEnProEntity getProById(String id) {
		return docEntityProDao.getProById(id);
	}

	/**
	 * 保存属性
	 * @param pro
	 */
	public void save(DocEnProEntity pro) {
		pro.setCreateTime(System.currentTimeMillis());
		docEntityProDao.save(pro);
	}

	/**
	 * 修改属性
	 * @param pro
	 */
	public void update(DocEnProEntity pro) {
		pro.setUpdateTime(System.currentTimeMillis());
		docEntityProDao.update(pro);
	}

	/**
	 * 删除属性
	 * @param id
	 */
	public void delete(String id) {
		docEntityProDao.delete(id);
	}

	/**
	 * 根据实体id获取参数列表
	 * @param entityid
	 * @return
	 */
	public List<DocEnProEntity> getAllProList(String entityid) {
		return docEntityProDao.getAllProList(entityid);
	}
	
	
	
}
