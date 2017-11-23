package com.leimingtech.service.module.website.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Navigation;
import com.leimingtech.service.module.website.dao.NavigationDao;
import com.leimingtech.service.module.website.service.NavigationService;
import com.leimingtech.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.leimingtech.service.module.website.service.impl
 * @Description:
 * @date 2014/11/11 14:42
 */
@Service
public class NavigationServiceImpl implements NavigationService {

	@Resource
	private NavigationDao navigationDao;

	/**
	 * 保存
	 *
	 * @param navigation
	 */
	@Override
	public void save(Navigation navigation) {

		switch (navigation.getNavType()) {
		case 0:
			navigation.setNavItemId("0");
			break;
		case 1:
			navigation.setNavItemId(navigation.getGcId());
			break;
		case 2:
			navigation.setNavItemId(navigation.getAcId());
			break;
		}
		navigation.setCreateTime(System.currentTimeMillis());
		navigationDao.save(navigation);
	}

	/**
	 * 修改
	 *
	 * @param navigation
	 */
	@Override
	public void update(Navigation navigation) {

		switch (navigation.getNavType()) {
		case 0:
			navigation.setNavItemId("0");
			break;
		case 1:
			navigation.setNavItemId(navigation.getGcId());
			break;
		case 2:
			navigation.setNavItemId(navigation.getAcId());
			break;
		}
		navigation.setUpdateTime(System.currentTimeMillis());
		navigationDao.update(navigation);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	@Override
	public void delete(String id) {
		navigationDao.delete(id);
	}

	/**
	 * 查询单条
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Navigation findById(String id) {
		return navigationDao.findById(id);
	}

	/**
	 * 列表查询
	 *
	 * @param pager
	 * @return
	 */
	@Override
	public Pager findListForPage(Pager pager) {
		List<Navigation> list = navigationDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 查询总条数
	 *
	 * @return
	 */
	@Override
	public int findCount(Navigation navigation) {
		return navigationDao.findCount(navigation);
	}

	@Override
	public List<Navigation> findAllList(Navigation navigation) {
		return navigationDao.findAllList(navigation);
	}
}
