package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Spec;
import com.leimingtech.core.entity.base.SpecValue;
import com.leimingtech.core.entity.vo.SpecVo;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.goods.dao.SpecDao;
import com.leimingtech.service.module.goods.dao.SpecValueDao;
import com.leimingtech.service.module.goods.service.SpecService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class SpecServiceImpl implements SpecService {

	@Resource
	private SpecDao specDao;

	@Resource
	private SpecValueDao specValueDao;

	/**
	 * 保存
	 * 
	 * @param spec
	 * @param specValues
	 */
	public void save(Spec spec, String specValues) {
		// 得到sp_id

		specDao.save(spec);
		String spId = spec.getSpId();
		// 删除spId下面所有的规格值
		specValueDao.deleteBySpId(spId);
		// 存入规格值
		// 首先得到规格值得list
		List<Object> list = JsonUtils.readJsonList(specValues, SpecValue.class);
		// 循环存入shop_spec_value表中
		for (int i = 0; i < list.size(); i++) {
			// 得到规格值实体
			SpecValue specValue = (SpecValue) list.get(i);
			// 设置spId
			specValue.setSpId(spId);
			// 存储
			specValueDao.save(specValue);
		}
	}

	/**
	 * 修改
	 * 
	 * @param spec
	 * @param specValues
	 */
	public void update(Spec spec, String specValues) {
		specDao.update(spec);
		// 得到sp_id
		String spId = spec.getSpId();
		// 删除spId下面所有的规格值
		specValueDao.deleteBySpId(spId);
		// 存入规格值
		// 首先得到规格值得list
		List<Object> list = JsonUtils.readJsonList(specValues, SpecValue.class);
		// 循环存入shop_spec_value表中
		for (int i = 0; i < list.size(); i++) {
			// 得到规格值实体
			SpecValue specValue = (SpecValue) list.get(i);
			// 设置spId
			specValue.setSpId(spId);
			String specValueId = specValue.getSpValueId();
			/*
			 * if(StringUtils.isNotEmpty(specValueId)){
			 * specValueDao.update(specValue); } else {
			 * specValueDao.save(specValue); }
			 */
			specValueDao.save(specValue);
		}
	}

	/**
	 * 修改
	 * 
	 * @param spec
	 */
	@Override
	public void update(Spec spec) {
		specDao.update(spec);
	}

	@Override
	public Spec findById(String spId) {
		return specDao.findById(spId);
	}

	@Override
	public List<Spec> findAllList(Spec spec) {
		return specDao.findAllList(spec);
	}

	@Override
	public Pager findPageList(Pager pager) {
		List<Spec> list = specDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public Integer findPageListCount(Pager pager) {
		return specDao.findPageListCount(pager);
	}

	@Override
	public List<Spec> findListBySpId(String spId) {
		return specDao.findListBySpId(spId);
	}

	@Override
	public List<SpecVo> findSpecListBySpId(String spId) {
		return specDao.findSpecListBySpId(spId);
	}

	@Override
	public List<SpecVo> findListByType(String typeId) {
		return specDao.findListByType(typeId);
	}

	@Override
	public void deleteSpecBySpId(String spId) {
		specDao.deleteSpecBySpId(spId);
	}

	/**
	 * 根据规格名获取规格数量
	 * 
	 * @param specName
	 * @return
	 */
	public Integer findSpecListByName(String specName) {
		return specDao.findSpecListByName(specName);
	}

}
