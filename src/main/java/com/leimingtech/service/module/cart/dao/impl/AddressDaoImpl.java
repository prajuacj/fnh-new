package com.leimingtech.service.module.cart.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.Address;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.cart.dao.AddressDao;
import com.leimingtech.service.module.cart.dao.mapper.AddressMapper;
import com.leimingtech.service.utils.page.Pager;


/**
 * 
 *    
 * 项目名称：leimingtech-front   
 * 类名称：AddressDaoImpl   
 * 类描述：   
 * 创建人：liuhao   
 * 创建时间：2014年12月27日 下午5:06:54   
 * 修改人：liuhao   
 * 修改时间：2014年12月27日 下午5:06:54   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class AddressDaoImpl extends BaseDao implements AddressDao {
    @Resource
    private AddressMapper addressMapper;

	@Override
	public List<Address> queryAddreassMemberId(String memberId) {
		// TODO Auto-generated method stub
		return addressMapper.queryAddreassMemberId(memberId);
	}

	@Override
	public int saveAddress(Address address) {
		// TODO Auto-generated method stub
		return addressMapper.saveAddress(address);
	}

	@Override
	public void deleteAddress(String addressId) {
		// TODO Auto-generated method stub
		addressMapper.deleteAddress(addressId);
	}

	@Override
	public Address queryById(String addressId) {
		// TODO Auto-generated method stub
		return addressMapper.queryById(addressId);
	}

	@Override
	public void updateAddress(Address address) {
		// TODO Auto-generated method stub
		addressMapper.updateAddress(address);
	}

	@Override
	public int countfindAll(Address address) {
		return addressMapper.countfindAll(address);
	}

	@Override
	public List<Address> findList(Pager pager) {
		return addressMapper.findList(pager);
	}


}
