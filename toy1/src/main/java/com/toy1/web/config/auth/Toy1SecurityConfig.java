package com.toy1.web.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//스프링 시큐리티 관련 속성들 커스텀 클래스
@EnableWebSecurity
@Configuration
public class Toy1SecurityConfig {

	/* ===== 패스워드 인코더 ===== */
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}//passwordEncoder ends
	

	
	/* ===== Spring Security 설정(csrf, 접근권한, 로그인, 로그아웃, 접근제한 페이지) ===== */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			//.csrf(csrf -> csrf.disable())
			
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/admin/**")
					.hasAnyRole("ADMIN")
					
				.requestMatchers("/vipmember/**")
					.hasAnyRole("ADMIN", "VIP_MEMBER")
					
				.requestMatchers("/member/**")
					.hasAnyRole("ADMIN", "MEMBER", "VIP_MEMBER")
					
				.anyRequest().permitAll())
			
			
			.formLogin(form -> form.loginPage("/user/login")
						.defaultSuccessUrl("/member/index"))
			
			.logout(logout -> logout.logoutUrl("/user/logout")
						.logoutSuccessUrl("/"))
			
			.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/"));

		return http.build();
	}//filterChain ends

}//class ends
