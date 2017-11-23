package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Daddress;
import com.leimingtech.service.module.trade.dao.DaddressDao;
import com.leimingtech.service.module.trade.service.DaddressService;

@Service
public class DaddressServiceImpl implements DaddressService{
	@Resource
    private DaddressDao daddressDao;
    
    /**
	 * 保存发货地址
	 * @param daddress
	 */
	public void saveDaddress(Daddress daddress){
		daddressDao.saveDaddress(daddress);
	}
	
	/**
	 * 修改发货地址,条件可传两个值,发货地址id和店铺id
	 * @param daddress
	 */
	public void updateDaddress(Daddress daddress){
		daddressDao.updateDaddress(daddress);
	}
	
	/**
	 * 根据id删除发货地址
	 * @param addressId
	 */
	public void deleteDaddress(String addressId){
		daddressDao.deleteDaddress(addressId);
	}
	
	/**
	 * 根据id查询发货地址
	 * @param addressId
	 * @return
	 */
	public Daddress findDaddressById(String addressId){
		return daddressDao.findDaddressById(addressId);
	}
	
	/**
	 * 根据店铺id查询发货地址列表
	 * @param storeId
	 * @return
	 */
	public List<Daddress> findDaddressByStoreId(String storeId){
		return daddressDao.findDaddressByStoreId(storeId);
	}
	
	/**
	 * 设置默认发货地址
	 * @param storeId 店铺id
	 * @param addressId 发货地址id
	 */
	public void updateDefault(String storeId,String addressId){
		//将店铺下的所有发货地址设置为不默认
		Daddress daddress = new Daddress();
		daddress.setIsDefault("0");
		daddress.setStoreId(storeId);
		daddressDao.updateDaddress(daddress);
		
		//将指定的发货地址设置为默认
		Daddress defaultDaddress = new Daddress();
		defaultDaddress.setIsDefault("1");
		defaultDaddress.setAddressId(addressId);
		daddressDao.updateDaddress(defaultDaddress);
	}
	
	/**
	 * 根据店铺id查询店铺下的默认发货地址数量
	 * @param storeId
	 * @return
	 */
	public int findDefaultCountByStoreId(String storeId){
		return daddressDao.findDefaultCountByStoreId(storeId);
	}
}
