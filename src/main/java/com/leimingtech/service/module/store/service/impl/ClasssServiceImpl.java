package com.leimingtech.service.module.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.leimingtech.core.entity.Classs;
import com.leimingtech.service.module.store.dao.ClasssDao;
import com.leimingtech.service.module.store.service.ClasssService;
import com.leimingtech.service.module.store.vo.ClasssVo;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 项目名称：leimingtech-admin 类名称：ClasssServiceImpl 类描述： 创建人：weiyue 创建时间：2014年11月5日
 * 下午10:43:18 修改人：weiyue 修改时间：2014年11月5日 下午10:43:18 修改备注：
 * 
 * @version
 * 
 */
@Service
public class ClasssServiceImpl implements ClasssService {

	@Resource
	private ClasssDao classsDao;

	@Override
	public Pager queryClasssList(Pager pager) {
		// 对数据进行整理
		List<ClasssVo> results = classsDao.queryClasssList(pager);
		List<ClasssVo> returnResults = Lists.newArrayList();
		String pId = "0";
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).getCId() == null) {
				returnResults.add(results.get(i));
			} else {
				if (pId.equals(results.get(i).getPId())) {
					ClasssVo cv = new ClasssVo();
					cv.setCId(results.get(i).getCId());
					cv.setCName(results.get(i).getCName());
					cv.setCSort(results.get(i).getCSort());
					cv.setPId(results.get(i).getPId());
					returnResults.add(cv);
				} else {
					ClasssVo cv = new ClasssVo();
					cv.setPId(results.get(i).getPId());
					cv.setPName(results.get(i).getPName());
					cv.setPSort(results.get(i).getPSort());
					returnResults.add(cv);
					cv = new ClasssVo();
					cv.setCId(results.get(i).getCId());
					cv.setCName(results.get(i).getCName());
					cv.setCSort(results.get(i).getCSort());
					cv.setPId(results.get(i).getPId());
					returnResults.add(cv);
				}
				pId = results.get(i).getPId();
			}
		}

		pager.setResult(returnResults);
		return pager;
	}

	@Override
	public List<Classs> queryClasssParentList() {
		// TODO Auto-generated method stub
		return classsDao.queryClasssParentList();
	}

	public void save(Classs classs) {
		classs.setCreateTime(System.currentTimeMillis());// 保存添加时间
		classsDao.save(classs);
	}

	public void delete(String id) {
		classsDao.delete(id);
	}

	public Classs queryById(String id) {
		return classsDao.queryById(id);
	}

	@Override
	public void update(Classs classs) {
		classs.setUpdateTime(System.currentTimeMillis());// 保存修改时间
		classsDao.update(classs);
	}

	/**
	 * 查询子节点
	 * 
	 * @return
	 */
	@Override
	public List<Classs> queryClasssChildrenList(Classs classs) {
		return classsDao.queryClasssChildrenList(classs);
	}

	/**
	 * 去重
	 *
	 * @param classs
	 * @return
	 */
	@Override
	public int findCount(Classs classs) {
		return classsDao.findCount(classs);
	}

	@Override
	public List<Classs> findList(String parentid) {
		return classsDao.findList(parentid);
	}

	@Override
	public Classs findClassByName(String className) {
		// TODO Auto-generated method stub
		return classsDao.findClassByName(className);
	}

	@Override
	public List<Classs> findByName(String name) {
		// TODO Auto-generated method stub
		return classsDao.findByName(name);
	}
}
