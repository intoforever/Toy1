package com.toy1.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy1.web.service.EmailSenderService;

@RestController
@RequestMapping("api/send-email")
public class EmailController {

	
	/* ===== Fields ===== */
	@Autowired
	private EmailSenderService emailService;
	
	
	/* ===== Methods ===== */
	
	// 1. 이메일 보내기
	@PostMapping("{to}/{name}")
	public String sendEmail(
			@PathVariable String to, @PathVariable String name) throws Exception {
		
		String key = emailService.templateSendEmail(to, name);
		return key;
	}
	
}//class ends
