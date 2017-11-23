package com.leimingtech.service.module.store.dao.mapper;

import com.leimingtech.core.entity.Classs;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.module.store.vo.ClasssVo;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;


/**
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：ClasssMapper   
 * 类描述：   
 * 创建人：weiyue   
 * 创建时间：2014年11月6日 下午10:47:37   
 * 修改人：weiyue   
 * 修改时间：2014年11月6日 下午10:47:37   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface ClasssMapper{
	 
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
