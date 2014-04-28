package com.eng.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eng.domain.Member;
import com.eng.service.MemberService;

@ContextConfiguration("classpath:test-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

	@Inject
	private MemberService service;
	

	@Test
	public void test() {
		
		Member member = new Member("Uras","Can","12345");
		service.dropMember();
//		service.setMember(new Member("CanCan","Oz","123456789"));
		service.insertFromAttributeMember(member);
		assertNotNull(service.findByFirstName("Uras"));
		
	}





}
