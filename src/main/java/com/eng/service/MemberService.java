package com.eng.service;

import java.util.List;





import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.eng.domain.Member;
import com.eng.repository.MemberRepository;


@Service("memberService")
public class MemberService {

	
	@Autowired
	MemberRepository memberRepository;
	private List<Member> members;
	private Member member = new Member();
	
	public void insertMember(){
		memberRepository.save(this.member);
	}
	
	public void insertFromAttributeMember(Member member){
		memberRepository.save(member);
	}
	
	public void findMember(String id){
		memberRepository.findOne(id);
	}
	
	public void dropMember(){
		memberRepository.deleteAll();
	}
	
	@PostConstruct
	public List<Member> findAllMembers(){
		this.members = memberRepository.findAll();
		return members;
	}

	
	public List<Member> findByFirstName(String name){
		return memberRepository.findByfirstName(name);
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
