package com.leimingtech.service.module.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.StoreSnsComment;
import com.leimingtech.service.module.store.dao.StoreSnsCommentDao;
import com.leimingtech.service.module.store.service.StoreSnsCommentService;
import com.leimingtech.service.utils.page.Pager;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目名称：leimingtech-admin 类名称：StoreSnsCommentServiceImpl 类描述： 创建人：yanghui
 * 创建时间：2014年11月15日 下午7:13:16 修改人：yanghui 修改时间：2014年11月15日 下午7:13:16 修改备注：
 */
@Service("storeSnsCommentService")
@Slf4j
public class StoreSnsCommentServiceImpl implements StoreSnsCommentService {

	@Autowired
	private StoreSnsCommentDao storeSnsCommentDao;

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
	public int countComment(Pager pager) {
		log.info("获取log列表记录数");
		return storeSnsCommentDao.countComment(pager);
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
	public Pager queryCommentList(Pager pager) {
		log.info("获取log列表List");
		List<StoreSnsComment> list = storeSnsCommentDao.queryCommentList(pager);
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
		storeSnsCommentDao.delete(id);
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
	public StoreSnsComment findById(String id) {
		return storeSnsCommentDao.findLogById(id);
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
		storeSnsCommentDao.updateStateById(id, state);
	}
}
