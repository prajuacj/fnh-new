package com.leimingtech.service.module.member.dao;

import java.util.List;

import com.leimingtech.core.entity.base.MemberGrade;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * @author cgl
 * 2015年08月24日15:50:03
 * 会员等级
 */
public interface MemberGradeDao {
	
	/**
	 * 查询默认的会员等级
	 */
	MemberGrade findDefaultGrade();

	/**
	 * 分页查询count
	 */
	Integer findMemberGradeCount(MemberGrade memberGrade);
	
	/**
	 * 分页list
	 */
	List<MemberGrade> findMemberGradePageList(Pager pager);
	
	/**
	 * 根据ID查询实体
	 */
	MemberGrade findMembeGraderById(String gradeId);
	
	/**
	 * 保存
	 */
	void save(MemberGrade memberGrade);
	
	/**
	 * 修改
	 */
	void update(MemberGrade memberGrade);
	
	/**
	 * 修改所有的默认值为0
	 */
	void updateDefault(String gradeId);
	
	/**
	 * 删除
	 */
	void delete(String gradeId);
	
	/**
	 * 查询所有的会员等级
	 */
	List<MemberGrade> findAllGrade();
}
