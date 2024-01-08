package com.toy1.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toy1.web.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Optional<Member> findByUsername(String username);
	
}
