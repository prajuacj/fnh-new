package com.leimingtech.service.module.website.dao;

import java.util.List;

import com.leimingtech.core.entity.ArticleClass;
import com.leimingtech.core.entity.ArticleClassVo;
import com.leimingtech.core.entity.ArticleVo;
import com.leimingtech.service.utils.page.Pager;

public interface ArticleClassDao {

	/**
	 * 添加
	 * @param articleClass
	 */
	public void save(ArticleClass articleClass);
	
	/**
     * 修改
     * @param articleClass
     */
	public void update(ArticleClass articleClass);
    
	/**
     * 删除
     * @param id
     */
	public void delete(String id);
    
	 /**
     * 根据id查找分类
     * @param id
     * @return
     */
	public ArticleClass findById(String id);
    
	/**
     * 列表
     * @return
     */
	public List<ArticleClass> findList();
    
	 /**
     * 获取子分类
     * @param id
     * @return
     */
	public List<ArticleClassVo> hasChildren(String id);
    
	/**
     * 获取总条数
     * @return
     */
	public int findCount();
    
	 /**
     * 列表分页方法
     * @return
     */
	public List<ArticleClassVo> findForPage();
    
	/**
     * 获取所有分类
     * @return
     */
	public List<ArticleClass> findAllList();
    
	 /**
	  * 重复数
	  * @param articleClass
	  * @return
	  */
	public int duplicate(ArticleClass articleClass);
    
	/**
     * 获取文章分类标题以及下面的文章标题 （acCode不为空的）
     * @return
     */
	public List<ArticleClassVo> findList(ArticleClassVo ArticleClass);

	/**
	 * 分页查询文章分类
	 * @return
	 */
	public List<ArticleClass> findArticlePageList(Pager pager);

	/**
	 * 查询文章分类及分类下的文章
	 * @param acId  如果为""则查询全部的
	 * @return
	 */
	List<ArticleVo> finfArticleClassAndArticleList(String acId);
}
