package com.leimingtech.service.module.member.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.ShopRelation;
import com.leimingtech.core.orm.mybatis.SqlMapper;
import com.leimingtech.service.utils.page.Pager;


/**
 * 
 *    
 * 项目名称：leimingtech-admin   
 * 类名称：MemberMapper   
 * 类描述：MemberMapper    
 * 创建人：sangyuchen   
 * 创建时间：2014年11月10日 上午9:38:26   
 * 修改人：sangyuchen   
 * 修改时间：2014年11月10日 上午9:38:26   
 * 修改备注：   
 * @version    
 *
 */
@SqlMapper
public interface MemberMapper {
    
    
    List<Member> findMemberList(Pager pager);
    
    
    void save(Member member);
    
    
    Member findById(String memberId);

    /**
     * 根据会员id修改密码
     * @param newPasswd
     * @param memberId
     */
    void updatePass(String newPasswd, String memberId);
    
    void updateMember(Member member);
    
    /**
     * 根据公司id查询用户
     * @return
     */
    List<Member> findByCompId(String companyId);
    
    
    /**
     * 删除用户信息
     * @param memberId
     */
    void delete(String memberId);

    /**
     * 用户移除角色
     * @param memberId
     * @param isSorF
     */
    void deleteShopRoleMemberVo(String memberId,Integer isSorF);
    /**
     * 根据用户id删除用户与角色绑定 
     * @param id
     */
    void deleteShopRoleMemberVoByMemberId(String id);
    
    /**
     * 根据membername查询member表
     * @param memberName
     * @return
     */
    List<Member> findByMemberName(String memberName);

    /**
     * 根据memberphone查询member表
     * @param memberMobile
     * @return
     */
    Member findMemberByMobile(@Param("memberMobile") String memberMobile);

    /**
     * 根据ID修改密码
     * @param member
     */
    void updatePassById(Member member);
    /**
     * 修改用户登录
     * @param memberName 用户名
     */
    void updateLoginNum(String memberName);
    
    Member findMemberByEmail(@Param("memberEmail") String memberEmail);
    
    /**
     * 查询会员信息
     * @param memberName
     * @return
     */
    Member findMemberByName(@Param("memberName") String memberName);
    
    /**
   	 * 获取总记录数
   	 * @param member
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
	
	/**
	 * 保存挂包帮关系
	 * @param list
	 * @return
	 */
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
