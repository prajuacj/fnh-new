package com.leimingtech.service.module.dictionary.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.leimingtech.core.cache.jedis.JedisConfig;
import com.leimingtech.core.cache.jedis.JedisUtils;
import com.leimingtech.core.entity.base.Dictionary;
import com.leimingtech.service.module.dictionary.dao.DictionaryDao;
import com.leimingtech.service.module.dictionary.service.DictionaryService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title:数据词典service</p>
 * <p>Description: 使用缓存必须先安装reids</p>
 *
 * @author linjm
 * @version 1.0
 * @date 2015年10月13日
 **/
@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryDao dictionaryDao;

    @Override
    public void save(Dictionary dictionary) {
        dictionaryDao.save(dictionary);
        //缓存更新  需要安装redis
        updateDicCache(dictionary);
    }

    @Override
    public void update(Dictionary dictionary) {
        dictionaryDao.update(dictionary);
        //缓存更新 需要安装redis
        updateDicCache(dictionary);
    }

    @Override
    public void delete(String dictionaryId) {
        /**
         *先删除缓存数据
         *删除不成功取的时候会重新放入
         *请别调整这里的删除顺序
         **/
        //需要安装redis
        delDicCache(dictionaryId + "");
        //删除数据库数据
        dictionaryDao.delete(dictionaryId);
    }

    @Override
    public Dictionary findByDictionaryId(String dictionaryId) {
        return dictionaryDao.findByDictionaryId(dictionaryId);
    }

    @Override
    public int countDictionaryidList(Dictionary dictionary) {
        return dictionaryDao.countDictionaryidList(dictionary);
    }

    @Override
    public Pager queryDictionaryidList(Pager pager) {
    	List<Dictionary> list=dictionaryDao.queryDictionaryidList(pager);
		pager.setResult(list);
		return pager;
    }

    @Override
    public List<Dictionary> findDictionaryByCode(String groupCode) {
        List<Dictionary> diclist;
        if (JedisConfig.JEDIS_STATUS) {
            ////需要安装redis
            Object obj = JedisUtils.getObject(JedisConfig.DIC_PREFIX + groupCode);
            if (obj == null) {
                diclist = dictionaryDao.findDictionaryByCode(groupCode);
                //10分钟
                JedisUtils.setObject(JedisConfig.DIC_PREFIX + groupCode, diclist, JedisConfig.JEDIS_EXPIRE);
                log.debug(JedisConfig.DIC_PREFIX + "存入redis");
            } else {
                diclist = (List<Dictionary>) obj;
                log.debug(JedisConfig.DIC_PREFIX + "转化成功");
            }
        } else {
            diclist = dictionaryDao.findDictionaryByCode(groupCode);
        }
        return diclist;
    }

    @Override
    public Dictionary findDictionaryByDictionaryId(String dictionaryId) {

        return dictionaryDao.findDictionaryByDictionaryId(dictionaryId);
    }

    /**
     * 更新缓存数据
     * 需要安装redis
     *
     * @param dictionary
     */
    public void updateDicCache(Dictionary dictionary) {
        if (JedisConfig.JEDIS_STATUS) {
            Pager pager = new Pager();
            Dictionary dic = new Dictionary();
            //这里默认是20条字典项超过20条就自己设置大小
            pager.setPageSize(1000);
            dic.setDictionaryCode(dictionary.getDictionaryCode());
            pager.setCondition(dic);
            List<Dictionary> diclist = dictionaryDao.queryDictionaryidList(pager);
            JedisUtils.setObject(JedisConfig.DIC_PREFIX + dictionary.getDictionaryCode(), diclist, 600);
            log.debug(JedisConfig.DIC_PREFIX + "更新redis");
        }
    }

    /**
     * 删除缓存
     * 需要安装redis
     *
     * @param value
     */
    public void delDicCache(String value) {
        if (StringUtils.isEmpty(value)) return;
        Dictionary dic = dictionaryDao.findDictionaryByDictionaryId(value);
        if (dic == null) return;
        JedisUtils.delObject(JedisConfig.DIC_PREFIX + dic.getDictionaryCode());
    }
}
