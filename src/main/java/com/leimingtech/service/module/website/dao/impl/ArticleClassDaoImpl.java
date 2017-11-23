package com.leimingtech.service.module.website.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.entity.ArticleVo;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.ArticleClass;
import com.leimingtech.core.entity.ArticleClassVo;
import com.leimingtech.service.module.website.dao.ArticleClassDao;
import com.leimingtech.service.module.website.dao.mapper.ArticleClassMapper;

/**
 * Created by ss on 2014/11/4.
 */
@Repository
public class ArticleClassDaoImpl implements ArticleClassDao{

	@Resource
    private ArticleClassMapper articleClassMapper;
    @Override
    public void save(ArticleClass articleClass) {
    	articleClass.setAcId(IdGen.uuid());
        articleClassMapper.save(articleClass);
    }

    @Override
    public void update(ArticleClass articleClass) {
        articleClassMapper.update(articleClass);
    }

    @Override
    public void delete(String id) {
        articleClassMapper.delete(id);
    }

    @Override
    public ArticleClass findById(String id) {
        return articleClassMapper.findById(id);
    }

    @Override
    public List<ArticleClass> findList() {
        return articleClassMapper.findList();
    }

    @Override
    public List<ArticleClassVo> hasChildren(String id) {
        return articleClassMapper.findChildren(id);
    }

    @Override
    public int findCount() {
        return articleClassMapper.findCount();
    }

    @Override
    public List<ArticleClassVo> findForPage() {
        return articleClassMapper.findPageList();
    }

    @Override
    public List<ArticleClass> findAllList() {
        return articleClassMapper.findAllList();
    }

    @Override
    public int duplicate(ArticleClass articleClass) {
        return articleClassMapper.duplicate(articleClass);
    }

	@Override
	public List<ArticleClassVo> findList(ArticleClassVo articleClass) {
		return articleClassMapper.findArticleList(articleClass);
	}

    @Override
    public List<ArticleClass> findArticlePageList(Pager pager) {
        return articleClassMapper.findArticlePageList(pager);
    }

    /**
     * 查询文章分类及分类下的文章
     * @param acId  如果为""则查询全部的
     * @return
     */
    public List<ArticleVo> finfArticleClassAndArticleList(String acId) {
        return articleClassMapper.finfArticleClassAndArticleList(acId);
    }
}
