package com.leimingtech.service.module.goods.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.service.module.goods.dao.GoodsSpecDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsSpecMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class GoodsSpecDaoImpl implements GoodsSpecDao {

    @Resource
    private GoodsSpecMapper goodsSpecMapper;

	public void updateGoodsSpec(GoodsSpec goodsSpec){
		goodsSpecMapper.updateGoodsSpec(goodsSpec);
	}

	@Override
	public void saveGoodsSpec(GoodsSpec goodsSpec) {
		// TODO Auto-generated method stub
		goodsSpec.setGoodsSpecId(IdGen.uuid());
		goodsSpecMapper.saveGoodsSpec(goodsSpec);
	}

	@Override
	public void deleteGoodsSpecByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		goodsSpecMapper.deleteGoodsSpecByGoodsId(goodsId);
	}

	@Override
	public List<GoodsSpec> findListByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findListByGoodsId(goodsId);
	}

	@Override
	public GoodsSpec findByGoodsSpecId(String goodsSpecId) {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findByGoodsSpecId(goodsSpecId);
	}

	@Override
	public List<GoodsSpec> findAllList() {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findAllList();
	}

	@Override
	public List<GoodsSpec> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findPageList(pager);
	}

	@Override
	public Integer findPageListCount(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecMapper.findPageListCount(pager);
	}

	@Override
	public void updateGoodsSpecStorage(GoodsSpec goodsSpec) {
		// TODO Auto-generated method stub
		goodsSpecMapper.updateGoodsSpecStorage(goodsSpec);
	}

}
