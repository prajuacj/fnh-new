package com.leimingtech.service.module.member.dao.impl;

import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.ShopRelation;
import com.leimingtech.service.module.base.BaseDao;
import com.leimingtech.service.module.member.dao.MemberDao;
import com.leimingtech.service.module.member.dao.mapper.MemberMapper;
import com.leimingtech.service.utils.page.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目名称：leimingtech-admin
 * 类名称：MemberDaoImpl
 * 类描述：   DAO 实现类
 * 修改备注：
 */
@Repository
public class MemberDaoImpl extends BaseDao implements MemberDao {

    @Autowired
    private MemberMapper memberMapper;


    /**
     * 获取结果集
     */
    public List<Member> findMemberList(Pager pager) {
        return memberMapper.findMemberList(pager);
    }

    public void save(Member member) {
		if (member.getMemberId() == null) {
			// 会员id外部没有指定创建uuid;
			String memberId = IdGen.uuid();
			member.setMemberId(memberId);
		}
        memberMapper.save(member);
    }

    public void update(Member member) {
        memberMapper.updateMember(member);
    }

    public void delete(String id) {
        memberMapper.delete(id);
    }

    public Member findById(String memberId) {
        return memberMapper.findById(memberId);
    }


    /**
     * 根据Member修改信息
     *
     * @param member
     */
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    public Member findMemberById(String id) {
        return memberMapper.findById(id);
    }

    public Member findMemberByEmail(String memberEmail) {
        return memberMapper.findMemberByEmail(memberEmail);
    }

    public Member findMemberByName(String memberName) {
        return memberMapper.findMemberByName(memberName);
    }

    public Member findMemberByMobile(String memberMobile) {
        return memberMapper.findMemberByMobile(memberMobile);
    }


    public int findMemberCount(Member member) {
        return memberMapper.findMemberCount(member);
    }

    /**
     * 根据会员信息查找
     *
     * @param
     * @return
     */
    @Override
    public Member findMember(Member member) {
        return memberMapper.findMember(member);
    }

    /**
     * 根据memberphone查询member表
     *
     * @param memberMobile
     * @return
     */
    @Override
    public int findMemberCountByMobile(String memberMobile) {
        return memberMapper.findMemberCountByMobile(memberMobile);
    }

    /**
     * 注册时验证用户是否存在
     *
     * @param member
     * @return
     */
    @Override
    public int findMemberExistCount(Member member) {
        return memberMapper.findMemberExistCount(member);
    }

    /**
     * 登录时验证用户是否存在
     *
     * @param member
     * @return
     */
    @Override
    public Member findMemberExist(Member member) {
        return memberMapper.findMemberExist(member);
    }


    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<Member> findMemberListall() {
        return memberMapper.findMemberListall();
    }

    /**
     * 通过会员等级获取会员总数
     * @param memberGradeId
     * @return
     */
	@Override
	public int findMemberCountByGradeId(String memberGradeId) {
		return memberMapper.findMemberCountByGradeId(memberGradeId);
	}

	 /**
     * 同时通过用户名、真实姓名、邮箱like查询
     * @param pager
     * @return
     */
	@Override
	public List<Member> findMemberListIsLike(Pager pager) {
		return memberMapper.findMemberListIsLike(pager);
	}

	@Override
	public int saveOrUpdateRelationByLeader(List<ShopRelation> list) {
		return memberMapper.saveOrUpdateRelationByLeader(list);
	}

	@Override
	public int deleteByLeader(String id) {
		return memberMapper.deleteByLeader(id);
	}

	@Override
	public int findCountByDistrict(Member member) {
		return memberMapper.findCountByDistrict(member);
	}

	@Override
	public List<Member> queryMemberList(Member member) {
		return memberMapper.queryMemberList(member);
	}

	@Override
	public int findTempTobacoouserCount(String mobile) {
		return memberMapper.findTempTobacoouserCount(mobile);
	}

	@Override
	public int findRefereeUserType(String refereeId) {
		return memberMapper.findRefereeUserType(refereeId);
	}
	
	@Override
	public Member findRefereeUser(String refereeId) {
		return memberMapper.findRefereeUser(refereeId);
	}
}
