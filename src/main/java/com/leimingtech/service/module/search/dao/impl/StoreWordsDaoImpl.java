package com.leimingtech.service.module.search.dao.impl;

import java.util.List;

import com.leimingtech.core.common.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leimingtech.core.entity.base.StoreWords;
import com.leimingtech.service.module.search.dao.StoreWordsDao;
import com.leimingtech.service.module.search.dao.mapper.StoreWordsMapper;
/**
 * 商品关键词
 * @author cgl
 * 2015年08月31日15:22:41
 */
@Component
public class StoreWordsDaoImpl implements StoreWordsDao{

	@Autowired
	StoreWordsMapper storeWordsMapper;
	
	@Override
	public void saveStoreWords(StoreWords storeWords) {
		// TODO Auto-generated method stub
		storeWords.setWordsId(IdGen.uuid());
		storeWordsMapper.saveStoreWords(storeWords);
	}

	@Override
	public void delete(String wordsId) {
		// TODO Auto-generated method stub
		storeWordsMapper.delete(wordsId);
	}

	@Override
	public void update(StoreWords storeWords) {
		// TODO Auto-generated method stub
		storeWordsMapper.update(storeWords);
	}

	@Override
	public String isExist(String keyword) {
		// TODO Auto-generated method stub
		return storeWordsMapper.isExist(keyword);
	}

	@Override
	public void updateWordsNum(StoreWords storeWords) {
		// TODO Auto-generated method stub
		storeWordsMapper.updateWordsNum(storeWords);
	}

	/**
	 * 关键词匹配
	 */
	public List<StoreWords> keywordMatch(StoreWords storeWords){
		return storeWordsMapper.keywordMatch(storeWords);
	}
	
	/**
	 * 删除所有的记录,初始化id
	 */
	public void deleteAll(){
		storeWordsMapper.deleteAll();
	}
}
