package com.leimingtech.service.module.attachpicture.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.AttachPicture;
import com.leimingtech.service.module.attachpicture.dao.AttachPictureDao;
import com.leimingtech.service.module.attachpicture.dao.mapper.AttachPictureMapper;
import com.leimingtech.service.utils.page.Pager;

@Repository
public class AttachPictureDaoImpl implements AttachPictureDao{
	
	@Resource
	private AttachPictureMapper attachPictureMapper;
	
	/**
	 * 保存图片库图片属性
	 * @param attachPicture
	 */
	@Override
	public void save(AttachPicture attachPicture) {
		attachPicture.setId(IdGen.uuid());
		attachPictureMapper.save(attachPicture);
	}
	
	/**
	 * 修改图片库图片属性
	 * @param attachPicture
	 */
	@Override
	public void update(AttachPicture attachPicture) {
		attachPictureMapper.update(attachPicture);
	}
	
	/**
	 * 根据id删除图片空间中图片数据
	 */
	@Override
	public void delete(String id) {
		attachPictureMapper.delete(id);
	}
	
	/**
	 * 查询分页条数(包括查询条件)
	 * @param pager
	 * @return
	 */
	@Override
	public int findCount(Pager pager) {
		return attachPictureMapper.findCount(pager);
	}
	
	/**
	 * 分页查询
	 * @param pager
	 * @return
	 */
	@Override
	public List<AttachPicture> findPageList(Pager pager) {
		return attachPictureMapper.findPageList(pager);
	}
	
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@Override
	public AttachPicture findById(String id) {
		return attachPictureMapper.findById(id);
	}
	
	/**
	 * 查询所有不含分页
	 * @return
	 */
	public List<AttachPicture> findList(){
		return attachPictureMapper.findList();
	}
	
	/**
	 * 根据店铺id查询
	 * @return
	 */
	public List<AttachPicture> findListByStoreID(String storeid){
		return findListByStoreID(storeid);
	}
}
