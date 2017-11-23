package com.leimingtech.service.module.goods.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.Spec;
import com.leimingtech.core.entity.vo.SpecVo;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;
@SqlMapper
public interface SpecMapper {
    /**
     * 保存
     * @param spec
     */
    void save(Spec spec);
    
    /**
     * 修改
     * @param spec
     */
    void update(Spec spec);

	Spec findById(String spId);
	
    List<Spec> findAllList(Spec spec);

    List<Spec> findPageList(Pager pager);
    
    Integer findPageListCount(Pager pager);
    
    List<Spec> findListBySpId(String spId);

    List<SpecVo> findSpecListBySpId(String spId);
    
    List<SpecVo> findListByType(String typeId);
    
    void deleteSpecBySpId(String spId);
    
    void deleteSpecValueBySpId(String spId);

    /**
     * 根据规格名获取规格数量
     * @param specName
     * @return
     */
    Integer findSpecListByName(String specName);
}
