/**
 * 
 */
package com.leimingtech.service.module.promotion.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Promotion;
import com.leimingtech.service.module.promotion.dao.PromotionDao;
import com.leimingtech.service.module.promotion.service.PromotionService;
import com.leimingtech.service.utils.page.Pager;

/**
 * <p>
 * Title: PromotionServiceImpl.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014-2018
 * </p>
 * <p>
 * Company: leimingtech.com
 * </p>
 * 
 * @author linjm
 * @date 2015年7月21日
 * @version 1.0
 */
@Service
public class PromotionServiceImpl implements PromotionService {

	@Resource
	private PromotionDao promotionDao;

	@Override
	public void save(Promotion promotion) {

		promotionDao.save(promotion);

	}

	@Override
	public void delete(String id) {

		promotionDao.delete(id);

	}

	@Override
	public void update(Promotion promotion) {

		promotionDao.update(promotion);
	}

	@Override
	public Pager findList(Pager pager) {
		List<Promotion> list = promotionDao.findList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public int findCount(Promotion promotion) {

		return promotionDao.findCount(promotion);
	}

	@Override
	public Promotion findById(String pcId) {

		return promotionDao.findById(pcId);
	}

}
