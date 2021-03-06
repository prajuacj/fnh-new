package com.leimingtech.service.module.area.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.Area;
import com.leimingtech.service.module.area.dao.AreaDao;
import com.leimingtech.service.module.area.dao.mapper.AreaMapper;
import com.leimingtech.service.module.base.BaseDao;


/**
 * 
 *    
 * 项目名称：gt-front   
 * 类名称：AddressDaoImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月27日 下午5:06:54   
 * 修改人：liuhao   
 * 修改时间：2014年12月27日 下午5:06:54   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class AreaDaoImpl extends BaseDao implements AreaDao {
    @Resource
    private AreaMapper areaMapper;

	public List<Area> queryAll() {
		return areaMapper.queryAll();
	}

	public List<Area> queryChildList(String parentid) {
		return areaMapper.queryChildList(parentid);
	}

	@Override
	public Area queryParentList(String parentid) {
		return areaMapper.queryParentList(parentid);
	}
	
	public String getAreaByAreaId(String areaId){
		return areaMapper.getAreaByAreaId(areaId);
	}

	@Override
	public List<Area> queryCityCount() {
		return areaMapper.queryCityCount();
	}

	@Override
	public List<Area> queryByAreaId(String areaId) {
		return areaMapper.queryByAreaId(areaId);
	}

	@Override
	public List<Area> queryProvince() {
		return areaMapper.queryProvince();
	}

	/**
     * 查出分级所有 根据分级ID
     * @param areaDeep
     * @return
     */
    public List<Area> queryListALLByDeep(Integer areaDeep){
    	return areaMapper.queryListALLByDeep(areaDeep);
    }
	
}
