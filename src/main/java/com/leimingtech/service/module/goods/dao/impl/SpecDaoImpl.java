package com.leimingtech.service.module.goods.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.leimingtech.core.common.IdGen;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.Spec;
import com.leimingtech.core.entity.vo.SpecVo;
import com.leimingtech.service.module.goods.dao.SpecDao;
import com.leimingtech.service.module.goods.dao.mapper.SpecMapper;
import com.leimingtech.service.utils.page.Pager;

@Repository
public class SpecDaoImpl implements SpecDao {

    @Resource
    private SpecMapper specMapper;
    
    /**
     * 保存
     * @param spec
     */
    @Override
    public void save(Spec spec) {
		spec.setSpId(IdGen.uuid());
		specMapper.save(spec);
    }

    /**
     * 修改
     * @param spec
     */
    @Override
    public void update(Spec spec) {
    	specMapper.update(spec);
    }
    
	@Override
	public Spec findById(String spId) {
		// TODO Auto-generated method stub
		return specMapper.findById(spId);
	}

	@Override
	public List<Spec> findAllList(Spec spec) {
		// TODO Auto-generated method stub
		return specMapper.findAllList(spec);
	}

	@Override
	public List<Spec> findPageList(Pager pager) {
		// TODO Auto-generated method stub
		return specMapper.findPageList(pager);
	}
	
	@Override
    public Integer findPageListCount(Pager pager){
		
		return specMapper.findPageListCount(pager);
    }

	@Override
	public List<Spec> findListBySpId(String spId) {
		// TODO Auto-generated method stub
		return specMapper.findListBySpId(spId);
	}
 
	@Override
	public List<SpecVo> findSpecListBySpId(String spId) {
		// TODO Auto-generated method stub
		return specMapper.findSpecListBySpId(spId);
	}
	
	@Override
	public List<SpecVo> findListByType(String typeId) {
		// TODO Auto-generated method stub
		return specMapper.findListByType(typeId);
	}
	
	@Override
    public void deleteSpecBySpId(String spId){
		// TODO Auto-generated method stub
		specMapper.deleteSpecBySpId(spId);
		specMapper.deleteSpecValueBySpId(spId);
    }

	/**
	 * 根据规格名获取规格数量
	 * @param specName
	 * @return
	 */
	public Integer findSpecListByName(String specName) {
		return specMapper.findSpecListByName(specName);
	}


}
