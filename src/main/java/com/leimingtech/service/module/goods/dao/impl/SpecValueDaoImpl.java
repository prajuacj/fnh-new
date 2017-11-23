package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import com.leimingtech.core.common.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leimingtech.core.entity.base.SpecValue;
import com.leimingtech.service.module.goods.dao.SpecValueDao;
import com.leimingtech.service.module.goods.dao.mapper.SpecValueMapper;
import com.leimingtech.service.utils.page.Pager;

@Component
public class SpecValueDaoImpl implements SpecValueDao{

	@Autowired
	SpecValueMapper goodsSpecValueMapper;
	
	@Override
	public void save(SpecValue specValue) {
		specValue.setSpValueId(IdGen.uuid());
		goodsSpecValueMapper.save(specValue);
	}

	@Override
	public void update(SpecValue specValue) {
		goodsSpecValueMapper.update(specValue);
	}

	@Override
	public void deleteBySpId(String id) {
		goodsSpecValueMapper.deleteBySpId(id);
	}

	@Override
	public SpecValue findById(String id) {
		return goodsSpecValueMapper.findById(id);
	}

	@Override
	public List<SpecValue> findListBySpId(String spId) {
		return goodsSpecValueMapper.findListBySpId(spId);
	}

	@Override
	public int findCount(Pager pager) {
		return goodsSpecValueMapper.findCount(pager);
	}

	@Override
	public List<SpecValue> findPageList(Pager pager) {
		return goodsSpecValueMapper.findPageList(pager);
	}

}
