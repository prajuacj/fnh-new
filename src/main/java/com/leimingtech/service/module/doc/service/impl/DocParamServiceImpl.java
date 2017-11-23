package com.leimingtech.service.module.doc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.DocParamEntity;
import com.leimingtech.service.module.doc.dao.DocParamDao;
import com.leimingtech.service.module.doc.service.DocParamService;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：DocParamServiceImpl   
 * 类描述：API文档参数管理
 * 创建人：lkang   
 * 创建时间：2015年5月07日 02:00:00   
 */
@Service("docParamServiceImpl")
public class DocParamServiceImpl implements DocParamService {

	@Autowired
	private DocParamDao docParamDaoImpl;
	
	/**
	 * 获取参数的总条数
	 * @param param
	 * @return
	 */
	public int getParamTotal(DocParamEntity param) {
		return docParamDaoImpl.getParamTotal(param);
	}

	/**
	 * 获取参数的分页数据
	 * @param pager
	 * @return
	 */
	public Pager getParamList(Pager pager) {
		List<DocParamEntity> list=docParamDaoImpl.getParamList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 保存参数
	 * @param param
	 */
	public void save(DocParamEntity param) {
		param.setCreateTime(System.currentTimeMillis());
		docParamDaoImpl.save(param);
	}

	/**
	 * 更新参数
	 * @param param
	 */
	public void update(DocParamEntity param) {
		param.setUpdateTime(System.currentTimeMillis());
		docParamDaoImpl.update(param);
	}

	/**
	 * 根据id删除参数
	 * @param id
	 */
	public void delete(String id) {
		docParamDaoImpl.delete(id);
	}

	/**
	 * 根据id获取参数
	 * @param id
	 * @return
	 */
	public DocParamEntity getParamById(String id) {
		return docParamDaoImpl.getParamById(id);
	}

	/**
	 * 根据docid获取所有参数
	 * @param docid
	 * @return
	 */
	public List<DocParamEntity> getAllParamList(String docid) {
		return docParamDaoImpl.getAllParamList(docid);
	}
	
	

}
