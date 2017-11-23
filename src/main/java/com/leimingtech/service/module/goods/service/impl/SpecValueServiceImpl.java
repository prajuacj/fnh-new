package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.SpecValue;
import com.leimingtech.service.module.goods.dao.SpecValueDao;
import com.leimingtech.service.module.goods.service.SpecValueService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class SpecValueServiceImpl implements SpecValueService {

	@Autowired
	SpecValueDao specValueDao;

	@Override
	public void save(SpecValue specValue) {
		specValueDao.save(specValue);
	}

	@Override
	public void update(SpecValue specValue) {
		specValueDao.update(specValue);
	}

	@Override
	public void deleteBySpId(String id) {
		specValueDao.deleteBySpId(id);
	}

	@Override
	public SpecValue findById(String id) {
		return specValueDao.findById(id);
	}

	@Override
	public List<SpecValue> findListBySpId(String spId) {
		return specValueDao.findListBySpId(spId);
	}

	@Override
	public int findCount(Pager pager) {
		return specValueDao.findCount(pager);
	}

	@Override
	public Pager findPageList(Pager pager) {
		List<SpecValue> list = specValueDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}
}
