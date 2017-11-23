package com.leimingtech.service.module.doc.dao.impl;

import java.util.List;

import com.leimingtech.core.common.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.DocEnEntity;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.doc.dao.DocEntityDao;
import com.leimingtech.service.module.doc.dao.mapper.DocEntityMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：DocEntityServiceImpl   
 * 类描述：文档实体管理
 * 创建人：lkang   
 * 创建时间：2015年5月04日 10:52:19   
 */
@Service("docEntityDao")
public class DocEntityDaoImpl extends BaseDao implements DocEntityDao {

	@Autowired
	private DocEntityMapper docEntityMapper;
	/**
	 * 获取引用实体总数
	 * @param docEntity
	 * @return
	 */
	public int getDocEntityTotal(DocEnEntity docEntity) {
		return docEntityMapper.getDocEntityTotal(docEntity);
	}

	/**
	 * 获取引用实体分页数据
	 * @param pager
	 * @return
	 */
	public List<DocEnEntity> getDocEntityList(Pager pager) {
		return docEntityMapper.getDocEntityList(pager);
	}

	/**
	 * 根据id获取引用实体
	 * @param entityId
	 * @return
	 */
	public DocEnEntity getDocEntityById(String entityId) {
		return docEntityMapper.getDocEntityById(entityId);
	}

	/**
	 * 保存引用实体
	 * @param docEntity
	 */
	public void saveDocEntity(DocEnEntity docEntity) {
		docEntity.setId(IdGen.uuid());
		docEntityMapper.saveDocEntity(docEntity);
	}

	/**
	 * 修改引用实体
	 * @param docEntity
	 */
	public void updateDocEntity(DocEnEntity docEntity) {
		docEntityMapper.updateDocEntity(docEntity);
	}

	/**
	 *根据id删除引用实体
	 * @param entityId
	 */
	public void deleteDocEntity(String entityId) {
		docEntityMapper.deleteDocEntity(entityId);
	}

	/**
	 * 获取所有的实体
	 * @return
	 */
	public List<DocEnEntity> getAllDocEntityList() {
		return docEntityMapper.getAllDocEntityList();
	}

	/**
	 * 根据名字获取引用实体
	 * @param entityName
	 * @return
	 */
	public DocEnEntity getDocEntityByName(String entityName) {
		return docEntityMapper.getDocEntityByName(entityName);
	}
	
	

}
