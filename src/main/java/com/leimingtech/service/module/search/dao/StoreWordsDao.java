package com.leimingtech.service.module.search.dao;

import java.util.List;

import com.leimingtech.core.entity.base.StoreWords;
/**
 * 商品关键词
 * @author cgl
 * 2015年08月18日10:05:53
 */
public interface StoreWordsDao {
	
	/**
	 * 增加
	 */
	public void saveStoreWords(StoreWords storeWords);
	
	/**
	 * 删除
	 */
	public void delete(String wordsId);
	
	/**
	 * 修改
	 */
	public void update(StoreWords storeWords);
	
	/**
	 * 查询
	 */
//	public List<GoodsWords> getListByCondition(GoodsWords goodsWords);
	
	/**
	 * 查询关键词,在数据库中是否已经存在,存在返回wordsId,否则返回null
	 */
	public String isExist(String keyword);
	
	/**
	 * 修改商品出现次数
	 * 需要设置的参数:
	 * wordsId
	 * wordsNum +1为 在原来的基础上数量增加1,-1为在原来的基础上减去1
	 */
	public void updateWordsNum(StoreWords storeWords);
	
	/**
	 * 关键词匹配
	 */
	public List<StoreWords> keywordMatch(StoreWords storeWords);
	
	/**
	 * 删除所有的记录,初始化id
	 */
	public void deleteAll();
}
