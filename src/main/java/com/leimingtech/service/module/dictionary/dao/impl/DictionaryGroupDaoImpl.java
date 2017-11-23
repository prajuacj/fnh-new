package com.leimingtech.service.module.dictionary.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.DictionaryGroup;
import com.leimingtech.service.module.dictionary.dao.DictionaryGroupDao;
import com.leimingtech.service.module.dictionary.dao.mapper.DictionaryGroupMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DictionaryGroupDaoImpl implements DictionaryGroupDao{

	@Resource 
	private DictionaryGroupMapper dictionaryGroupMapper;
	
	@Override
	public void save(DictionaryGroup dictionaryGroup) {
		dictionaryGroup.setGroupId(IdGen.uuid());
		dictionaryGroupMapper.save(dictionaryGroup);
	}

	@Override
	public void update(DictionaryGroup dictionaryGroup) {
		dictionaryGroupMapper.update(dictionaryGroup);
	}

	@Override
	public void delete(String groupId) {
		dictionaryGroupMapper.delete(groupId);
	}

	@Override
	public DictionaryGroup findByGroupId(String groupId) {
		return dictionaryGroupMapper.findByGroupId(groupId);
	}

	@Override
	public int countGroupidList(DictionaryGroup dictionaryGroup) {
		return dictionaryGroupMapper.countGroupidList(dictionaryGroup);
	}

	@Override
	public List<DictionaryGroup> queryGroupidList(Pager pager) {
		return dictionaryGroupMapper.queryGroupidList(pager);
	}
	
	public DictionaryGroup selectGroupByGroupCode(String groupCode){
		return dictionaryGroupMapper.selectGroupByGroupCode(groupCode);
	}

	
}
