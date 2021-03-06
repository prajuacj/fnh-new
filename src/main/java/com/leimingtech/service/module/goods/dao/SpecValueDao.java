package com.leimingtech.service.module.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.SpecValue;
import com.leimingtech.service.utils.page.Pager;

public interface SpecValueDao {

	/**
     * 保存
     * @param specValue
     */
    void save(SpecValue specValue);

    /**
     * 修改
     * @param specValue
     */
    void update(SpecValue specValue);

    /**
     * 删除
     * @param id
     */
    void deleteBySpId(@Param("spId") String id);

    /**
     * 查询单条,通过规格id
     * @param id
     * @return
     */
    SpecValue findById(@Param("spValueId") String id);

    public List<SpecValue> findListBySpId(String spId);

    /**
     * 查询总条数
     * @param pager
     * @return
     */
    int findCount(Pager pager);

    /**
     * 分页列表
     * @param pager
     * @return
     */
    List<SpecValue> findPageList(Pager pager);
}
