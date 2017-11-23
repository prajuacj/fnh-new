package com.leimingtech.service.module.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.GoodsRecommend;
import com.leimingtech.service.module.goods.dao.GoodsRecommendDao;
import com.leimingtech.service.module.goods.service.GoodsRecommendService;
import com.leimingtech.service.utils.page.Pager;

@Service
public class GoodsRecommendServiceImpl implements GoodsRecommendService{
	@Resource
    private GoodsRecommendDao goodsRecommendDao;
	
	@Override
	public void save(GoodsRecommend goodsRecommend) {
		goodsRecommend.setCreateTime(System.currentTimeMillis());//创建时间
		goodsRecommendDao.save(goodsRecommend);
	}

	@Override
	public void delete(String reCommendid) {
		goodsRecommendDao.delete(reCommendid);
	}

	@Override
	public void update(GoodsRecommend goodsRecommend) {
		goodsRecommendDao.update(goodsRecommend);
	}

	@Override
	public GoodsRecommend findById(String reCommendid) {
		return goodsRecommendDao.findById(reCommendid);
	}

	@Override
	public List<GoodsRecommend> findList(GoodsRecommend goodsRecommend) {
		return goodsRecommendDao.findList(goodsRecommend);
	}

	@Override
	public int findCount(GoodsRecommend goodsRecommend) {
		return goodsRecommendDao.findCount(goodsRecommend);
	}

	@Override
	public Pager findPageList(Pager pager) {
		List<GoodsRecommend> list=goodsRecommendDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public GoodsRecommend findBycolum(String recommendName) {
		return goodsRecommendDao.findBycolum(recommendName);
	}

}
