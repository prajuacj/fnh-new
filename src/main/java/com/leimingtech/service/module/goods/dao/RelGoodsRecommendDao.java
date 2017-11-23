package com.leimingtech.service.module.goods.dao;

import com.leimingtech.core.entity.base.RelGoodsRecommend;

import java.util.List;

/**
 * 
 *    
 * 项目名称：leimingtech-front
 * 类名称：RelGoodsRecommendDao   
 * 类描述：   
 * 创建人：gyh   
 * 创建时间：2015年8月25日 上午10:44:25   
 * 修改备注：   
 * @version    
 *
 */
public interface RelGoodsRecommendDao {
	/**
     * 保存
     * @param relGoodsRecommend
     */
    void save(RelGoodsRecommend relGoodsRecommend);
    /**
	 * 删除
	 * @param id
	 */
	void delete(String id);
	/**
     * 根据商品栏目查询商品
     * @param relGoodsRecommend
     */
	List<RelGoodsRecommend> findgoodsList(RelGoodsRecommend relGoodsRecommend);
	/**
     * 根据商品栏目id查询商品id
     * @param reCommendId
     */
	List<RelGoodsRecommend> findgoodsids(String reCommendId);
	/**
     * 修改
     * @param relGoodsRecommend
     */
	void updaterel(RelGoodsRecommend relGoodsRecommend);
}
