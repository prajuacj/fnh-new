package com.leimingtech.service.module.goods.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.GoodsGeneralCount;
import com.leimingtech.core.entity.base.Brand;
import com.leimingtech.core.entity.base.ShopStatGoods;
import com.leimingtech.service.module.goods.dao.ShopStatGoodsDao;
import com.leimingtech.service.module.goods.service.ShopStatGoodsService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

/**
 * @项目名称：leimingtech-seller
 * @类名称：ShopStatGoodsServiceImpl @类描述：
 * @创建人：gyh
 * @创建时间：2015年7月23日 下午16:54 @修改备注：
 */
@Slf4j
@Service
public class ShopStatGoodsServiceImpl implements ShopStatGoodsService {

	@Resource
	private ShopStatGoodsDao shopStatGoodsDao;

	@Override
	public void save(ShopStatGoods shopStatGoods) {
		shopStatGoodsDao.save(shopStatGoods);
	}

	@Override
	public void delete(String sId) {
		shopStatGoodsDao.delete(sId);
	}

	@Override
	public int findCount(Pager pager) {
		return shopStatGoodsDao.findCount(pager);
	}

	@Override
	public Pager findPageList(Pager pager) {
		List<ShopStatGoods> list = shopStatGoodsDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public List<ShopStatGoods> findList() {
		return shopStatGoodsDao.findList();
	}

	@Override
	public Brand findbyIds(ShopStatGoods shopStatGoods) {
		return shopStatGoodsDao.findbyIds(shopStatGoods);
	}

	/**
	 * 时间段，店铺id查找内容
	 *
	 * @param map
	 * @return
	 */
	@Override
	public List<GoodsGeneralCount> findStatbytime(Map<String, Object> map) {
		return shopStatGoodsDao.findStatbytime(map);
	}

}
