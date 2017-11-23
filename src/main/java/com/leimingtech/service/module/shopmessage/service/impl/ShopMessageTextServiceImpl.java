package com.leimingtech.service.module.shopmessage.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.leimingtech.core.entity.base.Member;
import com.leimingtech.core.entity.base.ShopMessageText;
import com.leimingtech.core.entity.base.ShopMessagezn;
import com.leimingtech.service.module.member.service.MemberService;
import com.leimingtech.service.module.shopmessage.dao.ShopMessageTextDao;
import com.leimingtech.service.module.shopmessage.service.ShopMessageTextService;
import com.leimingtech.service.module.shopmessage.service.ShopMessageznService;
import com.leimingtech.service.utils.page.Pager;

/**
 * 站内信ServiceImpl
 *
 * @author gyh
 * @version 2016-03-16
 */
@Service
public class ShopMessageTextServiceImpl implements ShopMessageTextService {

	/** 站内信DAO接口 */
	@Resource
	private ShopMessageTextDao shopMessageTextDao;
	@Resource
	private ShopMessageznService shopMessageznService;
	@Resource
	private MemberService mberService;

	/**
	 * 查询分页站内信数据
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	@Override
	public Pager findShopMessageTextPagerList(Pager pager) {
		List<ShopMessageText> list = shopMessageTextDao.findShopMessageTextPagerList(pager);
		pager.setResult(list);
		return pager;
	}

	/**
	 * 通过textId获取单条站内信数据
	 * 
	 * @param textId
	 * @return
	 */
	@Override
	public ShopMessageText findShopMessageTextByTextId(String textId) {
		return shopMessageTextDao.findShopMessageTextByTextId(textId);
	}

	/**
	 * 通过textId删除站内信数据
	 * 
	 * @param textId
	 */
	@Override
	public void deleteShopMessageTextByTextId(String textId) {
		shopMessageznService.deleteShopMessageznByMessage_id(textId);
		shopMessageTextDao.deleteShopMessageTextByTextId(textId);
	}

	/**
	 * 修改站内信数据
	 * 
	 * @param shopMessageText
	 */
	@Override
	public void updateShopMessageText(ShopMessageText shopMessageText) {
		shopMessageTextDao.updateShopMessageText(shopMessageText);
	}

	/**
	 * 保存站内信数据
	 * 
	 * @param shopMessageText
	 */
	@Override
	public void saveShopMessageText(ShopMessageText shopMessageText) {
		shopMessageText.setIsDel(0);// 默认不删除
		shopMessageText.setSendtime(System.currentTimeMillis());
		shopMessageTextDao.saveShopMessageText(shopMessageText);
		// 如果是系统消息
		if (shopMessageText.getMessageType().equals(1)) {
			// 保存收信人信息
			savememssagezn(shopMessageText);
		}
		// 私人消息
		else if (shopMessageText.getMessageType().equals(0)) {
			if (shopMessageText.getMemberid().length != 0) {
				savemessagezn2(shopMessageText);
			}
		}
	}

	/**
	 * 获取所有站内信数据
	 * 
	 * @return
	 */
	@Override
	public List<ShopMessageText> findShopMessageTextAllList() {
		return shopMessageTextDao.findShopMessageTextAllList();
	}

	/**
	 * 批量保存收信者
	 * 
	 * @param shopMessageText
	 */
	public void savememssagezn(ShopMessageText shopMessageText) {
		ShopMessagezn shopmessage = new ShopMessagezn();
		shopmessage.setIsDel(0);// 默认不删除
		shopmessage.setStatue(0);// 0未读 1已读
		List<Member> memerlist = mberService.findMemberListall();
		if (memerlist != null && memerlist.size() != 0) {
			for (Member member : memerlist) {
				// 站内信id
				shopmessage.setMessageId(shopMessageText.getTextId());
				// 接收人id
				shopmessage.setReceiveId(member.getMemberId());
				// 接收人姓名
				shopmessage.setReceiveName(member.getMemberName());
				shopMessageznService.saveShopMessagezn(shopmessage);
			}
		}
	}

	/**
	 * 私信批量保存收信者
	 * 
	 * @param shopMessageText
	 */
	public void savemessagezn2(ShopMessageText shopMessageText) {
		ShopMessagezn shopmessage = new ShopMessagezn();
		shopmessage.setIsDel(0);// 默认不删除
		shopmessage.setSendId(shopMessageText.getSendId());
		shopmessage.setSendName(shopMessageText.getSendName());
		shopmessage.setStatue(0);// 0未读 1已读
		for (int i = 0; i < shopMessageText.getMemberid().length; i++) {
			// 站内信id
			shopmessage.setMessageId(shopMessageText.getTextId());
			// 接收人id
			shopmessage.setReceiveId(shopMessageText.getMemberid()[i]);
			// 接收人姓名
			shopmessage.setReceiveName(shopMessageText.getMembername()[i]);
			shopMessageznService.saveShopMessagezn(shopmessage);
		}
	}
}