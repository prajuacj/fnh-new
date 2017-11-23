package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.entity.base.Store;
import com.leimingtech.core.entity.vo.StoreVo;
import com.leimingtech.service.module.store.dao.StoreDao;
import com.leimingtech.service.module.store.dao.mapper.StoreMapper;
import com.leimingtech.service.utils.page.Pager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @项目名称：leimingtech-seller
 * @类名称：StoreDaoImpl
 * @类描述：
 * @创建人：shining
 * @创建时间：2014年12月2日 上午11:36:39
 * @修改人：shining
 * @修改时间：2014年12月2日 上午11:36:39
 * @修改备注：
 */
@Repository
public class StoreDaoImpl implements StoreDao {
    @Resource
    private StoreMapper storeMapper;

    public Store findById(String id) {
        return storeMapper.findById(id);
    }

    public StoreVo findVoById(String id) {
        return storeMapper.findVoById(id);
    }

    public void updateStore(Store store) {
        storeMapper.updateStore(store);
    }

    public void updateStoreCus(Store store) {
        storeMapper.updateStoreCus(store);
    }

    public Store findByMemberId(String id) {
        return storeMapper.findByMemberId(id);
    }

    public void save(Store store) {
        storeMapper.save(store);
    }

    public Store findByStorename(String storename) {
        return storeMapper.findByStorename(storename);
    }

    public int queryCount(Store store) {
        return storeMapper.queryCount(store);
    }

    public List<Store> queryList(Pager pager) {
        return storeMapper.queryList(pager);
    }

    @Override
    public void updateStoreCount(Map<String, String> map) {
        storeMapper.updateStoreCount(map);
    }

    @Override
    public Store findByIds(Store store) {
        return storeMapper.findByIds(store);
    }

    /**
     * @param @param id    设定文件
     * @return void    返回类型
     * @Title: delete
     * @Description: TODO(根据ID 删除)
     */
    @Override
    public void delete(String id) {
        storeMapper.delete(id);
    }

    /**
     * 根据会员名字获取店铺信息
     * @param memberName
     * @return
     */
    public Store findByMemberName(String memberName) {
        return storeMapper.findByMemberName(memberName);
    }

	@Override
	public List<Store> findByAreaIdAndName(String areaId) {
		areaId = StringUtils.isNotEmpty(areaId) ? areaId : "";
		return storeMapper.findByAreaIdAndName(areaId);
	}

	@Override
	public List<Store> listStoreByCondition(Store store) {
		// TODO Auto-generated method stub
		return storeMapper.listStoreByCondition(store);
	}
	
	@Override
	public List<Store> listStoreByCooperative(String provinceId) {
		// TODO Auto-generated method stub
		return storeMapper.listStoreByCooperative(provinceId);
	}
	
	public Store findStoreByOne(String storeId) {
	        return storeMapper.findStoreByOne(storeId);
	}

	@Override
	public List<Store> selectList(Store store) {
		return storeMapper.selectList(store);
	}

	@Override
	public List<Store> selectListByLeaderId(String leaderId) {
		return storeMapper.selectListByLeaderId(leaderId);
	}
}
