package com.leimingtech.service.module.dictionary.dao;

import com.leimingtech.core.entity.base.DictionaryGroup;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

public interface DictionaryGroupDao {

    void save(DictionaryGroup dictionaryGroup);

    void update(DictionaryGroup dictionaryGroup);

    void delete(String groupId);

    /**
     * 根据字典组id查询字典组实体
     *
     * @param groupId
     * @return
     */
    DictionaryGroup findByGroupId(String groupId);

    /**
     * 总数查询
     *
     * @param dictionaryGroup
     * @return
     */
    int countGroupidList(DictionaryGroup dictionaryGroup);

    /**
     * 分页列表
     *
     * @param pager
     * @return
     */
    List<DictionaryGroup> queryGroupidList(Pager pager);

    DictionaryGroup selectGroupByGroupCode(String groupCode);
}
