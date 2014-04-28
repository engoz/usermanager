package com.eng.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eng.domain.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, String>, MemberRepositoryCustom {
	
	List<Member> findByfirstName(String name);
	

}
