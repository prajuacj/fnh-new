package com.leimingtech.service.module.website.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.leimingtech.core.entity.ArticleClass;
import com.leimingtech.core.entity.ArticleClassVo;
import com.leimingtech.core.entity.ArticleVo;
import com.leimingtech.core.entity.base.Article;
import com.leimingtech.core.entity.vo.ArticleClassTitleVo;
import com.leimingtech.service.module.website.dao.ArticleClassDao;
import com.leimingtech.service.module.website.service.ArticleClassService;
import com.leimingtech.service.utils.page.Pager;

/**
 * Created by rabook on 2014/11/4.
 */
@Service
public class ArticleClassServiceImpl implements ArticleClassService {

	@Resource
	private ArticleClassDao acDao;

	@Override
	public void save(ArticleClass articleClass) {
		acDao.save(articleClass);
	}

	@Override
	public void update(ArticleClass articleClass) {
		acDao.update(articleClass);
	}

	@Override
	public void delete(String id) {

		List<ArticleClassVo> list = acDao.hasChildren(id);
		for (ArticleClassVo vo : list) {
			acDao.delete(vo.getAcId());
		}
		acDao.delete(id);
	}

	@Override
	public ArticleClass findById(String id) {
		return acDao.findById(id);
	}

	@Override
	public List<ArticleClass> findList() {
		return acDao.findList();
	}

	@Override
	public List<ArticleClassVo> findListForPage() {
		return acDao.findForPage();
	}

	@Override
	public List<ArticleClassVo> findChildren(String id) {
		return acDao.hasChildren(id);
	}

	@Override
	public int findCount() {
		return acDao.findCount();
	}

	@Override
	public List<ArticleClass> findAllList() {
		return acDao.findAllList();
	}

	@Override
	public int duplicate(ArticleClass articleClass) {
		return acDao.duplicate(articleClass);
	}

	// @Override
	// public List<ArticleClassVo> findList(ArticleClassVo ArticleClass) {
	// return acDao.findList(ArticleClass);
	// }

	@Override
	public List<ArticleClassTitleVo> findTitleList() {
		List<ArticleClassTitleVo> listacts = Lists.newArrayList();
		ArticleClassVo articleClassVo = new ArticleClassVo();
		List<ArticleClassVo> lists = acDao.findList(articleClassVo);
		String temp = "";
		List<Article> listArticle = Lists.newArrayList();
		ArticleClassTitleVo actv = new ArticleClassTitleVo();

		for (int i = 0; i < lists.size(); i++) {
			if (temp != lists.get(i).getAcId()) {
				if (!temp.equals("")) {
					actv.setArticle(listArticle);
					listacts.add(actv);
					actv = new ArticleClassTitleVo();
					listArticle = Lists.newArrayList();
				}
				actv.setAcCode(lists.get(i).getAcCode());
				actv.setAcId(lists.get(i).getAcId());
				actv.setAcName(lists.get(i).getAcName());
				actv.setAcParentId(lists.get(i).getAcParentId());
				actv.setAcSort(lists.get(i).getAcSort());

				Article article = new Article();
				article.setArticleId(lists.get(i).getArticleId());
				article.setArticleTitle(lists.get(i).getArticleTitle());
				article.setArticleUrl(lists.get(i).getArticleUrl());
				article.setArticleContent(lists.get(i).getArticleContent());
				listArticle.add(article);

				temp = lists.get(i).getAcId();
			} else {
				Article article = new Article();
				article.setArticleId(lists.get(i).getArticleId());
				article.setArticleTitle(lists.get(i).getArticleTitle());
				article.setArticleUrl(lists.get(i).getArticleUrl());
				article.setArticleContent(lists.get(i).getArticleContent());
				listArticle.add(article);
				temp = lists.get(i).getAcId();
			}
		}
		actv.setArticle(listArticle);
		listacts.add(actv);
		return listacts;
	}

	@Override
	public Pager findArticlePageList(Pager pager) {
		List<ArticleClass> list = acDao.findArticlePageList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 查询文章分类及分类下的文章
	 * 
	 * @param acId
	 *            如果为""则查询全部的
	 * @return
	 */
	public List<ArticleVo> finfArticleClassAndArticleList(String acId) {
		return acDao.finfArticleClassAndArticleList(acId);
	}
}
