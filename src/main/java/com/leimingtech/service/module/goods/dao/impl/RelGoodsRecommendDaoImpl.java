package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.RelGoodsRecommend;
import com.leimingtech.service.module.goods.dao.RelGoodsRecommendDao;
import com.leimingtech.service.module.goods.dao.mapper.RelGoodsRecommendMapper;
/**
 * 
 *    
 * 项目名称：leimingtech-seller  
 * 类名称：RelGoodsRecommendDaoImpl   
 * 类描述：   
 * 创建人：gyh 
 * 创建时间：2015年8月25日 下午10:59:04   
 * 修改备注：   
 * @version    
 *
 */
@Repository
public class RelGoodsRecommendDaoImpl implements RelGoodsRecommendDao{
	@Resource
	private  RelGoodsRecommendMapper  relGoodsRecommendMapper;
		
	
	@Override
	public void save(RelGoodsRecommend relGoodsRecommend) {
		relGoodsRecommend.setRelId(IdGen.uuid());
		relGoodsRecommendMapper.save(relGoodsRecommend);
	}
	@Override
	public void delete(String id) {
		relGoodsRecommendMapper.delete(id);
	}

	@Override
	public List<RelGoodsRecommend> findgoodsList(RelGoodsRecommend relGoodsRecommend) {
		return relGoodsRecommendMapper.findgoodsList(relGoodsRecommend);
	}
	@Override
	public List<RelGoodsRecommend> findgoodsids(String reCommendId) {
		return relGoodsRecommendMapper.findgoodsids(reCommendId);
	}
	/**
     * 修改
     * @param relGoodsRecommend
     */
	@Override
	public void updaterel(RelGoodsRecommend relGoodsRecommend) {
		relGoodsRecommendMapper.updaterel(relGoodsRecommend);
	}
	
}
