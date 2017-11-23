package com.leimingtech.service.module.member.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.ShopRelation;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：MemberDao   
 * 类描述：接口
 * 修改备注：   
 * @version    
 *
 */
public interface MemberDao {
    
    
    List<Member> findMemberList(Pager pager);
    
    
    void save(Member member);
    
    void delete(String id);

    
    Member findById(String memberId);

    /**
     * 根据会员名查询会员信息
     * @param memberName
     * @return
     */
    Member findMemberByName(String memberName);
    
    /**
     * 根据Member修改信息
     * @param member
     */
    void updateMember(Member member);
    
    /**
     * 根据会员id查询会员信息
     * @param id
     * @return
     */
    Member findMemberById(String id);
    
    Member findMemberByEmail(String memberEmail);
    
    Member findMemberByMobile(String memberMobile);
    
    /**
   	 * 获取总记录数
   	 * @return
   	 */
    int findMemberCount(Member member);
   	
    /**
     * 根据会员信息查找
     * @param
     * @return
     */
    Member findMember(Member member);
    /**
     * 根据memberphone查询member表
     * @param memberMobile
     * @return
     */
    int findMemberCountByMobile(@Param("memberMobile") String memberMobile);

    /**
     * 注册时验证用户是否存在
     * @param member
     * @return
     */
    int findMemberExistCount(Member member);

	/**
     * 登录时验证用户是否存在
     * @param member
     * @return
     */
    Member findMemberExist(Member member);
    /**
     * 查询所有用户
     * @return
     */
    List<Member> findMemberListall();
    
    /**
     * 通过会员等级获取会员总数
     * @param memberGradeId
     * @return
     */
    int findMemberCountByGradeId(@Param("memberGradeId")String memberGradeId);

    /**
     * 同时通过用户名、真实姓名、邮箱like查询
     * @param pager
     * @return
     */
	List<Member> findMemberListIsLike(Pager pager);
	
	int saveOrUpdateRelationByLeader(List<ShopRelation> list);
	
	int deleteByLeader(String id);
	
	/**
	 * 查询区下开店贫困户数
	 * @author 张华
	 * @date 2016-8-8 下午7:49:52
	 * @param member
	 * @return
	 */
	int findCountByDistrict(Member member);
	
	/**
	 * 查询贫困户
	 * @author 张华
	 * @date 2016-8-9 下午5:06:07
	 * @param member
	 * @return
	 */
	List<Member> queryMemberList(Member member);
	
	/**
	 * 验证用户是否为行业用户
	 * @param mobile 手机号
	 * @return
	 */
    int findTempTobacoouserCount(String mobile);
    
    /**
	 * 验证推荐用户是否为行业用户
	 * @param refereeId 推荐用户ID
	 * @return
	 */
    int findRefereeUserType(String refereeId);
    
    /**
	 * 根据推荐人ID查看推荐人信息
	 * @param refereeId 推荐用户ID
	 * @return
	 */
    Member findRefereeUser(String refereeId);
}
