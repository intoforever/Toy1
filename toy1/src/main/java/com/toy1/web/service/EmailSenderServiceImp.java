package com.toy1.web.service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceImp implements EmailSenderService {
	
	/*
	 * JavaMailSender 클래스를 사용하기 위해서
	 * 1. starters에 dependency 추가되어 있을 것
	 * 2. application.yml 파일에 메일 관련 설정이 완료되어 있을 것
	 * 
	 *  (dependency만 추가하면 applicaion context가 초기화 될 때
	 *  application.yml 파일에 설정된 속성을 기반으로
	 *  자동으로 Sring이 Bean을 만들어주고
	 *  생성자에 자동으로 DI하여 준다.)
	 */
	
	/* ===== Fields ===== */
	private final JavaMailSender mailSender;
	private final TemplateEngine templateEngine; // 타임리프
	
	
	
	/* ===== Constructor ===== */
    public EmailSenderServiceImp(
    		JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }
    
    
    /* ===== Methods ===== */
    @Override
    public String templateSendEmail(String to, String name) throws Exception {
    	
		// 이메일 변수 설정
		Map<String, Object> variables = new HashMap<>();
		variables.put("name", name); // 아이디
		String key = generateKey(); 
		variables.put("key", key); // 인증번호
    	
		// Thymeleaf 템플릿 컨텍스트 생성
        Context context = new Context();
        context.setVariables(variables); // 템플릿에 전달할 변수 설정

        // 템플릿을 사용하여 이메일 내용 생성
        String templateName = "/user/email-authentication.html"; // 경로포함, 템플릿 이름
        String emailContent = templateEngine.process(templateName, context);

        // MimeMessage 생성 및 설정
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject("토이원 인증번호 입니다.");
        helper.setText(emailContent, true); // true - HTML 이메일

        // 이메일 전송
        mailSender.send(mimeMessage);
        System.out.println("이메일 전송 성공");
        
        return key;
    }
    

    // 난수 생성기
	public String generateKey() {
		
		// 난수 생성 시에 참고할 문자들
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        SecureRandom random = new SecureRandom(); // 랜덤하게 뽑는 기능
        StringBuilder key = new StringBuilder(8); // 자리수

        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(characters.length()); // 랜덤 인덱스
            key.append(characters.charAt(randomIndex)); // 랜덤 인덱스 위치에 있는 문자
        }

        return key.toString();
	}

	
}
