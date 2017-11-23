package com.leimingtech.service.module.submall.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.GoodsClass;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.GoodsCombinHaoHuo;
import com.leimingtech.core.entity.base.SubMall;
import com.leimingtech.core.entity.base.SubMallGcAreaRelation;
import com.leimingtech.core.entity.base.SubMallGcSubClassRelation;
import com.leimingtech.core.entity.base.SubMallGoodsClass;
import com.leimingtech.core.entity.vo.AreaCategoryVo;
import com.leimingtech.core.entity.vo.CategoryVo;
import com.leimingtech.core.entity.vo.SubCategoryVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 子商城分类mapper
 * @author Alienware
 *
 */
@SqlMapper
public interface SubMallGoodsClassMapper {
	
	List<CategoryVo> queryBySubMallId(String subMallId);
	
	List<CategoryVo> queryByPager(Pager pager);
	
	CategoryVo queryById(String sgId);
	
	List<SubCategoryVo> querySubCategoryBysgId(String smGcId);
	
	SubCategoryVo querySubCategoryById(String subCategoryId);
	
	List<AreaCategoryVo> queryAreaCategoryBysgId(String smGcId);
	
	AreaCategoryVo queryAreaCategoryById(String areaCategoryId); 
	
	List<Goods> queryGoodsByAreaCategoryId(String areaCategoryId);
	
	List<Goods> queryGoodsBySmGcId(String smGcId);
	
	List<GoodsClass> queryGoodsClassNoInSubMall(String subMallId);
	
	List<GoodsClass> queryGoodsClassNoInSubGoodsClass(String smGcId);
	
	int insertSubMallGoodsClass(SubMallGoodsClass SubMallGoodsClass);
	
	int deleteById(String smGcId);
	
	int deleteSubCategoryBySmGcId(String smGcId);
	
	int deleteAreaCategoryBySmGcId(String smGcId);
	
	List<SubMall> querySubMallByPager(Pager pager);
	
	List<AreaCategoryVo> queryAreaCategoryByPager(Pager pager);
	
	int saveArea(SubMallGcAreaRelation subMallGcAreaRelation);
	
	int deleteAreaById(Integer areaCategoryId);
	
	int deleteSubCategoryById(String subCategoryId);
	
	int saveSubCategory(SubMallGcSubClassRelation subMallGcSubClassRelation);
	
	List<SubCategoryVo> querySubCategoryByPager(Pager pager);
	
}
