package com.leimingtech.service.module.log.dao.impl;

import java.util.List;

import com.leimingtech.core.common.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leimingtech.core.entity.base.SellerLog;
import com.leimingtech.service.module.log.dao.SellerLogDao;
import com.leimingtech.service.module.log.dao.mapper.SellerLogDaoMapper;
import com.leimingtech.service.utils.page.Pager;

@Component
public class SellerLogDaoImpl implements SellerLogDao{
	
		@Autowired
		private SellerLogDaoMapper sellerLogDaoMapper;

		//增加日志
		public int saveSellerLog(SellerLog sellerLog){
			sellerLog.setLogId(IdGen.uuid());
			return sellerLogDaoMapper.saveSellerLog(sellerLog);
		}
		
		//删除日志
		public int deleteSellerLog(String logId){
			return sellerLogDaoMapper.deleteSellerLog(logId);
		}
		
		//修改日志
		public int updateSellerLog(SellerLog sellerLog){
			return sellerLogDaoMapper.updateSellerLog(sellerLog);
		}
		
		//查询所有日志
		public List<SellerLog> selectAllSellerLog(){
			return sellerLogDaoMapper.selectAllSellerLog();
		}

		@Override
		public int countSellerLog(SellerLog sellerLog) {
			return sellerLogDaoMapper.countSellerLog(sellerLog);
		}

		@Override
		public List<SellerLog> selectSellerLogByPager(Pager pager) {
			return sellerLogDaoMapper.selectSellerLogByPager(pager);
		}
		
		
}
