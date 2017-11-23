package com.leimingtech.service.module.website.dao.mapper;

import java.util.List;
import java.util.Map;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.base.Article;
import com.leimingtech.service.utils.page.Pager;

/**
 * Created by rabook on 2014/11/9.
 */

@SqlMapper
public interface ArticleMapper {

	 /**
     * 保存
     * @param article
     * @return List<Article>
     */
    public void save(Article article);
    
    /**
     * 更新
     * @param article
     * @return List<Article>
     */
    public void update(Article article);
    
    /**
     * 删除
     * @param id
     * @return List<Article>
     */
    public void delete(String id);
    
    /**
     * 根据id查询对象
     * @param id
     * @return List<Article>
     */
    public Article findById(String id);
    
    /**
     * 查询列表
     * @return List<Article>
     */
    public List<Article> findList();
    
    /**
     * 总条数
     * @param article
     * @return int
     */
    public int findCount(Article article);
    
    /**
     * 分页查询列表
     * @param map
     * @return List<Article>
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
    List<Article> findArticlePageList(Pager pager);

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
