package com.toy1.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

	//1. 로그인(GET)
	@GetMapping("login")
	public String login() {
		return "user/login";
	}
	
	//2. 회원가입(GET)
	@GetMapping("signup")
	public String signup() {
		return "user/signup";
	}
	
	
	//3. 회원가입(POST)
}
