package com.leimingtech.service.module.store.dao;

import com.leimingtech.core.entity.base.Store;
import com.leimingtech.core.entity.vo.StoreVo;
import com.leimingtech.service.utils.page.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @项目名称：leimingtech-seller
 * @类名称：StoreDao
 * @类描述：
 * @创建人：shining
 * @创建时间：2014年12月2日 上午11:19:31
 * @修改人：shining
 * @修改时间：2014年12月2日 上午11:19:31
 * @修改备注：
 */
public interface StoreDao {
    /**
     * 根据店铺id获取店铺信息，不包括店铺等级信息
     *
     * @param id
     * @return
     */
    Store findById(@Param("id") String id);

    /**
     * 根据店铺di获取店铺信息，包括店铺等级信息
     *
     * @param id
     * @return
     */
    StoreVo findVoById(@Param("id") String id);

    /**
     * 修改店铺信息
     *
     * @param store
     */
    void updateStore(Store store);

    /**
     * 修改商店客户
     *
     * @param store
     */
    void updateStoreCus(Store store);

    /**
     * 根据会员I获取店铺
     *
     * @param id
     * @return
     */
    Store findByMemberId(String id);

    /**
     * 保存店铺信息
     *
     * @param store
     */
    void save(Store store);

    /**
     * 根据店铺名字获取店铺信息
     *
     * @param storename
     * @return
     */
    Store findByStorename(String storename);

    /**
     * 查询条数
     *
     * @param store
     * @return
     */
    int queryCount(Store store);

    /**
     * 查询店铺分页数据
     *
     * @param pager
     * @return
     */
    List<Store> queryList(Pager pager);

    /**
     * 修改店铺收藏的数量
     *
     * @param map
     */
    void updateStoreCount(Map<String, String> map);

    /**
     * 根据id获取店铺
     *
     * @param store
     * @return
     */
    Store findByIds(Store store);

    /**
     * @param @param id    设定文件
     * @return void    返回类型
     * @Title: delete
     * @Description: TODO(根据ID 删除)
     */
    void delete(@Param("id") String id);

    /**
     * 根据会员名字获取店铺信息
     *
     * @param memberName
     * @return
     */
    Store findByMemberName(String memberName);

    /**
     * 根据地区ID和'%合作社过滤出店铺'
     * @param areaId
     * @return
     */
    List<Store> findByAreaIdAndName(String areaId);

	List<Store> listStoreByCondition(Store store);
	
	/**
	 * 合作社店铺列表
	 * @param provinceId
	 * @return
	 */
	List<Store> listStoreByCooperative(String provinceId);
	
	/**
     * 根据店铺Id获取店铺信息
     * @param storeId
     * @return
     */
    Store findStoreByOne(String storeId);
    
    /**
     * 查询店铺列表
     * @param store
     * @return
     */
    List<Store> selectList(Store store);
    
    /**
     * 通过领导ID查找挂包帮
     * @param leaderId
     * @return
     */
    List<Store> selectListByLeaderId(String leaderId);
}
