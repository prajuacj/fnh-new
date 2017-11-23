package com.leimingtech.service.module.store.dao.mapper;

import com.leimingtech.core.entity.base.EvaluateStore;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @项目名称：leimingtech-seller
 * @类名称：EvaluateStoreMapper
 * @类描述：
 * @创建人：sangyuchen
 * @创建时间：2014年12月18日 上午11:58:05
 * @修改备注：
 * @version
 * 
 */
@SqlMapper
public interface EvaluateStoreMapper {
   
	EvaluateStore findEvaluateStore(@Param("id") String id);
	
	List<EvaluateStore> findPageList(Pager pager);

    int findCount(EvaluateStore evaluateStore);

    void delete(String id);
    
    /**
     * 保存店铺评论
     * @param evaluateStore
     */
    void save(EvaluateStore evaluateStore);
    
    /**
     * 获取店铺的平均分
     * @param map
     */
    List<EvaluateStore> findEvaluate(Map<Object, Object> map);
	
}