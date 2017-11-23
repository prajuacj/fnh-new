package com.leimingtech.service.module.dictionary.dao.mapper;

import com.leimingtech.core.entity.base.Dictionary;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

@SqlMapper
public interface DictionaryMapper {

    void save(Dictionary dictionary);

    void update(Dictionary dictionary);

    void delete(String dictionaryId);

    /**
     * 根据字典id查询字典实体
     *
     * @param dictionaryId
     * @return
     */
    Dictionary findByDictionaryId(String dictionaryId);

    /**
     * 总数查询
     *
     * @param dictionary
     * @return
     */
    int countDictionaryidList(Dictionary dictionary);

    /**
     * 分页列表
     *
     * @param pager
     * @return
     */
    List<Dictionary> queryDictionaryidList(Pager pager);

    List<Dictionary> findDictionaryByCode(String groupCode);

    Dictionary findDictionaryByDictionaryId(String dictionaryId);

    void updateAllDictionaryCodeByGroupId(Dictionary dictionary);

}
