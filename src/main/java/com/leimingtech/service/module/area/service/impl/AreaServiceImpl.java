package com.leimingtech.service.module.area.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.leimingtech.core.common.Collections3;
import com.leimingtech.core.entity.Area;
import com.leimingtech.service.module.area.dao.AreaDao;
import com.leimingtech.service.module.area.service.AreaService;
import com.leimingtech.service.module.setting.dao.OffPayAreaDao;

@Service
@Slf4j
public class AreaServiceImpl implements AreaService {

	@Resource
	private AreaDao areaDao;
	
	@Resource
    private OffPayAreaDao offPayAreaDao;

	public List<Area> queryAll() {
		log.info("获取到一级地区！");
		return areaDao.queryAll();
	}

	public List<Area> queryChildList(String parentid) {
		return areaDao.queryChildList(parentid);
	}

	@Override
	public Area queryParentList(String parentid) {
		// TODO Auto-generated method stub
		return areaDao.queryParentList(parentid);
	}
	
	public String getAreaByAreaId(String areaId){
		return areaDao.getAreaByAreaId(areaId);
	}

	/**
     * 查询省市区信息
     * @return
     */
    @Override
    public Map<String,Object> queryAllArea() {

        //取出已选中的区
        String areaId = offPayAreaDao.queryByStoreId();
        //查询省市信息
        List<Area> province = areaDao.queryListALLByDeep(1); //所有的一级
        List<Area> province2 = areaDao.queryListALLByDeep(2);//所有的二级
        Map<String, List<Area>> areaMp = new HashMap<String, List<Area>>();
        List<Area> arealist = null;
        for (Area area : province2) {
        	String pId = area.getAreaParentId();
        	if(areaMp.get(pId) == null){
        		arealist = new ArrayList<Area>();
        		arealist.add(area);
        		areaMp.put(pId, arealist);
        	}else{
        		arealist = areaMp.get(pId);
        		arealist.add(area);
        		areaMp.put(pId, arealist);

        	}
		}
        for (Area area : province) {
        	String id = area.getAreaId();
        	List<Area> lAreas = areaMp.get(id);
        	if(lAreas!=null ){
        		area.setChildern(lAreas);
        	}
		}
        
        Map<String,List<String>> countryMap = Maps.newHashMap();
        if(StringUtils.isNotBlank(areaId)){
            //通过已选中的区，查询对应的城市
            List<Area> cityListByCountry = areaDao.queryByAreaId(areaId);
            //统计城市list里，相同城市选中区的个数
            Map<String,Integer> map = Maps.newHashMap();
            for(Area area : cityListByCountry){
                Integer count = map.get(area.getAreaParentId());
                map.put(area.getAreaParentId(),count == null ? 1 : count+1);
            }
            //查询所有的城市信息
            List<Area> cityList = areaDao.queryCityCount();
            Map<String,Boolean> cityMap = Maps.newHashMap();
            for(Area area : cityList){
                //判断选中区对应的城市里是否包含该城市，如果包含比较是否所有的区为全选状态
                if(map.containsKey(area.getAreaParentId())){
                    if(map.get(area.getAreaParentId()) == area.getCountChildren()){
                        cityMap.put(area.getAreaParentId(),true);
                    }else{
                        cityMap.put(area.getAreaParentId(),false);
                    }
                }else{
                    cityMap.put(area.getAreaParentId(),false);
                }
            }
            //查询所有的省信息
            for(Area area : province){
                List<Boolean> tempList = Lists.newArrayList();
                if(Collections3.isNotEmpty(area.getChildern())){
                    for(Area area1 : area.getChildern()){
                        //判断该城市是否选中
                        if(cityMap.containsKey(area1.getAreaId())){
                            boolean flag = cityMap.get(area1.getAreaId());
                            area1.setIsChecked(flag);
                            tempList.add(flag);
                        }else{
                            tempList.add(false);
                        }
                        //向已选中城市添加区的个数
                        if(map.containsKey(area1.getAreaId())){
                            area1.setCountChildren(map.get(area1.getAreaId()));
                        }
                    }
                }
                //判断该省份是否应该选中
                if(tempList.contains(false)){
                    area.setIsChecked(false);
                }else{
                    area.setIsChecked(true);
                }
            }
            //拼装已选中的城市和区域关系 Map<城市id,List<区域id>>
            for (Map.Entry entry : map.entrySet()) {
                List<String> list = Lists.newArrayList();
                for(Area area : cityListByCountry){
                    if(entry.getKey() == area.getAreaParentId()){
                        list.add(area.getAreaId());
                    }
                }
                countryMap.put(entry.getKey().toString(),list);
            }
        }
        Map<String,Object> returnMap = Maps.newHashMap();
        returnMap.put("country",countryMap);
        returnMap.put("province",province);
        return returnMap;
    }
    
	/**
	 * 获得一个 省市的地区信息
	 */
	@Override
	public List<Area> getProvinceCityArea() {
		List<Area> list = areaDao.queryAll();
		List<Area> areas = new ArrayList<Area>();
		for(Area area : list){
			area.setChildern(areaDao.queryChildList(area.getAreaId()));
			areas.add(area);
		}
		return areas;
	}

	/**
	 * 获取所有的地区信息
	 * @return
	 */
	public List<Area> queryProvince() {
		List<Area> province = areaDao.queryProvince();
		return province;
	}
	
	/**
     * 查出分级所有 根据分级ID
     * @param areaDeep
     * @return
     */
    public List<Area> queryListALLByDeep(Integer areaDeep){
    	return areaDao.queryListALLByDeep(areaDeep);
    }
	
}
