package com.leimingtech.service.module.attachpicture.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.AttachPicture;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 图片空间
 * @author liuk
 */
@SqlMapper
public interface AttachPictureMapper {
	
	/**
	 * 保存图片库图片属性
	 * @param attachPicture
	 */
	void save(AttachPicture attachPicture);
	
	/**
	 * 修改图片库图片属性
	 * @param attachPicture
	 */
	void update(AttachPicture attachPicture);
	
	/**
	 * 根据id删除图片空间中图片数据
	 */
	void delete(String id);
	
	/**
	 * 查询分页条数(包括查询条件)
	 * @param pager
	 * @return
	 */
	int findCount(Pager pager);
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	List<AttachPicture> findPageList(Pager pager);
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	AttachPicture findById(String id);
	
	/**
	 * 查询所有不含分页
	 * @return
	 */
	List<AttachPicture> findList();
	
	/**
	 * 根据店铺id查询
	 * @return
	 */
	List<AttachPicture> findListByStoreID(String storeid);
	
}
