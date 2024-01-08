package com.toy1.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toy1.web.entity.Member;
import com.toy1.web.entity.MemberRole;
import com.toy1.web.entity.PrimaryKeys;
import com.toy1.web.repository.MemberRepository;
import com.toy1.web.repository.MemberRoleRepository;

@Service
public class MemberServiceImp implements MemberService {

	
	/* ===== Fields ===== */
	@Autowired
	MemberRepository mRepository;
	
	@Autowired
	MemberRoleRepository rRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	
	/* ===== Methods ===== */
	
	//회원등록
	@Transactional
	@Override
	public void regMember(Member member) {
		String plainPassword = member.getPassword();
		String hashedPassword = encoder.encode(plainPassword);
		member.setPassword(hashedPassword);
		
		mRepository.save(member);
		
		PrimaryKeys primayKey = PrimaryKeys.builder()
				.memberId(member.getId())
				.roleId(1)
				.build();
		
		MemberRole memberRole = MemberRole.builder()
				.primaryKey(primayKey)
				.build();
		
		rRepository.save(memberRole);
	}

}
