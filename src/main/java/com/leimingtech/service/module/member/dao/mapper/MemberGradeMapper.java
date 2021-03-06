package com.leimingtech.service.module.member.dao.mapper;

import java.util.List;

import com.leimingtech.core.entity.base.MemberGrade;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;


/**
 *	cgl
 *	2015年08月24日15:48:54
 *	会员等级
 */
@SqlMapper
public interface MemberGradeMapper {
	
	/**
	 * 查询默认的会员等级
	 */
	public MemberGrade findDefaultGrade();
    
	/**
	 * 分页查询count
	 */
	public Integer findMemberGradeCount(MemberGrade memberGrade);
	
	/**
	 * 分页list
	 */
	public List<MemberGrade> findMemberGradePageList(Pager pager);
	
	/**
	 * 根据ID查询实体
	 */
	public MemberGrade findMembeGraderById(String gradeId);
	
	/**
	 * 保存
	 */
	public void save(MemberGrade memberGrade);
	
	/**
	 * 修改
	 */
	public void update(MemberGrade memberGrade);
	
	/**
	 * 修改所有的默认值为0
	 */
	public void updateDefault(String gradeId);
	
	/**
	 * 删除
	 */
	public void delete(String gradeId);
	
	/**
	 * 查询所有的会员等级
	 */
	public List<MemberGrade> findAllGrade();
	
}
