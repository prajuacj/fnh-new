package com.leimingtech.service.module.goods.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.goods.dao.BrandDao;
import com.leimingtech.service.module.goods.service.BrandService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @项目名称：leimingtech-seller
 * @类名称：BrandServiceImpl
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@Slf4j
@Service
public class BrandServiceImpl implements BrandService {

	@Resource
	private BrandDao brandDao;

	public void save(Brand brand) {
		brand.setIsDel(0);
		brand.setCreateTime(System.currentTimeMillis());
		brandDao.save(brand);
	}

	public void delete(String id) {
		brandDao.delete(id);
	}

	public void update(Brand brand) {
		brand.setUpdateTime(System.currentTimeMillis());
		brandDao.update(brand);
	}

	public Pager findPageList(Pager pager) {
		List<Brand> list=brandDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	public List<Brand> findList() {
		return brandDao.findList();
	}

	public Brand findById(String id) {
		return brandDao.findById(id);
	}

	public List<Brand> findByClassId(String classId) {
		return brandDao.findByClassId(classId);
	}

	public List<Brand> findBrandGroupByClassId() {
		return brandDao.findBrandGroupByClassId();
	}

	public Map<String, Object> saveStorebrand(String jsondata, String storeid) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			Brand brand = JsonUtils.fromJson(jsondata, Brand.class);
			brand.setStoreId(storeid);
			if (StringUtils.isNotEmpty(brand.getBrandId())) {
				brandDao.update(brand);
				map.put("success", true);
			} else {
				brand.setBrandApply(0);
				brand.setBrandRecommend(0);
				brand.setBrandId(IdGen.uuid());
				brandDao.save(brand);
				map.put("success", true);
			}
		} catch (Exception e) {
			log.error("保存品牌失败" + e.getMessage());
			map.put("success", false);
		}
		return map;
	}
	
	public Map<String, Object> saveStorebrand(Brand brand) {
		Map<String, Object> map = Maps.newHashMap();
		try {
			if (StringUtils.isNotEmpty(brand.getBrandId())) {
				brandDao.update(brand);
				map.put("success", true);
			} else {
				brand.setBrandApply(0);
				brand.setBrandRecommend(0);
				brand.setBrandId(IdGen.uuid());
				brandDao.save(brand);
				map.put("success", true);
			}
		} catch (Exception e) {
			log.error("保存品牌失败" + e.getMessage());
			map.put("success", false);
		}
		return map;
	}

	public List<Brand> getBrandListByStoreId(String storeId) {
		return brandDao.getBrandListByStoreId(storeId);
	}

	@Override
	public List<Brand> getBrandListByTypeId(String typeId) {
		return brandDao.getBrandListByTypeId(typeId);
	}

	@Override
	public int countBrand(Brand brand) {
		return brandDao.countBrand(brand);
	}

	/**
	 * 根据商品分类like查询所有关联的品牌，包括子集商品分类下品牌
	 */
	@Override
	public List<Brand> findAllByClassPath(String gcIdPath) {
		return brandDao.findAllByClassPath(gcIdPath);
	}
	

}
