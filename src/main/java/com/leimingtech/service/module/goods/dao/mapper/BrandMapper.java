package com.leimingtech.service.module.goods.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * @项目名称：leimingtech-seller
 * @类名称：BrandMapper
 * @类描述：
 * @修改备注：
 * @version
 * 
 */
@SqlMapper
public interface BrandMapper {
	/**
	 * 保存品牌
	 * @param brand
	 */
	void save(Brand brand);
	
	/**
	 * 删除品牌
	 * @param id
	 */
	void delete(String id);
	
	/**
	 * 更新品牌
	 * @param brand
	 */
	void update(Brand brand);
	
	/**
	 * 获取分页数据
	 * @param pager
	 * @return
	 */
	List<Brand> findPageList(Pager pager);

	/**
	 * 获取全部数据
	 * @return
	 */
	List<Brand> findList();

	/**
	 * 根据id获取品牌
	 * @param id
	 * @return
	 */
	Brand findById(String id);
	
    /**
     * 根据classId查询品牌
     * @return
     */
    List<Brand> findByClassId(String classId);
    
    /**
     * 获取品牌的分类
     * @return
     */
    List<Brand> findBrandGroupByClassId();
    
    /**
     * 根据店铺id获取店铺的品牌
     * @param storeId
     * @return
     */
    List<Brand> getBrandListByStoreId(String storeId);
    
    /**
     * 根据typeid获取品牌
     * @param typeId
     * @return
     */
    List<Brand> getBrandListByTypeId(String typeId);
    
    /**
     * 根据店铺typeId获取店铺的品牌
     * @param typeId
     * @return
     */
    List<Brand> findListByType(String typeId);
    
    /**
     * 获取品牌数量
     * @param brand
     * @return
     */
    int countBrand(Brand brand);

    /**
     * 根据商品分类like查询所有关联的品牌，包括子集商品分类下品牌
     * @param gcIdPath
     * @return
     */
	List<Brand> findAllByClassPath(@Param("gcIdPath") String gcIdPath,
			@Param("dbName") String dbName);
    
}
