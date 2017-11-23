package com.leimingtech.service.module.goods.service.impl;

import com.leimingtech.core.entity.base.RelGoodsRecommend;
import com.leimingtech.service.module.goods.dao.RelGoodsRecommendDao;
import com.leimingtech.service.module.goods.service.RelGoodsRecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class RelGoodsRecommendServiceImpl implements RelGoodsRecommendService{
	@Resource
    private RelGoodsRecommendDao relGoodsRecommendDao;
	
	@Override
	public void save(RelGoodsRecommend relGoodsRecommend) {
		relGoodsRecommendDao.save(relGoodsRecommend);
	}
	@Override
	public void delete(String id) {
		relGoodsRecommendDao.delete(id);
	}
	@Override
	public List<RelGoodsRecommend> findgoodsList(RelGoodsRecommend relGoodsRecommend) {
		return relGoodsRecommendDao.findgoodsList(relGoodsRecommend);
	}
	@Override
	public List<RelGoodsRecommend> findgoodsids(String reCommendId) {
		return relGoodsRecommendDao.findgoodsids(reCommendId);
	}
	/**
     * 修改
     * @param relGoodsRecommend
     */
	@Override
	public void updaterel(RelGoodsRecommend relGoodsRecommend) {
		relGoodsRecommendDao.updaterel(relGoodsRecommend);
	}
}
