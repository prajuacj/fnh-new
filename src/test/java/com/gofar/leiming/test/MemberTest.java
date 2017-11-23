package com.gofar.leiming.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leimingtech.core.entity.base.Member;
import com.leimingtech.service.module.member.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:context/applicationContext.xml")
public class MemberTest {
	
	@Resource
	private MemberService memberService;

	@Test
	public void testSaveMember(){
		Member member=new Member();
		//member.set
		memberService.save(member);
	}
}
