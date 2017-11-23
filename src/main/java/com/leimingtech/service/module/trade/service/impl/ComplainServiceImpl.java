package com.leimingtech.service.module.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Complain;
import com.leimingtech.service.module.trade.dao.ComplainDao;
import com.leimingtech.service.module.trade.service.ComplainService;
import com.leimingtech.service.utils.page.Pager;

/**
 * Created by rabook on 2014/12/20.
 */
@Service
public class ComplainServiceImpl implements ComplainService {

	@Resource
	private ComplainDao complainDao;

	/**
	 * 列表
	 *
	 * @param pager
	 * @return
	 */
	@Override
	public Pager findList(Pager pager) {
		List<Complain> list = complainDao.findList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 查询条数
	 *
	 * @param pager
	 * @return
	 */
	@Override
	public int findCount(Pager pager) {
		return complainDao.findCount(pager);
	}

	@Override
	public Complain findById(String id) {
		return complainDao.findById(id);
	}
}
