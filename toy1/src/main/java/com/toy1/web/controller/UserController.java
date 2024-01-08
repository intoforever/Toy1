package com.toy1.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.toy1.web.entity.Member;
import com.toy1.web.service.MemberService;

@Controller
@RequestMapping("user")
public class UserController {
	
	
	/* ===== Fields ===== */
	@Autowired
	MemberService service;
	
	

	/* ===== Methods ===== */
	
	// 1. 로그인(GET)
	@GetMapping("login")
	public String login() {
		return "user/login";
	}
	
	// 2. 회원가입(GET)
	@GetMapping("signup")
	public String signup() {
		return "user/signup";
	}
	
	// 3. 회원가입(POST)
	@PostMapping("signup")
	public String form(
			@RequestParam String username, @RequestParam String password,
			@RequestParam String nickname, @RequestParam String email) {
		
		//member 객체 만들기
		Member member = Member.builder()
				.username(username)
				.password(password)
				.nickname(nickname)
				.email(email)
				.build();
		
		//member 객체 db 등록
		service.regMember(member);
		
		//회원가입 후 로그인 페이지로
		return "redirect:/user/login";
	}

	
}//class ends
