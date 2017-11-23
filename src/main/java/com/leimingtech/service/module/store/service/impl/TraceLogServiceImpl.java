package com.leimingtech.service.module.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.TraceLog;
import com.leimingtech.service.module.store.dao.TracelogDao;
import com.leimingtech.service.module.store.service.TraceLogService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目名称：leimingtech-admin 类名称：TraceLogServiceImpl 类描述： 创建人：yanghui
 * 创建时间：2014年11月7日 下午2:04:30 修改人：yanghui 修改时间：2014年11月7日 下午2:04:30 修改备注：
 */
@Service("traceLogService")
@Slf4j
public class TraceLogServiceImpl implements TraceLogService {

	@Autowired
	private TracelogDao tracelogDao;

	/**
	 * @param @param
	 *            pager
	 * @param @return
	 *            设定文件
	 * @return int 返回类型
	 * @throws @Title:
	 *             countTraceLog
	 * @Description: count总数查询
	 */
	@Override
	public int countTraceLog(Pager pager) {
		log.info("获取log列表记录数");
		return tracelogDao.countTraceLog(pager);
	}

	/**
	 * @param @param
	 *            pager
	 * @param @return
	 *            设定文件
	 * @return List<AdminLog> 返回类型
	 * @throws @Title:
	 *             queryTraceLogList
	 * @Description: 带分页list 查询
	 */
	@Override
	public Pager queryTraceLogList(Pager pager) {
		log.info("获取log列表List");
		List<TraceLog> list = tracelogDao.queryTraceLogList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * @param @param
	 *            id 设定文件
	 * @return void 返回类型
	 * @throws @Title:
	 *             delete
	 * @Description: 根据id删除数据
	 */
	@Override
	public void delete(String id) {
		tracelogDao.delete(id);
	}

	/**
	 * @param @param
	 *            id
	 * @param @return
	 *            设定文件
	 * @return AdminLog 返回类型
	 * @throws @Title:
	 *             findLogById
	 * @Description: 根据ID 查询明细
	 */
	@Override
	public TraceLog findLogById(String id) {
		return tracelogDao.findLogById(id);
	}

	/**
	 * @param @param
	 *            id
	 * @param @param
	 *            state 设定文件
	 * @return void 返回类型
	 * @throws @Title:
	 *             updateById
	 * @Description: 根据ID修改状态
	 */
	@Override
	public void updateStateById(String id, Integer state) {
		tracelogDao.updateStateById(id, state);
	}
}
