package com.leimingtech.service.module.store.dao;

import com.leimingtech.core.entity.base.Upload;

import java.util.List;

/**
 * Created by rabook on 2015/3/7.
 */
public interface UploadDao {

    /**
     * 保存
     * @param upload
     */
    void save(Upload upload);

    /**
     * 删除
     * @param upload
     */
    void update( Upload upload);

    List<Upload> findByStoreId(String id);
}
