package com.leimingtech.service.module.area.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.Area;


/**
 * 
 */
public interface AreaDao {

	
	List<Area> queryAll();
	
	List<Area> queryChildList(String parentid);

	Area queryParentList(@Param("parentId") String parentid);
	
	String getAreaByAreaId(String areaId);
	
	/**
     * 根据城市id查询县个数
     * @return
     */
    List<Area> queryCityCount();

    /**
     *  查询市
     * @param areaId
     * @return
     */
    List<Area> queryByAreaId(String areaId);

    /**
     * 查询省市
     * @return
     */
    List<Area> queryProvince();

    /**
     * 查出分级所有 根据分级ID
     * @param areaDeep
     * @return
     */
    List<Area> queryListALLByDeep(Integer areaDeep);	
}
