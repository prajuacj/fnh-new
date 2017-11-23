package com.leimingtech.service.module.store.dao;

import com.leimingtech.core.entity.base.EvaluateStore;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 *    
 * @项目名称：leimingtech-seller   
 * @类名称：StoreBindClassDao  
 * @类描述：   
 * @创建人：sangyuchen   
 * @创建时间：2014年12月18日 上午11:57:21   
 * @修改备注：   
 * @version    
 *
 */
@Repository
public interface EvaluateStoreDao {

	EvaluateStore findEvaluateStore(String id);
	
	/**
     * 分页列表
     * @param pager
     * @return
     */
    List<EvaluateStore> findlist(Pager pager);

    /**
     * 总条数
     * @param evaluateStore
     * @return
     */
    int findCount(EvaluateStore evaluateStore);

    /**
     * 删除
     * @param id
     */
    void delete(String id);
    
    /**
     * 保存
     * @param evaluateStore
     */
    void save(EvaluateStore evaluateStore);
    /**
     * 获取店铺的平均分
     * @param map
     */
    List<EvaluateStore> findEvaluate(Map<Object, Object> map);
	
}