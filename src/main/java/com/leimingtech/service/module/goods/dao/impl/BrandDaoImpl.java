package com.leimingtech.service.module.goods.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.common.JDBCConstants;
import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.service.module.goods.dao.BrandDao;
import com.leimingtech.service.module.goods.dao.mapper.BrandMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @项目名称：leimingtech-seller
 * @类名称：BrandDaoImpl
 * @类描述：
 * @修改备注：
 */
@Repository
public class BrandDaoImpl implements BrandDao {
    @Resource
    private BrandMapper brandMapper;

    @Override
    public void save(Brand brand) {
        brand.setBrandId(IdGen.uuid());
        brandMapper.save(brand);
    }

    @Override
    public void delete(String id) {
        brandMapper.delete(id);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.update(brand);
    }

    @Override
    public List<Brand> findPageList(Pager pager) {
        return brandMapper.findPageList(pager);
    }

    @Override
    public List<Brand> findList() {
        return brandMapper.findList();
    }

    @Override
    public Brand findById(String id) {
        return brandMapper.findById(id);
    }

    @Override
    public List<Brand> findByClassId(String classId) {
        return brandMapper.findByClassId(classId);
    }

    @Override
    public List<Brand> findBrandGroupByClassId() {
        return brandMapper.findBrandGroupByClassId();
    }

    @Override
    public List<Brand> getBrandListByStoreId(String storeId) {
        return brandMapper.getBrandListByStoreId(storeId);
    }

    @Override
    public List<Brand> getBrandListByTypeId(String typeId) {
        return brandMapper.getBrandListByTypeId(typeId);
    }

    @Override
    public List<Brand> findListByType(String typeId) {
        return brandMapper.findListByType(typeId);
    }

    /**
     * 获取品牌数量
     *
     * @param brand
     * @return
     */
    @Override
    public int countBrand(Brand brand) {
        return brandMapper.countBrand(brand);
    }

    /**
     * 根据商品分类like查询所有关联的品牌，包括子集商品分类下品牌
     */
	@Override
	public List<Brand> findAllByClassPath(String gcIdPath) {
		String dbName = JDBCConstants.JDBC_TYPE;
		return brandMapper.findAllByClassPath(gcIdPath, dbName);
	}

}
