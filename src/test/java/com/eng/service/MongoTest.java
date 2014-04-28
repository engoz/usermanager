package com.eng.service;


import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.eng.domain.Member;

@ContextConfiguration("classpath:test-config.xml")
public class MongoTest {
	

	
	public static void main( String[] args ) {  
	
	ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("test-config.xml");
	
	MemberService service = context.getBean(MemberService.class);
	service.dropMember();
	service.setMember(new Member("Uras","Oz","123456789"));
//	Member member = new Member("Can2","Oz","123456789");
//	service.insertMember(member);
	service.insertMember();
	
	List<Member> memberList = service.findAllMembers();
	
	for(Member members:memberList){
		System.out.println(members.getId() +" "+ members.getFirstName());
	}
			
	}


}
