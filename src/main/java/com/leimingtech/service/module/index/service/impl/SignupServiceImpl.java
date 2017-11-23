package com.leimingtech.service.module.index.service.impl;

import com.google.common.collect.Maps;
import com.leimingtech.core.common.CommonConstants;
import com.leimingtech.core.common.DateUtils;
import com.leimingtech.core.common.simplemail.SimpleMailSender;
import com.leimingtech.core.entity.base.Member;
import com.leimingtech.service.module.index.service.SignupService;
import com.leimingtech.service.module.member.dao.MemberDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by rabook on 2015/3/27.
 */
@Service
public class SignupServiceImpl implements SignupService{

    @Resource
    private MemberDao memberDao;

    @Override
    public void saveSendEmail(Member member) {
	    //发送邮件
		String code = DateUtils.getRandomString(8);
	    Map<String,Object> map = Maps.newHashMap();
	    map.put("username", member.getMemberName());
	    String url = CommonConstants.FRONT_SERVER+"/signResult?userName="+member.getMemberName()+"&code="+code;
	    map.put("url", url);

        try {
            SimpleMailSender mailSender = new SimpleMailSender();
            mailSender.sendEmailHtml(member.getMemberEmail(), "注册激活", map, "register.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String updateSign(String userName, String code) {

        Member member = memberDao.findMemberByName(userName);
        member.setMemberState(1);
        member.setSignCodeState(1);
        memberDao.updateMember(member);
        if(code.equals(member.getSignCode()) && member.getSignCodeState() == 0){
            return "恭喜您注册成功";
        }else{
            return "您好！你的验证码已经过期！";
        }
    }

	@Override
	public boolean saveSendEmail(Member member, String valid) {
		//发送邮件
	    Map<String,Object> map = Maps.newHashMap();
	    map.put("username", member.getMemberName());
	    map.put("emailCode", valid);

        try {
            SimpleMailSender mailSender = new SimpleMailSender();
            mailSender.sendEmailHtml(member.getMemberEmail(), "注册激活", map, "emailRegister.ftl");
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
		
	}
}
