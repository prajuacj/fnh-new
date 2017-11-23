package com.leimingtech.service.module.cart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Favorites;
import com.leimingtech.core.entity.base.Goods;
import com.leimingtech.service.module.cart.dao.FavoritesDao;
import com.leimingtech.service.module.cart.service.FavoritesService;
import com.leimingtech.service.module.goods.dao.GoodsDao;
import com.leimingtech.service.module.store.service.StoreService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FavoritesServiceImpl implements FavoritesService{
	
	@Resource
	private FavoritesDao favoritesDao;
	
	@Resource
    private StoreService storeService;
	
	@Resource
	private GoodsDao goodsDao;
	
	/**
	 * 添加收藏商品
	 * @param goodsId 商品id
	 * @param buyerId 用户id
	 * @return
	 */
	public void saveFavGoods(String goodsId,String buyerId) {
		try {
			Favorites favorites = new Favorites();
			favorites.setFavId(goodsId);
			favorites.setMemberId(buyerId);
			favorites.setFavType("goods");//收藏商品
			favorites.setFavTime(System.currentTimeMillis());//收藏时间
			favoritesDao.saveFav(favorites);
			
			// 修改商品的的收藏数量
			Goods goods = new Goods();
			goods.setGoodsId(goodsId);//商品id
			goods.setGoodsCollect(1);//1表示新增收藏
			goodsDao.updateGoods(goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加收藏店铺
	 * @param storeId 店铺id
	 * @param buyerId 用户id
	 * @return
	 */
	public void saveFavStore(String storeId,String buyerId) {
		Favorites favorites = new Favorites();
		favorites.setFavId(storeId);
		favorites.setMemberId(buyerId);
		favorites.setFavType("store");//收藏商品
		favorites.setFavTime(System.currentTimeMillis());//收藏时间
		favoritesDao.saveFav(favorites);
		
		Map<String, String> storeMap = new HashMap<String, String>();
		storeMap.put("storeId", storeId + "");
		storeMap.put("flags", "add");//flags是add时表示增加收藏，等于reduce时表示取消收藏
		storeService.updateStoreCount(storeMap);
	}
	
	/**
	 * 获取到收藏夹信息
	 * @Title: queryFavById 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param favorites
	 * @param @return    设定文件 
	 * @return List<Favorites>    返回类型 
	 * @throws
	 */
	@Override
	public List<Favorites> queryFavById(Favorites favorites) {
		return favoritesDao.queryFavById(favorites);
	}
	
	/**
	 * 查询收藏(商品)分页数量
	 * @param pager
	 * @return
	 */
	@Override
	public int countfindAll(Pager pager) {
		return favoritesDao.countfindAll(pager);
	}
	
	/**
	 * 查询收藏(店铺)分页数量
	 * @param pager
	 * @return
	 */
	@Override
	public int countfindShopAll(Pager pager) {
		return favoritesDao.countfindShopAll(pager);
	}

	/**
	 * 删除收藏信息
	 * @param favorites
	 * @return
	 */
	public int deleteAllFav(Favorites favorites) {
		int result = 0;
		try {
			favoritesDao.deleteAllFav(favorites);
			result = 1;
		} catch (Exception e) {
			result = 0;
			log.error("删除失败！"+e.getMessage());
		}
		return result;
	}
	
	@Override
	public Pager findShopList(Pager pager) {
		return null;
	}

	@Override
	public int FavoriteGoodsCount(Favorites favorites) {
		return favoritesDao.FavoriteGoodsCount(favorites);
	}

	@Override
	public Pager findFavoriteGoodsList(Pager pager) {
		List<Favorites> list=favoritesDao.findFavoriteGoodsList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public int FavoriteStoreCount(Favorites favorites) {
		return favoritesDao.FavoriteStoreCount(favorites);
	}

	@Override
	public Pager findFavoriteStoreList(Pager pager) {
		List<Favorites> list=favoritesDao.findFavoriteStoreList(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public int findcountFav(String goodsId, String buyerId, Integer FavType) {
		int result = 0;
		Favorites favorites = new Favorites();
		favorites.setFavId(goodsId);
		favorites.setMemberId(buyerId);
		//favorites.setFavTime();
		if(FavType == 1){
			favorites.setFavType("goods");//收藏商品
		}else{
			favorites.setFavType("store");//收藏店铺
		}
		result = favoritesDao.findCountFav(favorites);
		favorites=null;//释放内存
		return result;
	}
	
	/**
	 * 添加收藏
	 * @param goodsId 商品id
	 * @param buyerId 用户id
	 * @param FavType 收藏类型
	 * @return
	 */
	@Override
	public void saveFav(String goodsId,String buyerId,Integer FavType) {
		Favorites favorites = new Favorites();
		favorites.setFavId(goodsId);
		favorites.setMemberId(buyerId);
		if(FavType == 1){
			favorites.setFavType("goods");//收藏商品
		}else{
			favorites.setFavType("store");//收藏店铺
		}
		favorites.setFavTime(System.currentTimeMillis());//收藏时间
		favoritesDao.saveFav(favorites);
		favorites=null;//释放内存
	}
	
	/**
	 * 取消收藏商品
	 * @param goodsId
	 * @param memberId
	 */
	public void deleteFavGoods(String goodsId,String memberId){
		
		try {
			Favorites favorites = new Favorites();
			favorites.setMemberId(memberId);
			favorites.setFavId(goodsId);
			favorites.setFavType("goods");
			favoritesDao.deleteAllFav(favorites);
			
			// 修改商品的的收藏数量
			Goods goods = new Goods();
			goods.setGoodsId(goodsId);//商品id
			goods.setGoodsCollect(-1);//-1减少收藏
			goodsDao.updateGoods(goods);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取消收藏店铺
	 * @param storeId
	 * @param memberId
	 */
	public void deleteFavStore(String storeId,String memberId){
		Favorites favorites = new Favorites();
		favorites.setMemberId(memberId);
		favorites.setFavId(storeId);
		favorites.setFavType("store");
		favoritesDao.deleteAllFav(favorites);
		
		// 减少店铺收藏的数量
		Map<String, String> storeMap = new HashMap<String, String>();
		storeMap.put("storeId", storeId + "");
		storeMap.put("flags", "reduce");//flags是add时表示增加收藏，等于reduce时表示取消收藏
		storeService.updateStoreCount(storeMap);
	}
	/**
	 * 我的店铺被收藏的数量
	 * @param storeId
	 * @return
	 */
	public Integer findMyStoreFavoriteCount(String storeId){
		return favoritesDao.findMyStoreFavoriteCount(storeId);
	}
}
