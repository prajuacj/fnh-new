package com.leimingtech.service.module.cart.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Address;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.cart.dao.AddressDao;
import com.leimingtech.service.module.cart.service.AddressService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    public List<Address> queryAddreassMemberId(String memberId) {
        return addressDao.queryAddreassMemberId(memberId);
    }


    /**
     * 保存收货地址
     */
    public Map<String,Object> saveAddress(Address address,String memberId) {

        Map<String,Object> map = Maps.newHashMap();
        try {
            if (address.getAddressId() != null) {
                addressDao.updateAddress(address);
                map.put("success",true);
            } else {
            	//判断用户是否有收货地址
            	List<Address> addrList = addressDao.queryAddreassMemberId(memberId);
            	
            	if (addrList.isEmpty()) {
            		//新增第一条地址，设为默认
            		address.setIsDefault("1");
            	}
            	else if (address.getIsDefault().equals("1")) {
            		//添加默认地址，则将之前的默认地址取消
            		Address updAddress = new Address();
            		updAddress.setMemberId(memberId);
            		updAddress.setIsDefault("0");
            		addressDao.updateAddress(updAddress);
            	}
            	
            	address.setAddressId(IdGen.uuid());
                address.setCityId(address.getCityId());
                address.setAreaId(address.getAreaId());
                address.setProvinceId(address.getProvinceId());
                address.setMemberId(memberId);
                addressDao.saveAddress(address);
                map.put("success",true);
                map.put("data",address);
            }

        } catch (Exception e) {
            log.error("保存收货地址失败" + e.getMessage());
            map.put("success",false);
        }
        return map;
    }

    /**
     * 保存收货地址
     */
    public int updateAddress(String jsondata) {
        int result = 0;
        try {
            Address address = JsonUtils.fromJson(jsondata, Address.class);
            if (address.getAddressId() != null) {
                addressDao.updateAddress(address);
                result = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("保存收货地址失败" + e.getMessage());
        }
        return result;
    }


    @Override
    public void deleteAddress(String addressId) {
        addressDao.deleteAddress(addressId);
    }


    @Override
    public Address queryById(String addressId) {
        // TODO Auto-generated method stub
        return addressDao.queryById(addressId);
    }


    public int updateDef(String addressId, String memberId) {
        int result = 0;
        if (addressId != null) {
            Address address = new Address();
            address.setMemberId(memberId);
            address.setIsDefault("0");
            addressDao.updateAddress(address);
            address.setMemberId(null);
            address.setAddressId(addressId);
            address.setIsDefault("1");
            addressDao.updateAddress(address);
            result = 1;
        }
        return result;
    }


    @Override
    public int countfindAll(Address address) {
        return addressDao.countfindAll(address);
    }

    @Override
    public Pager findList(Pager pager) {
        List<Address> list=addressDao.findList(pager);
		pager.setResult(list);
		return pager;
    }
    
    /**
	 * 保存收货地址
	 * @param address
	 */
	public void saveAddress(Address address){
		address.setAddressId(IdGen.uuid());
		addressDao.saveAddress(address);
	}
	
	/**
	 * 修改收货地址
	 * @param address
	 */
	public void updateAddress(Address address, String memberId){
		if (address.getIsDefault().equals("1")) {
    		//设置默认地址，则将之前的默认地址取消
    		Address updAddress = new Address();
    		updAddress.setMemberId(memberId);
    		updAddress.setIsDefault("0");
    		addressDao.updateAddress(updAddress);
    	}
		
		addressDao.updateAddress(address);
	}

}
