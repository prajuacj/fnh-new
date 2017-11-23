package com.leimingtech.service.module.dictionary.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Dictionary;
import com.leimingtech.service.module.dictionary.dao.DictionaryDao;
import com.leimingtech.service.module.dictionary.dao.mapper.DictionaryMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DictionaryDaoImpl implements DictionaryDao {

    @Resource
    private DictionaryMapper dictionaryMapper;

    @Override
    public void save(Dictionary dictionary) {
        dictionary.setDictionaryId(IdGen.uuid());
        dictionaryMapper.save(dictionary);
    }

    @Override
    public void update(Dictionary dictionary) {
        dictionaryMapper.update(dictionary);
    }

    @Override
    public void delete(String dictionaryId) {
        dictionaryMapper.delete(dictionaryId);
    }

    @Override
    public Dictionary findByDictionaryId(String dictionaryId) {
        return dictionaryMapper.findByDictionaryId(dictionaryId);
    }

    @Override
    public int countDictionaryidList(Dictionary dictionary) {
        return dictionaryMapper.countDictionaryidList(dictionary);
    }

    @Override
    public List<Dictionary> queryDictionaryidList(Pager pager) {
        return dictionaryMapper.queryDictionaryidList(pager);
    }

    @Override
    public List<Dictionary> findDictionaryByCode(String groupCode) {
        return dictionaryMapper.findDictionaryByCode(groupCode);
    }

    @Override
    public Dictionary findDictionaryByDictionaryId(String dictionaryId) {
        return dictionaryMapper.findDictionaryByDictionaryId(dictionaryId);
    }

    public void updateAllDictionaryCodeByGroupId(Dictionary dictionary) {
        dictionaryMapper.updateAllDictionaryCodeByGroupId(dictionary);
    }
}
