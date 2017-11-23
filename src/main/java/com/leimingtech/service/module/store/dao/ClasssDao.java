package com.leimingtech.service.module.store.dao;


import com.leimingtech.core.entity.Classs;
import com.leimingtech.service.module.store.vo.ClasssVo;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 *
 */
public interface ClasssDao {

    List<ClasssVo> queryClasssList(Pager pager);

    List<Classs> queryClasssParentList();

    void save(Classs classs);

    void delete(String id);

    Classs queryById(String id);

    void update(Classs classs);

    /**
     * 查询子节点
     * @return
     */
    List<Classs> queryClasssChildrenList(Classs classs);

    /**
     * 去重
     * @param classs
     * @return
     */
    int findCount(Classs classs);
    
    /**
     * 根据父id查询分类列表
     * @param parentid 为0查询一级分类
     * @return
     */
    List<Classs> findList(String parentid);

	Classs findClassByName(String className);
	
	/**
	 * 根据店铺分类名称查询分类及其子分类
	 * @param name
	 * @return
	 */
	List<Classs> findByName(String name);
}
