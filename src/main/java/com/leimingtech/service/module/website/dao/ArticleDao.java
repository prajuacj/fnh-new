package com.leimingtech.service.module.website.dao;

import com.leimingtech.core.entity.base.Article;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by rabook on 2014/11/9.
 */
public interface ArticleDao {

	/**
	 * 保存
	 * @param article
	 */
    public void save(Article article);
    
    /**
     * 更新
     * @param article
     */
    public void update(Article article);
    
    /**
     * 删除
     * @param id
     */
    public void delete(String id);
    
    /**
     * 根据
     * @param id
     * @return
     */
    public Article findById(String id);
    
    /**
     * 查询列表
     * @return
     */
    public List<Article> findList();
    
    /**
     * 总条数
     * @param article
     * @return
     */
    public int findCount(Article article);
    
    /**
     * 分页查询列表
     * @param pager
     * @return
     */
    public List<Article> findPageList(Pager pager);
    
    /**
     * 根据条件查询列表
     * @param article
     * @return List<Article>
     */
    public List<Article> findListByArticle(Article article);

    /**
     * 分页查询列表
     * @param pager
     * @return List<Article>
     */
    public List<Article> findArticlePageList(Pager pager);

    /**
     * 根据文章分类id获取文章
     * @param acId 分类id
     * @return
     */
    List<Article> findArticleByClassId(String acId);

    /**
     * 根据文章实体查询文章列表
     * @param article
     * @return
     */
    List<Article> findArticleList(Article article);
}
