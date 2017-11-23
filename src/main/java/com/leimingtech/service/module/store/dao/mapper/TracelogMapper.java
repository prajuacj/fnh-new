package com.leimingtech.service.module.store.dao.mapper;

import com.leimingtech.core.entity.base.TraceLog;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 店铺动态
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：TraceLogMapper   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月7日 下午2:04:36   
 * 修改人：yanghui   
 * 修改时间：2014年11月7日 下午2:04:36   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface TracelogMapper{
	 
	/**
	 * 
	 * @Title: countTraceLog 
	 * @Description: TODO(count总数查询) 
	 * @param @param pager
	 * @param @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	int countTraceLog(Pager pager);
    
   /**
    * 
    * @Title: queryTraceLogList 
    * @Description: TODO(带分页list查询) 
    * @param @param pager
    * @param @return    设定文件 
    * @return List<TraceLog>    返回类型 
    * @throws
    */
   List<TraceLog> queryTraceLogList(Pager pager);
    
    /**
     * 
     * @Title: delete 
     * @Description: TODO(根据ID 删除) 
     * @param @param id    设定文件 
     * @return void    返回类型 
     * @throws
     */
	void delete(@Param("id") String id);
    
    /**
     * 
     * @Title: findLogById 
     * @Description: TODO(根据ID 查询明细) 
     * @param @param id
     * @param @return    设定文件 
     * @return AdminLog    返回类型 
     * @throws
     */
    TraceLog findLogById(@Param("id") String id);
    
    void updateStateById(@Param("id") String id, @Param("state") Integer state);
}
