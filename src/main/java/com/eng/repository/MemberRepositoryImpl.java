package com.eng.repository;

import java.util.List;



import org.springframework.stereotype.Component;

import com.eng.domain.Member;

@Component
public class MemberRepositoryImpl implements MemberRepositoryCustom{

	@Override
	public List<Member> findByfirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
