package com.leimingtech.service.module.goods.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.GoodsSpec;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.service.module.goods.dao.GoodsSpecDao;
import com.leimingtech.service.module.goods.service.GoodsSpecService;
import com.leimingtech.service.utils.goods.GoodsUtils;
import com.leimingtech.service.utils.page.Pager;

@Service
public class GoodsSpecServiceImpl implements GoodsSpecService {

	@Resource
	private GoodsSpecDao goodsSpecDao;

	public void updateGoodsSpec(GoodsSpec goodsSpec) {
		goodsSpecDao.updateGoodsSpec(goodsSpec);
	}

	@Override
	public void saveGoodsSpec(GoodsSpec goodsSpec) {
		// TODO Auto-generated method stub
		goodsSpecDao.saveGoodsSpec(goodsSpec);
	}

	@Override
	public void deleteGoodsSpecByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		goodsSpecDao.deleteGoodsSpecByGoodsId(goodsId);
	}

	@Override
	public List<GoodsSpec> findListByGoodsId(String goodsId) {
		// TODO Auto-generated method stub
		return goodsSpecDao.findListByGoodsId(goodsId);
	}

	@Override
	public GoodsSpec findByGoodsSpecId(String goodsSpecId) {
		// TODO Auto-generated method stub
		return goodsSpecDao.findByGoodsSpecId(goodsSpecId);
	}

	@Override
	public List<GoodsSpec> findAllList() {
		// TODO Auto-generated method stub
		return goodsSpecDao.findAllList();
	}

	@Override
	public Pager findPageList(Pager pager) {
		// TODO Auto-generated method stub
		List<GoodsSpec> list = goodsSpecDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public Integer findPageListCount(Pager pager) {
		// TODO Auto-generated method stub
		return goodsSpecDao.findPageListCount(pager);
	}

	@Override
	public void updateGoodsSpecStorage(GoodsSpec goodsSpec) {
		// TODO Auto-generated method stub
		goodsSpecDao.updateGoodsSpecStorage(goodsSpec);
	}

	/**
	 * 通过已选择的规格值id(以逗号分隔)得到对应的规格商品
	 * 
	 * @return String
	 */
	@Override
	public GoodsSpec getGoodsSpecBySpecValueId(String specValuesStr, Goods goods) {

		if (goods != null) {
			/** 通过商品id获得商品下所有对应的规格 */
			List<GoodsSpec> goodsSpecs = findListByGoodsId(goods.getGoodsId());
			if (StringUtils.isNotEmpty(specValuesStr)) {
				/** 将传入的规格值id,以逗号分隔转成String数组 */
				String[] strArr01 = specValuesStr.split(",");
				Arrays.sort(strArr01);
				/** 转成int数组 */

				for (GoodsSpec goodsSpec : goodsSpecs) {
					/** 得到以逗号分隔的规格值id */
					String str = GoodsUtils.getThisGoodsAllSpecValueId(goodsSpec.getSpecGoodsSpec());

					/** 和传入的进行比较,如果匹配则返回,否则继续匹配 */
					String[] strArr02 = str.split(",");
					Arrays.sort(strArr02);

					/** 比较两个数组是否相等 */
					/** 匹配 */
					if (Arrays.equals(strArr01, strArr02)) {
						return goodsSpec;
					}
				}
			} else {
				/** 如果只有一个规格那么直接返回 */
				if (goodsSpecs.size() == 1) {
					return goodsSpecs.get(0);
				}
			}

		} else {
			return null;
		}
		return null;
	}
}
