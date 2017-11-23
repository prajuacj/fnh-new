package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.GoodsType;
import com.leimingtech.core.entity.vo.GoodsTypeVO;
import com.leimingtech.service.module.goods.dao.GoodsTypeDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsTypeMapper;
import com.leimingtech.service.module.goods.vo.GoodsTypeVo;
import com.leimingtech.service.utils.page.Pager;

@Repository
public class GoodsTypeDaoImpl implements GoodsTypeDao{

	@Resource
	private GoodsTypeMapper goodsTypeMapper;
	
	@Override
	public void save(GoodsType goodsType) {
		String id = IdGen.uuid();
		goodsType.setTypeId(id);
		goodsType.setIdPath(id + ",");
		goodsTypeMapper.save(goodsType);
	}

	@Override
	public void update(GoodsType goodsType) {
		goodsTypeMapper.update(goodsType);
	}

	@Override
	public void delete(String typeId) {
		goodsTypeMapper.delete(typeId);
	}

	@Override
	public GoodsType findById(String typeId) {
		return goodsTypeMapper.findById(typeId);
	}

	@Override
	public List<GoodsType> findList() {
		return goodsTypeMapper.findList();
	}

	@Override
	public GoodsTypeVO selectTypeFetchOther(String typeId) {
		return goodsTypeMapper.selectTypeFetchOther(typeId);
	}

	@Override
	public int findCount(Pager pager) {
		return goodsTypeMapper.findCount(pager);
	}

	@Override
	public List<GoodsType> findPageList(Pager pager) {
		return goodsTypeMapper.findPageList(pager);
	}

	@Override
	public void saveGoodsType(GoodsTypeVo vo) {
		goodsTypeMapper.saveGoodsType(vo);
	}

	public void updateGoodsType(GoodsTypeVo vo) {
		goodsTypeMapper.updateGoodsType(vo);
	}

	public void updateType(GoodsType type) {
		goodsTypeMapper.updateType(type);
	}
    
	 /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
	@Override
	public List<GoodsType> findList2(String parentid) {
		return goodsTypeMapper.findList2(parentid);
	}
    
	/**
     * 通过父id查询子分类
     * @param gtParentId
     * @return
     */
	@Override
	public List<GoodsType> findChild(String gtParentId) {
		return goodsTypeMapper.findChild(gtParentId);
	}

	@Override
	public int findCountByName(String typeName) {
		return goodsTypeMapper.findCountByName(typeName);
	}

}
