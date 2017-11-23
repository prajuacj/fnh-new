package com.leimingtech.service.module.website.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.ArticleVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.ArticleClass;
import com.leimingtech.core.entity.ArticleClassVo;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

/**
 * Created by rabook on 2014/11/4.
 */

@SqlMapper
public interface ArticleClassMapper {

	void save(ArticleClass articleClass);

    void update(ArticleClass articleClass);

    void delete(String id);

    ArticleClass findById(String id);

    List<ArticleClass> findList();

    List<ArticleClassVo> findPageList();

    int findCount();

    List<ArticleClassVo> findChildren(String id);

    List<ArticleClass> findAllList();

    int duplicate(ArticleClass articleClass);
    
    List<ArticleClassVo> findArticleList(ArticleClassVo articleClassVo);

    List<ArticleClass> findArticlePageList(Pager pager);

    /**
     * 查询文章分类及分类下的文章
     * @param acId  如果为""则查询全部的
     * @return
     */
    List<ArticleVo> finfArticleClassAndArticleList(@Param("acId") String acId);
}
