package com.leimingtech.service.module.website.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Article;
import com.leimingtech.service.module.website.dao.ArticleDao;
import com.leimingtech.service.module.website.dao.mapper.ArticleMapper;

/**
 * Created by rabook on 2014/11/9.
 */
@Repository
public class ArticleDaoImpl implements ArticleDao{

    @Resource
    private ArticleMapper articleMapper;
    @Override
    public void save(Article article) {
    	article.setArticleId(IdGen.uuid());
        articleMapper.save(article);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void delete(String id) {
        articleMapper.delete(id);
    }

    @Override
    public Article findById(String id) {
        return articleMapper.findById(id);
    }

    @Override
    public List<Article> findList() {
        return articleMapper.findList();
    }

    @Override
    public int findCount(Article article) {
        return articleMapper.findCount(article);
    }

    @Override
    public List<Article> findPageList(Pager pager) {
        return articleMapper.findPageList(pager);
    }

	@Override
	public List<Article> findListByArticle(Article article) {
		return articleMapper.findListByArticle(article);
	}

    @Override
    public List<Article> findArticlePageList(Pager pager) {
        return articleMapper.findArticlePageList(pager);
    }

    /**
     * 根据文章分类id获取文章
     * @param acId 分类id
     * @return
     */
    public List<Article> findArticleByClassId(String acId) {
        return articleMapper.findArticleByClassId(acId);
    }

    /**
     * 根据文章实体查询文章列表
     * @param article
     * @return
     */
    public List<Article> findArticleList(Article article) {
        return articleMapper.findArticleList(article);
    }
}
