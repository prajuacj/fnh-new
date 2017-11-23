package com.leimingtech.service.module.dictionary.dao;

import java.util.List;

import com.leimingtech.core.entity.base.Dictionary;
import com.leimingtech.service.utils.page.Pager;

public interface DictionaryDao {

	void save(Dictionary dictionary);
	void update(Dictionary dictionary);
	void delete(String dictionaryId);
	/**
	 * 根据字典id查询字典实体
	 * @param dictionaryId
	 * @return
	 */
	Dictionary findByDictionaryId(String dictionaryId);
	/**
	 * 总数查询
	 * @param pager
	 * @return
	 */
	int countDictionaryidList(Dictionary dictionary);
	/**
	 * 分页列表
	 * @param pager
	 * @return
	 */
	List<Dictionary> queryDictionaryidList(Pager pager);

	List<Dictionary> findDictionaryByCode(String groupCode);
	
	Dictionary findDictionaryByDictionaryId(String dictionaryId);
	
	void updateAllDictionaryCodeByGroupId(Dictionary dictionary);

}
