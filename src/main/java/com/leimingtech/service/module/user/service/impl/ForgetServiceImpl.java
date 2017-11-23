package com.leimingtech.service.module.user.service.impl;

import com.google.common.collect.Maps;
import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.DateUtils;
import com.leimingtech.core.common.simplemail.SimpleMailSender;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.service.module.member.dao.MemberDao;
import com.leimingtech.service.module.user.service.ForgetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ForgetServiceImpl implements ForgetService{

	@Resource
    private MemberDao memberDao;

	/**
	 * 发送邮件
	 * @param memberName
	 * @param email
	 * @throws Exception
	 */
	public int saveSendEmail(String memberName, String email) throws Exception {
		
		if(!DateUtils.paramLength(memberName) && !DateUtils.paramLength(email)) {
			Member member = memberDao.findMemberByName(memberName);
			if(member.getMemberEmail().equals(email)){
				String getNewPassword =  DateUtils.getRandomString(8);
				member.setMemberPasswd(getNewPassword);
				memberDao.updateMember(member);

				//发送邮件
				Map<String,Object> map = Maps.newHashMap();
	            map.put("username", member.getMemberName());
	            String url = CommonConstants.FRONT_SERVER+"/forget/activate?username="+member.getMemberName()+"&code="+getNewPassword;
	            map.put("url", url);
				try {
					SimpleMailSender mailSender = new SimpleMailSender();
					mailSender.sendEmailHtml(member.getMemberEmail(), "注册激活", map, "register.ftl");
				} catch (Exception e) {
					e.printStackTrace();
				}
	            return 1;
			}else{
				return 2;
			}
			
		}else{
			return 0;
		}
	}
}
