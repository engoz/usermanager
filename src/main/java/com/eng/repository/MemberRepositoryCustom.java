package com.eng.repository;

import java.util.List;

import com.eng.domain.Member;

public interface MemberRepositoryCustom {
	List<Member> findByfirstName(String firstName);
}
