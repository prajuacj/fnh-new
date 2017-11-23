package com.leimingtech.service.module.doc.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.DocEnProEntity;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：DocEntityProMapper   
 * 类描述：文档实体属性管理
 * 创建人：lkang   
 * 创建时间：2015年5月04日 02:00:00   
 */
@SqlMapper
public interface DocEntityProMapper {
	
	/**
	 * 获取属性的总数
	 * @param pro
	 * @return
	 */
	int getProTotal(DocEnProEntity pro);
	
	/**
	 * 获取属性的分页数据
	 * @param pager
	 * @return
	 */
	List<DocEnProEntity> getProList(Pager pager);
	
	/**
	 * 根据id获取属性
	 * @param id
	 * @return
	 */
	DocEnProEntity getProById(String id);
	
	/**
	 * 保存属性
	 * @param pro
	 */
	void save(DocEnProEntity pro);
	
	/**
	 * 修改属性
	 * @param pro
	 */
	void update(DocEnProEntity pro);
	
	/**
	 * 删除属性
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 根据实体id获取参数列表
	 * @param entityid
	 * @return
	 */
	List<DocEnProEntity> getAllProList(String entityid);
}
