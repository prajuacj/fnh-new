package com.leimingtech.service.module.area.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.core.entity.Area;

/**
 *    
 * 项目名称：gt-front   
 * 类名称：AreaMapper   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月27日 下午11:05:09   
 * 修改人：liuhao   
 * 修改时间：2014年12月27日 下午11:05:09   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface AreaMapper {
	List<Area> queryAll();
	
	List<Area> queryChildList(@Param("parentId") String parentid);
	
	Area queryParentList(@Param("areaId") String parentid);
	
	String getAreaByAreaId(String areaId);
	
	/**
     *  查询市
     * @param areaId
     * @return
     */
    List<Area> queryByAreaId(@Param("areaId") String areaId);
	
	/**
     * 根据城市id查询县个数
     * @return
     */
    List<Area> queryCityCount();

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
