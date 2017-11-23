package com.leimingtech.service.module.goods.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.GoodsExcel;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.core.entity.base.ReserveInfo;
import com.leimingtech.core.entity.vo.GoodsTradeVo;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.goods.dao.mapper.GoodsMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 
 *    
 * 项目名称：leimingtech-seller   
 * 类名称：GoodsDaoImpl   
 * 类描述：   
 * 创建人：cgl
 * 创建时间：2015年06月29日10:01:27
 * 修改人：liuhao   
 * 修改时间：2015年06月29日10:01:27
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class GoodsDaoImpl implements GoodsDao{

    @Resource
    private GoodsMapper goodsMapper;

	public Goods findGoodById(String goodsId) {
		return goodsMapper.findGoodById(goodsId);
	}

	public List<Goods> findGoodPagerList(Pager pager) {
		return goodsMapper.findGoodPagerList(pager);
	}

	public void saveGoods(Goods goods) {
		String goodsId = IdGen.uuid();
		goods.setGoodsId(goodsId);
		goodsMapper.saveGoods(goods);
	}

	public void updateGoods(Goods goods) {
		goodsMapper.updateGoods(goods);
	}

	public void deleteGoods(String goodsId) {
		goodsMapper.deleteGoods(goodsId);
	}

	public Goods findOneGoodByCondition(Goods goods) {
		return goodsMapper.findOneGoodByCondition(goods);
	}

	public int countGoods(Goods goods) {
		return goodsMapper.countGoods(goods);
	}
	
	/**
	 * 分页查询获得findTradeGoodlist
	 * @param pager
	 * @return
	 */
	@Override
	public List<GoodsTradeVo> findTradeGoodPagerList(Pager pager) {
		return goodsMapper.findTradeGoodPagerList(pager);
	}
	/**
	 * 根据商品字段获取商品的数量
	 * @param goodsTradeVo
	 * @return
	 */
	@Override
	public int findTradeGoodcount(GoodsTradeVo goodsTradeVo) {
		return goodsMapper.findTradeGoodcount(goodsTradeVo);
	}
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	@Override
	public List<Goods> findGoodListbystoreid(String storeId) {
		return goodsMapper.findGoodListbystoreid(storeId);
	}

	@Override
	public List<Map<String,Object>> countGoodsClick(String storeId) {
		return goodsMapper.countGoodsClick(storeId);
	}
    
	/**
	 * 根据店铺id字段商品
	 * @param storeId
	 * @return
	 */
	@Override
	public List<GoodsExcel> findGoodListbystoreid2(String storeId) {
		return goodsMapper.findGoodListbystoreid2(storeId);
	}

	@Override
	public List<Goods> findGoodListByGcId(String gcId) {
		return goodsMapper.findGoodListByGcId(gcId);
	}

	@Override
	public void updateUrl(Goods goods) {
		goodsMapper.updateUrl(goods);
	}

	/**
	 * 分页查询获得list sellerApi专用
	 * @param pager
	 * @return
	 */
	@Override
	public List<Goods> findGoodPagerListSeller(Pager pager) {
		return goodsMapper.findGoodPagerListSeller(pager);
	}

	@Override
	public int saveReserveInfo(ReserveInfo reserveInfo) {
		String id = IdGen.uuid();
		reserveInfo.setId(id);
		return goodsMapper.saveReserveInfo(reserveInfo);
	}

	@Override
	public List<ReserveInfo> findReserveByExample(ReserveInfo reserveInfo) {
		return goodsMapper.findReserveByExample(reserveInfo);
	}

	@Override
	public List<ReserveInfo> findReserveByPager(Pager pager) {
		return goodsMapper.findReserveByPager(pager);
	}

	@Override
	public int updateReserveInfo(ReserveInfo reserveInfo) {
		return goodsMapper.updateReserveInfo(reserveInfo);
	}
	
	@Override
	public List<Goods> findStoreByGoodsList(Goods gs) {
		return goodsMapper.findStoreByGoodsList(gs);
	}
	
	@Override
	public Goods findIdByGoods(String goodsId) {
		return goodsMapper.findIdByGoods(goodsId);
	}

	@Override
	public List<Goods> findFrontGoodList(Goods goods) {
		return goodsMapper.findFrontGoodList(goods);
	}

	@Override
	public List<Goods> findGoodLimitList(Goods goods) {
		return goodsMapper.findGoodListByMemerId(goods);
	}
}
