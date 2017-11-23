package com.leimingtech.service.module.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.cache.annotation.CacheParamKey;
import com.leimingtech.core.cache.annotation.Cached;
import com.leimingtech.core.common.DateUtils;
import com.leimingtech.core.common.Digests;
import com.leimingtech.core.common.IdGen;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.MemberGrade;
import com.leimingtech.core.entity.base.ShopPointsLog;
import com.leimingtech.core.entity.base.ShopRelation;
import com.leimingtech.core.jackson.JsonUtils;
import com.leimingtech.service.module.member.common.PointsLogType;
import com.leimingtech.service.module.member.dao.MemberDao;
import com.leimingtech.service.module.member.service.MemberGradeService;
import com.leimingtech.service.module.member.service.MemberService;
import com.leimingtech.service.module.member.service.ShopPointsLogService;
import com.leimingtech.service.module.setting.service.SettingService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 
 * 项目名称：leimingtech-admin 类名称：MemberServiceImpl 类描述：service实现类 修改备注：
 * 
 * @version
 *
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;

	@Resource
	private MemberGradeService memberGradeService;

	@Resource
	private SettingService settingService;

	@Resource
	private ShopPointsLogService shopPointsLogService;

	/**
	 * 
	 * @Title: findMemberList @param @param pager @param @return 设定文件 @return
	 * List<Account> 返回类型 @throws
	 */
	public Pager findMemberList(Pager pager) {
		List<Member> list = memberDao.findMemberList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 保存member信息
	 * 
	 * @param member
	 */
	@Override
	public void save(Member member) {
		String code = DateUtils.getRandomString(8);
		member.setSignCode(code);
		member.setSignCodeState(0);
		member.setMemberState(1);// 会员的开启状态 1为开启 0为关闭
		// member.setMemberTruename("");//会员真实姓名
		if (StringUtils.isEmpty(member.getMemberAvatar())) {
			member.setMemberAvatar("/upload/img/avatar/01.jpg");// 会员头像
		}
		member.setMemberPasswd(Digests.entryptPassword(member.getMemberPasswd()));
		member.setCreateTime(System.currentTimeMillis());// 会员创建时间

		// 获取成功注册等级积分
		// String rankSettingPoints =
		// null;//settingService.findByNameAndCode("points", "register_rank");
		// //获取成功注册消费积分
		// String consSettingPoints =
		// null;//settingService.findByNameAndCode("points", "register_cons");
		// if(!StringUtils.isNotBlank(rankSettingPoints)){
		// rankSettingPoints = "0";
		// }
		// member.setMemberRankPoints(Integer.valueOf(rankSettingPoints));
		// if(!StringUtils.isNotBlank(consSettingPoints)){
		// consSettingPoints = "0";
		// }
		// member.setMemberConsumePoints(Integer.valueOf(consSettingPoints));

		member.setMemberRankPoints(0);
		member.setMemberConsumePoints(0);

		MemberGrade memberGrade = memberGradeService.findDefaultGrade();
		if (memberGrade != null) {
			member.setMemberGradeId(memberGrade.getGradeId());// 默认会员等级
			member.setGradeDate(System.currentTimeMillis());
		}
		// id在service赋予，下面添加积分也可使用
		member.setMemberId(IdGen.uuid());

		memberDao.save(member);

		/**
		 * 积分日志
		 */
		// 用户注册时为邀请/分享用户添加积分
		saveShopPointsLog(member);

		// ShopPointsLog shopPointsLog = new ShopPointsLog();
		// shopPointsLog.setMemberid(member.getMemberId());
		// shopPointsLog.setMembername(member.getMemberName());
		// shopPointsLog.setAdminid("1");
		// shopPointsLog.setAdminname("admin");
		//// shopPointsLog.setPoints(Integer.valueOf(consSettingPoints));
		// shopPointsLog.setPoints(0);
		// shopPointsLog.setCreateTime(System.currentTimeMillis());
		// shopPointsLog.setType(PointsLogType.POINTS_TYPE_REGISTER); //积分操作类型
		// shopPointsLog.setDesc("注册成功");
		// shopPointsLog.setStage("注册完成,增加会员积分");
		// //保存会员积分日志表
		// shopPointsLogService.save(shopPointsLog);

		// ObjectNode node =
		// EasemobIMUsers.createNewIMUserSingle(member.getMemberName(),
		// "lmshopb2b2c");
		// if (null != node) {
		// System.out.println("EASEMOBIMUSERS-注册IM用户[单个]: " + node.toString());
		// }else{
		// System.out.println("EASEMOBIMUSERS-注册IM用户[单个]:失败");
		// }
	}

	/**
	 * 用户注册时为邀请/分享用户添加积分
	 * 
	 * @param member
	 */
	public void saveShopPointsLog(Member member) {
		try {
			ShopPointsLog shopPointsLog = new ShopPointsLog();
			shopPointsLog.setMemberid(member.getRefereeId());// 为推荐/分享用户id添加积分日志
			shopPointsLog.setMembername("");
			shopPointsLog.setAdminid("1");
			shopPointsLog.setAdminname("admin");
			shopPointsLog.setCreateTime(System.currentTimeMillis());
			shopPointsLog.setPoints(30);// 邀请或分享用户，可获得（30积分/人）
			shopPointsLog.setSponsor(member.getMemberId());// 注册用户为发起者
			if (null != member.getGoodsId() && !member.getGoodsId().equals("")) {// 分享获得类型
				shopPointsLog.setType(PointsLogType.POINTS_TYPE_SHARE);
				shopPointsLog.setObjectId(member.getGoodsId());// 操作对象为分享商品id
				shopPointsLog.setDesc("分享注册成功");
				shopPointsLog.setStage("分享注册成功，增加会员积分");
			} else {// 邀请获得类型
				shopPointsLog.setType(PointsLogType.POINTS_TYPE_REQUEST);
				shopPointsLog.setObjectId(member.getRefereeId());// 操作对象为推荐/分享用户id
				shopPointsLog.setDesc("邀请注册成功");
				shopPointsLog.setStage("邀请注册成功，增加会员积分");
			}
			shopPointsLogService.save(shopPointsLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: delete @param @param id 设定文件 @return void 返回类型 @throws
	 */
	@Override
	public void delete(String id) {
		memberDao.delete(id);
	}

	/**
	 * 根据会员id获取会员信息
	 * 
	 * @param memberId
	 * @return
	 */
	@Override
	@Cached(key = "user")
	public Member findById(@CacheParamKey String memberId) {
		return memberDao.findById(memberId);
	}

	/**
	 * 根据会员名查询会员信息
	 * 
	 * @param memberName
	 * @return
	 */
	@Override
	public Member findMemberByName(String memberName) {
		return memberDao.findMemberByName(memberName);
	}

	/**
	 * 根据Member修改信息
	 * 
	 * @param member
	 */
	public void updateMember(Member member) {
		/*
		 * if(StringUtils.isNotEmpty(member.getMemberPasswd())){
		 * member.setMemberPasswd(Digests.entryptPassword(member.getMemberPasswd
		 * ()));//修改密码 }
		 */

		// 如果关系不为空，则先删除关系，再保存挂包帮关系
		if (member.getLeaderFarmer() != null) {
			String[] leaderFarmers = member.getLeaderFarmer().split(",");

			List<ShopRelation> list = new ArrayList<ShopRelation>();

			for (int i = 0; i < leaderFarmers.length; i++) {
				if (StringUtils.isNotEmpty(leaderFarmers[i])) {
					ShopRelation shopRelation = new ShopRelation();
					shopRelation.setId(IdGen.uuid());
					shopRelation.setLeaderId(member.getMemberId());
					shopRelation.setSellerId(leaderFarmers[i]);
					list.add(shopRelation);
				}
			}

			memberDao.deleteByLeader(member.getMemberId());

			if (!list.isEmpty()) {
				memberDao.saveOrUpdateRelationByLeader(list);
			}
		}

		memberDao.updateMember(member);
	}

	@Override
	public Member findMemberById(String id) {
		return memberDao.findMemberById(id);
	}

	@Override
	public int updatePass(String newPasswd, String memberId) {
		// Member member = memberDao.findMemberById(memberId);
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPasswd(newPasswd);
		try {
			member.setMemberPasswd(Digests.entryptPassword(newPasswd));
			memberDao.updateMember(member);
			return 1;
		} catch (Exception e) {
			System.out.println("更新密码失败" + e.getMessage());
			return 0;
		}
	}

	@Override
	public int updateMember(String data) {
		int result = 0;
		try {
			Member member = JsonUtils.fromJson(data, Member.class);
			if (member.getMemberId() != null) {
				memberDao.updateMember(member);
				result = 1;
			}
		} catch (Exception e) {
			System.out.println("保存失败" + e.getMessage());
		}
		return result;
	}

	/**
	 * 根据memberId修改密码
	 * 
	 * @param newPasswd
	 * @param memberId
	 */
	@Override
	public int updatePass(String memberPasswd, String newPasswd, String memberId) {
		Member member = memberDao.findMemberById(memberId);
		// 判断原密码是否正确
		if (!Digests.validatePassword(String.valueOf(memberPasswd), member.getMemberPasswd())) {
			return 2;
		} else {
			try {
				member.setMemberPasswd(Digests.entryptPassword(newPasswd).toString());
				memberDao.updateMember(member);
				return 1;
			} catch (Exception e) {
				System.out.println("更新密码失败" + e.getMessage());
				return 0;
			}
		}
	}

	/**
	 * 修改头像
	 * 
	 * @param path
	 * @param memberId
	 * @return
	 */
	@Override
	public int updateFace(String path, String memberId) {
		// Member member = memberDao.findMemberById(memberId);
		Member member = new Member();
		member.setMemberId(memberId);
		try {
			member.setMemberAvatar(path);
			memberDao.updateMember(member);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("更新密码失败"+e.getMessage());
			System.out.println("更新头像失败" + e.getMessage());
			return 0;
		}
	}

	/**
	 * 根据email查询会员
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public Member findMemberByEmail(String email) {
		return memberDao.findMemberByEmail(email);
	}

	/**
	 * 根据memberMobile查询member表
	 * 
	 * @param memberMobile
	 * @return
	 */
	@Override
	public Member findMemberByMobile(String memberMobile) {
		return memberDao.findMemberByMobile(memberMobile);
	}

	/**
	 * 仅仅修改当前登陆人的
	 * 
	 * @param memberId
	 * @return
	 */
	@Override
	public void updateweiMember(String memberId) {
		Member member = memberDao.findById(memberId);
		Member member9 = new Member();
		if (member != null) {
			member9.setMemberId(member.getMemberId());
			member9.setMemberOldLoginTime(member.getMemberLoginTime());// 上次登陆时间
			member9.setMemberLoginTime(System.currentTimeMillis());// 最后登陆时间
			System.out.println(System.currentTimeMillis());
			member9.setMemberLoginNum(1);// 登陆次数
			memberDao.updateMember(member9);
		}
	}

	/**
	 * 获取总记录数
	 * 
	 * @param member
	 * @return
	 */
	public int findMemberCount(Member member) {
		return memberDao.findMemberCount(member);
	}

	/**
	 * 根据会员信息查找
	 * 
	 * @param
	 * @return
	 */
	@Override
	public Member findMember(Member member) {
		return memberDao.findMember(member);
	}

	/**
	 * 根据memberphone查询member表
	 * 
	 * @param memberMobile
	 * @return
	 */
	@Override
	public int findMemberCountByMobile(String memberMobile) {
		return memberDao.findMemberCountByMobile(memberMobile);
	}

	/**
	 * 注册时验证用户是否存在
	 * 
	 * @param member
	 * @return
	 */
	@Override
	public int findMemberExistCount(Member member) {
		return memberDao.findMemberExistCount(member);
	}

	/**
	 * 登录时验证用户是否存在
	 * 
	 * @param member
	 * @return
	 */
	@Override
	public Member findMemberExist(Member member) {
		return memberDao.findMemberExist(member);
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@Override
	public List<Member> findMemberListall() {
		return memberDao.findMemberListall();
	}

	/**
	 * 通过会员等级获取会员总数
	 * 
	 * @param memberGradeId
	 * @return
	 */
	@Override
	public int findMemberCountByGradeId(String memberGradeId) {
		return memberDao.findMemberCountByGradeId(memberGradeId);
	}

	/**
	 * 同时通过用户名、真实姓名、邮箱like查询
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public Pager findMemberListIsLike(Pager pager) {
		List<Member> list = memberDao.findMemberListIsLike(pager);
		pager.setResult(list);
		return pager;
	}

	@Override
	public boolean login(String username, String password, String dbPassword) throws Exception {
		boolean validPwd = Digests.validatePassword(password, dbPassword);
		if (!validPwd) {
			// 密码错误
			throw new Exception("密码错误！");
		}
		return true;
	}

	@Override
	public int findCountByDistrict(Member member) {
		return memberDao.findCountByDistrict(member);
	}

	@Override
	public List<Member> queryMemberList(Member member) {
		return memberDao.queryMemberList(member);
	}

	@Override
	public int findUserType(String mobile, String refereeId) {
		int count = memberDao.findTempTobacoouserCount(mobile);
		if (count > 0) {
			return 4;// 当行业临时表中存在该用户手机号，即标识为行业用户
		} else {
			// 如果注册用户为推荐或者邀请用户，则根据推荐人身份给予注册用户身份
			if (null != refereeId && !refereeId.equals("")) {
				int refereeType = memberDao.findRefereeUserType(refereeId);
				if (refereeType == 4) {
					return 3;// 当推荐人为行业用户，标识注册人为半行业用户
				}
			}
		}
		return -1;
	}

	@Override
	public Member findRefereeUser(String refereeId) {
		return memberDao.findRefereeUser(refereeId);
	}

}
