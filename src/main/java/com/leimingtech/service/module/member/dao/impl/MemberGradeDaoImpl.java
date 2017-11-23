package com.leimingtech.service.module.member.dao.impl;

import java.util.List;

import com.leimingtech.core.common.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leimingtech.core.entity.base.MemberGrade;
import com.leimingtech.service.module.member.dao.MemberGradeDao;
import com.leimingtech.service.module.member.dao.mapper.MemberGradeMapper;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * @author cgl
 * 2015年08月24日15:50:03
 * 会员等级
 */
@Repository
public class MemberGradeDaoImpl implements MemberGradeDao {
	
    @Autowired
    private MemberGradeMapper memberGradeMapper;
	
	/**
	 * 查询默认的会员等级
	 */
	public MemberGrade findDefaultGrade(){
		return memberGradeMapper.findDefaultGrade();
	}

	/**
	 * 分页查询count
	 */
	public Integer findMemberGradeCount(MemberGrade memberGrade){
		return memberGradeMapper.findMemberGradeCount(memberGrade);
	}
	
	/**
	 * 分页list
	 */
	public List<MemberGrade> findMemberGradePageList(Pager pager){
		return memberGradeMapper.findMemberGradePageList(pager);
	}
	
	/**
	 * 根据ID查询实体
	 */
	public MemberGrade findMembeGraderById(String gradeId){
		return memberGradeMapper.findMembeGraderById(gradeId);
	}
	
	/**
	 * 保存
	 */
	public void save(MemberGrade memberGrade){
		memberGrade.setGradeId(IdGen.uuid());
		memberGradeMapper.save(memberGrade);
	}
	
	/**
	 * 修改
	 */
	public void update(MemberGrade memberGrade){
		memberGradeMapper.update(memberGrade);
	}
	
	/**
	 * 修改所有的默认值为0
	 */
	public void updateDefault(String gradeId){
		memberGradeMapper.updateDefault(gradeId);
	}
	
	/**
	 * 删除
	 */
	public void delete(String gradeId){
		memberGradeMapper.delete(gradeId);
	}

	/**
	 * 查询所有的会员等级
	 */
	@Override
	public List<MemberGrade> findAllGrade() {
		return memberGradeMapper.findAllGrade();
	}
}
