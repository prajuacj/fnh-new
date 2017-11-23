package com.leimingtech.service.module.search.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.vo.SpecVo;
import com.leimingtech.service.module.search.dao.GoodsSearchDao;
import com.leimingtech.service.module.search.dao.mapper.GoodsSearchMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 商品搜索
 * @author cgl
 */
@Component
public class GoodsSearchDaoImpl implements GoodsSearchDao{

	@Autowired
	GoodsSearchMapper goodsSearchMapper;
	
	/**
	 * 获得当前关键词所对应的可选规格
	 */
	@Override
	public List<SpecVo> getSpecListByKeyword(String keyword, String dbName) {
		return goodsSearchMapper.getSpecListByKeyword(keyword, dbName);
	}

	@Override
	public List<SpecVo> getSpecListByGcId(String gcId) {
		return goodsSearchMapper.getSpecListByGcId(gcId);
	}

	@Override
	public List<SpecVo> getSpecListByTypeId(String typeId) {
		// TODO Auto-generated method stub
		return goodsSearchMapper.getSpecListByTypeId(typeId);
	}

	@Override
	public List<SpecVo> getSpecListByBrandId(String brandId) {
		// TODO Auto-generated method stub
		return goodsSearchMapper.getSpecListByBrandId(brandId);
	}

	@Override
	public List<Brand> getBrandListByKeyword(String keyword, String dbName) {
		return goodsSearchMapper.getBrandListByKeyword(keyword, dbName);
	}

	@Override
	public List<Brand> getBrandListByGcId(String gcId) {
		return goodsSearchMapper.getBrandListByGcId(gcId);
	}

	@Override
	public List<Brand> getBrandListByTypeId(String typeId) {
		return goodsSearchMapper.getBrandListByTypeId(typeId);
	}

	@Override
	public int findGoodPagerListCount(Goods goods) {
		return goodsSearchMapper.findGoodPagerListCount(goods);
	}

	@Override
	public List<Goods> findGoodPagerList(Pager pager) {
		return goodsSearchMapper.findGoodPagerList(pager);
	}

	@Override
	public Goods findOneGoodByCondition(Goods goods) {
		return goodsSearchMapper.findOneGoodByCondition(goods);
	}
}
