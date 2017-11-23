package com.leimingtech.service.module.store.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.Classs;
import com.leimingtech.service.module.store.dao.ClasssDao;
import com.leimingtech.service.module.store.dao.mapper.ClasssMapper;
import com.leimingtech.service.module.store.vo.ClasssVo;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LH
 */
@Repository
public class ClasssDaoImpl implements ClasssDao {
    @Resource
    private ClasssMapper classsMapper;

    @Override
    public List<ClasssVo> queryClasssList(Pager pager) {
        return classsMapper.queryClasssList(pager);
    }

    @Override
    public List<Classs> queryClasssParentList() {
        return classsMapper.queryClasssParentList();
    }

    @Override
    public void save(Classs classs) {
        classs.setId(IdGen.uuid());
        classsMapper.save(classs);
    }

    @Override
    public void delete(String id) {
        classsMapper.delete(id);
    }

    @Override
    public Classs queryById(String id) {
        return classsMapper.queryById(id);
    }

    @Override
    public void update(Classs classs) {
        classsMapper.update(classs);
    }

    /**
     * 查询子节点
     *
     * @param classs
     * @return
     */
    @Override
    public List<Classs> queryClasssChildrenList(Classs classs) {
        return classsMapper.queryClasssChildrenList(classs);
    }

    /**
     * 去重
     *
     * @param classs
     * @return
     */
    @Override
    public int findCount(Classs classs) {
        return classsMapper.findCount(classs);
    }

    @Override
    public List<Classs> findList(String parentid) {
        return classsMapper.findList(parentid);
    }

	@Override
	public Classs findClassByName(String className) {
		// TODO Auto-generated method stub
		return classsMapper.findClassByName(className);
	}
	
	@Override
	public List<Classs> findByName(String name) {
		// TODO Auto-generated method stub
		return classsMapper.findByName(name);
	}
}
