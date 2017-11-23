package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.entity.base.StoreJoinin;
import com.leimingtech.service.module.store.dao.StoreJoininDao;
import com.leimingtech.service.module.store.dao.mapper.StoreJoininMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店铺入驻daoImpl
 *
 * @author gyh
 * @version 2015-12-30
 */
@Repository
public class StoreJoinInDaoImpl implements StoreJoininDao {

    /** 店铺入驻mapper接口*/
    @Resource
    private StoreJoininMapper shopStoreJoininMapper;

    /**
     * 查询分页店铺入驻数据
     *
     * @param pager 分页对象
     * @return
     */
    @Override
    public List<StoreJoinin> findShopStoreJoininPagerList(Pager pager){
        return shopStoreJoininMapper.findShopStoreJoininPagerList(pager);
    }

    /**
     * 通过memberId获取单条店铺入驻数据
     *
     * @param memberId
     * @return
     */
    @Override
    public StoreJoinin findShopStoreJoininByMemberId(String memberId){
        return shopStoreJoininMapper.findShopStoreJoininByMemberId(memberId);
    }

    /**
     * 通过memberId删除店铺入驻数据
     *
     * @param memberId
     */
    @Override
    public void deleteShopStoreJoininByMemberId(String memberId){
        shopStoreJoininMapper.deleteShopStoreJoininByMemberId(memberId);
    }

    /**
     * 修改店铺入驻数据
     *
     * @param shopStoreJoinin
     */
    @Override
    public void updateShopStoreJoinin(StoreJoinin shopStoreJoinin){
        shopStoreJoininMapper.updateShopStoreJoinin(shopStoreJoinin);
    }
    /**
     * 保存店铺入驻数据
     *
     * @param shopStoreJoinin
     */
    @Override
    public void saveShopStoreJoinin(StoreJoinin shopStoreJoinin){
        shopStoreJoininMapper.saveShopStoreJoinin(shopStoreJoinin);
    }
    /**
     * 获取所有店铺入驻数据
     *
     * @return
     */
    @Override
    public List<StoreJoinin> findShopStoreJoininAllList(){
        return shopStoreJoininMapper.findShopStoreJoininAllList();
    }

    /**
     * 根据会员名字获取入驻信息
     * @param memberName
     * @return
     */
    public StoreJoinin findShopStoreJoininByMemberName(String memberName) {
        return shopStoreJoininMapper.findShopStoreJoininByMemberName(memberName);
    }

}