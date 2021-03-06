package com.leimingtech.service.module.joininlog.dao.mapper;

import com.leimingtech.core.entity.base.JoininLog;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;

import java.util.List;

/**
 * 商家入驻流程日志mapper接口
 * 
 * @author zhubo
 * @version 22016-07-14
 */
@SqlMapper
public interface JoininLogMapper {

	/**
	 * 查询分页商家入驻流程日志数据
	 * 
	 * @param pager 分页对象
	 * @return
	 */
	List<JoininLog> findJoininLogPagerList(Pager pager);

	/**
	 * 通过joininLogId获取单条商家入驻流程日志数据
	 * 
	 * @param joininLogId
	 * @return
	 */
	JoininLog findJoininLogByJoininLogId(String joininLogId);

	/**
	 * 通过joininLogId删除商家入驻流程日志数据
	 * 
	 * @param joininLogId
	 */
	void deleteJoininLogByJoininLogId(String joininLogId);

	/**
	 * 修改商家入驻流程日志数据
	 * 
	 * @param JoininLog
	 */
	void updateJoininLog(JoininLog JoininLog);

	/**
	 * 保存商家入驻流程日志数据
	 * 
	 * @param JoininLog
	 */
	void saveJoininLog(JoininLog JoininLog);

	/**
	 * 获取所有商家入驻流程日志数据
	 * 
	 * @return
	 */
	List<JoininLog> findJoininLogAllList();


	/**
	 * 根据实体类型来查找日志信息列表
	 * @param JoininLog
	 * @return
     */
	List<JoininLog> findJoininLogsByEntity(JoininLog JoininLog);

}