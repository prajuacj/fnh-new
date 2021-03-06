package com.leimingtech.service.module.website.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Document;
import com.leimingtech.service.module.website.dao.DocumentDao;
import com.leimingtech.service.module.website.service.DocumentService;
import com.leimingtech.service.utils.page.Pager;

/**
 * @author llf
 * @Package com.leimingtech.service.module.website.service.impl
 * @Description:
 * @date 2014/11/11 11:35
 */
@Service
public class DocumentServiceImpl implements DocumentService {

	@Resource
	private DocumentDao documentDao;

	/**
	 * 保存
	 *
	 * @param document
	 */
	@Override
	public void save(Document document) {
		documentDao.save(document);
	}

	/**
	 * 修改
	 *
	 * @param document
	 */
	@Override
	public void update(Document document) {
		documentDao.update(document);
	}

	/**
	 * 删除
	 *
	 * @param id
	 */
	@Override
	public void delete(String id) {
		documentDao.delete(id);
	}

	/**
	 * 查询单条
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Document findById(String id) {
		return documentDao.findById(id);
	}

	/**
	 * 分页列表
	 *
	 * @param pager
	 * @return
	 */
	@Override
	public Pager findListForPage(Pager pager) {
		List<Document> list = documentDao.findPageList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 总条数
	 *
	 * @return
	 */
	@Override
	public int findCount() {
		return documentDao.findCount();
	}
}
