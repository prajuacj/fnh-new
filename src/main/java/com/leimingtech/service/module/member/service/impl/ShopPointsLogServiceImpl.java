package com.leimingtech.service.module.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.ShopPointsLog;
import com.leimingtech.service.module.member.common.PointsLogType;
import com.leimingtech.service.module.member.dao.MemberDao;
import com.leimingtech.service.module.member.dao.ShopPointsLogDao;
import com.leimingtech.service.module.member.service.ShopPointsLogService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 
 * 
 * 项目名称：leimingtech-admin 类名称：ShopPointsLogService 类描述：service实现类 创建人：gyh
 * 创建时间：2015年7月24日 上午9:44:03 修改备注：
 * 
 * @version
 *
 */
@Service("ShopPointsLogService")
public class ShopPointsLogServiceImpl implements ShopPointsLogService {
	@Autowired
	private ShopPointsLogDao shopPointsLogDao;

	@Autowired
	private MemberDao memberDao;

	@Override
	public void save(ShopPointsLog shopPointsLog) {
		shopPointsLogDao.save(shopPointsLog);

		// 更新用户积分
		Member member = memberDao.findById(shopPointsLog.getMemberid());
		Member newMember = new Member();
		newMember.setMemberId(member.getMemberId());

		if (shopPointsLog.getType() == PointsLogType.POINTS_TYPE_ORDERPAY) { // 消费积分更新用户的消费积分
			Integer memberConsumePoints = member.getMemberConsumePoints();
			memberConsumePoints = memberConsumePoints + shopPointsLog.getPoints();
			newMember.setMemberConsumePoints(memberConsumePoints);
		} else {
			// 非消费积分更新用户的等级积分
			Integer memberRankPoints = member.getMemberRankPoints();
			memberRankPoints = memberRankPoints + shopPointsLog.getPoints();
			newMember.setMemberRankPoints(memberRankPoints);
		}

		memberDao.updateMember(newMember);
	}

	@Override
	public int findCount(ShopPointsLog shopPointsLog) {
		return shopPointsLogDao.findCount(shopPointsLog);
	}

	@Override
	public Pager findPageList(Pager pager, int type) {
		List<ShopPointsLog> pointsLogList = shopPointsLogDao.findPageList(pager);

		if (type == PointsLogType.POINTS_TYPE_SHARE || type == PointsLogType.POINTS_TYPE_REQUEST) {
			ShopPointsLog condPointsLog = new ShopPointsLog();

			for (ShopPointsLog pointsLog : pointsLogList) {
				condPointsLog.setMemberid(pointsLog.getMemberid());
				condPointsLog.setObjectId(pointsLog.getObjectId());

				List<ShopPointsLog> splList = shopPointsLogDao.selectMemberList(condPointsLog);
				pointsLog.setPointsLogList(splList);
			}
		}

		pager.setResult(pointsLogList);
		return pager;
	}

	@Override
	public List<ShopPointsLog> findList() {
		return shopPointsLogDao.findList();
	}

}
