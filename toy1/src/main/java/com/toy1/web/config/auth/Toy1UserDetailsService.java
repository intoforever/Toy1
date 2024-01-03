package com.toy1.web.config.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toy1.web.entity.Member;
import com.toy1.web.entity.MemberRoleView;
import com.toy1.web.repository.MemberRepository;
import com.toy1.web.repository.MemberRoleRepository;

@Service
public class Toy1UserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberRoleRepository roleRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//로그인 아이디로 member 객체 만들고 권한 정보 얻어오기
		Optional<Member> optionalMember = memberRepository.findByUsername(username);
		Member member = optionalMember.orElseThrow(() -> new NoSuchElementException("Member not found"));		
		List<MemberRoleView> memberRoleList = roleRepository.findAllByMemberId(member.getId());
		
		
		//UserDetails 객체 생성해서 setter로 로그인 아이디로 만든 member 객체값 저장
		Toy1UserDetails userDetails = new Toy1UserDetails();
		userDetails.setId(member.getId());
		userDetails.setUsername(member.getUsername());
		userDetails.setPassword(member.getPassword());
		userDetails.setNickname(member.getNickname());
		
		
		//Spring Authority에 로그인 아이디가 가진 권한 정보 저장하기
		//반드시 롤네임은 ROLE_형태이어야 함
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (MemberRoleView mr : memberRoleList) {
			authorities.add(new SimpleGrantedAuthority(mr.getRoleName()));
			System.out.println(mr);
		}
		userDetails.setAuthorities(authorities);
		
		
		return userDetails;
	}

}
