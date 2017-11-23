package com.leimingtech.service.module.website.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Article;
import com.leimingtech.service.module.website.dao.ArticleDao;
import com.leimingtech.service.module.website.service.ArticleService;
import com.leimingtech.service.utils.page.Pager;

/**
 * Created by rabook on 2014/11/9.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Resource
	private ArticleDao articleDao;

	@Override
	public void save(Article article) {
		article.setCreateTime(System.currentTimeMillis());
		articleDao.save(article);
	}

	@Override
	public void update(Article article) {
		articleDao.update(article);
	}

	@Override
	public void delete(String id) {
		articleDao.delete(id);
	}

	@Override
	public Article findById(String id) {
		return articleDao.findById(id);
	}

	@Override
	public List<Article> findList() {
		return articleDao.findList();
	}

	@Override
	public Pager findListForPage(Pager pager, Article article) {
		pager.setCondition(article);

		List<Article> list = articleDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public int findCount(Article article) {
		return articleDao.findCount(article);
	}

	@Override
	public List<Article> findListByArticle(Article article) {
		return articleDao.findListByArticle(article);
	}

	@Override
	public Pager findArticlePageList(Pager pager) {
		List<Article> list = articleDao.findArticlePageList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 根据文章分类id获取文章
	 * 
	 * @param acId
	 *            分类id
	 * @return
	 */
	public List<Article> findArticleByClassId(String acId) {
		return articleDao.findArticleByClassId(acId);
	}

	/**
	 * 根据文章实体查询文章列表
	 * 
	 * @param article
	 * @return
	 */
	public List<Article> findArticleList(Article article) {
		return articleDao.findArticleList(article);
	}
}
