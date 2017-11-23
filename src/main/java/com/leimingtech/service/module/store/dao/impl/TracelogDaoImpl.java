package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.entity.base.TraceLog;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.store.dao.TracelogDao;
import com.leimingtech.service.module.store.dao.mapper.TracelogMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称：leimingtech-admin
 * 类名称：TracelogDaoImpl
 * 类描述：
 * 创建人：yanghui
 * 创建时间：2014年11月13日 下午4:13:20
 * 修改人：yanghui
 * 修改时间：2014年11月13日 下午4:13:20
 * 修改备注：
 */
@Service("tracelogDao")
public class TracelogDaoImpl extends BaseDao implements TracelogDao {
    @Autowired
    private TracelogMapper tracelogMapper;

    @Override
    public int countTraceLog(Pager pager) {
        return tracelogMapper.countTraceLog(pager);
    }

    @Override
    public List<TraceLog> queryTraceLogList(Pager pager) {
        return tracelogMapper.queryTraceLogList(pager);
    }


    @Override
    public void delete(String id) {
        tracelogMapper.delete(id);
    }


    @Override
    public TraceLog findLogById(String id) {
        return tracelogMapper.findLogById(id);
    }

    @Override
    public void updateStateById(String id, Integer state) {
        tracelogMapper.updateStateById(id, state);
    }

}
