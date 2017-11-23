package com.leimingtech.service.module.submall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.GoodsClass;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.GoodsCombinHaoHuo;
import com.leimingtech.core.entity.base.GoodsCombinRelation;
import com.leimingtech.core.entity.base.SubMallGcAreaRelation;
import com.leimingtech.core.entity.base.SubMallGcSubClassRelation;
import com.leimingtech.core.entity.base.SubMallGoodsClass;
import com.leimingtech.core.entity.vo.AreaCategoryVo;
import com.leimingtech.core.entity.vo.CategoryVo;
import com.leimingtech.core.entity.vo.IndexHaohuoGoodsVo;
import com.leimingtech.core.entity.vo.SubCategoryVo;
import com.leimingtech.service.module.submall.dao.mapper.GoodsCombinHaohuoMapper;
import com.leimingtech.service.module.submall.dao.mapper.SubMallGoodsClassMapper;
import com.leimingtech.service.module.submall.service.SubMallGoodsClassService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class SubMallGoodsClassServiceImpl implements SubMallGoodsClassService {
	
	@Resource
	private SubMallGoodsClassMapper subMallGoodsClassMapper;
	
	@Resource
	private GoodsCombinHaohuoMapper goodsCombinHaohuoMapper;
	
	@Override
	public List<CategoryVo> queryBySubMallId(String subMallId) throws Exception {
		return subMallGoodsClassMapper.queryBySubMallId(subMallId);
	}

	@Override
	public List<GoodsCombinHaoHuo> queryHaohuo() throws Exception {
		return goodsCombinHaohuoMapper.queryHaohuo();
	}

	@Override
	public List<SubCategoryVo> querySubCategoryBysgId(String smGcId)
			throws Exception {
		return subMallGoodsClassMapper.querySubCategoryBysgId(smGcId);
	}

	@Override
	public List<AreaCategoryVo> queryAreaCategoryBysgId(String smGcId)
			throws Exception {
		return subMallGoodsClassMapper.queryAreaCategoryBysgId(smGcId);
	}

	@Override
	public AreaCategoryVo queryAreaCategoryById(String areaCategoryId)
			throws Exception {
		return subMallGoodsClassMapper.queryAreaCategoryById(areaCategoryId);
	}

	@Override
	public List<Goods> queryGoodsByAreaCategoryId(String areaCategoryId)
			throws Exception {
		return subMallGoodsClassMapper.queryGoodsByAreaCategoryId(areaCategoryId);
	}

	@Override
	public GoodsCombinHaoHuo queryHaohuoById(String combinCode)
			throws Exception {
		return goodsCombinHaohuoMapper.queryHaohuoById(combinCode);
	}

	@Override
	public List<Goods> queryGoodsByHaohuoId(String combinCode) throws Exception {
		return goodsCombinHaohuoMapper.queryGoodsByHaohuoId(combinCode);
	}

	@Override
	public SubCategoryVo querySubCategoryById(String subCategoryId)
			throws Exception {
		return subMallGoodsClassMapper.querySubCategoryById(subCategoryId);
	}

	@Override
	public List<GoodsCombinHaoHuo> queryHaohuoByIndex() throws Exception {
		return goodsCombinHaohuoMapper.queryHaohuoByIndex();
	}

	@Override
	public List<IndexHaohuoGoodsVo> queryHaohuoAndGoodsByIndex()
			throws Exception {
		return goodsCombinHaohuoMapper.queryHaohuoAndGoodsByIndex();
	}

	@Override
	public CategoryVo queryById(String sgId) throws Exception {
		return subMallGoodsClassMapper.queryById(sgId);
	}

	@Override
	public List<Goods> queryGoodsBySmGcId(String smGcId) throws Exception {
		return subMallGoodsClassMapper.queryGoodsBySmGcId(smGcId);
	}

	@Override
	public Pager queryByPager(Pager pager) throws Exception {
		pager.setResult(subMallGoodsClassMapper.queryByPager(pager));
		return pager;
	}

	@Override
	public List<GoodsClass> queryGoodsClassNoInSubMall(String subMallId)
			throws Exception {
		return subMallGoodsClassMapper.queryGoodsClassNoInSubMall(subMallId);
	}

	@Override
	public List<GoodsClass> queryGoodsClassNoInSubGoodsClass(String smGcId)
			throws Exception {
		return subMallGoodsClassMapper.queryGoodsClassNoInSubGoodsClass(smGcId);
	}

	@Override
	public int saveSubMallGoodsClass(SubMallGoodsClass subMallGoodsClass)
			throws Exception {
		subMallGoodsClass.setId(IdGen.uuid());
		return subMallGoodsClassMapper.insertSubMallGoodsClass(subMallGoodsClass);
	}

	@Override
	public void deleteById(String smGcId) throws Exception {
		subMallGoodsClassMapper.deleteById(smGcId);
		subMallGoodsClassMapper.deleteAreaCategoryBySmGcId(smGcId);
		subMallGoodsClassMapper.deleteSubCategoryBySmGcId(smGcId);
	}

	@Override
	public Pager querySubMallByPager(Pager pager) throws Exception {
		pager.setResult(subMallGoodsClassMapper.querySubMallByPager(pager));
		return pager;
	}

	@Override
	public Pager queryAreaCategoryByPager(Pager pager) throws Exception {
		pager.setResult(subMallGoodsClassMapper.queryAreaCategoryByPager(pager));
		return pager;
	}

	@Override
	public int saveArea(SubMallGcAreaRelation subMallGcAreaRelation)
			throws Exception {
		return subMallGoodsClassMapper.saveArea(subMallGcAreaRelation);
	}

	@Override
	public int deleteArea(String areaCategoryId) throws Exception {
		
		return subMallGoodsClassMapper.deleteAreaById(Integer.parseInt(areaCategoryId));
	}

	@Override
	public int deleteSubCategoryById(String subCategoryId) throws Exception {
		return subMallGoodsClassMapper.deleteSubCategoryById(subCategoryId);
	}

	@Override
	public int saveSubCategory(
			SubMallGcSubClassRelation subMallGcSubClassRelation)
			throws Exception {
		return subMallGoodsClassMapper.saveSubCategory(subMallGcSubClassRelation);
	}

	@Override
	public Pager querySubCategoryByPager(Pager pager) throws Exception {
		pager.setResult(subMallGoodsClassMapper.querySubCategoryByPager(pager));
		return pager;
	}

	@Override
	public Pager queryHaohuoByPager(Pager pager) throws Exception {
		pager.setResult(goodsCombinHaohuoMapper.queryHaohuoByPager(pager));
		return pager;
	}

	@Override
	public int saveHaohuo(GoodsCombinHaoHuo goodsCombinHaoHuo) throws Exception {
		goodsCombinHaoHuo.setCode(IdGen.uuid());
		return goodsCombinHaohuoMapper.savaHaohuo(goodsCombinHaoHuo);
	}

	@Override
	public int deleteHaohuoById(String code) throws Exception {
		goodsCombinHaohuoMapper.deleteHaohuoById(code);
		goodsCombinHaohuoMapper.deleteHaohuoRelationById(code);
		return 1;
	}

	@Override
	public Pager queryNoInHaohuoGoodsByPager(Pager pager) throws Exception {
		pager.setResult(goodsCombinHaohuoMapper.queryGoodsByPager(pager));
		return pager;
	}

	@Override
	public int saveHaohuoGoods(String deleteIds, String addArray, String code)
			throws Exception {
		JSONArray addJsonArray = JSONArray.parseArray(addArray);
		List<GoodsCombinRelation> addList = new ArrayList<GoodsCombinRelation>();
		for (int i = 0; i < addJsonArray.size(); i++) {
			GoodsCombinRelation entity = new GoodsCombinRelation();
			entity.setCombinCode(addJsonArray.getJSONObject(i).getString("combinCode"));
			entity.setGoodsId(addJsonArray.getJSONObject(i).getString("goodsId"));
			entity.setGroupOrder(addJsonArray.getJSONObject(i).getInteger("groupOrder"));
			addList.add(entity);
		}
		if (!addList.isEmpty()) {
			goodsCombinHaohuoMapper.saveHaohuoGoods(addList);
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("deleteIds", deleteIds);
		paramMap.put("code", code);
		goodsCombinHaohuoMapper.deleteHaohuoGoods(paramMap);
		return 1;
	}
	

}
